package cn.tata.t2s.ssm.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import cn.tata.t2s.ssm.cache.RedisCache;
import cn.tata.t2s.ssm.dao.EnrollDao;
import cn.tata.t2s.ssm.dao.PersonDao;
import cn.tata.t2s.ssm.dao.ProjectDao;
import cn.tata.t2s.ssm.entity.Enroll;
import cn.tata.t2s.ssm.entity.Person;
import cn.tata.t2s.ssm.entity.Project;
import cn.tata.t2s.ssm.entity.ProjectApplication;
import cn.tata.t2s.ssm.enums.ResultEnum;
import cn.tata.t2s.ssm.exception.BizException;
import cn.tata.t2s.ssm.service.ManagerService;
import cn.tata.t2s.ssm.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PersonDao personDao;
	@Autowired
	private ProjectDao projectDao;
	@Autowired
	private EnrollDao enrollDao;
	@Autowired
	private RedisCache cache;
	@Autowired
	private ManagerService managerService;

	@Override
	public List<Project> getSelfProjectList(String personId, String stateName, int offset, int limit) {
		String cacheKey = RedisCache.CAHCENAME + "|getSelfProjectList|" + personId + "|" + stateName + "|" + offset
				+ "|" + limit;
		List<Project> resultCache = cache.getListCache(cacheKey, Project.class);
		if (resultCache != null) {
			LOG.info("get cache with key:" + cacheKey);
			return resultCache;
		} else {
			resultCache = projectDao.selectSelfProject(personId,
					managerService.getStateTypeIdByStateName(stateName, "project"), offset, limit);
			cache.putCache(cacheKey, resultCache);
			LOG.info("put cache with key:" + cacheKey);
			return resultCache;
		}
	}

	@Override
	public void createProject(Project project) {
		String personId = project.getPerson().getPersonId();
		Assert.notNull(personId, "personId is required");
		
		Person person = personDao.selectProfileById(personId);
		
		if(project.getManagerIntro() == null) {
			project.setManagerIntro(person.getDefaultResume());
		}
		
		int first_result = projectDao.insertProjectBasicInfo(project);

		if (first_result <= 0) {
			// 创建项目失败
			throw new BizException(ResultEnum.DB_UPDATE_RESULT_ERROR.getMsg());
		}

		int projectId = project.getProjectId();
		Integer projectStateTypeId = managerService.getStateTypeIdByStateName("validating", "project");
		Assert.notNull(projectStateTypeId, "cannot find id for validating");
		int second_result = projectDao.insertProjectState(projectId, projectStateTypeId);

		if (second_result <= 0) {
			// 创建项目失败
			throw new BizException(ResultEnum.DB_UPDATE_RESULT_ERROR.getMsg());
		}

		cache.deleteCacheWithPattern("getSelfProjectList*" + personId + "*");
		LOG.info("delete cache with key: getSelfTopicList*" + personId + "*");
		return;

	}

	@Override
	public void saveProject(Project project) {
		String personId = project.getPerson().getPersonId();
		Assert.notNull(personId, "personId is required");
		String stateName = project.getState().getStateName();
		if (stateName.equals("online")) {
			// 已经上线的项目不允许修改,抛出业务异常
			throw new BizException(ResultEnum.PROJECT_UPDATE_ERROR.getMsg());
		}

		Integer projectId = project.getProjectId();
		Assert.notNull(projectId, "projectId is required");
		int result = projectDao.updateProjectBasicInfo(project);
		if (result <= 0) {
			// 更新项目失败
			throw new BizException(ResultEnum.DB_UPDATE_RESULT_ERROR.getMsg());
		}
		cache.deleteCacheWithPattern("getSelfProjectList*" + personId + "*");
		LOG.info("delete cache with key: getSelfTopicList*" + personId + "*");
		return;
	}

	@Override
	public void saveProjectState(String personId, int projectId, String stateName, String reason) {
		Assert.notNull(personId, "personId is required");
		String currentStateName = projectDao.selectProjectStateById(projectId).getStateName();

		// online to offline
		if (currentStateName.equals("online") && stateName.equals("offline")) {
			personDao.insertProjectApplication(personId, projectId, stateName, reason);
			return;
		}

		// offline to online
		if (currentStateName.equals("offline") && stateName.equals("online")) {
			personDao.insertProjectApplication(personId, projectId, stateName, reason);
			return;
		}

		// cannot update the project whose state is validating or online
		if (currentStateName.equals("validating") || currentStateName.equals("online")) {
			throw new BizException(ResultEnum.PROJECT_STATE_UPDATE_ERROR.getMsg());
		}

		Integer projectStateTypeId = managerService.getStateTypeIdByStateName(stateName, "project");
		Assert.notNull(projectStateTypeId, "unknown project state");
		int result = projectDao.updateProjectStateById(projectId, projectStateTypeId);
		if (result <= 0) {
			// fail to saveProjectState
			throw new BizException(ResultEnum.DB_UPDATE_RESULT_ERROR.getMsg());
		}
		cache.deleteCacheWithPattern("getSelfProjectList*" + personId + "*");
		LOG.info("delete cache with key: getSelfTopicList*" + personId + "*");
		return;
	}

	@Override
	public void admitStudentEnroll(String tPersonId, String sPersonId, int projectId) {
		int result = enrollDao.updateEnrollState(projectId, sPersonId, "admitted");
		if (result <= 0) {
			// 录用失败,内部错误
			throw new BizException(ResultEnum.DB_UPDATE_RESULT_ERROR.getMsg());
		}
		cache.deleteCacheWithPattern("getProjectEnrollInfo*" + tPersonId + "*");
		LOG.info("delete cache with key: getProjectEnrollInfo*" + tPersonId + "*");
		return;
	}

	@Override
	public void dropProject(int projectId, String personId) {

		String stateName = projectDao.selectProjectStateById(projectId).getStateName();
		if (stateName.equals("online") || stateName.equals("validate")) {
			// 上线的项目不能删除, 要发送请求给管理员下线,后删除
			throw new BizException(ResultEnum.PROJECT_DELETE_ERROR.getMsg());
		}
		int detail_result = projectDao.setOnDelete(projectId);

		if (detail_result <= 0) {
			// 删除帖子失败
			throw new BizException(ResultEnum.DB_UPDATE_RESULT_ERROR.getMsg());
		} else {
			int main_result = projectDao.setOnDelete(projectId);
			if (main_result <= 0) {
				// 删除帖子失败
				throw new BizException(ResultEnum.DB_UPDATE_RESULT_ERROR.getMsg());
			}
			cache.deleteCacheWithPattern("getSelfProjectList*" + personId + "*");
			LOG.info("delete cache with key: getSelfProjectList*" + personId + "*");
			return;
		}
	}

	@Override
	public List<Enroll> getProjectEnrollInfo(String personId, int projectId, int offset, int limit) {
		String cacheKey = RedisCache.CAHCENAME + "|getProjectEnrollInfo|" + personId + "|" + projectId + "|" + offset
				+ "|" + limit;
		List<Enroll> resultCache = cache.getListCache(cacheKey, Enroll.class);
		if (resultCache != null) {
			LOG.info("get cache with key:" + cacheKey);
			return resultCache;
		} else {
			resultCache = enrollDao.selectProjectEnrollInfoByProjectId(projectId, offset, limit);
			cache.putCache(cacheKey, resultCache);
			LOG.info("put cache with key:" + cacheKey);
			return resultCache;
		}
	}

	@Override
	public Enroll getEnroll(long enrollId, String personId, int projectId) {
		String cacheKey = RedisCache.CAHCENAME + "|getEnroll|" + enrollId;
		Enroll resultCache = cache.getCache(cacheKey, Enroll.class);
		if (resultCache != null) {
			LOG.info("get cache with key:" + cacheKey);
			return resultCache;
		} else {
			resultCache = enrollDao.selectEnrollByErnollId(enrollId, personId, projectId);
			cache.putCache(cacheKey, resultCache);
			LOG.info("put cache with key:" + cacheKey);
			return resultCache;
		}
	}

}
