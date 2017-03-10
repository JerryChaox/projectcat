package cn.tata.t2s.ssm.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.tata.t2s.ssm.dao.EnrollDao;
import cn.tata.t2s.ssm.entity.Enroll;

@Repository
@Transactional
public class EnrollDaoImpl implements EnrollDao {

	@Override
	public Enroll selectEnrollByErnollId(long enrollId, String personId, int projectId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Enroll> selectProjectEnrollInfoByPersonId(String personId, int offset, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Enroll> selectProjectEnrollInfoByProjectId(int projectId, int offset, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countPersonEnrollByState(String personId, String stateName) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int selectEnrollCount(String personId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateEnrollState(int projectId, String personId, String stateName) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertEnroll(Enroll enroll) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertEnrollState(int enrollId, String stateName) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateEnrolledCount(int projectId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean hasFullEnrolled(int projectId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isDead(int proejctId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int setOnDelete(long enrollId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
