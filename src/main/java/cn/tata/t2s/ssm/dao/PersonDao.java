package cn.tata.t2s.ssm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.tata.t2s.ssm.entity.Person;
import cn.tata.t2s.ssm.entity.Project;
import cn.tata.t2s.ssm.entity.ProjectApplication;

public interface PersonDao {

	/**
	 * 通过id查询所属类别(用户,老师用户,学生用户)
	 * 
	 * @param 用户唯一id
	 * @return 类型标识
	 */
	public String selectpTypeById(String personId);

	/**
	 * select the personal profile by personId
	 * 
	 * @param 用户唯一id
	 * @return 资料所需字段信息
	 */
	public <T extends Person> T selectProfileById(String personId);

//	/**
//	 * 根据id查询已报名项目
//	 * 
//	 * @param 用户唯一id
//	 * @return 项目唯一id
//	 */
//	public List<Project> selectEnrollProjectId(String personId);
	
	/**
	 * 
	 * @param personId
	 * @return
	 */
	public List<Person> selectFollowing(
			@Param("personId") String personId,
			@Param("offset") int offset,
			@Param("limit") int limit);
	
	/**
	 * 
	 * @param offset
	 * @param limit
	 * @return
	 */
	public List<ProjectApplication> selectProjectApplication(int offset, int limit);
	
	/**
	 * 
	 * @param personId
	 * @return
	 */
	public int insertVisitor(@Param("personId") String personId);
	
	/**
	 * 
	 * @param personId
	 * @param followedId
	 * @return
	 */
	public int insertFollow(
			@Param("personId") String personId,
			@Param("followedId") String followedId);
	
	/**
	 * 
	 * @param pApplication
	 * @return
	 */
	public int insertProjectApplication(
			@Param("personId") String personId,
			@Param("projectId") int projectId,
			@Param("reason") String reason,
			@Param("targetStateName") String targetStateName);
	
	/**
	 * unfollow
	 * @param personId
	 * @param followedId
	 * @return
	 */
	public int setFollowOnDelete(
			@Param("personId") String personId,
			@Param("followedId") String followedId);
	
	/**
	 * 
	 * @param applicationId
	 * @return
	 */
	public int setApplicationOnAdmit(int applicationId);
	
	/**
	 * 绑定和更新个人资料
	 * 
	 */
	public <T extends Person> int saveProfile(T person);
	
	/**
	 * 业务删除person
	 */
	public int setOnDelete(String personId);
}