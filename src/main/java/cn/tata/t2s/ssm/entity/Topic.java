package cn.tata.t2s.ssm.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Topic{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long topicId;
	//private int classId;
	private String className;
	private String title;
	private String body;
	private long hits;
	private long replyCount;
	private long likeCount;
	private String replyBy;
	
	@Embedded
	private CommonInfo commonInfo;
	
	@ManyToOne
	private Person person;
	
	@OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Reply> replyList;
	
	@OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<State> topicStateList;
	
	public Topic() {}
	
	public Topic(int topicId) {
		this.topicId = topicId;
	}
	
	public long getTopicId() {
		return topicId;
	}

	public void setTopicId(long topicId) {
		this.topicId = topicId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

//	public int getClassId() {
//		return classId;
//	}
//
//	public void setClassId(int classId) {
//		this.classId = classId;
//	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public long getHits() {
		return hits;
	}

	public void setHits(long hits) {
		this.hits = hits;
	}

	public long getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(long replyCount) {
		this.replyCount = replyCount;
	}

	public long getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(long likeCount) {
		this.likeCount = likeCount;
	}

	public String getReplyBy() {
		return replyBy;
	}

	public void setReplyBy(String replyBy) {
		this.replyBy = replyBy;
	}
	

	public CommonInfo getCommonInfo() {
		return commonInfo;
	}

	public void setCommonInfo(CommonInfo commonInfo) {
		this.commonInfo = commonInfo;
	}

	public List<Reply> getReplyList() {
		return replyList;
	}

	public void setReplyList(List<Reply> replyList) {
		this.replyList = replyList;
	}


	public List<State> getTopicStateList() {
		return topicStateList;
	}

	public void setTopicStateList(List<State> topicStateList) {
		this.topicStateList = topicStateList;
	}

	@Override
	public String toString() {
		return "Topic [topicId=" + topicId + ", className=" + className + ", title=" + title + ", body=" + body
				+ ", hits=" + hits + ", replyCount=" + replyCount + ", likeCount=" + likeCount + ", replyBy=" + replyBy
				+ ", commonInfo=" + commonInfo + ", person=" + person + ", replyList=" + replyList + ", topicStateList="
				+ topicStateList + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (topicId ^ (topicId >>> 32));
		return result;
	}

	/* (non-Javadoc)
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
		Topic other = (Topic) obj;
		if (topicId != other.topicId)
			return false;
		return true;
	}
}
