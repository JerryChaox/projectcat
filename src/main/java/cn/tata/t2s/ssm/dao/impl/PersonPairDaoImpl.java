package cn.tata.t2s.ssm.dao.impl;

import org.springframework.stereotype.Repository;

import cn.tata.t2s.ssm.dao.PersonPairDao;
import cn.tata.t2s.ssm.entity.CompositePerson;
import cn.tata.t2s.ssm.entity.PersonPair;

@Repository
public class PersonPairDaoImpl extends SuperDaoImpl<PersonPair, CompositePerson> implements PersonPairDao {


	@Override
	public PersonPair selectPersonPair(CompositePerson compositePerson) {
		return select(compositePerson);
	}

	@Override
	public PersonPair updatePersonPair(PersonPair personPair) {
		return update(personPair);
	}

	@Override
	public int insertPersonPair(PersonPair personPair) {
		return insert(personPair);
	}

	

	

	
}
