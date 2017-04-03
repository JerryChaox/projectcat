package cn.tata.t2s.ssm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;

import org.springframework.stereotype.Repository;

import cn.tata.t2s.ssm.dao.PersonDao;
import cn.tata.t2s.ssm.entity.Person;
import cn.tata.t2s.ssm.entity.ProjectApplication;

@Repository
public class PersonDaoImpl extends SuperDaoImpl<Person, String> implements PersonDao {

	@Override
	public List<ProjectApplication> selectProjectApplication(int offset, int limit) {

		return null;
	}

	@Override
	public Person selectPerson(String personId) {
		return select(personId);
	}
	
	@Override
	public int insertPerson(Person person) {
		return insert(person);
	}

//	@Override
//	public int insertFollow(String personId, String followedId) {
//		Person person = entityManager.find(Person.class, personId);
//		Person following = entityManager.find(Person.class, followedId);
//		person.getFollowSet().add(following);
//		// entityManager.merge(person);
//		return 1;
//	}

	@Override
	public int insertProjectApplication(String personId, int projectId, String reason, String targetStateName) {
		// TODO Auto-generated method stub
		return 0;
	}

//	@Override
//	public int deleteFollow(String personId, String followedId) {
//		Person person = entityManager.find(Person.class, personId);
//		person.getFollowSet().remove(new Person(followedId));
//		return 1;
//	}

	@Override
	public int setApplicationOnAdmit(int applicationId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Person savePerson(Person person) {
		return update(person);
	}

	@Override
	public int setOnDelete(String personId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
