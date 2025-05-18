package com.edureka.training.entity;

//InvoiceItem.java
public class InvoiceItem {
	 private Long id;

	    private String productName;
	    private double priceAtPurchase;
	    private int quantity;
	    
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getProductName() {
			return productName;
		}
		public void setProductName(String productName) {
			this.productName = productName;
		}
		public double getPriceAtPurchase() {
			return priceAtPurchase;
		}
		public void setPriceAtPurchase(double priceAtPurchase) {
			this.priceAtPurchase = priceAtPurchase;
		}
		public int getQuantity() {
			return quantity;
		}
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}

}