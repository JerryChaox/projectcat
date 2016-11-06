package cn.tata.t2s.ssm.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.GrantedAuthority;

import cn.tata.t2s.ssm.entity.Right;

public interface ManagerDao {
	
	/**
	 * 获取用户组信息
	 * 
	 */
	public List<HashMap<String,Object>> selectGroupInfo();
	
	
	/**
	 * 获取权限信息表
	 */
	public List<String> selectAllManagement();
	
	/**
	 * 获取用户组权限信息
	 */
	public HashMap<Integer,Integer> selectGroupRightInfo();
	
	/**
	 * 根据用户id获取用户权限信息
	 */
	public List<GrantedAuthority> selectUserRightInfo(String personId);
	
	/**
	 * 增加用户权限
	 * 
	 */
	public int insertUserRightById(@Param("personId") String personId, @Param("rightList") List<Right> rightList);
	
	/**
	 * 修改用户权限
	 * 
	 */
	public int updateUserRightById(@Param("personId") String personId, @Param("rightList") List<Right> rightList);
	
	/**
	 * 删除用户权限
	 * 
	 */
	public int deleteSingleUserRightById(@Param("personId") String personId, @Param("groupId") String groupId,
			@Param("classId") String classId);
}
