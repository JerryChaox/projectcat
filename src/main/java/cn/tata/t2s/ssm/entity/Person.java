package cn.tata.t2s.ssm.entity;

import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Person{
	@Id
	private String personId;
	
	@Embedded
	private Profile profile;
	
	@Embedded
	private CommonInfo commonInfo;
	
	@OneToMany//(cascade = CascadeType.REMOVE, orphanRemoval = true)
	private Set<Project> projectSet;
	
	@OneToMany//(cascade = CascadeType.REMOVE, orphanRemoval = true)
	private Set<Enroll> enrollSet;
	
	@OneToMany(mappedBy="person")
	private Set<Topic> topicSet;
	
	@OneToMany(mappedBy="person")
	private Set<Reply> replySet;
	
//	@OneToMany(targetEntity=Project.class, cascade = CascadeType.REMOVE, orphanRemoval = true)
//	private List<Star<Project>> projectStarList;
//	
//	@OneToMany(targetEntity=Topic.class, cascade = CascadeType.REMOVE, orphanRemoval = true)
//	private List<Star<Topic>> topicStarList;
	
	@OneToMany(mappedBy="compositePerson.fromPerson")
	private Set<PersonPair> personPairSet;
	
//	@OneToMany(mappedBy="compositePerson.fromPerson")
//	private Set<Fan> fanSet;

	public Person() {}
	
	public Person(String personId) {
		this.personId = personId;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public CommonInfo getCommonInfo() {
		return commonInfo;
	}

	public void setCommonInfo(CommonInfo commonInfo) {
		this.commonInfo = commonInfo;
	}
	
	public Set<Project> getProjectSet() {
		return projectSet;
	}

	public void setProjectSet(Set<Project> projectSet) {
		this.projectSet = projectSet;
	}

	public Set<Enroll> getEnrollSet() {
		return enrollSet;
	}

	public void setEnrollSet(Set<Enroll> enrollSet) {
		this.enrollSet = enrollSet;
	}

	public Set<Topic> getTopicSet() {
		return topicSet;
	}

	public void setTopicSet(Set<Topic> topicSet) {
		this.topicSet = topicSet;
	}

	public Set<Reply> getReplySet() {
		return replySet;
	}

	public void setReplySet(Set<Reply> replySet) {
		this.replySet = replySet;
	}

	/**
	 * @return the personPairSet
	 */
	public Set<PersonPair> getPersonPairSet() {
		return personPairSet;
	}

	/**
	 * @param personPairSet the personPairSet to set
	 */
	public void setPersonPairSet(Set<PersonPair> personPairSet) {
		this.personPairSet = personPairSet;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Person [\n\tpersonId=" + personId + ", \n\tprofile=" + profile + ", \n\tcommonInfo=" + commonInfo
				+ "\n]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((personId == null) ? 0 : personId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (personId == null) {
			if (other.personId != null)
				return false;
		} else if (!personId.equals(other.personId))
			return false;
		return true;
	}

}