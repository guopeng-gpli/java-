package entity;

public class ProductInfo extends Products{
	private String categoryName;//商品种类名字

	public ProductInfo() {
		super();
	}

	public ProductInfo(String categoryName) {
		super();
		this.categoryName = categoryName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
}
