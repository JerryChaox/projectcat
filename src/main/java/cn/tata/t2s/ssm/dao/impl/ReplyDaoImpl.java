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
	public int insertReply(Reply reply) {
		this.insert(reply);
		return 0;
	}

	@Override
	public Reply updateReply(Reply reply) {
		return this.update(reply);
	}

	@Override
	public Reply deleteReply(long replyId) {
		Reply entity = this.select(replyId);
		entity.getCommonInfo().setOnDelete(true);
		return this.update(entity);
	}

	@Override
	public int setOnDelete(long replyId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
