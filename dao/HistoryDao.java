package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import service.CategorieService;
import entity.Customers;
import entity.HistoryInfo;



public class HistoryDao {
	
	
	public List<HistoryInfo> getHistoryInfoByCustomerNo(String customerNo){
		List<HistoryInfo> list=new ArrayList<HistoryInfo>();
		Connection conn=DBManager.getConnection();
		PreparedStatement stat0=null;
		ResultSet rs0=null;
		try {
			stat0=conn.prepareStatement("select * from customers where customer_no = ?");
			stat0.setString(1, customerNo);
			rs0=stat0.executeQuery();
			if(!rs0.next()){
			JOptionPane.showMessageDialog(null,"查询的会员号不存在","错误提示",JOptionPane.ERROR_MESSAGE);
		}
			else{stat0=conn.prepareStatement("select * from sale_history where customer_no = ?");
			stat0.setString(1, customerNo);
			rs0=stat0.executeQuery();
			if(!rs0.next()){
			JOptionPane.showMessageDialog(null,"查询的会员号未有消费记录","错误提示",JOptionPane.ERROR_MESSAGE);
		}}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		PreparedStatement stat=null;
		ResultSet rs=null;
		try {
			stat=conn.prepareStatement("select * from sale_history where customer_no =?");
			stat.setString(1, customerNo);
			rs=stat.executeQuery();

			while(rs.next()){
				HistoryInfo history = new HistoryInfo();
				history.setProductNo(rs.getString(2));
				history.setCount(rs.getInt(3));
				history.setPrice(rs.getDouble(4));
				history.setDiscount(rs.getDouble(5));
				history.setcaNo(rs.getInt(6));
				history.setTime(rs.getString(7));
				history.setCustomerNo(rs.getString(8));	
				list.add(history);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBManager.closeAll(conn, stat, rs);
		}
		return list;
	}
	
	
	
	public List<HistoryInfo> getHistoryInfoBycaNo(String caNo){
		List<HistoryInfo> list=new ArrayList<HistoryInfo>();
		Connection conn=DBManager.getConnection();
		PreparedStatement stat0=null;
		ResultSet rs0=null;
		try {
			stat0=conn.prepareStatement("select * from users where userId = ?");
			stat0.setString(1, caNo);
			rs0=stat0.executeQuery();
			if(!rs0.next()){
			JOptionPane.showMessageDialog(null,"查询的收银台号不存在","错误提示",JOptionPane.ERROR_MESSAGE);
		}
			else{stat0=conn.prepareStatement("select * from sale_history where userId = ?");
			stat0.setString(1, caNo);
			rs0=stat0.executeQuery();
			if(!rs0.next()){
			JOptionPane.showMessageDialog(null,"查询的收银台未有工作记录","错误提示",JOptionPane.ERROR_MESSAGE);
		}}
		} catch (Exception e) {
			e.printStackTrace();
		}
		PreparedStatement stat=null;
		ResultSet rs=null;
		try {
			stat=conn.prepareStatement("select * from sale_history where userId =?");
			stat.setString(1,caNo);
			rs=stat.executeQuery();
			while(rs.next()){
				HistoryInfo history = new HistoryInfo();
				history.setProductNo(rs.getString(2));
				history.setCount(rs.getInt(3));
				history.setPrice(rs.getDouble(4));
				history.setDiscount(rs.getDouble(5));
				history.setcaNo(rs.getInt(6));
				history.setTime(rs.getString(7));
				history.setCustomerNo(rs.getString(8));	

				list.add(history);
				System.out.println(rs.getString(2));
				
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBManager.closeAll(conn, stat, rs);
		}
		return list;
	}
	
	
	/*
	 *  
	 * */		
	public HistoryInfo getHistoryInfoByCustomerNo1(String customerNo){
		HistoryInfo history = new HistoryInfo();
		Connection conn=DBManager.getConnection();
		PreparedStatement stat=null;
		ResultSet rs=null;
		try {
			stat=conn.prepareStatement("select * from sale_history where customer_no =?");
			stat.setString(1, customerNo);
			rs=stat.executeQuery();
			if(rs.next()){
				history.setProductNo(rs.getString(2));
				history.setCount(rs.getInt(3));
				history.setPrice(rs.getDouble(4));
				history.setDiscount(rs.getDouble(5));
				history.setcaNo(rs.getInt(6));
				history.setTime(rs.getString(7));
				history.setCustomerNo(rs.getString(8));	
			}else{
				JOptionPane.showMessageDialog(null,"查询的会员号不存在","错误提示",JOptionPane.ERROR_MESSAGE);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBManager.closeAll(conn, stat, rs);
		}
		return history;
	}
}
