package cn.tata.t2s.ssm.service.util;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

/**
 * 利用方法注入构建CriteriaQuery相关的辅助变量
 * @author chan
 *
 */
public abstract class CriteriaQueryManager {
	
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
	public <X, Y, T> CriteriaQueryHelper<X, Y, T> 
	getCriteriaQueryHelper(final CriteriaBuilder builder,
			final SetAttribute<X, T> setAttribute) {
		
		CriteriaQueryHelper<X, Y, T> cqh = 
				(CriteriaQueryHelper<X, Y, T>)createCriteriaQueryHelper();
		return cqh.init(builder, setAttribute);
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
		
	public abstract PagedResult<Object> createPagedResult();
	
	@SuppressWarnings("rawtypes")
	public abstract CriteriaQueryHelper createCriteriaQueryHelper();
	
	@SuppressWarnings("rawtypes")
	public abstract ListParameter createListParameter();
	
	
}
