package cn.tata.t2s.ssm.dao;

import cn.tata.t2s.ssm.entity.CompositePerson;
import cn.tata.t2s.ssm.entity.PersonPair;

public interface PersonPairDao {
	public PersonPair selectPersonPair(CompositePerson compositePerson);

	public PersonPair updatePersonPair(PersonPair personPair);
	
	public int insertPersonPair(PersonPair personPair);
}
