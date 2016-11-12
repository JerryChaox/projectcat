package cn.tata.t2s.ssm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.tata.t2s.ssm.dao.TopicDao;
import cn.tata.t2s.ssm.entity.Person;
import cn.tata.t2s.ssm.entity.Topic;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class TopicDaoTest {
	@Autowired
	TopicDao topicDao;
	private List<Topic> topicList;
	private Map<String, Object> paramMap = new HashMap<String, Object>();

	@Test
	public void selectAllTopic() {
		topicList = topicDao.selectAllTopic("tech_module",0, 50);
		for (Topic topic : topicList) {
			System.out.println(topic);
		}
	}

	@Test
	public void dynamicSelectTopic() {
		String[] school = {"GDUT","HNLG"};
		String[] academy = {"zdh","cn"};
		topicList = topicDao.dynamicSelectTopic("tech_module", school, academy, "t2s", 0, 50);
		System.out.print(topicList);
	}
	
	@Test
	public void selectTopicById() {
		System.out.println(topicDao.selectTopicById(1));
	}

	@Test
	public void selectTopicByPersonId() {
		topicList = topicDao.selectTopicByPersonId("1", 0, 50);
		for (Topic topic : topicList) {
			System.out.println(topic);
		}
	}

	@Test
	public void insertTopic() {
//		paramMap.put("topicId", 123);
//		paramMap.put("title", "t2s_ssm launching");
//		paramMap.put("personId", "1");
//		paramMap.put("classId", 1);
//		paramMap.put("body", "这是一个充满****的世界");
		Person person = new Person();
		person.setPersonId("2");
		Topic topic = new Topic();
		topic.setClassName("1");
		topic.setTitle("456465");
		topic.setBody("123123");
		System.out.println(topicDao.insertTopic(topic) + "------------");
		System.out.println(topic.getTopicId());
	}

	@Test
	public void insertTopicStateById() {
		System.out.println(topicDao.insertTopicStateById(123, 4));
	}

	@Test
	public void updateToicById() {
//		paramMap.put("body", "cetcetcetfail");
//		paramMap.put("title", "cet failed");
//		paramMap.put("topicId", 123);
		Topic topic = new Topic();
		topic.setBody("okokok");
		topic.setTitle("coco");
		topic.setTopicId(1);
		System.out.println(topicDao.updateToicById(topic));
	}

	@Test
	public void updateTopicStateById() {
		int	topicId = 123;
		int topicStateTypeId = 1;
		System.out.println(topicDao.updateTopicStateById(topicId, topicStateTypeId));
	}

	@Test
	public void deleteTopicById() {
		int topicId = 123;
		System.out.println(topicDao.deleteTopicStateById(topicId));
		System.out.println(topicDao.deleteTopicById(topicId));
	}
}
