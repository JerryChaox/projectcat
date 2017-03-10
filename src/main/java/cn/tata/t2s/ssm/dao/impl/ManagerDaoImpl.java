package cn.tata.t2s.ssm.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.tata.t2s.ssm.dao.ManagerDao;
import cn.tata.t2s.ssm.entity.Right;

@Repository
@Transactional
public class ManagerDaoImpl implements ManagerDao {

	@Override
	public List<HashMap<String, Object>> selectGroupInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> selectAllManagement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<Integer, Integer> selectGroupRightInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GrantedAuthority> selectUserRightInfo(String personId) {
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
		authList.add(new SimpleGrantedAuthority("test"));
		return authList;
	}

	@Override
	public int insertUserRightById(String personId, List<Right> rightList) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateUserRightById(String personId, List<Right> rightList) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteSingleUserRightById(String personId, String groupId, String classId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
