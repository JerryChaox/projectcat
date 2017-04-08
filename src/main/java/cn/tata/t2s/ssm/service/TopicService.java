package cn.tata.t2s.ssm.service;

import java.util.List;

import cn.tata.t2s.ssm.entity.Reply;
import cn.tata.t2s.ssm.entity.Topic;

public interface TopicService {
	
	
	public Topic getTopic(int topicId);
	
	public List<Topic> getAllTopicList(String className, int offset, int limit);
	
	public List<Topic> dynamicGetTopicList(String className, 
			String[] school, String[] academy, String fluzzyName, 
			int offset, int limit);
	
	public List<Reply> getTopicReplyList(int topicId, int offset, int limit);
	
}
