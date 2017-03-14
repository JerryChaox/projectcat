package cn.tata.t2s.ssm.dao.impl;

import java.util.Collections;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.metamodel.Attribute;

import cn.tata.t2s.ssm.dao.BaseDao;

public class BaseDaoImpl implements BaseDao{
	@PersistenceContext
	protected EntityManager entityManager;
	
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

	

	
}
