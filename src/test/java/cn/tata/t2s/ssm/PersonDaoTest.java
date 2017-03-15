package cn.tata.t2s.ssm;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cn.tata.t2s.ssm.dao.PersonDao;
import cn.tata.t2s.ssm.entity.Person;
import cn.tata.t2s.ssm.entity.Person_;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
@Transactional(transactionManager = "txManager")
@Rollback(false)
public class PersonDaoTest {
	@PersistenceContext
	private EntityManager em;
	@Autowired
	private PersonDao personDao;

	
	@Test
	public void selectpTypeById() {
		String pType = personDao.selectpTypeById("1");
		System.out.println(pType);
	}
	
	@Test
	public void selectProfileById() {
		Person person = personDao.selectPerson("1");
		System.out.println(person.getFollowList().size());
	}
	
	@Test
	public void selectPerson() {
		Person person = personDao.select("1", Person.class, Person_.followList, Person_.projectList);
		System.out.println(person.getFollowList().size());
	}
	
	@Test
	public void insertFollow() {
		personDao.insertFollow("1", "20170312");
	}
	
	@Test
	public void removeFollow() {
		personDao.setFollowOnDelete("1", "20170312");
	}
	
	@Test
	public void insertVistor() {
		System.out.println("--------------------");
		int result = personDao.insertVisitor(new Person("20170315"));
		System.out.println(result);
		System.out.println("--------------------");
	}
	
	@Test
	public void saveProfile() {
	}

}
