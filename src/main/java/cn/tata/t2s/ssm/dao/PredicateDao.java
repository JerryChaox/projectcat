package cn.tata.t2s.ssm.dao;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

import org.apache.commons.lang3.tuple.Pair;

public interface PredicateDao<X, Y> {
	public Predicate getIdPredicate(
			Pair<SingularAttribute<X, Y>, Y> idPair
			, Root<X> entityRoot);
	
	public <T> Predicate onDeletePredicate(Path<T> path);
}
