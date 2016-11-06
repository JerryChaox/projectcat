package cn.tata.t2s.ssm.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tata.t2s.ssm.cache.RedisCache;
import cn.tata.t2s.ssm.dao.PersonDao;
import cn.tata.t2s.ssm.dao.ProjectDao;
import cn.tata.t2s.ssm.entity.Person;
import cn.tata.t2s.ssm.entity.Project;
import cn.tata.t2s.ssm.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private RedisCache cache;
	@Autowired
	private PersonDao personDao;
	@Autowired
	private ProjectDao projectDao;
	@Override
	
	public Project getProjectByProjectId(int projectId) {
		Project project = projectDao.selectProjectById(projectId);
		if(LOG.isDebugEnabled()) {
			System.out.println(project);
		}
		return project;
	}
	
	@Override
	public List<Project> getAllProjectList(int offset, int limit) {
		String cacheKey = RedisCache.CAHCENAME + "|getAllProjectList|" + offset + "|" + limit;
		List<Project> resultCache = cache.getListCache(cacheKey, Project.class);
		if (resultCache != null) {
			LOG.info("get cache with key:" + cacheKey);
			return resultCache;
		} else {
			resultCache = projectDao.selectAllProject(offset, limit);
			cache.putCache(cacheKey, resultCache);
			LOG.info("put cache with key:" + cacheKey);
			return resultCache;
		}
	}
	
	@Override
	public List<Project> dynamicGetProjectList(
			String personId,
			String fluzzyName, int offset,
			int limit) {
		Person person = personDao.selectProfileById(personId);
		return projectDao.dynamicSelectProject(person.getSchool(), person.getAcademy(), fluzzyName, offset, limit);
	}
}
