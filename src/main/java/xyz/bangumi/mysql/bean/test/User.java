package xyz.bangumi.mysql.bean.test;

/**
 * 用户类
 */
public class User {
    /** 用户ID */
    private Integer uid;//通过一些计算得出来
    /** 用户名 */
    private String username;
    /** 密码 */
    private String password;
    /** 用户联系方式 */
    private String email;//邮箱
    private Integer qq;  //QQ
    private String other;//其他的联系方式
    /** 用户头像 */
    private String Profile_pic; //有一个默认头像
    /** 用户的自定义url */
    private String url;//如果没有url则显示UID
    /** 用户昵称 */
    private String nickname; //如果没有昵称则显示用户名
    /** 个人资料背景 */
    private String backpic;//有一个默认背景
    /** 用户提供的个人信息 */
    private String info;

    //====================================================
    /** 用户的权限 */
    private Integer authority;
    /** 用户注册日期 */
    private String sign_up_data;//（仅仅用来读取）
	




    //*************************************************** */
    //*************************************************** */
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
	public Integer getQq() {
		return qq;
	}
	public void setQq(Integer qq) {
		this.qq = qq;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public String getProfile_pic() {
		return Profile_pic;
	}
	public void setProfile_pic(String profile_pic) {
		Profile_pic = profile_pic;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getBackpic() {
		return backpic;
	}
	public void setBackpic(String backpic) {
		this.backpic = backpic;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public Integer getAuthority() {
		return authority;
	}
	public void setAuthority(Integer authority) {
		this.authority = authority;
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
		result = prime * result + ((Profile_pic == null) ? 0 : Profile_pic.hashCode());
		result = prime * result + ((authority == null) ? 0 : authority.hashCode());
		result = prime * result + ((backpic == null) ? 0 : backpic.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((info == null) ? 0 : info.hashCode());
		result = prime * result + ((nickname == null) ? 0 : nickname.hashCode());
		result = prime * result + ((other == null) ? 0 : other.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((qq == null) ? 0 : qq.hashCode());
		result = prime * result + ((sign_up_data == null) ? 0 : sign_up_data.hashCode());
		result = prime * result + ((uid == null) ? 0 : uid.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
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
		User other = (User) obj;
		if (Profile_pic == null) {
			if (other.Profile_pic != null)
				return false;
		} else if (!Profile_pic.equals(other.Profile_pic))
			return false;
		if (authority == null) {
			if (other.authority != null)
				return false;
		} else if (!authority.equals(other.authority))
			return false;
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
		if (nickname == null) {
			if (other.nickname != null)
				return false;
		} else if (!nickname.equals(other.nickname))
			return false;
		if (this.other == null) {
			if (other.other != null)
				return false;
		} else if (!this.other.equals(other.other))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (qq == null) {
			if (other.qq != null)
				return false;
		} else if (!qq.equals(other.qq))
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
		return true;
	}
	public User(Integer uid, String username, String password, String email, Integer qq, String other,
			String profile_pic, String url, String nickname, String backpic, String info, Integer authority,
			String sign_up_data) {
		super();
		this.uid = uid;
		this.username = username;
		this.password = password;
		this.email = email;
		this.qq = qq;
		this.other = other;
		Profile_pic = profile_pic;
		this.url = url;
		this.nickname = nickname;
		this.backpic = backpic;
		this.info = info;
		this.authority = authority;
		this.sign_up_data = sign_up_data;
	}
	public User() {
		super();
	}
	@Override
	public String toString() {
		return "User [uid=" + uid + ", username=" + username + ", password=" + password + ", email=" + email + ", qq="
				+ qq + ", other=" + other + ", Profile_pic=" + Profile_pic + ", url=" + url + ", nickname=" + nickname
				+ ", backpic=" + backpic + ", info=" + info + ", authority=" + authority + ", sign_up_data="
				+ sign_up_data + "]";
	}
	
}