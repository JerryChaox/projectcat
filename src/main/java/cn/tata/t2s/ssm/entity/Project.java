package cn.tata.t2s.ssm.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import cn.tata.t2s.ssm.util.CustomDateSerializer;

@Entity
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long projectId;
	@ManyToOne
	private Person person;
	private String projectName;
	private String projectAbstract;
	private String area;
	private String school;
	private String academy;
	private String background;
	private String major;
	private String requirement;
	private String managerIntro;
	private int maxEnrollCount;
	private int enrolledCount;
	
	@OneToOne
	private State state;
	
	@ElementCollection(fetch = FetchType.LAZY, targetClass = String.class)
	@CollectionTable
	private List<String> figurePath;
	
	// @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
	// private List<Person> personList;
	
	@OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Enroll> enrollList;
	
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

	public Project() {
	}

	public Project(long projectId) {
		this.projectId = projectId;
	}

	public long getProjectId() {
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

	// public List<Person> getPersonList() {
	// return personList;
	// }
	//
	// public void setPersonList(List<Person> personList) {
	// this.personList = personList;
	// }

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

	public List<String> getFigurePath() {
		return figurePath;
	}

	public void setFigurePath(List<String> figurePath) {
		this.figurePath = figurePath;
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
		return "Project [projectId=" + projectId + ", person=" + person + ", projectName=" + projectName
				+ ", projectAbstract=" + projectAbstract + ", area=" + area + ", school=" + school + ", academy="
				+ academy + ", background=" + background + ", major=" + major + ", requirement=" + requirement
				+ ", managerIntro=" + managerIntro + ", state=" + state + ", maxEnrollCount=" + maxEnrollCount
				+ ", enrolledCount=" + enrolledCount + ", figurePath=" + figurePath + ", enrollList=" + enrollList
				+ ", startingTime=" + startingTime + ", deadline=" + deadline + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", onDelete=" + onDelete + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (projectId ^ (projectId >>> 32));
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
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
		Project other = (Project) obj;
		if (projectId != other.projectId)
			return false;
		return true;
	}

}