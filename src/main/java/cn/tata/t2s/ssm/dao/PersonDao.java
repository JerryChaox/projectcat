package cn.tata.t2s.ssm.dao;

import java.util.List;

import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.SetAttribute;

import org.apache.ibatis.annotations.Param;

import cn.tata.t2s.ssm.entity.Person;
import cn.tata.t2s.ssm.entity.ProjectApplication;

public interface PersonDao extends BaseDao{

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
	public Person selectPerson(String personId);

//	/**
//	 * 根据id查询已报名项目
//	 * 
//	 * @param 用户唯一id
//	 * @return 项目唯一id
//	 */
//	public List<Project> selectEnrollProjectId(String personId);
	
	/**
	 * use entityGraph that is statisfied with any fetch strategy
	 * @param <T>
	 * @param personId
	 * @param attribute
	 * @return
	 */
	public Person selectPerson(String personId, SetAttribute<Person, ?>... setAttributes);
	
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
	public int insertVisitor(Person person);
	
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
	public Person savePerson(Person person);
	
	/**
	 * 业务删除person
	 */
	public int setOnDelete(String personId);
}