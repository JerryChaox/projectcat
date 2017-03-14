package cn.tata.t2s.ssm.dao.impl;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;

import org.springframework.stereotype.Repository;

import cn.tata.t2s.ssm.dao.PersonDao;
import cn.tata.t2s.ssm.entity.Person;
import cn.tata.t2s.ssm.entity.Person_;
import cn.tata.t2s.ssm.entity.ProjectApplication;

@Repository
public class PersonDaoImpl extends BaseDaoImpl implements PersonDao{
	
	public void init() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Person> criteria = builder.createQuery( Person.class );
		Root<Person> personRoot = criteria.from( Person.class );
		criteria.where( builder.equal( personRoot.get( Person_.name ), "John Doe" ) );
	}
	
	@Override
	public String selectpTypeById(String personId) {
		return null;
	}

	@Override
	public Person selectPerson(String personId) {
		Person person =  this.select(Person.class, personId);
		return person;
	}

	@Override
	public Person selectPerson(String personId, Attribute<Person, ?>... attribute) {
		return this.select(personId, Person.class, attribute);
	}
				        

	@Override
	public List<ProjectApplication> selectProjectApplication(int offset, int limit) {
		
		return null;
	}

	@Override
	public int insertVisitor(Person person) {
		return this.insert(person);
	}

	@Override
	public int insertFollow(String personId, String followedId) {
		Person person = entityManager.find(Person.class, personId);
		Person following = entityManager.find(Person.class, followedId);
		person.getFollowList().add(following);
		//entityManager.merge(person);
		return 1;
	}

	@Override
	public int insertProjectApplication(String personId, int projectId, String reason, String targetStateName) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setFollowOnDelete(String personId, String followedId) {
		Person person = entityManager.find(Person.class, personId);
		person.getFollowList().remove(new Person(followedId));
		return 1;
	}

	@Override
	public int setApplicationOnAdmit(int applicationId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Person savePerson(Person person) {
		this.update(person);
		return person;
	}

	@Override
	public int setOnDelete(String personId) {
		// TODO Auto-generated method stub
		return 0;
	}

	

	

}
