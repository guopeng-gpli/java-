package entity;

public class Customers {
	private String customerNo;//会员编号
	private String customerName;//会员姓名
	private String phone;//会员电话
	private int score;//会员积分
	
	public Customers() {
		super();
	}
	public Customers(String customerNo, String customerName, String phone,
			int score) {
		super();
		this.customerNo = customerNo;
		this.customerName = customerName;
		this.phone = phone;
		this.score = score;
	}
	public String getCustomerNo() {
		return customerNo;
	}
	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
}
