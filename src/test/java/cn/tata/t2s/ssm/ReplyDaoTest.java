package cn.tata.t2s.ssm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.tata.t2s.ssm.dao.ReplyDao;
import cn.tata.t2s.ssm.entity.Person;
import cn.tata.t2s.ssm.entity.Reply;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class ReplyDaoTest {
	@Autowired
	ReplyDao replyDao;
	List<Reply> replyList;
	Map<String,Object> paramMap = new HashMap<String,Object>();
	
	@Test
	public void selectReplyByTopicId() {
		replyList = replyDao.selectReplyByTopicId(1, 0, 50);
		System.out.println(replyList.getClass());
		for(Reply reply: replyList) {
			System.out.println(reply);
		}
		
	}
	
	@Test
	public void selectReplyByPersonId() {
		replyList = replyDao.selectReplyByPersonId("1", 0, 50);
		for(Reply reply: replyList) {
			System.out.println(reply);
		}
		
	}

	@Test
	public void insertReply() {
//		paramMap.put("topicId", 2);
//		paramMap.put("title", "title2");
//		paramMap.put("replyBody", "great score");
//		paramMap.put("personId", "2");
		Person person = new Person();
		person.setPersonId("2");
		Reply reply = new Reply();
		reply.setTopicId(3);
		reply.setTitle("123123");
		reply.setPerson(person);
		System.out.println(replyDao.insertReply(reply));
		System.out.println(reply.getReplyId());
		
	}
	
	@Test
	public void updateReplyById() {
		Reply reply = new Reply();
		reply.setReplyId(4);
		reply.setTitle("mmmm");
		reply.setReplyBody("bbbb");
		System.out.println(replyDao.updateReplyById(reply));
	}

}
