package cn.tata.t2s.ssm.service;

import java.util.List;

import cn.tata.t2s.ssm.entity.Person;
import cn.tata.t2s.ssm.entity.Profile;
import cn.tata.t2s.ssm.entity.Reply;
import cn.tata.t2s.ssm.entity.Star;
import cn.tata.t2s.ssm.entity.Topic;
import cn.tata.t2s.ssm.service.util.PagedResult;

/**
 * NORMAL AUTH
 * @author tata
 *
 */
public interface NormalUserService {
	
	public Person getPerson(String personId);
	
	/**
	 * get a user profile
	 * @param personId
	 * @return
	 */
	public Profile getProfile(String personId);
	
	/**
	 * 绑定了身份的用户
	 * replaced by getPerson
	 * @param personId
	 * @param offset
	 * @param limit
	 * @return
	 */
//	public <T extends Person> T getSelfProfile(String personId);

	
	public PagedResult<Topic> getPersonTopicList(String personId, int pageSize, int pageNumber);

	public PagedResult<Reply> getPersonReplyList(String personId, int pageSize, int pageNumber);
	
	public PagedResult<Person> getPersonFollowingList(String personId, int pageSize, int pageNumber);

	public void savePersonTopic(Topic topic);

	public Topic refreshPersonTopic(Topic topic);

	// 管理员
	// public void saveTopicState(int projectId, String state_name);

	public void removePersonTopic(long topicId, String personId);

	public void savePersonReply(Reply reply);

	public Reply refreshPersonReply(Reply reply);
	
	public void removePersonReply(long replyId, String personId);
	
	public void savePersonStar(String personId, int objectId, String starClass);
	
	public List<Star<Topic>> getPersonTopicStarList(String personId, int pageSize, int pageNumber);

	public void removePersonStar(long starId, String personId, String starClass);
	
	public void savePersonFollow(String followedId, String personId);
	
	public void removePesonFollow(String followedId, String personId);
}
