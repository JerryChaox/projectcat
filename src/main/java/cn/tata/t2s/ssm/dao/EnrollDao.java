package cn.tata.t2s.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tata.t2s.ssm.entity.Enroll;

public interface EnrollDao {
	public Enroll selectEnrollByErnollId(
			@Param("enrollId") long enrollId,
			@Param("personId") String personId,
			@Param("projectId") int projectId);
	
	public List<Enroll>  selectProjectEnrollInfoByPersonId(
			@Param("personId") String personId,
			@Param("offset") int offset, @Param("limit") int limit);
	
	public List<Enroll> selectProjectEnrollInfoByProjectId(
			@Param("projectId") int projectId,  
			@Param("offset") int offset, @Param("limit") int limit);
	
	public int countPersonEnrollByState(
			@Param("personId") String personId, 
			@Param("stateName") String stateName);
	
	public int selectEnrollCount(String personId);
	
	public int updateEnrollState(
			@Param("projectId") int projectId,
			@Param("personId") String personId, 
			@Param("stateName") String stateName);
	
	public int insertEnroll(Enroll enroll);
	
	public int insertEnrollState(
			@Param("enrollId") int enrollId,
			@Param("stateName") String stateName);
	
	public int updateEnrolledCount(int projectId);
	
	public boolean hasFullEnrolled(int projectId);
	
	public boolean isDead(int proejctId);
	
	public int setOnDelete(long enrollId);
	
}