package cn.tata.t2s.ssm;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.tata.t2s.ssm.dao.EnrollDao;
import cn.tata.t2s.ssm.entity.Enroll;
import cn.tata.t2s.ssm.entity.Project;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class EnrollDaoTest {
	@Autowired
	EnrollDao enrollDao;
	
	@Test
	public void selectEnrollByErnollId() {
		System.out.println(enrollDao.selectEnrollByErnollId(1, "1", 1));
	}
	
	@Test
	public void countPersonEnrollByState() {
		System.out.println(enrollDao.countPersonEnrollByState("3", "admitted"));
	}
	
	@Test
	public void selectProjectEnrollInfo() {
		List<Enroll> enrollList;
		enrollList = enrollDao.selectProjectEnrollInfoByPersonId("3",-1,-1);
		for(Enroll enroll: enrollList) {
			System.out.println(enroll);
		}
	}
	
	@Test
	public void selectProjectEnrollInfo_() {
		List<Enroll> enrollList;
		enrollList = enrollDao.selectProjectEnrollInfoByProjectId(3,-1,50);
		for(Enroll enroll: enrollList) {
			System.out.println(enroll + " " + enroll.getPerson().getClass());
		}
	}
	
	@Test
	public void insertEnroll() {
//		Enroll enroll = new Enroll();
//		Student person = new Student();
//		Project project = new Project();
//		project.setProjectId(1);
//		person.setPersonId("4");
//		enroll.setPerson(person);
//		enroll.setProject(project);
//		System.out.println(enrollDao.insertEnroll(enroll));
//		System.out.println(enrollDao.insertEnrollState(enroll.getEnrollId(), "admitted"));
	}
	
	@Test
	public void updateEnrollState() {
		System.out.println(enrollDao.updateEnrollState(1, "4", "deprecated"));
	}
}
