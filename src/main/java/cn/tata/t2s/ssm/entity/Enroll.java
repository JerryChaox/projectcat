package cn.tata.t2s.ssm.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import cn.tata.t2s.ssm.util.CustomDateSerializer;

@Entity
public class Enroll {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long enrollId;
	@ManyToOne
	private Person person;
	@ManyToOne
	private Project project;
	private String resume;
	@OneToOne
	private State state;

	@JsonFormat(pattern = "yyyy-MM-dd-HH-mm")
	@JsonSerialize(using = CustomDateSerializer.class)
	private Date createTime;

	@JsonFormat(pattern = "yyyy-MM-dd-HH-mm")
	@JsonSerialize(using = CustomDateSerializer.class)
	private Date updateTime;

	private boolean onDelete;

	public long getEnrollId() {
		return enrollId;
	}

	public void setEnrollId(long enrollId) {
		this.enrollId = enrollId;
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

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
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
				"Enroll  [\n\tenrollId=%s;\n\tperson=%s;\n\tproject=%s;\n\tresume=%s;\n\tstate=%s;\n\tcreateTime=%s;\n\tupdateTime=%s;\n\tonDelete=%s\n]",
				enrollId, person, project, resume, state, createTime, updateTime, onDelete);
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
		result = prime * result + (int) (enrollId ^ (enrollId >>> 32));
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
		Enroll other = (Enroll) obj;
		if (enrollId != other.enrollId)
			return false;
		return true;
	}
}
