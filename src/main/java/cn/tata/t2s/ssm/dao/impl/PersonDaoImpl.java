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
public class PersonDaoImpl implements PersonDao{
	@PersistenceContext
	private EntityManager entityManager;
	
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
	public <T extends Person> T selectPerson(String personId) {
		T person =  (T) entityManager.find(Person.class,"1");
		return person;
	}

	@Override
	public <T extends Person> T selectPerson(String personId, Attribute<T, ?>... attribute) {
		EntityGraph<T> graph = (EntityGraph<T>) entityManager.createEntityGraph(Person.class);
		graph.addAttributeNodes(attribute);

		T person = (T) entityManager.find(
				Person.class,
				personId,
			    Collections.singletonMap("javax.persistence.fetchgraph",graph)
			);
		
		return person;
	}
	
	
	@Override
	public Set<Person> selectFollowing(String personId, int offset, int limit) {
		EntityGraph<Person> graph = entityManager.createEntityGraph(Person.class);
		graph.addAttributeNodes(Person_.followList);
		
		Person person = entityManager.find(
				Person.class,
				personId,
			    Collections.singletonMap("javax.persistence.fetchgraph",graph)
			);
		System.out.println(person);
		return person.getFollowList();
	}			        

	@Override
	public List<ProjectApplication> selectProjectApplication(int offset, int limit) {
		
		return null;
	}

	@Override
	public int insertVisitor(String personId) {
		entityManager.persist(new Person(personId));
		return 1;
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
	public <T extends Person> int saveProfile(T person) {
		entityManager.merge(person);
		return 1;
	}

	@Override
	public int setOnDelete(String personId) {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
