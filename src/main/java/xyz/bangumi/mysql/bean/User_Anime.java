package xyz.bangumi.mysql.bean;

public class User_Anime {
	/**
	 * 用户id
	 */
	private Integer uid;
	/**
	 * 用户订阅的动画
	 */
	private Integer animeid;
	/**
	 * 用户看到的集数
	 */
	private Integer number;
	/**
	 * 动画的所有集数
	 */
	private Integer allnumber;
	/**
	 * 用户写的文章备忘录
	 */
	private String article;
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
	public String getArticle() {
		return article;
	}
	public void setArticle(String article) {
		this.article = article;
	}
	public User_Anime(Integer uid, Integer animeid, Integer number, Integer allnumber, String article) {
		super();
		this.uid = uid;
		this.animeid = animeid;
		this.number = number;
		this.allnumber = allnumber;
		this.article = article;
	}
	public User_Anime() {
		super();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((allnumber == null) ? 0 : allnumber.hashCode());
		result = prime * result + ((animeid == null) ? 0 : animeid.hashCode());
		result = prime * result + ((article == null) ? 0 : article.hashCode());
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
		if (article == null) {
			if (other.article != null)
				return false;
		} else if (!article.equals(other.article))
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
	@Override
	public String toString() {
		return "User_Anime [uid=" + uid + ", animeid=" + animeid + ", number=" + number + ", allnumber=" + allnumber
				+ ", article=" + article + "]";
	}
	
}
