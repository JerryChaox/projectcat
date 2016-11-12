package cn.tata.t2s.ssm.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import cn.tata.t2s.ssm.entity.Topic;

public interface TopicDao {
	
	/**
	 * 
	 */
	public Topic selectTopicById(int topicId);
	
	/**
	 * 查询所有帖子(一个页面)
	 * @param offset 从第几条算起
	 * @param limit 总共查询多少条
	 * @return 一堆帖子
	 */
	public List<Topic> selectAllTopic(
			@Param("className") String className, 
			@Param("offset") int offset, 
			@Param("limit") int limit);
	
	/**
	 * dynamic查询帖子(一个页面)
	 * @param offset 从第几条算起
	 * @param limit 总共查询多少条
	 * @return 一堆帖子
	 */
	public List<Topic> dynamicSelectTopic(
			@Param("className") String className, 
			@Param("school") String[] school, 
			@Param("academy") String[] academy, 
			@Param("fluzzyName") String fluzzyName,
			@Param("offset") int offset, @Param("limit") int limit);
	
	/**
	 * 根据person_id查询所有帖子
	 * @param personId 用户唯一标识
	 * @param offset 从第几条算起
	 * @param limit 总共查询多少条 
	 * @return 一堆帖子
	 */
	public List<Topic> selectTopicByPersonId(@Param("personId") String personId, 
			@Param("offset") int offset, @Param("limit") int limit);
	
	
	public int selectTopicCountToday(String personId);
	
	/**
	 * 添加帖子
	 * @param 存放参数的map结构
	 * @return 执行结果
	 */
	public int insertTopic(Topic topic);
	
	/**
	 * 添加帖子状态
	 * @param topicId 帖子唯一标识
	 * @param topicStateTypeId 帖子状态id标识
	 * @return 执行结果
	 */
	public int insertTopicStateById(@Param("topicId") int topicId, 
			@Param("topicStateTypeId") int topicStateTypeId);
	
	/**
	 * 编辑帖子
	 * @param 存放参数的map结构(必须含有topicId)
	 * @return 执行结果
	 */
	public int updateToicById(Topic topic);
	
	/**
	 * 物理删除帖子状态
	 * @param topicId 帖子唯一标识
	 * @return 执行结果
	 */
	public int deleteTopicStateById(int topicId);
	
	/**
	 * 物理删除帖子
	 * @param topicId 帖子唯一标识
	 * @return 执行结果
	 */
	public int deleteTopicById(int topicId);
	
	/**
	 * 修改帖子状态
	 * @param topicId 帖子唯一标识
	 * @param topicStateTypeId 帖子状态id标识
	 * @return 执行结果
	 */
	public int updateTopicStateById(@Param("topicId") int topicId, 
			@Param("topicStateTypeId") int topicStateTypeId);
	
	/**
	 * 物理删除帖子
	 * @param topicId 帖子唯一标识
	 * @return 执行结果
	 */
	public int setOnDelete(int topicId);
}
