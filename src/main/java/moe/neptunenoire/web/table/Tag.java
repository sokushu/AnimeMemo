package moe.neptunenoire.web.table;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tag")
public class Tag {
	/**
	 * tagID
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer tag_id;
	/**
	 * 标签名
	 */
	@Column(name="tag_name", length=255)
	private String tag_name;
	/**
	 * 创建的日期
	 */
	@Column(name="date_new")
	private Date date_new;
	/**
	 * 更新的日期
	 */
	@Column(name="data_update")
	private Date date_update;
	//================================
	public Integer getTag_id() {
		return tag_id;
	}
	public void setTag_id(Integer tag_id) {
		this.tag_id = tag_id;
	}
	public String getTag_name() {
		return tag_name;
	}
	public void setTag_name(String tag_name) {
		this.tag_name = tag_name;
	}
	public Date getDate_new() {
		return date_new;
	}
	public void setDate_new(Date date_new) {
		this.date_new = date_new;
	}
	public Date getDate_update() {
		return date_update;
	}
	public void setDate_update(Date date_update) {
		this.date_update = date_update;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date_new == null) ? 0 : date_new.hashCode());
		result = prime * result + ((date_update == null) ? 0 : date_update.hashCode());
		result = prime * result + ((tag_id == null) ? 0 : tag_id.hashCode());
		result = prime * result + ((tag_name == null) ? 0 : tag_name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tag other = (Tag) obj;
		if (date_new == null) {
			if (other.date_new != null)
				return false;
		} else if (!date_new.equals(other.date_new))
			return false;
		if (date_update == null) {
			if (other.date_update != null)
				return false;
		} else if (!date_update.equals(other.date_update))
			return false;
		if (tag_id == null) {
			if (other.tag_id != null)
				return false;
		} else if (!tag_id.equals(other.tag_id))
			return false;
		if (tag_name == null) {
			if (other.tag_name != null)
				return false;
		} else if (!tag_name.equals(other.tag_name))
			return false;
		return true;
	}
	public Tag(Integer tag_id, String tag_name, Date date_new, Date date_update) {
		super();
		this.tag_id = tag_id;
		this.tag_name = tag_name;
		this.date_new = date_new;
		this.date_update = date_update;
	}
	public Tag() {
		super();
	}
	@Override
	public String toString() {
		return "Tag [tag_id=" + tag_id + ", tag_name=" + tag_name + ", date_new=" + date_new + ", date_update="
				+ date_update + "]";
	}
	
}
