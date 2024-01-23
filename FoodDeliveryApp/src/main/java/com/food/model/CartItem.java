package com.food.model;

public class CartItem {
	private int itemId;
	private int retaurantId;
	private String itemname;
	private double price;
	private int quantity;
	
	
	public CartItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CartItem(int itemId, int retaurantId, String itemname, double price, int quantity) {
		super();
		this.itemId = itemId;
		this.retaurantId = retaurantId;
		this.itemname = itemname;
		this.price = price;
		this.quantity = quantity;
	}
	
	
	

	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getRetaurantId() {
		return retaurantId;
	}
	public void setRetaurantId(int retaurantId) {
		this.retaurantId = retaurantId;
	}
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public String toString() {
		return "CartItem [itemId=" + itemId + ", retaurantId=" + retaurantId + ", itemname=" + itemname + ", price="
				+ price + ", quantity=" + quantity + "]";
	}
	
}
