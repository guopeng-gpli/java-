package service;

import java.util.List;

import dao.UsersDao;

import entity.Users;

public class UsersService {
	public List<Users> getAllUsersService(){
		UsersDao ud = new UsersDao();
		return ud.getAllUsersDao();
	}
	public boolean checkUsersService(String UserName,String Pwd,int roleId){
		UsersDao ud = new UsersDao();
		return ud.checkUsersDao(UserName, Pwd, roleId);
	}
	public boolean deleteUsersService(int userId){
		UsersDao ud = new UsersDao();
		return ud.deleteUsersDao(userId);
	}
	public boolean newUsersService(Users user){
		UsersDao ud = new UsersDao();
		return ud.newUsersDao(user);
	}
	public int getUserIdByUserNameService(String userName){
		UsersDao ud = new UsersDao();
		return ud.getUserIdByUserName(userName);
	}
	public Users getUsersByUserIdService(int userId){
		UsersDao ud = new UsersDao();
		return ud.getUsersByUserId(userId);
	}
	public boolean updateUserPasswordService(int userId,String password){
		UsersDao ud = new UsersDao();
		return ud.updateUserPasswordDao(userId, password);
	}
}
