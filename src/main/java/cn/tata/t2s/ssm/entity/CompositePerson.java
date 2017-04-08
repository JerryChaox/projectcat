package cn.tata.t2s.ssm.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Embeddable
public class CompositePerson implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7049490706521017907L;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "fromPerson_PersonId")
	private Person fromPerson;
	
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "toPerson_PersonId")
	private Person toPerson;
	
	private String followOrFan;
	
	public CompositePerson() {
		
	}
	
	public CompositePerson(Person fromPerson, Person toPerson, String followOrFan) {
		this.fromPerson = fromPerson;
		this.toPerson = toPerson;
		this.followOrFan = followOrFan;
	}

	/**
	 * @return the fromPerson
	 */
	public Person getFromPerson() {
		return fromPerson;
	}

	/**
	 * @param fromPerson the fromPerson to set
	 */
	public void setFromPerson(Person fromPerson) {
		this.fromPerson = fromPerson;
	}

	/**
	 * @return the toPerson
	 */
	public Person getToPerson() {
		return toPerson;
	}

	/**
	 * @param toPerson the toPerson to set
	 */
	public void setToPerson(Person toPerson) {
		this.toPerson = toPerson;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CompositePerson [\n\tfromPerson=" + fromPerson + ", \n\ttoPerson=" + toPerson + "\n]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((followOrFan == null) ? 0 : followOrFan.hashCode());
		result = prime * result + ((fromPerson == null) ? 0 : fromPerson.hashCode());
		result = prime * result + ((toPerson == null) ? 0 : toPerson.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof CompositePerson)) {
			return false;
		}
		CompositePerson other = (CompositePerson) obj;
		if (followOrFan == null) {
			if (other.followOrFan != null) {
				return false;
			}
		} else if (!followOrFan.equals(other.followOrFan)) {
			return false;
		}
		if (fromPerson == null) {
			if (other.fromPerson != null) {
				return false;
			}
		} else if (!fromPerson.equals(other.fromPerson)) {
			return false;
		}
		if (toPerson == null) {
			if (other.toPerson != null) {
				return false;
			}
		} else if (!toPerson.equals(other.toPerson)) {
			return false;
		}
		return true;
	}
	
}
