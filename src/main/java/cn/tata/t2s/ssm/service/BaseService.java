package cn.tata.t2s.ssm.service;

import cn.tata.t2s.ssm.service.util.ListParameter;
import cn.tata.t2s.ssm.service.util.PagedResult;

public interface BaseService<X, Y> {

	public <T> int save(T entity);
	
	public <T> int remove(T entity);
	
	public <T> T refresh(T entity);
	
	X get(Object primaryKey);
	
	<T> PagedResult<T> list(ListParameter<T, X, Y> listParameter);
	
}
