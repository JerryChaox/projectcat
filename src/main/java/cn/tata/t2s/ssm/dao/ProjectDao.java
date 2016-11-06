package cn.tata.t2s.ssm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.tata.t2s.ssm.entity.Project;
import cn.tata.t2s.ssm.entity.State;

public interface ProjectDao {
	

	
	/**
	 * 根据项目id查询项目
	 * @param 项目唯一标识
	 * @return 项目所有资料
	 */
	public Project selectProjectById(int projectId);
	
	/**
	 * 查询一页的项目
	 * @param offset 从第几条算起
	 * @param limit 总共查询多少条
	 */
	public List<Project> selectAllProject(@Param("offset") int offset, @Param("limit") int limit);
	/**
	 * 根据项目id查询项目状态
	 * @param 项目唯一标识
	 * @return State 项目状态
	 */
	public State selectProjectStateById(int projectId);
	
	/**
	 * 根据项目id查询项目状态
	 * @param 项目唯一标识
	 * @param 状态
	 * @return 该状态下的项目数
	 */
	public int countProjectByState(
			@Param("personId") String personId,
			@Param("stateName") String stateName);
	
	/**
	 * 根据一个或多个学校\学院查询项目
	 * 页面展示
	 * @param school 学校
	 * @param academy 学院
	 * @param offset 从第几条算起
	 * @param limit 总共查询多少条
	 * @return 一个页面展示项目所需的信息
	 */
	public List<Project> dynamicSelectProject(
			@Param("school") String school, 
			@Param("academy") String academy, 
			@Param("fluzzyName") String fluzzyName,
			@Param("offset") int offset, @Param("limit") int limit);
	
	/**
	 * 查询自己各个状态的项目
	 * @param personId 用户唯一标识
	 * @param projectStateTypeId 状态类型标识
	 */
	public List<Project> selectSelfProject(@Param("personId") String personId, 
			@Param("projectStateTypeId") int projectStateTypeId,
			@Param("offset") int offset, @Param("limit") int limit);
	
//	/**
//	 * 根据名字模糊查询项目
//	 * @param 项目名称
//	 * @param offset 从第几条算起
//	 * @param limit 总共查询多少条
//	 * @return 一个页面展示项目所需的信息
//	 */
//	public List<Project> fuzzySelectProjectIdByName(@Param("fluzzyName") String fluzzyName, 
//			@Param("offset") int offset, @Param("limit") int limit);
//	
	
	/**
	 * 添加项目
	 * @param 项目基本信息(必须包括名字)
	 * @return 执行结果
	 */
	public int insertProjectBasicInfo(Project project);
	
	/**
	 * 插入项目状态
	 * @param projectId 项目唯一标识
	 * @param projectStateTypeId 状态类型标识
	 */
	public int insertProjectState(@Param("projectId") int projectId, @Param("projectStateTypeId") int projectStateTypeId);
	
	/**
	 * 修改项目资料
	 * @param 项目基本信息
	 * @return 执行结果
	 */
	public int updateProjectBasicInfo(Project project);
	
	/**
	 * 逻辑删除项目
	 * @param 项目唯一标识
	 * @return 执行结果
	 */
	public int deleteProjectById(int projectId);
		
	/**
	 * 改变项目状态
	 * @param projectId 项目唯一标识
	 * @param projectStateTypeId 状态类型标识
	 * @return 执行结果
	 */
	public int updateProjectStateById(@Param("projectId") int projectId, @Param("projectStateTypeId") int projectStateTypeId);
	
	/**
	 * 逻辑删除项目当前状态信息
	 * @param 项目唯一标识
	 * @return 执行结果
	 */
	public int deleteProjectStateById(int projectId);
	
	/**
	 * 业务删除项目
	 * @param 项目唯一标识
	 * @return 执行结果
	 */
	public int setOnDelete(int projectId);
}