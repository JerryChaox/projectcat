package cn.tata.t2s.ssm.dao;

import java.util.List;

import javax.persistence.Tuple;
import javax.persistence.criteria.Predicate;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;

import org.apache.commons.lang3.tuple.Pair;

import cn.tata.t2s.ssm.service.util.PagedResult;

public interface BaseDao {
	<T> int insert(T entity);
	
	<T> T update(T entity);
	
	<T> T select(Class<T> entityClass, Object primaryKey);
	
	<T> T select(Object primaryKey, Class<T> rootType, Attribute<T, ?>... attribute);
	
	<X,Y> List<Tuple> select(Pair<SingularAttribute<X,Y>, Y> idPair, SetAttribute<X, ?>... setAttributes);
	
	<T, X, Y> PagedResult<T> select(PagedResult<T> pagedResult, Pair<SingularAttribute<X,Y>, Y> idPair, SetAttribute<X, T> setAttribute, Predicate... customPredicate);
}
