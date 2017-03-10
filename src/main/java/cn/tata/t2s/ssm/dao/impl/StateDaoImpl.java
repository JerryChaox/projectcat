package cn.tata.t2s.ssm.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.tata.t2s.ssm.dao.StarDao;
import cn.tata.t2s.ssm.dao.StateDao;
import cn.tata.t2s.ssm.entity.Star;
import cn.tata.t2s.ssm.entity.Topic;

@Repository
@Transactional
public class StateDaoImpl implements StateDao {

	@Override
	public String selectStateNameById(int state_type_id, String fromTable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectIdByStateName(String stateName, String fromTable) {
		// TODO Auto-generated method stub
		return 0;
	}


}
