package cn.tata.t2s.ssm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Any;
import org.hibernate.annotations.AnyMetaDef;
import org.hibernate.annotations.MetaValue;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import cn.tata.t2s.ssm.util.CustomDateSerializer;

@Entity
public class Star<T> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long starId;
	@ManyToOne
	private Person person;

	@Any(metaColumn = @Column(name = "starObjectClass"))
	@AnyMetaDef(idType = "long", metaType = "string", metaValues = {
			@MetaValue(targetEntity = Project.class, value = "Project"),
			@MetaValue(targetEntity = Topic.class, value = "Topic") })
	@JoinColumn(name = "starObjectId")
	private T starObject;

	@JsonFormat(pattern = "yyyy-MM-dd-HH-mm")
	@JsonSerialize(using = CustomDateSerializer.class)
	private Date createTime;
	@JsonFormat(pattern = "yyyy-MM-dd-HH-mm")
	@JsonSerialize(using = CustomDateSerializer.class)
	private Date updateTime;
	private boolean onDelete;

	public Star() {
	}

	@SuppressWarnings("unchecked")
	public Star(long starId, int starObjectId, String starClass) {
		this.starId = starId;
		if (starClass.equals("project")) {
			this.starObject = (T) new Project(starObjectId);
		}

		if (starClass.equals("topic")) {
			this.starObject = (T) new Topic(starObjectId);
		}
	}

	public long getStarId() {
		return starId;
	}

	public void setStarId(long starId) {
		this.starId = starId;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public T getStarObject() {
		return starObject;
	}

	public void setStarObject(T starObject) {
		this.starObject = starObject;
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
				"Star  [\n\tstarId=%s;\n\tperson=%s;\n\tstarObject=%s;\n\tcreateTime=%s;\n\tupdateTime=%s;\n\tonDelete=%s\n]",
				starId, person, starObject, createTime, updateTime, onDelete);
	}

}
