package cn.tata.t2s.ssm.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.util.Assert;

public class CriteriaQueryUtil {

	public static <T, X> ImmutablePair<SingularAttribute<T, X>, X> getIdCriteriaPair(SingularAttribute<T, X> idAttribute, X id) {
		
		return new ImmutablePair<SingularAttribute<T, X>, X>(idAttribute, id);
	}
	
	public static <T> Pair<CriteriaQuery<T>, List<Root<?>>> getQueryRootPair(final CriteriaBuilder builder, Class<T> resultClass, Class<?>... entityClasses) {
		CriteriaQuery<T> query = builder.createQuery(resultClass);
		List<Root<?>> rootList = new ArrayList<Root<?>>();
		
		Assert.notNull(query, "null CriteriaQuery");
		Assert.notNull(rootList, "null root");
		
		int i = 0;
		for(Class<?> clazz: entityClasses) {
			Root<?> root = query.from(clazz);
			root.alias(clazz.getSimpleName() + i);
			rootList.add(root);
			i++;
		}
		
		return new MutablePair<CriteriaQuery<T>, List<Root<?>>>(query, rootList);
	}

	public static <T, X> Pair<CriteriaQuery<T>, Root<X>> getQueryRootPair(final CriteriaBuilder builder, Class<T> resultClass, Class<X> entityClass) {
		CriteriaQuery<T> query = builder.createQuery(resultClass);
		Root<X> root = query.from(entityClass);
		root.alias(entityClass.getSimpleName());
		
		Assert.notNull(query, "null CriteriaQuery");
		Assert.notNull(root, "null root");
		
		return new MutablePair<CriteriaQuery<T>, Root<X>>(query, root);
	}

	public static <T,X> CriteriaQuery<Long> createCountQuery(final CriteriaBuilder builder,
	        final CriteriaQuery<X> query, final Root<T> root) {

	    final CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
	    final Root<T> countRoot = countQuery.from(root.getModel());
	    
	    countRoot.alias(root.getAlias());
	    
	    doJoins(root.getJoins(), countRoot);
	    doJoinsOnFetches(root.getFetches(), countRoot);

	    countQuery.select(builder.count(countRoot));
	    countQuery.where(query.getRestriction());

	    

	    return countQuery.distinct(query.isDistinct());
	}

	@SuppressWarnings("unchecked")
	public static void doJoinsOnFetches(Set<? extends Fetch<?, ?>> joins, Root<?> root) {
	    doJoins((Set<? extends Join<?, ?>>) joins, root);
	}

	public static void doJoins(Set<? extends Join<?, ?>> joins, Root<?> root) {
	    for (Join<?, ?> join : joins) {
	        Join<?, ?> joined = root.join(join.getAttribute().getName(), join.getJoinType());
	        joined.alias(join.getAlias());
	        doJoins(join.getJoins(), joined);
	    }
	}

	public static void doJoins(Set<? extends Join<?, ?>> joins, Join<?, ?> root) {
	    for (Join<?, ?> join : joins) {
	        Join<?, ?> joined = root.join(join.getAttribute().getName(), join.getJoinType());
	        joined.alias(join.getAlias());
	        doJoins(join.getJoins(), joined);
	    }
	}
}
