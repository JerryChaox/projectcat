package cn.tata.t2s.ssm.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tata.t2s.ssm.cache.RedisCache;
import cn.tata.t2s.ssm.dao.SuperDao;
import cn.tata.t2s.ssm.service.BaseService;
import cn.tata.t2s.ssm.service.ListParameter;
import cn.tata.t2s.ssm.util.PagedResult;

@Service
public class SuperServiceImpl implements BaseService{
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RedisCache cache;
	
	@Autowired
	private SuperDao superDao;
	
	@Override
	public <T> T get(Class<T> entityClass, Object primaryKey) {
		//init
		String cacheKey = RedisCache.CAHCENAME 
				+ "|" + Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "|" + primaryKey;
		
		//cache
		T entityCache = cache.getCache(cacheKey, entityClass);
		if(entityCache != null) {
			LOG.info("get cache with key:" + cacheKey);
			return entityCache;
		
		//null cache
		} else {
			T entity = superDao.select(entityClass, primaryKey);
			cache.putCache(cacheKey, entity);
			LOG.info("put cache with key:" + cacheKey);
			return entity;
		}
	}

	@Override
	public <T, X, Y> PagedResult<T> list(ListParameter<T, X, Y> listParameterObject) {
		//init
		Class<T> resultClass = listParameterObject.getSetAttribute()
				.getElementType().getJavaType();
		String cacheKey = RedisCache.CAHCENAME 
				+ "|" + Thread.currentThread().getStackTrace()[2].getMethodName() 
				+ "|" + listParameterObject.getIdPair().getLeft() 
				+ "|" + listParameterObject.getPagedResult().getStartPosition() 
				+ "|" + listParameterObject.getPagedResult().getPageSize();
		
		//cache
		List<T> listCache = cache.getListCache(cacheKey, resultClass);
		if (listCache != null) {
			listParameterObject.getPagedResult().setData(listCache);
			LOG.info("get listCache with key:" + cacheKey);
			return listParameterObject.getPagedResult();
			
		//null cache 
		} else {
			superDao.select(listParameterObject.getPagedResult()
					, listParameterObject.getIdPair()
					, listParameterObject.getSetAttribute()
					, listParameterObject.getCustomPredicate());
			cache.putCache(cacheKey, listParameterObject.getPagedResult().getData());
			LOG.info("put listCache with key:" + cacheKey);
			return listParameterObject.getPagedResult();
		}
	}

}
