package cn.tata.t2s.ssm.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tata.t2s.ssm.cache.RedisCache;
import cn.tata.t2s.ssm.dao.ReplyDao;
import cn.tata.t2s.ssm.dao.TopicDao;
import cn.tata.t2s.ssm.entity.Reply;
import cn.tata.t2s.ssm.entity.Topic;
import cn.tata.t2s.ssm.service.TopicService;

@Service
public class TopicServiceImpl implements TopicService{
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private RedisCache cache;
	@Autowired
	private TopicDao topicDao;
	@Autowired
	private ReplyDao replyDao;
	
	@Override
	public List<Topic> getAllTopicList(String className, int offset, int limit) {
		String cacheKey = RedisCache.CAHCENAME + "|getTopicList|" + offset + "|" + limit;
		List<Topic> resultCache = cache.getListCache(cacheKey, Topic.class);
		if (resultCache != null) {
			LOG.info("get cache with key:" + cacheKey);
			return resultCache;
		} else {
			resultCache = topicDao.selectAllTopic(className, offset, limit);
			cache.putCache(cacheKey, resultCache);
			LOG.info("put cache with key:" + cacheKey);
			return resultCache;
		}
	}

	@Override
	public Topic getTopicByTopicId(int topicId) {
		return topicDao.selectTopicById(topicId);
	}

	@Override
	public List<Topic> dynamicGetTopicList(String className, 
			String[] school, String[] academy, String fluzzyName, 
			int offset, int limit) {
		return topicDao.dynamicSelectTopic(className, school, academy, fluzzyName, offset, limit);
	}

	@Override
	public List<Reply> getTopicReplyList(int topicId, int offset, int limit) {
		String cacheKey = RedisCache.CAHCENAME + "|getTopicReplyList|" + topicId + "|" + offset + "|" + limit;
		List<Reply> resultCache = null;
		if (resultCache != null) {
			LOG.info("get cache with key:" + cacheKey);
			return resultCache;
		} else {
			resultCache = replyDao.selectReplyByTopicId(topicId, offset, limit);
			cache.putCache(cacheKey, resultCache);
			LOG.info("put cache with key:" + cacheKey);
			return resultCache;
		}
	}
}
