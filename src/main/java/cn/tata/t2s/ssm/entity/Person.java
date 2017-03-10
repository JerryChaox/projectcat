package cn.tata.t2s.ssm.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import cn.tata.t2s.ssm.util.CustomDateSerializer;

@Entity
public class Person {
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
	protected String pType;
	@ElementCollection(fetch = FetchType.LAZY, 
			targetClass = String.class) 
	@CollectionTable 
	protected List<String> major;

	@OneToOne
	protected ProjectApplication projectApplication;
	
	@OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
	protected List<Project> projectList;
	
	@OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
	protected List<Enroll> enrollList;
	
	@OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
	protected List<Topic> topicList;
	
	@OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
	protected List<Reply> replyList;
	
	@OneToMany(targetEntity=Project.class, cascade = CascadeType.REMOVE, orphanRemoval = true)
	protected List<Star<Project>> projectStarList;
	
	@OneToMany(targetEntity=Topic.class, cascade = CascadeType.REMOVE, orphanRemoval = true)
	protected List<Star<Topic>> topicStarList;
	
	@OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
	protected List<Person> followList;
	
	protected boolean onDelete;
	// 这里展示了jackson封装好的以及自定义的对时间格式的转换方式
	// 后续对于一些复杂的转换可以自定义转换方式
	@JsonFormat(pattern = "yyyy-MM-dd-HH-mm")
	@JsonSerialize(using = CustomDateSerializer.class)
	protected Date createTime;

	@JsonFormat(pattern = "yyyy-MM-dd-HH-mm")
	@JsonSerialize(using = CustomDateSerializer.class)
	protected Date updateTime;

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

	/**
	 * @return the projectApplication
	 */
	public ProjectApplication getProjectApplication() {
		return projectApplication;
	}

	/**
	 * @param projectApplication the projectApplication to set
	 */
	public void setProjectApplication(ProjectApplication projectApplication) {
		this.projectApplication = projectApplication;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public List<Project> getProjectList() {
		return projectList;
	}

	public void setProjectList(List<Project> projectList) {
		this.projectList = projectList;
	}

	public List<Enroll> getEnrollList() {
		return enrollList;
	}

	public void setEnrollList(List<Enroll> enrollList) {
		this.enrollList = enrollList;
	}

	public List<Topic> getTopicList() {
		return topicList;
	}

	public void setTopicList(List<Topic> topicList) {
		this.topicList = topicList;
	}

	public List<Reply> getReplyList() {
		return replyList;
	}

	public void setReplyList(List<Reply> replyList) {
		this.replyList = replyList;
	}

	public List<Star<Project>> getProjectStarList() {
		return projectStarList;
	}

	public void setProjectStarList(List<Star<Project>> projectStarList) {
		this.projectStarList = projectStarList;
	}

	public List<Star<Topic>> getTopicStarList() {
		return topicStarList;
	}

	public void setTopicStarList(List<Star<Topic>> topicStarList) {
		this.topicStarList = topicStarList;
	}

	public List<Person> getFollowList() {
		return followList;
	}

	public void setFollowList(List<Person> followList) {
		this.followList = followList;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public boolean isOnDelete() {
		return onDelete;
	}

	public void setOnDelete(boolean onDelete) {
		this.onDelete = onDelete;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Person [personId=" + personId + ", name=" + name + ", nickName=" + nickName + ", phoneNumber="
				+ phoneNumber + ", mail=" + mail + ", school=" + school + ", academy=" + academy + ", headUrl="
				+ headUrl + ", selfIntroduction=" + selfIntroduction + ", defaultResume=" + defaultResume + ", grade="
				+ grade + ", profession=" + profession + ", pType=" + pType + ", major=" + major
				+ ", projectApplication=" + projectApplication + ", projectList=" + projectList + ", enrollList="
				+ enrollList + ", topicList=" + topicList + ", replyList=" + replyList + ", projectStarList="
				+ projectStarList + ", topicStarList=" + topicStarList + ", followList=" + followList + ", onDelete="
				+ onDelete + ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
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