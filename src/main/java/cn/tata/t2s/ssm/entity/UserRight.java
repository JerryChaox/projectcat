package cn.tata.t2s.ssm.entity;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

public class UserRight {
	private String personId;
	private List<GrantedAuthority> authList;

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public List<GrantedAuthority> getAuthList() {
		return authList;
	}

	public void setAuthList(List<GrantedAuthority> authList) {
		this.authList = authList;
	}

	@Override
	public String toString() {
		return String.format("UserRight  [\n\tpersonId=%s\n]", personId);
	}

	

	
	
	
}
