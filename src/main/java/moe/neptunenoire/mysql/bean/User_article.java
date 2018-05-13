package moe.neptunenoire.mysql.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户的文章类
 * @author miri
 *
 */
@Entity
@Table(name="user_acticle")
public class User_article {
	/**
	 * 文章的ID
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="article_id")
	private Integer article_id;
	/**
	 * 用户ID
	 */
	@Column(name="userid")
	private Integer userid;
	/**
	 * 用户下的文章url
	 * 生成一个静态html文件
	 */
	@Column(name="article_url")
	private String article_url;
	/**
	 * 动画的id
	 */
	@Column(name="animeid")
	private Integer animeid;
	/**
	 * 动画分集
	 */
	@Column(name="animenumber")
	private Integer animenumber;
	/**
	 * 创建日期
	 */
	@Column(name="data_new")
	private String data_new;
	/**
	 * 更新日期
	 */
	@Column(name="data_updata")
	private String data_updata;
	//==================================
	public Integer getArticle_id() {
		return article_id;
	}
	public void setArticle_id(Integer article_id) {
		this.article_id = article_id;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getArticle_url() {
		return article_url;
	}
	public void setArticle_url(String article_url) {
		this.article_url = article_url;
	}
	public Integer getAnimeid() {
		return animeid;
	}
	public void setAnimeid(Integer animeid) {
		this.animeid = animeid;
	}
	public Integer getAnimenumber() {
		return animenumber;
	}
	public void setAnimenumber(Integer animenumber) {
		this.animenumber = animenumber;
	}
	public String getData_new() {
		return data_new;
	}
	public void setData_new(String data_new) {
		this.data_new = data_new;
	}
	public String getData_updata() {
		return data_updata;
	}
	public void setData_updata(String data_updata) {
		this.data_updata = data_updata;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((animeid == null) ? 0 : animeid.hashCode());
		result = prime * result + ((animenumber == null) ? 0 : animenumber.hashCode());
		result = prime * result + ((article_id == null) ? 0 : article_id.hashCode());
		result = prime * result + ((article_url == null) ? 0 : article_url.hashCode());
		result = prime * result + ((data_new == null) ? 0 : data_new.hashCode());
		result = prime * result + ((data_updata == null) ? 0 : data_updata.hashCode());
		result = prime * result + ((userid == null) ? 0 : userid.hashCode());
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
		User_article other = (User_article) obj;
		if (animeid == null) {
			if (other.animeid != null)
				return false;
		} else if (!animeid.equals(other.animeid))
			return false;
		if (animenumber == null) {
			if (other.animenumber != null)
				return false;
		} else if (!animenumber.equals(other.animenumber))
			return false;
		if (article_id == null) {
			if (other.article_id != null)
				return false;
		} else if (!article_id.equals(other.article_id))
			return false;
		if (article_url == null) {
			if (other.article_url != null)
				return false;
		} else if (!article_url.equals(other.article_url))
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
		if (userid == null) {
			if (other.userid != null)
				return false;
		} else if (!userid.equals(other.userid))
			return false;
		return true;
	}
	public User_article(Integer article_id, Integer userid, String article_url, Integer animeid, Integer animenumber,
			String data_new, String data_updata) {
		super();
		this.article_id = article_id;
		this.userid = userid;
		this.article_url = article_url;
		this.animeid = animeid;
		this.animenumber = animenumber;
		this.data_new = data_new;
		this.data_updata = data_updata;
	}
	public User_article() {
		super();
	}
	@Override
	public String toString() {
		return "User_article [article_id=" + article_id + ", userid=" + userid + ", article_url=" + article_url
				+ ", animeid=" + animeid + ", animenumber=" + animenumber + ", data_new=" + data_new + ", data_updata="
				+ data_updata + "]";
	}
}
