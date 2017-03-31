package cn.tata.t2s.ssm.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tata.t2s.ssm.cache.RedisCache;
import cn.tata.t2s.ssm.dao.SuperDao;
import cn.tata.t2s.ssm.service.BaseService;
import cn.tata.t2s.ssm.service.util.CriteriaParamManager;
import cn.tata.t2s.ssm.service.util.ListParameter;
import cn.tata.t2s.ssm.service.util.PagedResult;

@Service
public class SuperServiceImpl<X, Y> implements BaseService<X, Y>{
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	protected CriteriaParamManager cpm;
	
	@Autowired
	private RedisCache cache;
	
	@Autowired
	private SuperDao superDao;
	
	@Override
	public X get(Class<X> entityClass, Object primaryKey) {
		//init
		String cacheKey = RedisCache.CAHCENAME 
				+ "|" + Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "|" + primaryKey;
		
		//cache
		X entityCache = cache.getCache(cacheKey, entityClass);
		if(entityCache != null) {
			LOG.info("get cache with key:" + cacheKey);
			return entityCache;
		
		//null cache
		} else {
			X entity = superDao.select(entityClass, primaryKey);
			cache.putCache(cacheKey, entity);
			LOG.info("put cache with key:" + cacheKey);
			return entity;
		}
	}

	@Override
	public <T> PagedResult<T> list(ListParameter<T, X, Y> listParameter) {
		//init
		Class<T> resultClass = listParameter.getSetAttribute()
				.getElementType().getJavaType();
		String cacheKey = RedisCache.CAHCENAME 
				+ "|" + Thread.currentThread().getStackTrace()[2].getMethodName() 
				+ "|" + listParameter.getIdPair().getLeft().getDeclaringType().getJavaType().getSimpleName()
				+ ": " + listParameter.getIdPair().getRight()
				+ "|" + listParameter.getPagedResult().getStartPosition() 
				+ "|" + listParameter.getPagedResult().getPageSize();
		
		//cache
		List<T> listCache = cache.getListCache(cacheKey, resultClass);
		if (listCache != null) {
			listParameter.getPagedResult().setData(listCache);
			LOG.info("get listCache with key:" + cacheKey);
			return listParameter.getPagedResult();
			
		//null cache 
		} else {
			superDao.select(listParameter.getPagedResult()
					, listParameter.getIdPair()
					, listParameter.getSetAttribute()
					, listParameter.getCustomPredicate());
			cache.putCache(cacheKey, listParameter.getPagedResult().getData());
			LOG.info("put listCache with key:" + cacheKey);
			return listParameter.getPagedResult();
		}
	}

}
