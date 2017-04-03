package cn.tata.t2s.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tata.t2s.ssm.entity.Reply;

public interface ReplyDao{
	/**
	 * 根据topic_id查询评论(一页)
	 * @param topicId 帖子唯一标识
	 * @param offset 从第几条算起
	 * @param limit 总共查询多少条
	 * @return 一堆帖子
	 */
	public List<Reply> selectReplyByTopicId(@Param("topicId") int tocpicId, 
			@Param("offset") int offset, @Param("limit") int limit);
	
	/**
	 * 根据person_id查询评论(查询自己评论,一页)
	 * @param personId 用户唯一标识
	 * @param offset 从第几条算起
	 * @param limit 总共查询多少条
	 * @return 一堆帖子
	 */
	public List<Reply> selectReplyByPersonId(@Param("personId") String personId, 
			@Param("offset") int offset, @Param("limit") int limit);
	/**
	 * 回复帖子
	 * @param 存放参数的map结构
	 * @return 执行结果
	 */
	public int insertReply(Reply reply);
	
	/**
	 * 编辑回复
	 * @param replyId 回复id唯一标识
	 * @param title 标题
	 * @param replyBody 回复内容
	 */
	public Reply updateReply(Reply reply);
	
	/**
	 * 逻辑删除回复
	 * @param replyId 回复id唯一标识
	 */
	public Reply deleteReply(long replyId);
	
	/**
	 * 业务删除回复
	 * @param replyId 回复id唯一标识
	 */
	public int setOnDelete(long replyId);
}
