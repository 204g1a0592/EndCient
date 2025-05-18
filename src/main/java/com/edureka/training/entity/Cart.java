package com.edureka.training.entity;

//Cart.java
import java.util.Set;

public class Cart {
 private Long cartId;
 private Long userId;  // reference user id (no object)
 private Set<CartItem> cartItems;  // items in this cart
public Long getCartId() {
	return cartId;
}
public void setCartId(Long cartId) {
	this.cartId = cartId;
}
public Long getUserId() {
	return userId;
}
public void setUserId(Long userId) {
	this.userId = userId;
}
public Set<CartItem> getCartItems() {
	return cartItems;
}
public void setCartItems(Set<CartItem> cartItems) {
	this.cartItems = cartItems;
}
 
}

