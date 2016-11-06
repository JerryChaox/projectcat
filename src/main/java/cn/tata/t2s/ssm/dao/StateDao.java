package cn.tata.t2s.ssm.dao;

import org.apache.ibatis.annotations.Param;

public interface StateDao {
	
	public String selectStateNameById(@Param("id") int state_type_id, 
			@Param("fromTable") String fromTable);

	public int selectIdByStateName(@Param("stateName") String stateName, 
			@Param("fromTable") String fromTable);
	
}
