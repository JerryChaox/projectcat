package cn.tata.t2s.ssm.service;

import cn.tata.t2s.ssm.service.util.ListParameter;
import cn.tata.t2s.ssm.service.util.PagedResult;

public interface BaseService<X, Y> {
	
	X get(Class<X> entityClass, Object primaryKey);
	
	<T> PagedResult<T> list(ListParameter<T, X, Y> listParameter);
	
}
