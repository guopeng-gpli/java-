package entity;

public class SaleSummary extends SaleHistory{
	private double memberDiscount;//会员折扣
	private double totalAmount;//单行总金额
	public double getMemberDiscount() {
		return memberDiscount;
	}
	public void setMemberDiscount(double memberDiscount) {
		this.memberDiscount = memberDiscount;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public SaleSummary() {
		super();
	}
	public SaleSummary(double memberDiscount, double totalAmount) {
		super();
		this.memberDiscount = memberDiscount;
		this.totalAmount = totalAmount;
	}
}
