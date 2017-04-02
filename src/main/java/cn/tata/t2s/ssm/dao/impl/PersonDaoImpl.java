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

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Repository;

import cn.tata.t2s.ssm.dao.PersonDao;
import cn.tata.t2s.ssm.entity.Person;
import cn.tata.t2s.ssm.entity.ProjectApplication;

@Repository
public class PersonDaoImpl extends SuperDaoImpl<Person, String> implements PersonDao {

	@Override
	public String selectpTypeById(String personId) {
		return null;
	}

	@Override
	public Person selectPerson(String personId) {
		return this.selectPerson(personId);
	}

	@Override
	public <X,Y> List<Tuple> selectPerson(Pair<SingularAttribute<X,Y>, Y> idPair, SetAttribute<X, ?>... setAttributes) {
		Class<X> entityClass = setAttributes[0].getDeclaringType().getJavaType();
		
		// init
 		CriteriaQuery<Tuple> query = builder.createTupleQuery();
		Root<X> root = query.from(entityClass);
		root.alias(entityClass.getSimpleName());
		
		//Predicate
		Predicate predicate = builder.equal(root.get(idPair.getLeft()), idPair.getRight());
		
		//configure root
		int i = 1;
		List<Selection<?>> selectionList = new ArrayList<Selection<?>>();
		for(SetAttribute<X, ?> setAttribute: setAttributes) {
			//for count
			CriteriaQuery<?> tempQuery = builder.createQuery(setAttribute.getBindableJavaType());
			Root<X> tempRoot = query.from(entityClass);
			tempRoot.alias(entityClass.getSimpleName() + i);
			tempRoot.join(setAttribute);
			tempQuery.where(builder.equal(tempRoot.get(idPair.getLeft()), idPair.getRight()));
//			System.out.println("count: " + this.count(builder, tempQuery, tempRoot));
			
			//for result
			selectionList.add(root.get(setAttribute));
			root.join(setAttribute);
			
			//alias
			i++;
		}
		
		query.multiselect(selectionList);
		// criteria building
		query.where(predicate);
		return entityManager.createQuery(query).getResultList();
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
		// entityManager.merge(person);
		return 1;
	}

	@Override
	public int insertProjectApplication(String personId, int projectId, String reason, String targetStateName) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteFollow(String personId, String followedId) {
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
