package cn.tata.t2s.ssm.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;

import cn.tata.t2s.ssm.dao.SuperDao;
import cn.tata.t2s.ssm.service.util.PagedResult;
import cn.tata.t2s.ssm.util.CriteriaQueryUtil;

public class SuperDaoImpl implements SuperDao{
	@PersistenceContext
	protected EntityManager entityManager;
	
	@Autowired
	protected CriteriaBuilder builder;
	
	@Override
	public final <T> int insert(T entity) {
		entityManager.persist(entity);
		return 1;
	}
	
	@Override
	public final <T> T update(T entity) {
		return entityManager.merge(entity);
	}
	
	@Override
	public final <T> T select(Class<T> entityClass, Object primaryKey) {
		return entityManager.find(entityClass, primaryKey);
	}
	
	@Override
	@SafeVarargs
	public final <T> T select(Object primaryKey, Class<T> rootType, Attribute<T, ?>... attribute) {
		EntityGraph<T> graph = entityManager.createEntityGraph(rootType);
		graph.addAttributeNodes(attribute);

		T entity = (T) entityManager.find(
				rootType,
				primaryKey,
			    Collections.singletonMap("javax.persistence.fetchgraph",graph)
			);
		
		return entity;
	}
	
	@Override
	public <X,Y> List<Tuple> select(Pair<SingularAttribute<X,Y>, Y> idPair, SetAttribute<X, ?>... setAttributes) {
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
			System.out.println("count: " + this.count(builder, tempQuery, tempRoot));
			
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
	
	@Override
	public <T, X, Y> PagedResult<T> select(PagedResult<T> pagedResult, Pair<SingularAttribute<X, Y>, Y> idPair, SetAttribute<X, T> setAttribute, Predicate... customPredicate) {
		// init
		Class<X> entityClass = setAttribute.getDeclaringType().getJavaType();
		Class<T> resultClass = setAttribute.getElementType().getJavaType();
 		CriteriaQuery<T> query = builder.createQuery(resultClass);
		Root<X> root = query.from(entityClass);
		root.alias(entityClass.getSimpleName());
		
		//Predicate
		Predicate predicate = builder.equal(root.get(idPair.getLeft()), idPair.getRight());
		customPredicate = ArrayUtils.add(customPredicate, predicate);
		
		//set Root and query
		root.get(setAttribute);
		root.join(setAttribute);
		query.where(customPredicate);
		
		//count and paging result
		pagedResult.setTotalRecords(count(builder, query, root));
		pagedResult.setTotalPages(pagedResult.getTotalRecords(), pagedResult.getPageSize());
		List<T> data = entityManager.createQuery(query)
				.setFirstResult(pagedResult.getStartPosition())
				.setMaxResults(pagedResult.getPageSize())
				.getResultList();
		pagedResult.setData(data);
		
		return pagedResult;
	}

	@Override
	public <T,X> long count(final CriteriaBuilder builder, final CriteriaQuery<T> selectQuery,
	        Root<X> root) {
	    CriteriaQuery<Long> countQuery = CriteriaQueryUtil.createCountQuery(builder, selectQuery, root);
	    return this.entityManager.createQuery(countQuery).getSingleResult();
	}

	
}
