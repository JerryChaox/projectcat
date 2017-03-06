package cn.tata.t2s.ssm.dao.impl;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.tata.t2s.ssm.dao.PersonDao;
import cn.tata.t2s.ssm.entity.Person;
import cn.tata.t2s.ssm.entity.Person_;
import cn.tata.t2s.ssm.entity.ProjectApplication;

@Repository
@Transactional
public class PersonDaoImpl implements PersonDao{
	@PersistenceContext
	private EntityManager entityManager;
	
	public void init() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Person> criteria = builder.createQuery( Person.class );
		Root<Person> root = criteria.from( Person.class );
		criteria.where( builder.equal( root.get( Person_.name ), "John Doe" ) );
	}
	
	@Override
	public String selectpTypeById(String personId) {
		return null;
	}

	@Override
	public <T extends Person> T selectProfileById(String personId) {
		T person =  (T) entityManager.find(Person.class,"1");
		return person;
	}

	@Override
	public List<Person> selectFollowing(String personId, int offset, int limit) {
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertVisitor(String personId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertFollow(String personId, String followedId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertProjectApplication(String personId, int projectId, String reason, String targetStateName) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setFollowOnDelete(String personId, String followedId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setApplicationOnAdmit(int applicationId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <T extends Person> int saveProfile(T person) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setOnDelete(String personId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
