package xyz.bangumi.mysql.bean;

public class Tag {
	private Integer tag_id;
	private String tag_name;
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
	public Tag(Integer tag_id, String tag_name) {
		super();
		this.tag_id = tag_id;
		this.tag_name = tag_name;
	}
	public Tag() {
		super();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
	@Override
	public String toString() {
		return "Tag [tag_id=" + tag_id + ", tag_name=" + tag_name + "]";
	}
	
}
