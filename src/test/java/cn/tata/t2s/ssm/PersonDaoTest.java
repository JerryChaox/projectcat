package cn.tata.t2s.ssm;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cn.tata.t2s.ssm.dao.PersonDao;
import cn.tata.t2s.ssm.entity.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
@Transactional(transactionManager = "txManager")
public class PersonDaoTest {
	@PersistenceContext
	private EntityManager em;
	@Autowired
	private PersonDao personDao;
	@Before
	public void init() {
		Session session = em.unwrap(org.hibernate.Session.class);
	}

	
	@Test
	public void selectpTypeById() {
		String pType = personDao.selectpTypeById("1");
		System.out.println(pType);
	}
	
	@Test
	public void selectProfileById() {
		Person person = personDao.selectProfileById("1");
		//Student student = (Student)personDao.selectProfileById("2");
		//Teacher teacher = (Teacher)personDao.selectProfileById("3");
		System.out.println(person);
	}
	
	@Test
	public void selectFollowing(){
		System.out.println(personDao.selectFollowing("1",1,1));
	}
	
	@Test
	public void insertVistor() {
		System.out.println("--------------------");
		int result = personDao.insertVisitor("sadad54561");
		System.out.println(result);
		System.out.println("--------------------");
	}
	
	@Test
	public void saveProfile() {
	}

}
