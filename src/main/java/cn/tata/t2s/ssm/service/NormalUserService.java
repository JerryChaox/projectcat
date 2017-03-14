package cn.tata.t2s.ssm.service;

import java.util.List;

import cn.tata.t2s.ssm.entity.Person;
import cn.tata.t2s.ssm.entity.Reply;
import cn.tata.t2s.ssm.entity.Star;
import cn.tata.t2s.ssm.entity.Topic;

public interface NormalUserService {

	public Person getPerson(String personId);
	
	// 绑定了身份的用户
	public <T extends Person> T getSelfProfile(String personId);

	// 绑定了身份的用户
	public List<Topic> getSelfTopicList(String personId, int offset, int limit);

	// 绑定了身份的用户
	public List<Reply> getSelfReplyList(String personId, int offset, int limit);

	// 绑定了身份的用户
	public void createTopic(Topic topic);

	// 绑定了身份的用户
	public void saveTopic(Topic topic);

	// 管理员
	// public void saveTopicState(int projectId, String state_name);

	// 绑定了身份的用户
	public void dropTopic(int topicId, String personId);

	// 绑定了身份的用户
	public void createReply(Reply reply);

	// 绑定了身份的用户
	public void saveReply(Reply reply);
	
	// 绑定了身份的用户
	public void dropReply(long replyId, String personId);
	
	public void star(String personId, int objectId, String starClass);
	
	public List<Star<Topic>> getTopicStarList(String personId, int offset, int limit);

	public void unStar(long starId, String personId, String starClass);
	
	public void follow(String followedId, String personId);
	
	public List<Person> getFollowingList(String personId, int offset, int limit);

	public void unfollow(String followedId, String personId);
}
