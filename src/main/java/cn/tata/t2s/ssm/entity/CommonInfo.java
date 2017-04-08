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
	
	public CommonInfo() {
		
	}
	
	public CommonInfo(Boolean onDelete, LocalDateTime createTime, LocalDateTime updateTime) {
		this.onDelete = onDelete;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}
	
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + (onDelete ? 1231 : 1237);
		result = prime * result + ((updateTime == null) ? 0 : updateTime.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof CommonInfo)) {
			return false;
		}
		CommonInfo other = (CommonInfo) obj;
		if (createTime == null) {
			if (other.createTime != null) {
				return false;
			}
		} else if (!createTime.equals(other.createTime)) {
			return false;
		}
		if (onDelete != other.onDelete) {
			return false;
		}
		if (updateTime == null) {
			if (other.updateTime != null) {
				return false;
			}
		} else if (!updateTime.equals(other.updateTime)) {
			return false;
		}
		return true;
	}
}
