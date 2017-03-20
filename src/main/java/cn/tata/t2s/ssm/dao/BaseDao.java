package cn.tata.t2s.ssm.dao;

import java.util.List;

import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public interface BaseDao {
	<T> int insert(T entity);
	
	<T> T update(T entity);
	
	<T> T select(Class<T> entityClass, Object primaryKey);
	
	<T> T select(Object primaryKey, Class<T> rootType, Attribute<T, ?>... attribute);
	
	<X,Y> List<Tuple> select(Pair<SingularAttribute<X,Y>, Y> idPair, SetAttribute<X, ?>... setAttributes);
	
	<T, X, Y> List<T> select(Pair<SingularAttribute<X,Y>, Y> idPair, SetAttribute<X, T> setAttribute, Predicate... customPredicate);
}
