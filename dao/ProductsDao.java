package dao;

import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import service.CategorieService;
import ui.GoodsAdministrator;

import entity.Customers;
import entity.ProductInfo;
import entity.Products;

public class ProductsDao {
	public List<ProductInfo> getAllProductsDao(){
		List<ProductInfo> list=new ArrayList<ProductInfo>();
		Connection conn=DBManager.getConnection();
		PreparedStatement stat=null;
		ResultSet rs=null;
		try {
			stat=conn.prepareStatement("select * from products");
			rs=stat.executeQuery();
			while(rs.next()){
				ProductInfo product = new ProductInfo();
				product.setProductNo(rs.getString(1));
				product.setCategoryId(rs.getString(2));
				product.setProductName(rs.getString(3));
				product.setType(rs.getString(4));
				product.setUnit(rs.getString(5));
				product.setCount(rs.getInt(6));
				product.setPrice(rs.getDouble(7));
				product.setDiscount(rs.getDouble(8));
				CategorieService cs = new CategorieService();
				product.setCategoryName(cs.getCategoryNameService(product.getCategoryId()));
				list.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBManager.closeAll(conn, stat, rs);
		}
		return list;
	}
	public ProductInfo getProductInfoByProductNo(String ProductNo){
		ProductInfo product = new ProductInfo();
		Connection conn=DBManager.getConnection();
		PreparedStatement stat=null;
		ResultSet rs=null;
		try {
			stat=conn.prepareStatement("select * from products where product_no = ?");
			stat.setString(1, ProductNo);
			rs=stat.executeQuery();
			if(rs.next()){
			product.setProductNo(rs.getString(1));
			product.setCategoryId(rs.getString(2));
			product.setProductName(rs.getString(3));
			product.setType(rs.getString(4));
			product.setUnit(rs.getString(5));
			product.setCount(rs.getInt(6));
			product.setPrice(rs.getDouble(7));
			product.setDiscount(rs.getDouble(8));
			CategorieService cs = new CategorieService();
			product.setCategoryName(cs.getCategoryNameService(product.getCategoryId()));
			}else{
				JOptionPane.showMessageDialog(null,"查询的商品不存在","错误提示",JOptionPane.ERROR_MESSAGE);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBManager.closeAll(conn, stat, rs);
		}
		return product;
	}
	public ProductInfo getProductInfoByproductName(String productName){
		ProductInfo product = new ProductInfo();
		Connection conn=DBManager.getConnection();
		PreparedStatement stat=null;
		ResultSet rs=null;
		try {
			stat=conn.prepareStatement("select * from products where product_name =?");
			stat.setString(1, productName);
			rs=stat.executeQuery();
			if(rs.next()){
				product.setProductNo(rs.getString(1));
				product.setCategoryId(rs.getString(2));
				product.setProductName(rs.getString(3));
				product.setType(rs.getString(4));
				product.setUnit(rs.getString(5));
				product.setCount(rs.getInt(6));
				product.setPrice(rs.getDouble(7));
				product.setDiscount(rs.getDouble(8));
				CategorieService cs = new CategorieService();
				product.setCategoryName(cs.getCategoryNameService(product.getCategoryId()));
			}else{
				JOptionPane.showMessageDialog(null,"查询的商品不存在","错误提示",JOptionPane.ERROR_MESSAGE);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBManager.closeAll(conn, stat, rs);
		}
		return product;
	}
	public boolean newProductsDao(Products product){
		boolean flag = false;
		String sql = "insert into products values(?,?,?,?,?,?,?,?)";
		Object[] objs = {product.getProductNo(),product.getCategoryId(),product.getProductName(),product.getType(),product.getUnit()
				,product.getCount(),product.getPrice(),product.getDiscount()};
		flag = DBManager.executeUpdate(sql, objs);
		return flag;
	}
	public boolean deleteProductsDao(String productNo){
		boolean flag = false;
		String sql = "delete from products where product_no =?";
		Object[] objs = {productNo};
		flag = DBManager.executeUpdate(sql, objs);
		return flag;
	}
	public boolean updateProductsDao(String productNo,int count,double discount){
		boolean flag = false;
		String sql = "update products set count = ?,discount = ? where product_no = ?";
		Object[] objs = {count,discount,productNo};
		flag = DBManager.executeUpdate(sql, objs);
		return flag;
	}
	public boolean updateProductsCountDao(String productNo,int soldNumber){
		boolean flag = false;
		int count = 0;
		ProductsDao pd = new ProductsDao();
		count = pd.getProductInfoByProductNo(productNo).getCount()-soldNumber;
		String sql = "update products set count = ? where product_no = ?";
		Object[] objs = {count,productNo};
		flag = DBManager.executeUpdate(sql, objs);
		return flag;
	}
	public boolean checkProductsNoDao(String ProductsNo){
		boolean flag = false;
		Connection conn=DBManager.getConnection();
		PreparedStatement stat=null;
		ResultSet rs=null;
		try {
			stat=conn.prepareStatement("select * from products where product_no = ?");
			stat.setString(1, ProductsNo);
			rs=stat.executeQuery();
			if(rs.next()){
				JOptionPane.showMessageDialog(null,"该商品编号已经存在","错误提示",JOptionPane.ERROR_MESSAGE);
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
}
