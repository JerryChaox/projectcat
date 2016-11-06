package cn.tata.t2s.ssm;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.tata.t2s.ssm.dao.PersonDao;
import cn.tata.t2s.ssm.entity.Person;
import cn.tata.t2s.ssm.entity.Student;
import cn.tata.t2s.ssm.entity.Teacher;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class PersonDaoTest {
	@Autowired
	private PersonDao personDao;
	
	
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
		System.out.println(personDao.selectProfileById("2").getClass().getName());
		System.out.println(personDao.selectProfileById("3").getClass().getName());
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
//		Map<String, Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("nickName", "tata");
//		paramMap.put("personId", "4");
//		paramMap.put("phoneNumber", "18588557892");
//		//paramMap.put("pType", "s");
//		paramMap.put("pType", "t");
//		paramMap.put("school", "GDUT");
//		paramMap.put("academy", "cn");
//		//paramMap.put("grade", "2015");
//		paramMap.put("major", "计算机, 数学");\
		Student person = new Student();
		person.setPersonId("1");
		person.setNickName("goooboy");
		System.out.println(personDao.saveProfile(person));
		System.out.println(personDao.selectProfileById("1"));
//		Teacher person = new Teacher();
//		person.setPersonId("4");
//		person.setNickName("oop");
//		person.setpType("Student");
//		System.out.println(personDao.saveProfile(person));
	}

}
