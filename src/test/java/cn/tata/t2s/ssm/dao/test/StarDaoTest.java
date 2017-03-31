package cn.tata.t2s.ssm.dao.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.tata.t2s.ssm.dao.StarDao;
import cn.tata.t2s.ssm.entity.Project;
import cn.tata.t2s.ssm.entity.Star;
import cn.tata.t2s.ssm.entity.Topic;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class StarDaoTest {
	@Autowired
	private StarDao starDao;
	
	@Test
	public void selectStar() {
		//List<Star<T>> project_star = starDao.selectPersonalStar("1", "project");
		List<Star<Topic>> topic_star = starDao.selectTopicStar("1",0,0);
		System.out.println(topic_star);
		//System.out.println(topic_star);
	}
	
	@Test
	public void insertStarItem() {
		int result = starDao.insertStarItem("1", 5, "project");
		System.out.println(result);
	}
	
	@Test
	public void deleteStarItem() {
		System.out.println(starDao.setOnDelete(1,"project"));
	}
}
