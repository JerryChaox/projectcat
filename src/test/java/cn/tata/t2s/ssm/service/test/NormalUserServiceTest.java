package cn.tata.t2s.ssm.service.test;

import java.time.LocalDateTime;

import org.joda.time.LocalDate;
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
	public void saveTopic() {
		Topic topic = new Topic();
		topic.setOnDelete(false);
		topic.setPerson(normalUserService.getPerson("1"));
		topic.setCreateTime(LocalDateTime.now());
		topic.setUpdateTime(topic.getCreateTime());
		normalUserService.saveTopic(topic, topic.getTopicId());
	}
	
	@Test
	public void getPerson() {
		System.out.println(normalUserService.getPerson("1").getName());
	}

	@Test
	public void getFollowList() {
		System.out.println(normalUserService.getFollowingList("1", 2, 1));
	}
}
