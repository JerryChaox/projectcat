package cn.tata.t2s.ssm.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.tata.t2s.ssm.dao.ProjectDao;
import cn.tata.t2s.ssm.entity.Project;
import cn.tata.t2s.ssm.entity.State;

@Repository
@Transactional
public class ProjectDaoImpl implements ProjectDao{

	@Override
	public Project selectProjectById(int projectId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Project> selectAllProject(int offset, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public State selectProjectStateById(int projectId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countProjectByState(String personId, String stateName) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Project> dynamicSelectProject(String school, String academy, String fluzzyName, int offset, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Project> selectSelfProject(String personId, int projectStateTypeId, int offset, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertProjectBasicInfo(Project project) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertProjectState(long projectId, int projectStateTypeId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateProjectBasicInfo(Project project) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteProjectById(int projectId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateProjectStateById(long l, int projectStateTypeId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteProjectStateById(int projectId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setOnDelete(int projectId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
