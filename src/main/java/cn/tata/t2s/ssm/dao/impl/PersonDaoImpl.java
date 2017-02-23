package cn.tata.t2s.ssm.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import cn.tata.t2s.ssm.dao.PersonDao;
import cn.tata.t2s.ssm.entity.Person;
import cn.tata.t2s.ssm.entity.ProjectApplication;

@Repository
public class PersonDaoImpl implements PersonDao{
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public String selectpTypeById(String personId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends Person> T selectProfileById(String personId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> selectFollowing(String personId, int offset, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProjectApplication> selectProjectApplication(int offset, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertVisitor(String personId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertFollow(String personId, String followedId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertProjectApplication(String personId, int projectId, String reason, String targetStateName) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setFollowOnDelete(String personId, String followedId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setApplicationOnAdmit(int applicationId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <T extends Person> int saveProfile(T person) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setOnDelete(String personId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
