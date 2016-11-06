package cn.tata.t2s.ssm;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.tata.t2s.ssm.dao.StateDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class StateDaoTest {
	@Autowired
	StateDao stateDao;
	
	@Test
	public void selectStateNameById() {
		System.out.println(stateDao.selectStateNameById(1, "topic"));
		System.out.println(stateDao.selectStateNameById(1, "project"));
	}
	
	@Test
	public void selectIdByStateName() {
		//Integer id = stateDao.selectIdByStateName("sdasd", "topic");
		Integer oo = null;
		System.out.println(oo);
	}
}
