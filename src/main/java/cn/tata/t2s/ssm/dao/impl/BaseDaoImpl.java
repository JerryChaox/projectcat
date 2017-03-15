package cn.tata.t2s.ssm.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import cn.tata.t2s.ssm.dao.BaseDao;
import cn.tata.t2s.ssm.entity.Person;

public class BaseDaoImpl implements BaseDao{
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
	public <T> Pair<CriteriaQuery<T>, List<Root<?>>> getQueryRootPair(Class<T> resultClass, Class<?>... entityClasses) {
		CriteriaQuery<T> query = builder.createQuery(resultClass);
		List<Root<?>> rootList = new ArrayList<Root<?>>();
		
		Assert.notNull(query, "null CriteriaQuery");
		Assert.notNull(rootList, "null root");
		
		for(Class<?> clazz: entityClasses) {
			rootList.add(query.from(clazz));
		}
		return new MutablePair<CriteriaQuery<T>, List<Root<?>>>(query, rootList);
	}

	@Override
	public <T, X> Pair<CriteriaQuery<T>, Root<X>> getQueryRootPair(Class<T> resultClass, Class<X> entityClass) {
		CriteriaQuery<T> query = builder.createQuery(resultClass);
		Root<X> root = query.from(entityClass);
		
		Assert.notNull(query, "null CriteriaQuery");
		Assert.notNull(root, "null root");
		
		return new MutablePair<CriteriaQuery<T>, Root<X>>(query, root);
	}


	
}
