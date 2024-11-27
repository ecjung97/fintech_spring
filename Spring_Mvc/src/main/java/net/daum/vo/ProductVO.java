package net.daum.vo;

public class ProductVO { // Data 저장 bean class

	private String name; // 상품명
	private int price; // 상품가격
	
	public ProductVO(String name, int price) {
		this.name=name;
		this.price=price;
	} // 생성자 오버로딩

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}
}
