package moe.neptunenoire.mysql.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="user_anime")
public class User_Anime {
	/**
	 * 用户id
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="uid")
	private Integer uid;
	/**
	 * 用户订阅的动画
	 */
	@Column(name="animeid")
	private Integer animeid;
	/**
	 * 用户看到的集数
	 */
	@Column(name="number")
	private Integer number;
	/**
	 * 动画的所有集数
	 */
	@Column(name="allnumber")
	private Integer allnumber;
	/**
	 * 用户订阅动画的时间
	 */
	@CreationTimestamp
	@Column(name="data_new")
	private Date data_new;
	/**
	 * 用户看过动画后
	 * 更新的时间
	 */
	@UpdateTimestamp
	@Column(name="data_updata")
	private Date data_updata;
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getAnimeid() {
		return animeid;
	}
	public void setAnimeid(Integer animeid) {
		this.animeid = animeid;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Integer getAllnumber() {
		return allnumber;
	}
	public void setAllnumber(Integer allnumber) {
		this.allnumber = allnumber;
	}
	public Date getData_new() {
		return data_new;
	}
	public void setData_new(Date data_new) {
		this.data_new = data_new;
	}
	public Date getData_updata() {
		return data_updata;
	}
	public void setData_updata(Date data_updata) {
		this.data_updata = data_updata;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((allnumber == null) ? 0 : allnumber.hashCode());
		result = prime * result + ((animeid == null) ? 0 : animeid.hashCode());
		result = prime * result + ((data_new == null) ? 0 : data_new.hashCode());
		result = prime * result + ((data_updata == null) ? 0 : data_updata.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
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
		User_Anime other = (User_Anime) obj;
		if (allnumber == null) {
			if (other.allnumber != null)
				return false;
		} else if (!allnumber.equals(other.allnumber))
			return false;
		if (animeid == null) {
			if (other.animeid != null)
				return false;
		} else if (!animeid.equals(other.animeid))
			return false;
		if (data_new == null) {
			if (other.data_new != null)
				return false;
		} else if (!data_new.equals(other.data_new))
			return false;
		if (data_updata == null) {
			if (other.data_updata != null)
				return false;
		} else if (!data_updata.equals(other.data_updata))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (uid == null) {
			if (other.uid != null)
				return false;
		} else if (!uid.equals(other.uid))
			return false;
		return true;
	}
	public User_Anime(Integer uid, Integer animeid, Integer number, Integer allnumber, Date data_new,
	Date data_updata) {
		super();
		this.uid = uid;
		this.animeid = animeid;
		this.number = number;
		this.allnumber = allnumber;
		this.data_new = data_new;
		this.data_updata = data_updata;
	}
	public User_Anime() {
		super();
	}
	@Override
	public String toString() {
		return "User_Anime [uid=" + uid + ", animeid=" + animeid + ", number=" + number + ", allnumber=" + allnumber
				+ ", data_new=" + data_new + ", data_updata=" + data_updata + "]";
	}
}
