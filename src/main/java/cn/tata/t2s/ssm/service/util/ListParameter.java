package cn.tata.t2s.ssm.service.util;

import javax.inject.Scope;
import javax.persistence.criteria.Predicate;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.context.annotation.Bean;

public class ListParameter<T, X, Y> {
	private PagedResult<T> pagedResult;
	private Pair<SingularAttribute<X, Y>, Y> idPair;
	private SetAttribute<X, T> setAttribute;
	private Predicate[] customPredicate;
	
	public ListParameter() {}

	public ListParameter(PagedResult<T> pagedResult
			, Pair<SingularAttribute<X, Y>, Y> idPair
			, SetAttribute<X, T> setAttribute
			, Predicate... customPredicate) {
		this.pagedResult = pagedResult;
		this.idPair = idPair;
		this.setAttribute = setAttribute;
		this.customPredicate = customPredicate;
	}

	public PagedResult<T> getPagedResult() {
		return pagedResult;
	}

	public Pair<SingularAttribute<X, Y>, Y> getIdPair() {
		return idPair;
	}

	public SetAttribute<X, T> getSetAttribute() {
		return setAttribute;
	}

	public Predicate[] getCustomPredicate() {
		return customPredicate;
	}

	public void setPagedResult(PagedResult<T> pagedResult) {
		this.pagedResult = pagedResult;
	}

	public void setIdPair(Pair<SingularAttribute<X, Y>, Y> idPair) {
		this.idPair = idPair;
	}

	public void setSetAttribute(SetAttribute<X, T> setAttribute) {
		this.setAttribute = setAttribute;
	}

	public void setCustomPredicate(Predicate[] customPredicate) {
		this.customPredicate = customPredicate;
	}
	
	
}