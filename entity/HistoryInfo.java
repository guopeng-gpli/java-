package entity;

import java.text.DateFormat;

public class HistoryInfo {
	private String CustomerNo;
	private int caNo;
	private String ProductNo;//商品编号
	private int count;//购买数量
	private double price;//单价
	private double discount;//商品折扣
	private String time;//销售时间
	public HistoryInfo() {
		super();
	}
	public HistoryInfo(String CustomerNo,	int caNo,	String ProductNo,	String categoryId,
			int count,double price,double discount,String time) {
		super();
		this.CustomerNo = CustomerNo;
		this.caNo = caNo;
		this.ProductNo = ProductNo;
		this.count = count;
		this.price = price;
		this.discount = discount;
		this.time = time;
	}
	public String getCustomerNo(){
		return CustomerNo;
	}
	public void setCustomerNo(String CustomerNo) {
		this.CustomerNo = CustomerNo;
	}
	
	public int getcaNo(){
		return caNo;
	}
	public void setcaNo(int caNo){
		this.caNo=caNo;
	}
	public String getProductNo(){
		return ProductNo;
	}
	public void setProductNo(String ProductNo){
		this.ProductNo=ProductNo;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
}
