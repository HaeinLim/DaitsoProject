package com.three.DTO;

public class CartDTO {
	private int cartId;
	private String memId;
	private int sangId;
	private int sangCnt;
	
	public CartDTO() {}
	public CartDTO(int cartId, String memId, int sangId, int sangCnt) {
		super();
		this.cartId = cartId;
		this.memId = memId;
		this.sangId = sangId;
		this.sangCnt = sangCnt;
	}
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public int getSangId() {
		return sangId;
	}
	public void setSangId(int sangId) {
		this.sangId = sangId;
	}
	public int getSangCnt() {
		return sangCnt;
	}
	public void setSangCnt(int sangCnt) {
		this.sangCnt = sangCnt;
	}
	
}
