package cn.tata.t2s.ssm.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public interface CountDao {

	<T,X> long count(final CriteriaBuilder cb, final CriteriaQuery<X> selectQuery, Root<T> root);
}
