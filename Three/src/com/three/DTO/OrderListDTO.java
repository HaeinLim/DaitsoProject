package com.three.DTO;

public class OrderListDTO {
	private int orderId;
	private int sangId;
	private int guipId;
	private String orderSname;
	private int orderScnt;
	private int orderSprice;
	
	public OrderListDTO() { }
	public OrderListDTO(int orderId, int sangId, int guipId, String orderSname, int orderScnt, int orderSprice) {
		super();
		this.orderId = orderId;
		this.sangId = sangId;
		this.guipId = guipId;
		this.orderSname = orderSname;
		this.orderScnt = orderScnt;
		this.orderSprice = orderSprice;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getSangId() {
		return sangId;
	}
	public void setSangId(int sangId) {
		this.sangId = sangId;
	}
	public int getGuipId() {
		return guipId;
	}
	public void setGuipId(int guipId) {
		this.guipId = guipId;
	}
	public String getOrderSname() {
		return orderSname;
	}
	public void setOrderSname(String orderSname) {
		this.orderSname = orderSname;
	}
	public int getOrderScnt() {
		return orderScnt;
	}
	public void setOrderScnt(int orderScnt) {
		this.orderScnt = orderScnt;
	}
	public int getOrderSprice() {
		return orderSprice;
	}
	public void setOrderSprice(int orderSprice) {
		this.orderSprice = orderSprice;
	}
	
}
