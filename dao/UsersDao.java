package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import service.CategorieService;

import entity.ProductInfo;
import entity.Users;
/*
 * UsersDao类
 * 连接数据库获取返回的对象
 * 将各个数据赋值给变量
 * */
public class UsersDao {
	public List<Users> getAllUsersDao(){
		List<Users> list=new ArrayList<Users>();//n个用户的数据
		Connection conn=DBManager.getConnection();//连接数据库
		PreparedStatement stat=null;//PreparedStatement 接口继承了Statement
		ResultSet rs=null;//返回的对象
		try {
			stat=conn.prepareStatement("select * from users");
			rs=stat.executeQuery();//用statement类的executeQuery()方法来下达select指令以查询数据库，
			while(rs.next()){
				Users user=new Users();
				user.setUserId(rs.getInt(1));//返回的第一个数据
				user.setUserName(rs.getString(2));//返回的第二个数据
				user.setPassword(rs.getString(3));//返回的第三个数据
				user.setRoleId(rs.getInt(4));
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBManager.closeAll(conn, stat, rs);
		}
		return list;
	}
	/*
	 * checkUsersDao判断用户信息是否正确
	 * 判断name password 角色是否对应
	 * */
	public boolean checkUsersDao(String UserName,String Pwd,int roleId){
		boolean flag = false;
		if(UserName.isEmpty()){
			return flag;
		}else if(Pwd.isEmpty()){
			return flag;
		}else{
			UsersDao user = new UsersDao();
			List<Users> l =user.getAllUsersDao();
			boolean tag = false;
			for (Users a : l) {
				if(UserName.equals(a.getUserName()) && Pwd.equals(a.getPassword()) && roleId == a.getRoleId()){
					tag = true;
					flag = true;
				}
			}
			if(!tag){
				return flag;
			}
		}
		return flag;
	}
	/*
	 * 新建用户
	 * 
	 * */
	
	
	public boolean newUsersDao(Users user){
		boolean flag = false;
		String sql = "insert into users values(?,?,?)";
		Object[] objs = {user.getUserName(),user.getPassword(),user.getRoleId()};
		flag = DBManager.executeUpdate(sql, objs);
		return flag;
	}
	public boolean deleteUsersDao(int userId){
		boolean flag = false;
		String sql = "delete from users where userId =?";
		Object[] objs = {userId};
		flag = DBManager.executeUpdate(sql, objs);
		return flag;
	}
	public int getUserIdByUserName(String userName){
		int userId = 1000;
		Connection conn=DBManager.getConnection();
		PreparedStatement stat=null;
		ResultSet rs=null;
		try {
			stat=conn.prepareStatement("select userId from users where userName = ?");
			stat.setString(1, userName);
			rs=stat.executeQuery();
			if(rs.next()){
				userId = rs.getInt(1);
			}else{
				JOptionPane.showMessageDialog(null,"查询的管理员信息不存在","错误提示",JOptionPane.ERROR_MESSAGE);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBManager.closeAll(conn, stat, rs);
		}
		return userId;
	}
	public Users getUsersByUserId(int userId){
		Users user = new Users();
		Connection conn=DBManager.getConnection();
		PreparedStatement stat=null;
		ResultSet rs=null;
		try {
			stat=conn.prepareStatement("select * from users where userId =?");
			stat.setInt(1, userId);
			rs=stat.executeQuery();
			if(rs.next()){
				user.setUserId(rs.getInt(1));
				user.setUserName(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setRoleId(rs.getInt(4));
			}else{
				JOptionPane.showMessageDialog(null,"查询的管理员信息不存在","错误提示",JOptionPane.ERROR_MESSAGE);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBManager.closeAll(conn, stat, rs);
		}
		return user;
	}
	public boolean updateUserPasswordDao(int userId,String password){
		boolean flag = false;
		String sql = "update users set password = ? where userId = ?";
		Object[] objs = {password,userId};
		flag = DBManager.executeUpdate(sql, objs);
		return flag;
	}
}
