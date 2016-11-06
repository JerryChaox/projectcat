package cn.tata.t2s.ssm;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.tata.t2s.ssm.dao.ManagerDao;
import cn.tata.t2s.ssm.util.HttpDeal;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class ManagerDaoTest {
	@Autowired
	ManagerDao managerDao;
	// List<UserRight> userRightList;
	List<HashMap<String, Object>> list1;

	@Test
	public void selectGroupInfo() {
		list1 = managerDao.selectGroupInfo();
		for (HashMap<String, Object> map : list1) {
			for (Entry<String, Object> entry : map.entrySet()) {
				System.out.println(entry.getKey().getClass());
				System.out.println(entry.getValue().getClass());
			}
		}
	}

	@Test
	public void selectManagementInfo() {
		String uri = "http://open.weixin.qq.com";
		HashMap<String,String> params = new HashMap<String,String>();
		params.put("appid", "wx55588b5b1cf20a27");
		params.put("redirect_uri", "http://www.qq.com");
		params.put("response_type", "code");
		params.put("scope", "snsapi_base");
		params.put("state","1#wechat_redirect");
		HttpDeal.post(uri, params);
	}

	@Test
	public void selectGroupRightInfo() {
	}

	@Test
	public void insertUserRightById() {
	}

	@Test
	public void updateUserRightById() {
	}

	@Test
	public void deleteSingleUserRightById() {
	}

	@Test
	public void selectUserRightInfo() {
		List<GrantedAuthority> authList = managerDao.selectUserRightInfo("1");
		
		System.out.println(authList);
	}
}
