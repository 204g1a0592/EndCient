package com.edureka.training.entity;

//CartItem.java
public class CartItem {
 private Long cartItemId;
 private Long cartId;      // reference cart id
 private Long productId;   // product id
 private Integer quantity; // quantity added
 private Double unitPrice; // price at time of add
public Long getCartItemId() {
	return cartItemId;
}
public void setCartItemId(Long cartItemId) {
	this.cartItemId = cartItemId;
}
public Long getCartId() {
	return cartId;
}
public void setCartId(Long cartId) {
	this.cartId = cartId;
}
public Long getProductId() {
	return productId;
}
public void setProductId(Long productId) {
	this.productId = productId;
}
public Integer getQuantity() {
	return quantity;
}
public void setQuantity(Integer quantity) {
	this.quantity = quantity;
}
public Double getUnitPrice() {
	return unitPrice;
}
public void setUnitPrice(Double unitPrice) {
	this.unitPrice = unitPrice;
}
 
}
