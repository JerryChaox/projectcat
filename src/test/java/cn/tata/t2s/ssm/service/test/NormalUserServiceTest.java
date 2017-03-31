package cn.tata.t2s.ssm.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cn.tata.t2s.ssm.service.NormalUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"
	, "classpath:spring/spring-service.xml"
	, "classpath:spring/spring-redis.xml"})
@Transactional(transactionManager = "txManager")
public class NormalUserServiceTest {
	
	@Autowired
	NormalUserService normalUserService;

	@Test
	public void getFollowList() {
		System.out.println(normalUserService.getFollowingList("1", 2, 1));
	}
}
