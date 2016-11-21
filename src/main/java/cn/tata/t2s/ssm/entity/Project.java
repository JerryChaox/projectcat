package cn.tata.t2s.ssm.entity;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import cn.tata.t2s.ssm.util.CustomDateSerializer;

public class Project {
	private int projectId;
	private Person person;
	private List<Person> personList;
	private List<Enroll> enrollList;
	private String projectName;
	private String projectAbstract;
	private String[] figurePath;
	private String area;
	private String school;
	private String academy;
	private String background;
	private String major;
	private String requirement;
	private String managerIntro;
	private State state;
	private int maxEnrollCount;
	private int enrolledCount;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@JsonSerialize(using = CustomDateSerializer.class)
	private Date startingTime;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@JsonSerialize(using = CustomDateSerializer.class)
	private Date deadline;
	
	@JsonFormat(pattern = "yyyy-MM-dd-HH-mm")
	@JsonSerialize(using = CustomDateSerializer.class)
	private Date createTime;
	
	@JsonFormat(pattern = "yyyy-MM-dd-HH-mm")
	@JsonSerialize(using = CustomDateSerializer.class)
	private Date updateTime;
	
	private boolean onDelete;
	
	public Project() {}
	
	public Project(int projectId) {
		this.projectId = projectId;
	}
	
	
	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public List<Person> getPersonList() {
		return personList;
	}

	public void setPersonList(List<Person> personList) {
		this.personList = personList;
	}

	public List<Enroll> getEnrollList() {
		return enrollList;
	}

	public void setEnrollList(List<Enroll> enrollList) {
		this.enrollList = enrollList;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectAbstract() {
		return projectAbstract;
	}

	public void setProjectAbstract(String projectAbstract) {
		this.projectAbstract = projectAbstract;
	}

	public String getFigurePath() {
		String figurePath = StringUtils.join(this.figurePath, ',');
		return figurePath;
	}

	public void setFigurePath(String figurePath) {
		if(figurePath == null) {
			this.figurePath = null;
		} else {
			this.figurePath = StringUtils.split(figurePath, ',');
		}	
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
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

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getRequirement() {
		return requirement;
	}

	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}

	public String getManagerIntro() {
		return managerIntro;
	}

	public void setManagerIntro(String managerIntro) {
		this.managerIntro = managerIntro;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public int getMaxEnrollCount() {
		return maxEnrollCount;
	}

	public void setMaxEnrollCount(int maxEnrollCount) {
		this.maxEnrollCount = maxEnrollCount;
	}

	public int getEnrolledCount() {
		return enrolledCount;
	}

	public void setEnrolledCount(int enrolledCount) {
		this.enrolledCount = enrolledCount;
	}

	public Date getStartingTime() {
		return startingTime;
	}

	public void setStartingTime(Date startingTime) {
		this.startingTime = startingTime;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
				"Project  [\n\tprojectId=%s;\n\tperson=%s;\n\tpersonList=%s;\n\tenrollList=%s;\n\tprojectName=%s;\n\tprojectAbstract=%s;\n\tfigurePath=%s;\n\tarea=%s;\n\tschool=%s;\n\tacademy=%s;\n\tbackground=%s;\n\tmajor=%s;\n\trequirement=%s;\n\tmanagerIntro=%s;\n\tstate=%s;\n\tmaxEnrollCount=%s;\n\tenrolledCount=%s;\n\tstartingTime=%s;\n\tdeadline=%s;\n\tcreateTime=%s;\n\tupdateTime=%s;\n\tonDelete=%s\n]",
				projectId, person, personList, enrollList, projectName, projectAbstract, Arrays.toString(figurePath),
				area, school, academy, background, major, requirement, managerIntro, state, maxEnrollCount,
				enrolledCount, startingTime, deadline, createTime, updateTime, onDelete);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + projectId;
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
		Project other = (Project) obj;
		if (projectId != other.projectId)
			return false;
		return true;
	}	

}