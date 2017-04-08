package cn.tata.t2s.ssm.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import javax.persistence.criteria.SetJoin;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;

import cn.tata.t2s.ssm.dao.SuperDao;
import cn.tata.t2s.ssm.entity.Person;
import cn.tata.t2s.ssm.entity.Person_;
import cn.tata.t2s.ssm.service.util.CriteriaQueryHelper;
import cn.tata.t2s.ssm.service.util.PagedResult;
import cn.tata.t2s.ssm.util.CriteriaQueryUtil;

public class SuperDaoImpl<X, Y> implements SuperDao<X, Y>{
	@PersistenceContext
	protected EntityManager entityManager;
	
	@Autowired
	protected CriteriaBuilder builder;
	
	protected CriteriaQuery<?> query;
	
	protected Root<X> entityRoot;
	
	protected SetAttribute<X, ?> setAttribute;
	
	protected Pair<SingularAttribute<X, Y>, Y> idPair;
	
	protected SetJoin<X, ?> setJoin;
	
	protected List<Predicate> customPredicate;
	
	@SuppressWarnings("unchecked")
	private Class<X> getEntityClass() {
		Class<X> entityClass = (Class<X>) 
				((ParameterizedType)getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		return entityClass;
	}
	
	@Override
	public final int insert(X entity){
		entityManager.persist(entity);
		return 1;
	}
	
	@Override
	public final X update (X entity){
		return entityManager.merge(entity);
	}
	
	@Override
	public final int delete(X entity){
		entityManager.remove(entity);
		return 1;
	}
	
	
	@Override
	public final X select(Y primaryKey){
		Class<X> entityClass = getEntityClass();
		return entityManager.find(entityClass, primaryKey);
	}
	
	@Override
	@SafeVarargs
	public final X select(Y primaryKey, Class<X> rootType
			, Attribute<X, ?>... attribute){
		EntityGraph<X> graph = entityManager.createEntityGraph(rootType);
		graph.addAttributeNodes(attribute);

		X entity = entityManager.find(
				rootType,
				primaryKey,
			    Collections.singletonMap("javax.persistence.fetchgraph",graph)
			);
		
		return entity;
	}
	
	@Override
	public List<Tuple> select(Pair<SingularAttribute<X,Y>, Y> idPair
			, SetAttribute<X, ?>... setAttributes){
		Class<X> entityClass = setAttributes[0].getDeclaringType().getJavaType();
		
		// init
 		CriteriaQuery<Tuple> query = builder.createTupleQuery();
		Root<X> root = query.from(entityClass);
		root.alias(entityClass.getSimpleName());
		
		//Predicate
		Predicate predicate = builder.equal(root.get(idPair.getLeft()), idPair.getRight());
		
		//configure root
		int i = 1;
		List<Selection<?>> selectionList = new ArrayList<Selection<?>>();
		for(SetAttribute<X, ?> setAttribute: setAttributes) {
			//for count
			CriteriaQuery<?> tempQuery = builder.createQuery(setAttribute.getBindableJavaType());
			Root<X> tempRoot = query.from(entityClass);
			tempRoot.alias(entityClass.getSimpleName() + i);
			tempRoot.join(setAttribute);
			tempQuery.where(builder.equal(tempRoot.get(idPair.getLeft()), idPair.getRight()));
//			System.out.println("count: " + this.count(builder, tempQuery, tempRoot));
			
			//for result
			selectionList.add(root.get(setAttribute));
			root.join(setAttribute);
			
			//alias
			i++;
		}
		
		query.multiselect(selectionList);
		// criteria building
		query.where(predicate);
		return entityManager.createQuery(query).getResultList();
	}
	
	protected <T> void init(SetAttribute<X, T> setAttribute
			, Pair<SingularAttribute<X, Y>, Y> idPair) {
		//query Init
		Class<T> resultClass = setAttribute.getElementType().getJavaType();
 		query = builder.createQuery(resultClass);
		
		// entityRootInit
		Class<X> entityClass = setAttribute.getDeclaringType().getJavaType();
		this.entityRoot = query.from(entityClass);
		this.entityRoot.alias(entityClass.getSimpleName());
		
		//setAttributeInit
		this.setAttribute = setAttribute;
		
		//idPairInit
		this.idPair = idPair;
		
		//setJoinInit
		this.setJoin = entityRoot.join(setAttribute);
		this.setJoin.alias(setAttribute.getBindableJavaType().getSimpleName());
		
		//predicateListInit
		this.customPredicate = new ArrayList<Predicate>();
		return;
	}
	
	protected void predicateInit() {
		//iDPredicate
		Predicate idPredicate = builder.equal(entityRoot.get(idPair.getLeft()), idPair.getRight());
		//onDeletePredicate
		Predicate onDeletePredicate = builder.equal(setJoin.get("commonInfo")
				.get("onDelete"), false);
		onDeletePredicate.alias(setJoin.getAlias());
		
		customPredicate.add(idPredicate);
		customPredicate.add(onDeletePredicate);
		
		
		//set Predicate
		query.where(builder.and(customPredicate.toArray(new Predicate[0])));
		return;
	}
	
	@Override
	public <T> PagedResult<T> select(PagedResult<T> pagedResult
			, CriteriaQuery<T> query
			, SetJoin<X, T> setJoin){
		query.select(setJoin);
		
		
		//count and paging result
		pagedResult.setTotalRecords(count(builder, query, entityRoot));
		pagedResult.setTotalPages(pagedResult.getTotalRecords(), pagedResult.getPageSize());
		List<T> data = entityManager.createQuery(query)
				.setFirstResult(pagedResult.getStartPosition())
				.setMaxResults(pagedResult.getPageSize())
				.getResultList();
		pagedResult.setData(data);
		
		return pagedResult;
	}
	
	public <T> PagedResult<T> select(PagedResult<T> pagedResult
			,CriteriaQueryHelper<X, Y, T> cqh){
		
		CriteriaQuery<T> query = cqh.getQuery();
		Root<X> entityRoot = cqh.getEntityRoot();
		
		//count and paging result
		pagedResult.setTotalRecords(count(builder, query, entityRoot));
		pagedResult.setTotalPages(pagedResult.getTotalRecords(), pagedResult.getPageSize());
		List<T> data = entityManager.createQuery(query)
				.setFirstResult(pagedResult.getStartPosition())
				.setMaxResults(pagedResult.getPageSize())
				.getResultList();
		pagedResult.setData(data);
		
		return pagedResult;
	}

	@Override
	public <T> long count(
			final CriteriaBuilder builder
			, final CriteriaQuery<T> selectQuery,
	        Root<X> root) {
	    CriteriaQuery<Long> countQuery = CriteriaQueryUtil.createCountQuery(builder, selectQuery, root);
	    return this.entityManager.createQuery(countQuery).getSingleResult();
	}

	@Override
	public Predicate getIdPredicate(
			Pair<SingularAttribute<X, Y>, Y> idPair
			, Root<X> entityRoot) {
		return builder.equal(entityRoot.get(idPair.getLeft()), idPair.getRight());
	}
	
	@Override
	public <T> Predicate onDeletePredicate(Path<T> path) {
		return builder.equal(path.get("commonInfo").get("onDelete"), false);
	}
	
	@Override
	public CriteriaBuilder getCriteriaBuilder() {
		return builder;
	}

}
