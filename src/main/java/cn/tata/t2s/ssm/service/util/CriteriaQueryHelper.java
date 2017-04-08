package cn.tata.t2s.ssm.service.util;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 利用链式调用构建CriteriaQuery语句
 * 该类非单例
 * @author JerryChaox
 *
 * @param <X> 查询实体的类型
 * @param <Y> 实体的主键类型
 * @param <T> 返回结果的类型
 */
public class CriteriaQueryHelper<X, Y, T> {
	private CriteriaBuilder builder;
	
	private CriteriaQuery<T> query;

	private Root<X> entityRoot;
	
	private SetJoin<X, T> setJoin;
	
	private List<Predicate> customPredicate;
	
	
	public CriteriaQueryHelper<X, Y, T> init(
			final CriteriaBuilder builder
			, final SetAttribute<X, T> setAttribute) {
		//builder Init
		this.builder = builder;
		
		//query Init
		Class<T> resultClass = setAttribute.getElementType().getJavaType();
 		query = builder.createQuery(resultClass);
		
		// entityRootInit
		Class<X> entityClass = setAttribute.getDeclaringType().getJavaType();
		this.entityRoot = query.from(entityClass);
		this.entityRoot.alias(entityClass.getSimpleName());
		
		//setJoinInit
		this.setJoin = entityRoot.join(setAttribute);
		this.setJoin.alias(setAttribute.getBindableJavaType().getSimpleName());
		
		//predicateListInit
		this.customPredicate = new ArrayList<Predicate>();
		return this;
	}
	
	
	public CriteriaQueryHelper<X, Y, T> addPredicate(final Predicate predicate) {
		customPredicate.add(predicate);
		return this;
	}
	
	public CriteriaQueryHelper<X, Y, T> addPredicate(List<Predicate> predicateList) {
		customPredicate.addAll(predicateList);
		return this;
	}
	
	public CriteriaQueryHelper<X, Y, T> predicateInit() {
		//set Predicate
		query.where(builder.and(customPredicate.toArray(new Predicate[0])));
		return this;
	}
	
	public CriteriaQueryHelper<X, Y, T> select(){
		query.select(setJoin);
		return this;
	}


	/**
	 * @return the query
	 */
	public CriteriaQuery<T> getQuery() {
		return query;
	}


	/**
	 * @param query the query to set
	 */
	public void setQuery(final CriteriaQuery<T> query) {
		this.query = query;
	}


	/**
	 * @return the entityRoot
	 */
	public Root<X> getEntityRoot() {
		return entityRoot;
	}


	/**
	 * @param entityRoot the entityRoot to set
	 */
	public void setEntityRoot(final Root<X> entityRoot) {
		this.entityRoot = entityRoot;
	}


	/**
	 * @return the setJoin
	 */
	public SetJoin<X, T> getSetJoin() {
		return setJoin;
	}


	/**
	 * @param setJoin the setJoin to set
	 */
	public void setSetJoin(final SetJoin<X, T> setJoin) {
		this.setJoin = setJoin;
	}


	/**
	 * @return the customPredicate
	 */
	public List<Predicate> getCustomPredicate() {
		return customPredicate;
	}


	/**
	 * @param customPredicate the customPredicate to set
	 */
	public void setCustomPredicate(List<Predicate> customPredicate) {
		this.customPredicate = customPredicate;
	}
}
