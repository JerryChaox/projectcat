package cn.tata.t2s.ssm.dao;

import java.util.List;

import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.SetJoin;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;

import org.apache.commons.lang3.tuple.Pair;

import cn.tata.t2s.ssm.service.util.CriteriaQueryHelper;
import cn.tata.t2s.ssm.service.util.PagedResult;

public interface BaseDao<X, Y> {
	public int insert(X entity);
	
	public X update(X entity);
	
	public int delete(X entity);
	
	public X select(Y primaryKey);
	
	public X select(Y primaryKey
			, Class<X> rootXype
			, Attribute<X, ?>... attribute);
	
	public List<Tuple> select(Pair<SingularAttribute<X,Y>, Y> idPair, SetAttribute<X, ?>... setAttributes);
	
	public <T> PagedResult<T> select(PagedResult<T> pagedResult
			, CriteriaQuery<T> query
			, SetJoin<X, T> setJoin);
	
	public <T> PagedResult<T> select(PagedResult<T> pagedResult
			,CriteriaQueryHelper<X, Y, T> cqh);
	
}
