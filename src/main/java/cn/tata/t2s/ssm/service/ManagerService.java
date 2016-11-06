package cn.tata.t2s.ssm.service;

import java.util.List;

import cn.tata.t2s.ssm.entity.ProjectApplication;

public interface ManagerService {
	/**
	 * 
	 * @param name
	 * @return
	 */
	public int getStateTypeIdByStateName(String stateName, String fromTable);
	
	/**
	 * 
	 * @param offset
	 * @param limit
	 * @return
	 */
	public List<ProjectApplication> getProjectApplicationList(int offset, int limit);
	
	/**
	 * 
	 * @param applicationId
	 */
	public void admitProjectApplication(int applicationId);
	
	/**
	 * 
	 * @param applicationId
	 */
	public void sentBackProjectApplication(int projectId);
}
