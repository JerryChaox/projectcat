package cn.tata.t2s.ssm.service.impl;

import java.util.List;

import javax.persistence.criteria.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tata.t2s.ssm.cache.RedisCache;
import cn.tata.t2s.ssm.dao.PersonDao;
import cn.tata.t2s.ssm.dao.ReplyDao;
import cn.tata.t2s.ssm.dao.StarDao;
import cn.tata.t2s.ssm.dao.TopicDao;
import cn.tata.t2s.ssm.entity.Person;
import cn.tata.t2s.ssm.entity.Person_;
import cn.tata.t2s.ssm.entity.Reply;
import cn.tata.t2s.ssm.entity.Star;
import cn.tata.t2s.ssm.entity.Topic;
import cn.tata.t2s.ssm.enums.ResultEnum;
import cn.tata.t2s.ssm.exception.BizException;
import cn.tata.t2s.ssm.service.NormalUserService;
import cn.tata.t2s.ssm.service.util.ListParameter;
import cn.tata.t2s.ssm.service.util.PagedResult;

@Service("normalUserService")
public class NormalUserServiceImpl extends SuperServiceImpl<Person, String> implements NormalUserService {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PersonDao personDao;
	@Autowired
	private ReplyDao replyDao;
	@Autowired
	private TopicDao topicDao;
	@Autowired
	private StarDao starDao;
	@Autowired
	private RedisCache cache;

	@Override
	public Person getPerson(String personId) {
		return get(personId);
	}
	
//	@Override
//	public <T extends Person> T getSelfProfile(String personId) {
//		T person = (T) personDao.selectPerson(personId);
//		return person;
//	}

	@Override
	public PagedResult<Topic> getSelfTopicList(String personId, int pageSize, int pageNumber) {
		ListParameter<Topic, Person, String> listParameter = 
				cpm.getListParameter(cpm.getPagedResult(pageSize, pageNumber)
				,cpm.getIdPair(Person_.personId, personId), 
				Person_.topicList);
		return this.list(listParameter);
	}

	@Override
	public PagedResult<Reply> getSelfReplyList(String personId, int pageSize, int pageNumber) {
		ListParameter<Reply, Person, String> listParameter = 
				cpm.getListParameter(cpm.getPagedResult(pageSize, pageNumber)
				,cpm.getIdPair(Person_.personId, personId), 
				Person_.replyList);
		return this.list(listParameter);
	}

	@Override
	public List<Star<Topic>> getTopicStarList(String personId, int pageSize, int pageNumber) {
		List<Star<Topic>> starTopicList = starDao.selectTopicStar(personId, pageSize, pageNumber);
		return starTopicList;
	}

	@Override
	public PagedResult<Person> getFollowingList(String personId, int pageSize, int pageNumber) {
		ListParameter<Person, Person, String> listParameter = 
				cpm.getListParameter(cpm.getPagedResult(pageSize, pageNumber)
				,cpm.getIdPair(Person_.personId, personId), 
				Person_.followList);
		return this.list(listParameter);
	}

	@Override
	public void saveTopic(Topic topic, long id) {
		this.save(topic);
//		String personId = topic.getPerson().getPersonId();
//		int result = topicDao.insertTopic(topic);
//		if (result <= 0) {
//			// 创建帖子失败
//			throw new BizException(ResultEnum.DB_UPDATE_RESULT_ERROR.getMsg());
//		} else {
//			cache.deleteCacheWithPattern("getTopicList*");
//			cache.deleteCacheWithPattern("getSelfTopicList*" + personId + "*");
//			LOG.info("delete cache with key: getTopicList*");
//			LOG.info("delete cache with key: getSelfTopicList*" + personId + "*");
//			return;
//		}
	}

	@Override
	public void saveReply(Reply reply) {
		String personId = reply.getPerson().getPersonId();
		int result = replyDao.insertReply(reply);
		if (result <= 0) {
			// 创建回复失败
			throw new BizException(ResultEnum.DB_UPDATE_RESULT_ERROR.getMsg());
		} else {
			cache.deleteCacheWithPattern("getReplyList*");
			cache.deleteCacheWithPattern("getSelfReplyList*" + personId + "*");
			LOG.info("delete cache with key: getReplyList*");
			LOG.info("delete cache with key: getSelfReplyList*" + personId + "*");
			return;
		}
	}

	@Override
	public void star(String personId, int objectId, String starClass) {
		int result = starDao.insertStarItem(personId, objectId, starClass);
		if (result <= 0) {
			// star failed
			throw new BizException(ResultEnum.DB_INSERT_RESULT_ERROR.getMsg());
		}
	}

	@Override
	public void follow(String followedId, String personId) {
		int result = personDao.insertFollow(personId, followedId);
		if (result <= 0) {
			// follow失败
			throw new BizException(ResultEnum.DB_INSERT_RESULT_ERROR.getMsg());
		}
		return;
	}

	@Override
	public void refreshTopic(Topic topic) {
		int result = topicDao.updateToicById(topic);
		String personId = topic.getPerson().getPersonId();
		if (result <= 0) {
			// 更新帖子失败
			throw new BizException(ResultEnum.DB_UPDATE_RESULT_ERROR.getMsg());
		} else {
			cache.deleteCacheWithPattern("getTopicList*");
			cache.deleteCacheWithPattern("getSelfTopicList*" + personId + "*");
			LOG.info("delete cache with key: getTopicList*");
			LOG.info("delete cache with key: getSelfTopicList*" + personId + "*");
			return;
		}
	}

	@Override
	public void refreshReply(Reply reply) {
		String personId = reply.getPerson().getPersonId();
		int result = replyDao.updateReplyById(reply);
		if (result <= 0) {
			// 更新回复失败
			throw new BizException(ResultEnum.DB_UPDATE_RESULT_ERROR.getMsg());
		} else {
			cache.deleteCacheWithPattern("getReplyList*");
			cache.deleteCacheWithPattern("getSelfReplyList*" + personId + "*");
			LOG.info("delete cache with key: getReplyList*");
			LOG.info("delete cache with key: getSelfReplyList*" + personId + "*");
			return;
		}
	}

	@Override
	public void dropTopic(int topicId, String personId) {
		int first_result = topicDao.setOnDelete(topicId);
		if (first_result <= 0) {
			// 删除帖子失败
			throw new BizException(ResultEnum.DB_UPDATE_RESULT_ERROR.getMsg());
		} else {
			int second_result = topicDao.deleteTopicById(topicId);
			if (second_result <= 0) {
				// 删除帖子失败
				throw new BizException(ResultEnum.DB_UPDATE_RESULT_ERROR.getMsg());
			}
			cache.deleteCacheWithPattern("getTopicList*");
			cache.deleteCacheWithPattern("getSelfTopicList*" + personId + "*");
			LOG.info("delete cache with key: getTopicList*");
			LOG.info("delete cache with key: getSelfTopicList*" + personId + "*");
			return;
		}
	}

	@Override
	public void dropReply(long replyId, String personId) {
		int result = replyDao.setOnDelete(replyId);
		if (result <= 0) {
			// 删除帖子失败
			throw new BizException(ResultEnum.DB_UPDATE_RESULT_ERROR.getMsg());
		}
		cache.deleteCacheWithPattern("getReplyList*");
		cache.deleteCacheWithPattern("getSelfReplyList*" + personId + "*");
		LOG.info("delete cache with key: getReplyList*");
		LOG.info("delete cache with key: getSelfReplyList*" + personId + "*");
		return;
	}

	@Override
	public void unStar(long starId, String personId, String starClass) {
		int result = starDao.setOnDelete(starId, starClass);
		if (result <= 0) {
			// 删除帖子失败
			throw new BizException(ResultEnum.DB_UPDATE_RESULT_ERROR.getMsg());
		}
		return;
	}

	@Override
	public void unfollow(String followedId, String personId) {
		int result = personDao.setFollowOnDelete(personId, followedId);
		if (result <= 0) {
			// follow失败
			throw new BizException(ResultEnum.DB_SET_ON_DELETE_ERROR.getMsg());
		}
		return;
	}



}
