package xyz.bangumi.mysql.bean;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
/**
 * 用户表
 * @author miri
 *
 */
public class Users {
	/**
	 * 用户id
	 */
	private Integer uid;

	@NotEmpty(message = "用户名不能为空哦")
	private String username;

	@NotEmpty(message = "密码不能为空哦")
	@Length(min = 6, message = "密码长度至少为6位哦")
	private String password;

	@NotEmpty(message = "邮箱不能为空哦")
	@Email(message = "您输入的可不是一个邮箱啊")
	private String email;
	/**
	 * 自定义url（必填）
	 */
	@NotEmpty(message = "请填写您的自定义主页URL")
	@Length(max = 128, message = "歪，这也有点太长了吧")
	private String url;
	//==========================
	/**
	 * 用户头像
	 */
	private String userpic;
	/**
	 * 个人信息
	 */
	private String info;
	/**
	 * 对外显示的名字
	 */
	private String pageusername;
	/**
	 * 个人资料背景
	 */
	private String backpic;
	/**
	 * 用户注册时间(仅用来读)
	 */
	private String sign_up_data;
	//===========================================
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
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
	public String getUserpic() {
		return userpic;
	}
	public void setUserpic(String userpic) {
		this.userpic = userpic;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getPageusername() {
		return pageusername;
	}
	public void setPageusername(String pageusername) {
		this.pageusername = pageusername;
	}
	public String getBackpic() {
		return backpic;
	}
	public void setBackpic(String backpic) {
		this.backpic = backpic;
	}
	public String getSign_up_data() {
		return sign_up_data;
	}
	public void setSign_up_data(String sign_up_data) {
		this.sign_up_data = sign_up_data;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((backpic == null) ? 0 : backpic.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((info == null) ? 0 : info.hashCode());
		result = prime * result + ((pageusername == null) ? 0 : pageusername.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((sign_up_data == null) ? 0 : sign_up_data.hashCode());
		result = prime * result + ((uid == null) ? 0 : uid.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		result = prime * result + ((userpic == null) ? 0 : userpic.hashCode());
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
		if (backpic == null) {
			if (other.backpic != null)
				return false;
		} else if (!backpic.equals(other.backpic))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (info == null) {
			if (other.info != null)
				return false;
		} else if (!info.equals(other.info))
			return false;
		if (pageusername == null) {
			if (other.pageusername != null)
				return false;
		} else if (!pageusername.equals(other.pageusername))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (sign_up_data == null) {
			if (other.sign_up_data != null)
				return false;
		} else if (!sign_up_data.equals(other.sign_up_data))
			return false;
		if (uid == null) {
			if (other.uid != null)
				return false;
		} else if (!uid.equals(other.uid))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		if (userpic == null) {
			if (other.userpic != null)
				return false;
		} else if (!userpic.equals(other.userpic))
			return false;
		return true;
	}
	public Users(Integer uid, String username, String password, String email, String url, String userpic, String info,
			String pageusername, String backpic, String sign_up_data) {
		super();
		this.uid = uid;
		this.username = username;
		this.password = password;
		this.email = email;
		this.url = url;
		this.userpic = userpic;
		this.info = info;
		this.pageusername = pageusername;
		this.backpic = backpic;
		this.sign_up_data = sign_up_data;
	}
	public Users() {
		super();
	}
	@Override
	public String toString() {
		return "Users [uid=" + uid + ", username=" + username + ", password=" + password + ", email=" + email + ", url="
				+ url + ", userpic=" + userpic + ", info=" + info + ", pageusername=" + pageusername + ", backpic="
				+ backpic + ", sign_up_data=" + sign_up_data + "]";
	}
	
}
