package entity;

public class Products {
	private String productNo;//商品编号
	private String categoryId;//种类编号
	private String productName;//商品名
	private String type;//规格
	private String unit;//单位
	private int count;//库存数量
	private double price;//单价
	private double discount;//折扣
	public Products() {
		super();
	}
	public Products(String productNo, String categoryId, String productName,
			String type, String unit, int count, double price, double discount) {
		super();
		this.productNo = productNo;
		this.categoryId = categoryId;
		this.productName = productName;
		this.type = type;
		this.unit = unit;
		this.count = count;
		this.price = price;
		this.discount = discount;
	}
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
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
	
}
