package entity;

public class SaleHistory {
	private int id;//记录编号
	private String productNo;//商品编号
	private int saleCount;//购买数量
	private double price;//商品单价
	private double discount;//商品折扣
	private int userId;//管理员编号
	private String saleTime;//售出时间
	private int customerNo;//消费者编号
	public SaleHistory() {
		super();
	}
	public SaleHistory(int id, String productNo, int saleCount, double price,
			double discount, int userId, String saleTime, int customerNo) {
		super();
		this.id = id;
		this.productNo = productNo;
		this.saleCount = saleCount;
		this.price = price;
		this.discount = discount;
		this.userId = userId;
		this.saleTime = saleTime;
		this.customerNo = customerNo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	public int getSaleCount() {
		return saleCount;
	}
	public void setSaleCount(int saleCount) {
		this.saleCount = saleCount;
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
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getSaleTime() {
		return saleTime;
	}
	public void setSaleTime(String saleTime) {
		this.saleTime = saleTime;
	}
	public int getCustomerNo() {
		return customerNo;
	}
	public void setCustomerNo(int customerNo) {
		this.customerNo = customerNo;
	}
	
}
