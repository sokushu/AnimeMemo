package moe.neptunenoire.mysql.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="message")
public class Message {
	/**
	 * 信息的id
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Integer id;
	/**
	 * 谁发的
	 * 
	 */
	@Column(name="fromuid")
	private Integer fromUID;
	/**
	 * 发给谁的
	 */
	@Column(name="touid")
	private Integer toUID;
	/**
	 * 信息内容
	 */
	@Column(name="message")
	private String message;
	/**
	 * 是否已读 已读 1 未读 0
	 */
	@Column(name="readed")
	private Integer readed;
	/**
	 * 发送日期(只用来读)
	 */
	@CreationTimestamp
	@Column(name="date")
	private Date date;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getFromUID() {
		return fromUID;
	}
	public void setFromUID(Integer fromUID) {
		this.fromUID = fromUID;
	}
	public Integer getToUID() {
		return toUID;
	}
	public void setToUID(Integer toUID) {
		this.toUID = toUID;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getReaded() {
		return readed;
	}
	public void setReaded(Integer readed) {
		this.readed = readed;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((fromUID == null) ? 0 : fromUID.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((readed == null) ? 0 : readed.hashCode());
		result = prime * result + ((toUID == null) ? 0 : toUID.hashCode());
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
		Message other = (Message) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (fromUID == null) {
			if (other.fromUID != null)
				return false;
		} else if (!fromUID.equals(other.fromUID))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (readed == null) {
			if (other.readed != null)
				return false;
		} else if (!readed.equals(other.readed))
			return false;
		if (toUID == null) {
			if (other.toUID != null)
				return false;
		} else if (!toUID.equals(other.toUID))
			return false;
		return true;
	}
	public Message(Integer id, Integer fromUID, Integer toUID, String message, Integer readed, Date date) {
		super();
		this.id = id;
		this.fromUID = fromUID;
		this.toUID = toUID;
		this.message = message;
		this.readed = readed;
		this.date = date;
	}
	public Message() {
		super();
	}
	@Override
	public String toString() {
		return "Message [id=" + id + ", fromUID=" + fromUID + ", toUID=" + toUID + ", message=" + message + ", readed="
				+ readed + ", date=" + date + "]";
	}
	
}
