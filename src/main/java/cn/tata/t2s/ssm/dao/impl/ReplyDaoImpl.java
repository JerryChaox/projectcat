package cn.tata.t2s.ssm.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.tata.t2s.ssm.dao.ReplyDao;
import cn.tata.t2s.ssm.entity.Reply;

@Repository
@Transactional
public class ReplyDaoImpl extends SuperDaoImpl<Reply, Long> implements ReplyDao {

	@Override
	public List<Reply> selectReplyByTopicId(int tocpicId, int offset, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reply> selectReplyByPersonId(String personId, int offset, int limit) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Reply selectReply(long replyId) {
		return select(replyId);
	}

	@Override
	public int insertReply(Reply reply) {
		this.insert(reply);
		return 0;
	}

	@Override
	public Reply updateReply(Reply reply) {
		return update(reply);
	}

	@Override
	public int deleteReply(long replyId) {
		Reply entity = this.select(replyId);
		return delete(entity);
	}

	@Override
	public int setOnDelete(long replyId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
