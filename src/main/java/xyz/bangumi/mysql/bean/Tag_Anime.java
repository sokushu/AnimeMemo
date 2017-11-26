package xyz.bangumi.mysql.bean;
/**
 * tag和动画的对应表
 * @author miri
 *
 */
public class Tag_Anime {
	/**
	 * 标签ID
	 */
	private Integer tagid;
	/**
	 * 动画ID
	 */
	private Integer animeid;
	public Integer getTagid() {
		return tagid;
	}
	public void setTagid(Integer tagid) {
		this.tagid = tagid;
	}
	public Integer getAnimeid() {
		return animeid;
	}
	public void setAnimeid(Integer animeid) {
		this.animeid = animeid;
	}
	public Tag_Anime(Integer tagid, Integer animeid) {
		super();
		this.tagid = tagid;
		this.animeid = animeid;
	}
	public Tag_Anime() {
		super();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((animeid == null) ? 0 : animeid.hashCode());
		result = prime * result + ((tagid == null) ? 0 : tagid.hashCode());
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
		Tag_Anime other = (Tag_Anime) obj;
		if (animeid == null) {
			if (other.animeid != null)
				return false;
		} else if (!animeid.equals(other.animeid))
			return false;
		if (tagid == null) {
			if (other.tagid != null)
				return false;
		} else if (!tagid.equals(other.tagid))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Tag_Anime [tagid=" + tagid + ", animeid=" + animeid + "]";
	}
	
}
