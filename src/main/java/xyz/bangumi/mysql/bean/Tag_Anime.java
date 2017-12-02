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
	private Integer tag_id;
	/**
	 * 动画ID
	 */
	private Integer anime_id;
	/**
	 * 动画名
	 */
	private String animename;
	/**
	 * 创建日期（只读）
	 */
	private String date_new;
	/**
	 * 更新日期（只读）
	 */
	private String date_update;
	public Integer getTag_id() {
		return tag_id;
	}
	public void setTag_id(Integer tag_id) {
		this.tag_id = tag_id;
	}
	public Integer getAnime_id() {
		return anime_id;
	}
	public void setAnime_id(Integer anime_id) {
		this.anime_id = anime_id;
	}
	public String getAnimename() {
		return animename;
	}
	public void setAnimename(String animename) {
		this.animename = animename;
	}
	public String getDate_new() {
		return date_new;
	}
	public void setDate_new(String date_new) {
		this.date_new = date_new;
	}
	public String getDate_update() {
		return date_update;
	}
	public void setDate_update(String date_update) {
		this.date_update = date_update;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((anime_id == null) ? 0 : anime_id.hashCode());
		result = prime * result + ((animename == null) ? 0 : animename.hashCode());
		result = prime * result + ((date_new == null) ? 0 : date_new.hashCode());
		result = prime * result + ((date_update == null) ? 0 : date_update.hashCode());
		result = prime * result + ((tag_id == null) ? 0 : tag_id.hashCode());
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
		if (anime_id == null) {
			if (other.anime_id != null)
				return false;
		} else if (!anime_id.equals(other.anime_id))
			return false;
		if (animename == null) {
			if (other.animename != null)
				return false;
		} else if (!animename.equals(other.animename))
			return false;
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
		return true;
	}
	public Tag_Anime(Integer tag_id, Integer anime_id, String animename, String date_new, String date_update) {
		super();
		this.tag_id = tag_id;
		this.anime_id = anime_id;
		this.animename = animename;
		this.date_new = date_new;
		this.date_update = date_update;
	}
	public Tag_Anime() {
		super();
	}
	@Override
	public String toString() {
		return "Tag_Anime [tag_id=" + tag_id + ", anime_id=" + anime_id + ", animename=" + animename + ", date_new="
				+ date_new + ", date_update=" + date_update + "]";
	}
}
