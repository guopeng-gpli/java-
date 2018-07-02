package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategorieDao {
	public String getCategoryNameDao(String categoryId){
		String categoryName = "";
		Connection conn=DBManager.getConnection();
		PreparedStatement stat=null;
		ResultSet rs=null;
		try {
			stat=conn.prepareStatement("select * from categories where category_id = ?");
			stat.setString(1, categoryId);
			rs=stat.executeQuery();
			rs.next();
			categoryName =rs.getString(2);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBManager.closeAll(conn, stat, rs);
		}
		return categoryName;
	}
}
