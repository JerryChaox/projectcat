package cn.tata.t2s.ssm.entity;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;

@Embeddable
public class Profile {
	private String name;
	private String nickName;
	private String phoneNumber;
	private String mail;
	private String school;
	private String academy;
	private String headUrl;
	private String selfIntroduction;
	private String defaultResume;
	private String grade;
	private String profession;
	
	private String pType = this.getClass().getSimpleName();
	@ElementCollection(fetch = FetchType.LAZY, 
			targetClass = String.class) 
	@CollectionTable 
	private List<String> major;
	@Override
	public String toString() {
		return "Profile [name=" + name + ", nickName=" + nickName + ", phoneNumber=" + phoneNumber + ", mail=" + mail
				+ ", school=" + school + ", academy=" + academy + ", headUrl=" + headUrl + ", selfIntroduction="
				+ selfIntroduction + ", defaultResume=" + defaultResume + ", grade=" + grade + ", profession="
				+ profession + ", pType=" + pType + ", major=" + major + "]";
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
	
	
}
