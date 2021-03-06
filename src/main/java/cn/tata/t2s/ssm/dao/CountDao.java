package cn.tata.t2s.ssm.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


public interface CountDao<X> {

	<T> long count(final CriteriaBuilder cb
			, final CriteriaQuery<T> selectQuery
			, Root<X> root);
}
