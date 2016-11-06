package cn.tata.t2s.ssm.entity;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import cn.tata.t2s.ssm.util.CustomDateSerializer;

public class Person {
	protected String personId;
	protected String name;
	protected String nickName;
	protected String phoneNumber;
	protected String mail;
	protected String school;
	protected String academy;
	protected String headUrl;
	protected String[] major;
	protected String selfIntroduction;
	protected String defaultResume;
	protected String grade;
	protected String profession;
	protected String pType;
	protected List<Project> projectList;
	protected List<Enroll> enrollList;
	protected List<Topic> topicList;
	protected List<Reply> replyList;
	protected List<Star<Project>> projectStarList;
	protected List<Star<Topic>> topicStarList;
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

	public String getMajor() {
		String major = StringUtils.join(this.major, ',');
		return major;
	}

	public void setMajor(String major) {
		if(major == null) {
			this.major = null;
		} else {
			this.major = StringUtils.split(major, ',');
		}
		
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

	@Override
	public String toString() {
		return String.format(
				"Person  [\n\tpersonId=%s;\n\tname=%s;\n\tnickName=%s;\n\tphoneNumber=%s;\n\tmail=%s;\n\tschool=%s;\n\tacademy=%s;\n\theadUrl=%s;\n\tmajor=%s;\n\tselfIntroduction=%s;\n\tdefaultResume=%s;\n\tgrade=%s;\n\tprofession=%s;\n\tpType=%s;\n\tprojectList=%s;\n\tenrollList=%s;\n\ttopicList=%s;\n\treplyList=%s;\n\tprojectStarList=%s;\n\ttopicStarList=%s;\n\tfollowList=%s;\n\tonDelete=%s;\n\tcreateTime=%s;\n\tupdateTime=%s\n]",
				personId, name, nickName, phoneNumber, mail, school, academy, headUrl, Arrays.toString(major),
				selfIntroduction, defaultResume, grade, profession, pType, projectList, enrollList, topicList,
				replyList, projectStarList, topicStarList, followList, onDelete, createTime, updateTime);
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