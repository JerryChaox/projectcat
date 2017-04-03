package cn.tata.t2s.ssm.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

@Embeddable
public class CommonInfo {
	@Column(insertable=false)
	@ColumnDefault("0")
	@Generated(GenerationTime.INSERT)
	private boolean onDelete;

	@Column(insertable=false
			, updatable=false)
	@Generated(GenerationTime.INSERT)
	@ColumnDefault("CURRENT_TIMESTAMP")
	private LocalDateTime createTime;
	
	@Column(insertable=false)
	@ColumnDefault("CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	@Generated(GenerationTime.ALWAYS)
	private LocalDateTime updateTime;
	
	
	public boolean isOnDelete() {
		return onDelete;
	}
	public void setOnDelete(boolean onDelete) {
		this.onDelete = onDelete;
	}
	
	
	public LocalDateTime getCreateTime() {
		return createTime;
	}
	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}
	
	
	public LocalDateTime getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}
	@Override
	public String toString() {
		return "CommonInfo [onDelete=" + onDelete + ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}
}
