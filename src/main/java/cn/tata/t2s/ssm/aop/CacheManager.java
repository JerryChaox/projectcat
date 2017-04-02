package cn.tata.t2s.ssm.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.tata.t2s.ssm.cache.RedisCache;

/**
 * 
 * @author tata
 * manage the cache through aop
 */
@Component
@Aspect
public class CacheManager {
	
	@Autowired
	RedisCache cache;
	
	/**
	 * 
	 * cover all the service
	 */
	@Pointcut("execution(* cn.tata.t2s.ssm.service..*.*(..))")
	public void servicePoint() {}
	
	/**
	 * delete cache when save || remove || refresh
	 * @param entity 
	 * @param primaryKey
	 */
	@Pointcut("servicePoint() "
			+ "&& ("
			+ "execution(* save*(..)) "
			+ "|| execution(* remove*(..))"
			+ "|| execution(* refresh*(..))"
			+ ")"
			+ "&& args(entity, primaryKey,..)")
	public <T, X> void deleteCacheAdvice(T entity, X primaryKey) {}
	
	@AfterReturning(pointcut = "deleteCacheAdvice(entity, primaryKey)"
			, argNames="entity, primaryKey")
	public <T, X> void after(JoinPoint point, T entity, X primaryKey) {
		System.out.println(entity.getClass().getSimpleName() + "_________" + primaryKey);
		String entityClassName = entity.getClass().getSimpleName();
		cache.deleteCacheWithPattern(entityClassName);
	}

}
