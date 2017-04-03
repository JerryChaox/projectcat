package cn.tata.t2s.ssm.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.tata.t2s.ssm.cache.RedisCache;
import cn.tata.t2s.ssm.dao.SuperDao;
import cn.tata.t2s.ssm.service.BaseService;
import cn.tata.t2s.ssm.service.util.CriteriaParamManager;
import cn.tata.t2s.ssm.service.util.ListParameter;
import cn.tata.t2s.ssm.service.util.PagedResult;

public class SuperServiceImpl<X, Y> implements BaseService<X, Y>{
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	protected CriteriaParamManager cpm;
	
	@Autowired
	private RedisCache cache;
	
	@Autowired
	private SuperDao<X, Y> superDao;
	
	@Override
	public int save(X entity) {
		return superDao.insert(entity);
	}
	
	@Override
	public int remove(X entity) {
		return superDao.delete(entity);
	}
	
	@Override
	public X refresh(X entity) {
		return superDao.update(entity);
	}
	
	@Override
	public X get(Y primaryKey) {
		//init
		X entity = superDao.select(primaryKey);
		return entity;
		/**
		 * disable cache (should be cut by aop)
		 */
//		String cacheKey = RedisCache.CAHCENAME 
//				+ "|" + Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "|" + primaryKey;
//		
//		//cache
//		X entityCache = cache.getCache(cacheKey, entityClass);
//		if(entityCache != null) {
//			LOG.info("get cache with key:" + cacheKey);
//			return entityCache;
//		
//		//null cache
//		} else {
//			X entity = superDao.select(entityClass, primaryKey);
//			cache.putCache(cacheKey, entity);
//			LOG.info("put cache with key:" + cacheKey);
//			return entity;
//		}
	}

	@Override
	public <T> PagedResult<T> list(ListParameter<T, X, Y> listParameter) {
		superDao.select(listParameter.getPagedResult()
				, listParameter.getIdPair()
				, listParameter.getSetAttribute()
				, listParameter.getCustomPredicate());
		return listParameter.getPagedResult();
		
		/**
		 * disable cache (should be cut by aop)
		 */
		//init
//		Class<T> resultClass = listParameter.getSetAttribute()
//				.getElementType().getJavaType();
//		String cacheKey = RedisCache.CAHCENAME 
//				+ "|" + Thread.currentThread().getStackTrace()[2].getMethodName() 
//				+ "|" + listParameter.getIdPair().getLeft().getDeclaringType().getJavaType().getSimpleName()
//				+ ": " + listParameter.getIdPair().getRight()
//				+ "|" + listParameter.getPagedResult().getStartPosition() 
//				+ "|" + listParameter.getPagedResult().getPageSize();
//		
//		//cache
//		List<T> listCache = cache.getListCache(cacheKey, resultClass);
//		if (listCache != null) {
//			listParameter.getPagedResult().setData(listCache);
//			LOG.info("get listCache with key:" + cacheKey);
//			return listParameter.getPagedResult();
//			
//		//null cache 
//		} else {
//			superDao.select(listParameter.getPagedResult()
//					, listParameter.getIdPair()
//					, listParameter.getSetAttribute()
//					, listParameter.getCustomPredicate());
//			cache.putListCache(cacheKey, listParameter.getPagedResult().getData());
//			LOG.info("put listCache with key:" + cacheKey);
//			return listParameter.getPagedResult();
//		}
	}

}
