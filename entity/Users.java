package entity;

public class Users {
	private int userId;//管理员编号
	private String userName;//管理员姓名
	private String password;//管理员密码
	private int roleId;//管理员级别
	public Users() {
		super();
	}
	public Users(int userId, String userName, String password, int roleId) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.roleId = roleId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
}
