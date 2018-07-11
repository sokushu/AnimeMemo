package moe.neptunenoire.web.table;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.CreationTimestamp;

/**
 * 留言
 * @author miri
 *
 */
@Entity
@Table(name="comments")
public class Comments {
	/**
	 * 留言id
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="commid")
	private Integer commid;
	/**
	 * 给谁的留言
	 */
	@Column(name="uid")
	private Integer uid;
	/**
	 * 谁的留言
	 */
	@Column(name="commuid")
	private Integer commuid;
	/**
	 * 留言用户的名字
	 */
	@Column(name="name")
	private String name;
	/**
	 * 留言用户的头像
	 */
	@Column(name="pic")
	private String pic;
	/**
	 * 留言内容
	 */
	@Column(name="comm")
	@NotEmpty(message = "不能为空哦")
	private String comm;
	/**
	 * 留言时间
	 */
	@CreationTimestamp
	@Column(name="comm_data")
	private Date comm_data;
//=======================================
	public Integer getCommid() {
		return commid;
	}
	public void setCommid(Integer commid) {
		this.commid = commid;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getCommuid() {
		return commuid;
	}
	public void setCommuid(Integer commuid) {
		this.commuid = commuid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getComm() {
		return comm;
	}
	public void setComm(String comm) {
		this.comm = comm;
	}
	public Date getComm_data() {
		return comm_data;
	}
	public void setComm_data(Date comm_data) {
		this.comm_data = comm_data;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comm == null) ? 0 : comm.hashCode());
		result = prime * result + ((comm_data == null) ? 0 : comm_data.hashCode());
		result = prime * result + ((commid == null) ? 0 : commid.hashCode());
		result = prime * result + ((commuid == null) ? 0 : commuid.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((pic == null) ? 0 : pic.hashCode());
		result = prime * result + ((uid == null) ? 0 : uid.hashCode());
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
		Comments other = (Comments) obj;
		if (comm == null) {
			if (other.comm != null)
				return false;
		} else if (!comm.equals(other.comm))
			return false;
		if (comm_data == null) {
			if (other.comm_data != null)
				return false;
		} else if (!comm_data.equals(other.comm_data))
			return false;
		if (commid == null) {
			if (other.commid != null)
				return false;
		} else if (!commid.equals(other.commid))
			return false;
		if (commuid == null) {
			if (other.commuid != null)
				return false;
		} else if (!commuid.equals(other.commuid))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (pic == null) {
			if (other.pic != null)
				return false;
		} else if (!pic.equals(other.pic))
			return false;
		if (uid == null) {
			if (other.uid != null)
				return false;
		} else if (!uid.equals(other.uid))
			return false;
		return true;
	}
	public Comments(Integer commid, Integer uid, Integer commuid, String name, String pic, String comm,
			Date comm_data) {
		super();
		this.commid = commid;
		this.uid = uid;
		this.commuid = commuid;
		this.name = name;
		this.pic = pic;
		this.comm = comm;
		this.comm_data = comm_data;
	}
	public Comments() {
		super();
	}
	@Override
	public String toString() {
		return "Comments [commid=" + commid + ", uid=" + uid + ", commuid=" + commuid + ", name=" + name + ", pic="
				+ pic + ", comm=" + comm + ", comm_data=" + comm_data + "]";
	}
}
