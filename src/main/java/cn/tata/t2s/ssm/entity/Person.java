package cn.tata.t2s.ssm.entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import cn.tata.t2s.ssm.util.CustomDateSerializer;

@Entity
public class Person extends Base{
	@Id
	protected String personId;
	protected String name;
	protected String nickName;
	protected String phoneNumber;
	protected String mail;
	protected String school;
	protected String academy;
	protected String headUrl;
	protected String selfIntroduction;
	protected String defaultResume;
	protected String grade;
	protected String profession;
	
	protected String pType = this.getClass().getSimpleName();
	@ElementCollection(fetch = FetchType.LAZY, 
			targetClass = String.class) 
	@CollectionTable 
	protected List<String> major;

	@OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
	protected Set<Project> projectList;
	
	@OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
	protected Set<Enroll> enrollList;
	
	@OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
	protected Set<Topic> topicList;
	
	@OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
	protected Set<Reply> replyList;
	
//	@OneToMany(targetEntity=Project.class, cascade = CascadeType.REMOVE, orphanRemoval = true)
//	protected List<Star<Project>> projectStarList;
//	
//	@OneToMany(targetEntity=Topic.class, cascade = CascadeType.REMOVE, orphanRemoval = true)
//	protected List<Star<Topic>> topicStarList;
	
	@OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
	protected Set<Person> followList;

	public Person() {

	}

	public Person(String personId) {
		this.personId = personId;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getAcademy() {
		return academy;
	}

	public void setAcademy(String academy) {
		this.academy = academy;
	}

	public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getSelfIntroduction() {
		return selfIntroduction;
	}

	public void setSelfIntroduction(String selfIntroduction) {
		this.selfIntroduction = selfIntroduction;
	}

	public String getDefaultResume() {
		return defaultResume;
	}

	public void setDefaultResume(String defaultResume) {
		this.defaultResume = defaultResume;
	}

	public String getpType() {
		return pType;
	}

	public void setpType(String pType) {
		this.pType = pType;
	}

	public List<String> getMajor() {
		return major;
	}

	public void setMajor(List<String> major) {
		this.major = major;
	}


	public Set<Project> getProjectList() {
		return projectList;
	}

	public void setProjectList(Set<Project> projectList) {
		this.projectList = projectList;
	}

	public Set<Enroll> getEnrollList() {
		return enrollList;
	}

	public void setEnrollList(Set<Enroll> enrollList) {
		this.enrollList = enrollList;
	}

	public Set<Topic> getTopicList() {
		return topicList;
	}

	public void setTopicList(Set<Topic> topicList) {
		this.topicList = topicList;
	}

	public Set<Reply> getReplyList() {
		return replyList;
	}

	public void setReplyList(Set<Reply> replyList) {
		this.replyList = replyList;
	}

//	public List<Star<Project>> getProjectStarList() {
//		return projectStarList;
//	}
//
//	public void setProjectStarList(List<Star<Project>> projectStarList) {
//		this.projectStarList = projectStarList;
//	}
//
//	public List<Star<Topic>> getTopicStarList() {
//		return topicStarList;
//	}
//
//	public void setTopicStarList(List<Star<Topic>> topicStarList) {
//		this.topicStarList = topicStarList;
//	}

	public Set<Person> getFollowList() {
		return followList;
	}

	public void setFollowList(Set<Person> followList) {
		this.followList = followList;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Person [\n\tpersonId=" + personId + ", \n\tname=" + name + ", \n\tnickName=" + nickName
				+ ", \n\tphoneNumber=" + phoneNumber + ", \n\tmail=" + mail + ", \n\tschool=" + school
				+ ", \n\tacademy=" + academy + ", \n\theadUrl=" + headUrl + ", \n\tselfIntroduction=" + selfIntroduction
				+ ", \n\tdefaultResume=" + defaultResume + ", \n\tgrade=" + grade + ", \n\tprofession=" + profession
				+ ", \n\tpType=" + pType + ", \n\tmajor=" + major + ", \n\tprojectList=" + projectList
				+ ", \n\tenrollList=" + enrollList + ", \n\ttopicList=" + topicList + ", \n\treplyList=" + replyList
				+ ", \n\tfollowList=" + followList + ", \n\tonDelete=" + onDelete + ", \n\tcreateTime=" + createTime
				+ ", \n\tupdateTime=" + updateTime + "\n]";
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