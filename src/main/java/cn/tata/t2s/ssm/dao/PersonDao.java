package cn.tata.t2s.ssm.dao;

import java.util.List;

import javax.persistence.Tuple;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.ibatis.annotations.Param;

import cn.tata.t2s.ssm.entity.Person;
import cn.tata.t2s.ssm.entity.ProjectApplication;

public interface PersonDao{

//	/**
//	 * 通过id查询所属类别(用户,老师用户,学生用户)
//	 * 
//	 * @param 用户唯一id
//	 * @return 类型标识
//	 */
//	public String selectpTypeById(String personId);
//
	/**
	 * select Person by personId
	 * 
	 * @param 用户personIdid
	 * @return Person entity
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
	 * @param personId
	 * @param resultClass
	 * @param entityClasses
	 * @param setAttributes
	 * @return
	 */
//	public <X,Y> List<Tuple> selectPerson(Pair<SingularAttribute<X,Y>, Y> idPair, SetAttribute<X, ?>... setAttributes);
	
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
	public int insertPerson(Person person);
	
	/**
	 * 
	 * @param personId
	 * @param followedId
	 * @return
	 */
//	public int insertFollow(
//			@Param("personId") String personId,
//			@Param("followedId") String followedId);
	
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
//	public int deleteFollow(
//			@Param("personId") String personId,
//			@Param("followedId") String followedId);
	
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