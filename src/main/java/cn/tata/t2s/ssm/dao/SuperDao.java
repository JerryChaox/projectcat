package cn.tata.t2s.ssm.dao;

import javax.persistence.criteria.CriteriaBuilder;

public interface SuperDao<X, Y> extends BaseDao<X, Y>, PredicateDao<X, Y>, CountDao<X>{

	CriteriaBuilder getCriteriaBuilder();
}
