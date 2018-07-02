package entity;

public class ScoreDiscount {
	private int minScore;//积分
	private double discount;//折扣
	public ScoreDiscount() {
		super();
	}
	public ScoreDiscount(int minScore, double discount) {
		super();
		this.minScore = minScore;
		this.discount = discount;
	}
	public int getMinScore() {
		return minScore;
	}
	public void setMinScore(int minScore) {
		this.minScore = minScore;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
}
