package cn.tata.t2s.ssm.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;

import org.apache.commons.lang3.tuple.Pair;

public interface BaseDao {
	<T> int insert(T entity);
	
	<T> T update(T entity);
	
	<T> T select(Class<T> entityClass, Object primaryKey);
	
	<T> T select(Object primaryKey, Class<T> rootType, Attribute<T, ?>... attribute);

	<T> Pair<CriteriaQuery<T>, List<Root<?>>> getQueryRootPair(Class<T> resultClass, Class<?>... entityClasses);

	<T,X> Pair<CriteriaQuery<T>, Root<X>> getQueryRootPair(Class<T> resultClass, Class<X> entityClass);
}
