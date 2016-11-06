package cn.tata.t2s.ssm.service;

import cn.tata.t2s.ssm.entity.Person;
import cn.tata.t2s.ssm.entity.Project;


public interface AnonymousUserService {
	
	// 全部
	public void registerOpenId(String personId);

	// 全部
	public void bindingProfile(Person person);
}
