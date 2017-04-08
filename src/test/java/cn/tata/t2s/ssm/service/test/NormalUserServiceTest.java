package cn.tata.t2s.ssm.service.test;

import java.util.Iterator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cn.tata.t2s.ssm.entity.Person;
import cn.tata.t2s.ssm.entity.PersonPair;
import cn.tata.t2s.ssm.entity.Topic;
import cn.tata.t2s.ssm.service.NormalUserService;
import cn.tata.t2s.ssm.service.TopicService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"
	, "classpath:spring/spring-service.xml"
	, "classpath:spring/spring-redis.xml"})
@Transactional(transactionManager = "txManager")
@Rollback(false)
public class NormalUserServiceTest {
	
	@Autowired
	NormalUserService normalUserService;
	@Autowired
	TopicService topicService;
	
	@Test
	public void savePersonTopic() {
		Topic topic = new Topic();
		topic.setPerson(new Person("1"));
		normalUserService.savePersonTopic(topic);
	}
	
	@Test
	public void refreshPersonTopic() {
		Person person = normalUserService.getPerson("1");
		Iterator<Topic> it = person.getTopicSet().iterator();
		Topic firstTopic = it.next();
		Topic topic = topicService.getTopic(1);
		topic.setHits(100);
		//normalUserService.refreshPersonTopic(topic);
		System.out.println(firstTopic == topic);
	}
	
	@Test
	public void getPerson() {
		System.out.println(normalUserService.getProfile("1").getName());
	}

	@Test
	public void savePersonFollowAndFan() {
		normalUserService.savePersonPair("1", "3");
	}
	
	@Test
	public void removePersonFollow() {
		normalUserService.removePersonPair("1", "2");
	}
	
	@Test
	public void getPersonPairList() {
		System.out.println(normalUserService.getPersonPairList(PersonPair.FOLLOW, "1", 2, 1));
	}
}
