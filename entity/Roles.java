package entity;

public class Roles {
	private int RoleId;//管理员类别编号
	private String RoleName;//管理员类别名
	public Roles() {
		super();
	}
	public Roles(int roleId, String roleName) {
		super();
		RoleId = roleId;
		RoleName = roleName;
	}
	public int getRoleId() {
		return RoleId;
	}
	public void setRoleId(int roleId) {
		RoleId = roleId;
	}
	public String getRoleName() {
		return RoleName;
	}
	public void setRoleName(String roleName) {
		RoleName = roleName;
	}
	
}
