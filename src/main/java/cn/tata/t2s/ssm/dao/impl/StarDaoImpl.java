package cn.tata.t2s.ssm.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.tata.t2s.ssm.dao.StarDao;
import cn.tata.t2s.ssm.entity.Star;
import cn.tata.t2s.ssm.entity.Topic;

@Repository
@Transactional
public class StarDaoImpl implements StarDao {

	@Override
	public List<Star<Topic>> selectTopicStar(String personId, int offset, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertStarItem(String personId, int objectId, String starClass) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setOnDelete(long starId, String starClass) {
		// TODO Auto-generated method stub
		return 0;
	}

}
