package cn.tata.t2s.ssm.service;

import cn.tata.t2s.ssm.util.PagedResult;

public interface BaseService {
	
	<T> T get(Class<T> entityClass, Object primaryKey);
	
	<T, X, Y> PagedResult<T> list(ListParameter<T, X, Y> listParameterObject);
	
}
