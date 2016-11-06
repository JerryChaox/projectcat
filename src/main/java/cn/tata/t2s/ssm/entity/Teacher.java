package cn.tata.t2s.ssm.entity;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

public class Teacher extends Person {
	

	public Teacher() {

	}

	

	@Override
	public String toString() {
		return "Teacher [major=" + Arrays.toString(major) + ", personId=" + personId + ", name=" + name + ", nickName="
				+ nickName + ", phoneNumber=" + phoneNumber + ", school=" + school + ", academy=" + academy
				+ ", headUrl=" + headUrl + ", pType=" + pType + ", createTime=" + createTime + "]";
	}

	 
	
	

}