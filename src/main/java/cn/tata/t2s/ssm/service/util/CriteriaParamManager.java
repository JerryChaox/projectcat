package cn.tata.t2s.ssm.service.util;

import javax.persistence.criteria.Predicate;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Lookup;

public abstract class CriteriaParamManager {
	
	public <X, Y> Pair<SingularAttribute<X, Y>, Y> getIdPair(SingularAttribute<X, Y> idAttribute, Y id) {
		return ImmutablePair.of(idAttribute, id);
	}
	
	@SuppressWarnings("unchecked")
	public <T> PagedResult<T> getPagedResult(int pageSize, int pageNumber) {
		PagedResult<T> pagedResult = (PagedResult<T>) createPagedResult();
		pagedResult.setPageSize(pageSize);
		pagedResult.setPageNumber(pageNumber);
		return pagedResult;
	}
	
	@SuppressWarnings("unchecked")
	public <T, X, Y> ListParameter<T, X, Y> getListParameter(PagedResult<T> pagedResult
			, Pair<SingularAttribute<X, Y>, Y> idPair
			, SetAttribute<X, T> setAttribute
			, Predicate... customPredicate) {
		
		ListParameter<T, X, Y> listParamter = (ListParameter<T, X, Y>) createListParameter();
		
		listParamter.setIdPair(idPair);
		listParamter.setPagedResult(pagedResult);
		listParamter.setSetAttribute(setAttribute);
		listParamter.setCustomPredicate(customPredicate);
		
		return (ListParameter<T, X, Y>)listParamter;
		
	}
		
	@Lookup("pagedResult")
	public abstract PagedResult<Object> createPagedResult();
	
	@Lookup("listParameter")
	public abstract ListParameter createListParameter();
	
	
}
