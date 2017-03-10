package cn.tata.t2s.ssm.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import cn.tata.t2s.ssm.util.CustomDateSerializer;

@Entity
public class ProjectApplication {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long applicationId;
	@OneToOne
	private Person person;
	@OneToOne
	private Project project;
	private String reason;
	private String targetStateName;
	private boolean onAdmit;

	@JsonFormat(pattern = "yyyy-MM-dd-HH-mm")
	@JsonSerialize(using = CustomDateSerializer.class)
	private Date createTime;

	@JsonFormat(pattern = "yyyy-MM-dd-HH-mm")
	@JsonSerialize(using = CustomDateSerializer.class)
	private Date updateTime;

	public long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(long applicationId) {
		this.applicationId = applicationId;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getTargetStateName() {
		return targetStateName;
	}

	public void setTargetStateName(String targetStateName) {
		this.targetStateName = targetStateName;
	}

	public boolean isOnAdmit() {
		return onAdmit;
	}

	public void setOnAdmit(boolean onAdmit) {
		this.onAdmit = onAdmit;
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

	@Override
	public String toString() {
		return String.format(
				"ProjectApplication  [\n\tapplicationId=%s;\n\tperson=%s;\n\tproject=%s;\n\ttargetStateName=%s;\n\tcreateTime=%s;\n\tupdateTime=%s\n]",
				applicationId, person, project, targetStateName, createTime, updateTime);
	}

}
