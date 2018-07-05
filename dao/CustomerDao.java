package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import service.CategorieService;
import service.ScoreDiscountService;

import entity.Customers;
import entity.ScoreDiscount;
import entity.Users;

public class CustomerDao {
	public List<Customers> getAllCustomersDao(){
		List<Customers> list=new ArrayList<Customers>();
		Connection conn=DBManager.getConnection();
		PreparedStatement stat=null;
		ResultSet rs=null;
		try {
			stat=conn.prepareStatement("select * from customers");
			rs=stat.executeQuery();
			while(rs.next()){
				Customers customer=new Customers();
				customer.setCustomerNo(rs.getInt(1));
				customer.setCustomerName(rs.getString(2));
				customer.setPhone(rs.getString(3));
				customer.setScore(rs.getInt(4));
				list.add(customer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBManager.closeAll(conn, stat, rs);
		}
		return list;
	}
	public int newCustomersDao(Customers customer){
		boolean flag = false;
		String sql = "insert into customers (customer_name,phone,score) values(?,?,?)";
		Object[] objs = {customer.getCustomerName(),customer.getPhone(),0};
		flag = DBManager.executeUpdate(sql, objs);
		
		
		
		
		int customerNo = 0;
		Connection conn=DBManager.getConnection();
		PreparedStatement stat=null;
		ResultSet rs=null;
		try {
			stat=conn.prepareStatement("select customer_no from customers where customer_name= ? ");
			stat.setString(1,customer.getCustomerName());
			rs=stat.executeQuery();
			if(rs.next()){
				 customerNo = rs.getInt(1);
			}else{
				JOptionPane.showMessageDialog(null,"新增会员错误","错误提示",JOptionPane.ERROR_MESSAGE);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBManager.closeAll(conn, stat, rs);
		}
		return customerNo;
	}
	public boolean deleteCustomersDao(String customerNo){
		boolean flag = false;
		String sql = "delete from customers where customer_no =?";
		Object[] objs = {customerNo};
		flag = DBManager.executeUpdate(sql, objs);
		return flag;
	}
	public boolean checkCustomerDao(int customerNo){
		boolean flag = false;
		Connection conn=DBManager.getConnection();
		PreparedStatement stat=null;
		ResultSet rs=null;
		try {
			stat=conn.prepareStatement("select * from customers where customer_no = ?");
			stat.setInt(1, customerNo);
			rs=stat.executeQuery();
			if(rs.next()){
				flag = true;
			}else{
				JOptionPane.showMessageDialog(null,"该会员不存在","错误提示",JOptionPane.ERROR_MESSAGE);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBManager.closeAll(conn, stat, rs);
		}
		return flag;
	}
	public boolean checkCustomerNoDao(String customerNo){
		boolean flag = false;
		Connection conn=DBManager.getConnection();
		PreparedStatement stat=null;
		ResultSet rs=null;
		try {
			stat=conn.prepareStatement("select * from customers where customer_no = ?");
			stat.setString(1, customerNo);
			rs=stat.executeQuery();
			if(rs.next()){
				JOptionPane.showMessageDialog(null,"该会员编号已经存在","错误提示",JOptionPane.ERROR_MESSAGE);
			}else{
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBManager.closeAll(conn, stat, rs);
		}
		return flag;
	}
	public double getMemberDiscountByCustomerNoDao(int customerNo){
		double discount = 1;
		Connection conn=DBManager.getConnection();
		PreparedStatement stat=null;
		ResultSet rs=null;
		try {
			stat=conn.prepareStatement("select * from customers where customer_no = ?");
			stat.setInt(1, customerNo);
			rs=stat.executeQuery();
			if(rs.next()){
				int score = rs.getInt(4);
				ScoreDiscountService sds = new ScoreDiscountService();
				List<ScoreDiscount> list = sds.getAllScoreDiscountService();
				for (ScoreDiscount scoreDiscount : list) {
					if(score>=scoreDiscount.getMinScore()){
						discount = scoreDiscount.getDiscount();
					}
				}
			}else{
				JOptionPane.showMessageDialog(null,"该会员不存在","错误提示",JOptionPane.ERROR_MESSAGE);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBManager.closeAll(conn, stat, rs);
		}
		return discount;
	}
	public boolean updateCustomerScoreDao(int customerNo,int score){
		boolean flag = false;
		String sql = "update customers set score=score+ ? where customer_no = ?";
		Object[] objs = {score,customerNo};
		flag = DBManager.executeUpdate(sql, objs);
		return flag;
	}
}
