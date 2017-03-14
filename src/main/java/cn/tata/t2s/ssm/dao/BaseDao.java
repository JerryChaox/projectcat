package cn.tata.t2s.ssm.dao;

import javax.persistence.metamodel.Attribute;

public interface BaseDao {
	<T> int insert(T entity);
	
	<T> T update(T entity);
	
	<T> T select(Class<T> entityClass, Object primaryKey);
	
	<T> T select(Object primaryKey, Class<T> rootType, Attribute<T, ?>... attribute);

}
