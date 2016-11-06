package cn.tata.t2s.ssm.service.security;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cn.tata.t2s.ssm.dao.ManagerDao;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired 
	ManagerDao managerDao;
	
	
	@Override
	public UserDetails loadUserByUsername(String personId) throws UsernameNotFoundException {
		LOG.info("invoke----------/loadUserByUsername-start " + "by " + personId);
		List<GrantedAuthority> authList = getAuthorities(personId);
		if (authList == null) {
            throw new UsernameNotFoundException("User details not found with this username: " + personId);
        }
		
		User user = new User(personId, "", authList);
		LOG.info("invoke----------/loadUserByUsername-end " + "by " + personId);
		return user;
	}
	
	private List<GrantedAuthority> getAuthorities(String personId) {
		List<GrantedAuthority> authList = null;
		if(personId != null) {
			authList = managerDao.selectUserRightInfo(personId);
		}
        return authList;
    }

}
