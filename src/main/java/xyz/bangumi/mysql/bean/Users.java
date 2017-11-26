package xyz.bangumi.mysql.bean;

public class Users {
	/**
	 * 用户id
	 */
	private Integer uid;
	private String username;
	private String password;
	private String email;
	/**
	 * 自定义url（必填）
	 */
	private String url;
	//==========================
	/**
	 * 用户头像
	 */
	private String user_pic;
	/**
	 * 个人信息
	 */
	private String info;
	/**
	 * 对外显示的名字
	 */
	private String name;
	/**
	 * 个人资料背景
	 */
	private String back_pic;
	public Integer getId() {
		return uid;
	}
	public void setId(Integer uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUser_pic() {
		return user_pic;
	}
	public void setUser_pic(String user_pic) {
		this.user_pic = user_pic;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBack_pic() {
		return back_pic;
	}
	public void setBack_pic(String back_pic) {
		this.back_pic = back_pic;
	}
	public Users(Integer id, String username, String password, String email, String url, String user_pic, String info,
			String name, String back_pic) {
		super();
		this.uid = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.url = url;
		this.user_pic = user_pic;
		this.info = info;
		this.name = name;
		this.back_pic = back_pic;
	}
	public Users() {
		super();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((back_pic == null) ? 0 : back_pic.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((uid == null) ? 0 : uid.hashCode());
		result = prime * result + ((info == null) ? 0 : info.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		result = prime * result + ((user_pic == null) ? 0 : user_pic.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		Users other = (Users) obj;
		if (back_pic == null) {
			if (other.back_pic != null)
				return false;
		} else if (!back_pic.equals(other.back_pic))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (uid == null) {
			if (other.uid != null)
				return false;
		} else if (!uid.equals(other.uid))
			return false;
		if (info == null) {
			if (other.info != null)
				return false;
		} else if (!info.equals(other.info))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		if (user_pic == null) {
			if (other.user_pic != null)
				return false;
		} else if (!user_pic.equals(other.user_pic))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Users [id=" + uid + ", username=" + username + ", password=" + password + ", email=" + email + ", url="
				+ url + ", user_pic=" + user_pic + ", info=" + info + ", name=" + name + ", back_pic=" + back_pic + "]";
	}
	
}
