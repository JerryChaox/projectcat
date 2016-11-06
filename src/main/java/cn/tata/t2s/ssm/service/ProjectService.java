package cn.tata.t2s.ssm.service;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import cn.tata.t2s.ssm.entity.Project;

public interface ProjectService {

	public Project getProjectByProjectId(int projectId);

	public List<Project> getAllProjectList(int offset, int limit);

	@Secured("select_project")
	public List<Project> dynamicGetProjectList(
			String personId,
			String fluzzyName, int offset,
			int limit);
}
