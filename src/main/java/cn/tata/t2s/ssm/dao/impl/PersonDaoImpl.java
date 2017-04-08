package cn.tata.t2s.ssm.dao.impl;

import java.util.Collections;
import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Repository;

import cn.tata.t2s.ssm.dao.PersonDao;
import cn.tata.t2s.ssm.entity.CompositePerson_;
import cn.tata.t2s.ssm.entity.Person;
import cn.tata.t2s.ssm.entity.PersonPair;
import cn.tata.t2s.ssm.entity.PersonPair_;
import cn.tata.t2s.ssm.entity.Person_;
import cn.tata.t2s.ssm.entity.ProjectApplication;
import cn.tata.t2s.ssm.service.util.CriteriaQueryHelper;
import cn.tata.t2s.ssm.service.util.ListParameter;
import cn.tata.t2s.ssm.service.util.PagedResult;

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
	public Person updatePerson(Person person) {
		return update(person);
	}

	@Override
	public int setOnDelete(String personId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PagedResult<PersonPair> selectPersonPersonPair(String followOrFan,
			ListParameter<PersonPair, Person, String> listParameter) {
		//init
		init(listParameter.getSetAttribute()
				, listParameter.getIdPair());
		//typed
		CriteriaQuery<PersonPair> typedQuery 
		= (CriteriaQuery<PersonPair>) query;
		SetJoin<Person, PersonPair> typedSetJoin 
		= (SetJoin<Person, PersonPair>) setJoin;
		
		
		//customPredicate
//		Predicate equalFollow = getEqualPredicate(
//				typedSetJoin.get(PersonPair_.compositePerson)
//				.get(CompositePerson_.followOrFan)
//				, PersonPair.FOLLOW);
//		customPredicate.add(equalFollow);
		predicateInit();
		
		//set and page result
		select(listParameter.getPagedResult()
				, typedQuery, typedSetJoin);
		
		
		return listParameter.getPagedResult();
	}

	public Predicate getPersonFollowPredicate(
			String followOrFan, 
			SetJoin<Person, PersonPair> setJoin) {
		
		Predicate equalFollowOrFan = builder.equal(
				setJoin
				.get(PersonPair_.compositePerson)
				.get(CompositePerson_.followOrFan)
				, followOrFan
				);
		
		return equalFollowOrFan;
	}
}
