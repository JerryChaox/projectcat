package cn.tata.t2s.ssm.service;

import java.util.List;

import cn.tata.t2s.ssm.entity.Person;
import cn.tata.t2s.ssm.entity.PersonPair;
import cn.tata.t2s.ssm.entity.Profile;
import cn.tata.t2s.ssm.entity.Reply;
import cn.tata.t2s.ssm.entity.Star;
import cn.tata.t2s.ssm.entity.Topic;
import cn.tata.t2s.ssm.service.util.PagedResult;

/**
 * NORMAL AUTH
 * @author JerryChaox
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

	
	public PagedResult<Topic> getPersonTopicList
	(String personId, int pageSize, int pageNumber);

	public PagedResult<Reply> getPersonReplyList
	(String personId, int pageSize, int pageNumber);
	
	public PagedResult<PersonPair> getPersonPairList
	(String followOrFan, String personId, int pageSize, int pageNumber);
	
	public List<Star<Topic>> getPersonTopicStarList
	(String personId, int pageSize, int pageNumber);
	
	public Topic savePersonTopic(Topic topic);

	// 管理员
	// public void saveTopicState(int projectId, String state_name);

	public Reply savePersonReply(Reply reply);
	
	public void savePersonPair(String fromPersonId, String toPersonId);
	
	public void savePersonStar(String personId, int objectId, String starClass);

	public Topic refreshPersonTopic(Topic topic);
	
	public Reply refreshPersonReply(Reply reply);
	
	public void removePersonReply(long replyId, String personId);
	
	public void removePersonTopic(long topicId, String personId);
	
	public void removePersonStar(long starId, String personId, String starClass);
	
	public void removePersonPair(String fromPersonId, String toPersonId);
}
