package cn.tata.t2s.ssm.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import cn.tata.t2s.ssm.util.CustomDateSerializer;

public class Star<T> {
	private long starId;
	private Person person;
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
