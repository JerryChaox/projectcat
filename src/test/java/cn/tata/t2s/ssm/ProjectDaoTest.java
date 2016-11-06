package cn.tata.t2s.ssm;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.tata.t2s.ssm.dao.ProjectDao;
import cn.tata.t2s.ssm.entity.Person;
import cn.tata.t2s.ssm.entity.Project;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class ProjectDaoTest {
	@Autowired
	ProjectDao projectDao;
	
	@Test
	public void selectProjectById() {
		Project project = projectDao.selectProjectById(1);
		DateTime deadline = new DateTime(project.getDeadline());
		System.out.println(project);
	}
	
	@Test
	public void selectAllProject() {
		List<Project> projectList = new ArrayList<Project>();
		projectList = projectDao.selectAllProject(0, 50);
		for(Project project: projectList) {
			System.out.println(project);
		}
	}
	
	@Test
	public void dynamicSelectProject() {
		String[] school = {"GDUT","HNLG"};
		String[] academy = {"CN","JK"};
		List<Project> projectList = new ArrayList<Project>();
		//projectList = projectDao.dynamicSelectProject(school, academy, "2",0, 50);
		//projectList = projectDao.dynamicSelectProject(null, null, "2",0, 50);
		for(Project project: projectList) {
			System.out.println(project);
		}
	}
	
//	@Test
//	public void fuzzySelectProjectIdByName() {
//		List<Project> projectList = new ArrayList<Project>();
//		String fuzzyName = "2";
//		//fuzzyName = "%" + fuzzyName + "%";
//		projectList = projectDao.fuzzySelectProjectIdByName(fuzzyName, 0, 50);
//		for(Project project: projectList) {
//			System.out.println(project);
//		}
//	}
	
	@Test
	public void selectSelfProject() {
		List<Project> projectList = new ArrayList<Project>();
		int projectStateTypeId = 1;
		projectList = projectDao.selectSelfProject("1", projectStateTypeId,0,50);
		for(Project project: projectList) {
			System.out.println(project);
		}
	}
	
	@Test
	public void selectProjectStateById() {
		System.out.println(projectDao.selectProjectStateById(1));
		
	}
	
	@Test
	public void countProjectByState() {
		System.out.println(projectDao.countProjectByState("1", "online"));
	}
	
	@Test
	public void insertProject() {
//		Map<String,Object> paramMap = new HashMap<String,Object>();
		int projectStateTypeId = 4;
//		paramMap.put("projectId", 6);
//		paramMap.put("projectName", "t2s_ssm");
		Person person = new Person();
		person.setPersonId("2");
		Project project = new Project();
		project.setProjectName("fufufufu");
		project.setPerson(person);
		int result1 = projectDao.insertProjectBasicInfo(project);
		int result2 = projectDao.insertProjectState(project.getProjectId(), projectStateTypeId);
		System.out.println(result1 + "   " + result2);
	}
	
	@Test
	public void updateProjectBasicInfo() {
//		Map<String,Object> paramMap = new HashMap<String,Object>();
//		paramMap.put("projectId", 6);
//		paramMap.put("projectName", "t2s_ssf");
//		int result = projectDao.updateProjectBasicInfo(paramMap);
		//System.out.println("---------" + result);
		Person person = new Person();
		person.setPersonId("1");
		Project project = new Project();
		project.setProjectId(5);
		project.setProjectName("tttt222sss");
		project.setMaxEnrollCount(10);
		System.out.println(projectDao.updateProjectBasicInfo(project));
	}
	
	@Test
	public void updateProjectStateById() {
		int result = projectDao.updateProjectStateById(6, 1);
		System.out.println("-----------");
		System.out.println(result);
	}
	
	@Test
	public void deleteProject() {
		int result2 = projectDao.deleteProjectStateById(6);
		int result1 = projectDao.deleteProjectById(6);
		System.out.println(result1 + "---------" + result2);
	}
	
	@Test
	public void setOnDelete() {
		System.out.println(projectDao.setOnDelete(7));
	}
}
