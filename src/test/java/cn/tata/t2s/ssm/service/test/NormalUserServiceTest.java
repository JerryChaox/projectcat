package cn.tata.t2s.ssm.service.test;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cn.tata.t2s.ssm.entity.Topic;
import cn.tata.t2s.ssm.service.NormalUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"
	, "classpath:spring/spring-service.xml"
	, "classpath:spring/spring-redis.xml"})
@Transactional(transactionManager = "txManager")
@Rollback(false)
public class NormalUserServiceTest {
	
	@Autowired
	NormalUserService normalUserService;
	
	@Test
	public void savePersonTopic() {
		Topic topic = new Topic();
		topic.setPerson(normalUserService.getPerson("1"));
		normalUserService.savePersonTopic(topic);
//		Person person = normalUserService.getPerson("1");
//		System.out.println(person.getTopicList().size());
	}
	
	@Test
	public void getPerson() {
		System.out.println(normalUserService.getProfile("1").getName());
	}

	@Test
	public void getFollowList() {
		System.out.println(normalUserService.getPersonFollowingList("1", 2, 1));
	}
}
