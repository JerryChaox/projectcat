package cn.tata.t2s.ssm.service.test;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cn.tata.t2s.ssm.entity.Person;
import cn.tata.t2s.ssm.entity.Profile;
import cn.tata.t2s.ssm.service.AnonymousUserService;
import cn.tata.t2s.ssm.service.NormalUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"
	, "classpath:spring/spring-service.xml"
	, "classpath:spring/spring-redis.xml"})
@Transactional(transactionManager = "txManager")
@Rollback(false)
public class AnonymousUserServiceTest {

	@Autowired
	AnonymousUserService anonymousUserService;
	@Autowired
	NormalUserService normalUserService;
	
	
	@Test
	public void savePerson() {
		anonymousUserService.savePerson("3");
	}
	
	@Test
	public void refreshPerson() {
		Person person = new Person("1");
		Profile profile = new Profile();
		profile.setAcademy("cs");
		profile.setSchool("GDUT");
		profile.setMajor(new ArrayList<String>(Arrays.asList("nodejs","Python","Java","C")));
		person.setProfile(profile);
		
		person = anonymousUserService.refreshPersonProfile(person);
		System.out.println(person.getProfile());
		
	}
}
