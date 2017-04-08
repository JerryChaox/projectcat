package cn.tata.t2s.ssm.dao;

import java.util.List;

import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;

import org.apache.ibatis.annotations.Param;

import cn.tata.t2s.ssm.entity.Person;
import cn.tata.t2s.ssm.entity.PersonPair;
import cn.tata.t2s.ssm.entity.ProjectApplication;
import cn.tata.t2s.ssm.service.util.ListParameter;
import cn.tata.t2s.ssm.service.util.PagedResult;

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
	
	public PagedResult<PersonPair> selectPersonPersonPair(
			String followOrFan
			, ListParameter<PersonPair, Person, String> listParameter);
	
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
	public Person updatePerson(Person person);
	
	/**
	 * 业务删除person
	 */
	public int setOnDelete(String personId);
	
	public Predicate getPersonFollowPredicate(
			String followOrFan, 
			SetJoin<Person, PersonPair> setJoin);
}