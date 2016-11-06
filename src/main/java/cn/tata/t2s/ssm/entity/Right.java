package cn.tata.t2s.ssm.entity;

public class Right {
	private int classId;
	private int groupId;

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	@Override
	public String toString() {
		return "Right [classId=" + classId + ", groupId=" + groupId + "]";
	}

}
