package cn.tata.t2s.ssm.service;

import java.util.List;

import cn.tata.t2s.ssm.entity.Enroll;
import cn.tata.t2s.ssm.entity.Project;

public interface TeacherService {

	// 会员老师
	public List<Project> getSelfProjectList(String personId, String StateName, int offset, int limit);

	// 老师会员
	public void createProject(Project project);

	// 老师会员
	public void saveProject(Project project);

	// 老师会员
	public void saveProjectState(String personId, int projectId, String stateName, String reason);
	
	//接受学生的报名
	public void admitStudentEnroll(String tPersonId, String sPersonId, int projectId);

	// 老师会员
	public void dropProject(int projectId, String personId);

	public Enroll getEnroll(long enrollId, String personId, int projectId);
	
	// 老师会员
	public List<Enroll> getProjectEnrollInfo(String personId, int projectId, int offset, int limit);

}
