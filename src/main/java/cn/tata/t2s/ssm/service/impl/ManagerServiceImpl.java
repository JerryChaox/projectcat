package cn.tata.t2s.ssm.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tata.t2s.ssm.cache.RedisCache;
import cn.tata.t2s.ssm.dao.PersonDao;
import cn.tata.t2s.ssm.dao.ProjectDao;
import cn.tata.t2s.ssm.dao.StateDao;
import cn.tata.t2s.ssm.entity.ProjectApplication;
import cn.tata.t2s.ssm.enums.ResultEnum;
import cn.tata.t2s.ssm.exception.BizException;
import cn.tata.t2s.ssm.service.ManagerService;

@Service
public class ManagerServiceImpl implements ManagerService{
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PersonDao personDao;
	@Autowired
	private ProjectDao projectDao;
	@Autowired
	private StateDao stateDao;
	@Autowired
	private RedisCache cache;
	@Override
	public int getStateTypeIdByStateName(String stateName, String fromTable) {
		return stateDao.selectIdByStateName(stateName, fromTable);
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ProjectApplication> getProjectApplicationList(int offset, int limit) {
		String cacheKey = RedisCache.CAHCENAME + "|getProjectApplicationList|";
		List<ProjectApplication> resultCache = cache.getListCache(cacheKey, ProjectApplication.class);
		if (resultCache != null) {
			LOG.info("get cache with key:" + cacheKey);
			return resultCache;
		} else {
			resultCache = personDao.selectProjectApplication(offset, limit);
			cache.putCache(cacheKey, resultCache);
			LOG.info("put cache with key:" + cacheKey);
			return resultCache;
		}
	}

	@Override
	public void admitProjectApplication(int applicationId) {
		int result = personDao.setApplicationOnAdmit(applicationId);
		if (result <= 0) {
			// admitProjectApplication failed
			throw new BizException(ResultEnum.DB_UPDATE_RESULT_ERROR.getMsg());
		} else {
			cache.deleteCacheWithPattern("getProjectApplicationList*");
			LOG.info("delete cache with key: getProjectApplicationList*");
			return;
		}
	}

	@Override
	public void sentBackProjectApplication(int projectId) {
		int offlineStateId = getStateTypeIdByStateName("offline", "project");
		int result = projectDao.updateProjectStateById(projectId, offlineStateId);
		if (result <= 0) {
			// sentBackProjectApplication failed
			throw new BizException(ResultEnum.DB_UPDATE_RESULT_ERROR.getMsg());
		} else {
			cache.deleteCacheWithPattern("sentBackProjectApplication*");
			LOG.info("delete cache with key: getProjectApplicationList*");
			return;
		}
	}

}
