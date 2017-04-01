package cn.tata.t2s.ssm.service;

import java.util.List;

import cn.tata.t2s.ssm.entity.Person;
import cn.tata.t2s.ssm.entity.Reply;
import cn.tata.t2s.ssm.entity.Star;
import cn.tata.t2s.ssm.entity.Topic;
import cn.tata.t2s.ssm.service.util.PagedResult;

public interface NormalUserService {
	
	/**
	 * get a user
	 * @param personId
	 * @return
	 */
	public Person getPerson(String personId);
	
	/**
	 * 绑定了身份的用户
	 * replaced by getPerson
	 * @param personId
	 * @param offset
	 * @param limit
	 * @return
	 */
//	public <T extends Person> T getSelfProfile(String personId);

	
	// 绑定了身份的用户
	public PagedResult<Topic> getSelfTopicList(String personId, int pageSize, int pageNumber);

	// 绑定了身份的用户
	public PagedResult<Reply> getSelfReplyList(String personId, int pageSize, int pageNumber);

	// 绑定了身份的用户
	public void saveTopic(Topic topic, long id);

	// 绑定了身份的用户
	public void refreshTopic(Topic topic);

	// 管理员
	// public void saveTopicState(int projectId, String state_name);

	// 绑定了身份的用户
	public void dropTopic(int topicId, String personId);

	// 绑定了身份的用户
	public void saveReply(Reply reply);

	// 绑定了身份的用户
	public void refreshReply(Reply reply);
	
	// 绑定了身份的用户
	public void dropReply(long replyId, String personId);
	
	public void star(String personId, int objectId, String starClass);
	
	public List<Star<Topic>> getTopicStarList(String personId, int pageSize, int pageNumber);

	public void unStar(long starId, String personId, String starClass);
	
	public void follow(String followedId, String personId);
	
	public PagedResult<Person> getFollowingList(String personId, int pageSize, int pageNumber);

	public void unfollow(String followedId, String personId);
}
