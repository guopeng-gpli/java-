package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.ScoreDiscount;

public class ScoreDiscountDao {
	public List<ScoreDiscount> getAllScoreDiscountDao(){
		List<ScoreDiscount> list=new ArrayList<ScoreDiscount>();
		Connection conn=DBManager.getConnection();
		PreparedStatement stat=null;
		ResultSet rs=null;
		try {
			stat=conn.prepareStatement("select * from score_discount");
			rs=stat.executeQuery();
			while(rs.next()){
				ScoreDiscount sc = new ScoreDiscount();
				sc.setMinScore(rs.getInt(1));
				sc.setDiscount(rs.getFloat(2));
				list.add(sc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBManager.closeAll(conn, stat, rs);
		}
		return list;
	}
}
