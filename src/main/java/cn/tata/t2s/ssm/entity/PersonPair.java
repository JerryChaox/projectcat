package cn.tata.t2s.ssm.entity;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="person_Pair"
, uniqueConstraints 
= {@UniqueConstraint(columnNames
		= {"fromPerson_PersonId","toPerson_PersonId"})})
public class PersonPair{
	public static String FOLLOW = "FOLLOW";
	public static String FAN = "FAN";
	
	@EmbeddedId
	private CompositePerson compositePerson;
	
	@Embedded
	private CommonInfo commonInfo;
	
	public PersonPair() {}

	/**
	 * @return the compositePerson
	 */
	public CompositePerson getCompositePerson() {
		return compositePerson;
	}

	/**
	 * @param compositePerson the compositePerson to set
	 */
	public void setCompositePerson(CompositePerson compositePerson) {
		this.compositePerson = compositePerson;
	}

	/**
	 * @return the commonInfo
	 */
	public CommonInfo getCommonInfo() {
		return commonInfo;
	}

	/**
	 * @param commonInfo the commonInfo to set
	 */
	public void setCommonInfo(CommonInfo commonInfo) {
		this.commonInfo = commonInfo;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PersonPair [\n\tcompositePerson=" + compositePerson + ", \n\tcommonInfo=" + commonInfo + "\n]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((compositePerson == null) ? 0 : compositePerson.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonPair other = (PersonPair) obj;
		if (compositePerson == null) {
			if (other.compositePerson != null)
				return false;
		} else if (!compositePerson.equals(other.compositePerson))
			return false;
		return true;
	}
}
