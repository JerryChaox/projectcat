package cn.tata.t2s.ssm.service;

import cn.tata.t2s.ssm.entity.Base;
import cn.tata.t2s.ssm.service.util.ListParameter;
import cn.tata.t2s.ssm.service.util.PagedResult;

public interface BaseService<X extends Base, Y> {

	public int save(X entity);
	
	public int remove(X entity);
	
	public X refresh(X entity);
	
	X get(Object primaryKey);
	
	<T> PagedResult<T> list(ListParameter<T, X, Y> listParameter);
	
}
