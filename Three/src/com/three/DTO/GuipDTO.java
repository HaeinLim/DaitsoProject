package com.three.DTO;

import java.util.Date;

public class GuipDTO {
	private int guipId;
	private String memId;
	private int guipTotal;
	private String address;
	private String zipcode;
	private String address2;
	private Date guipDate;
	
	public GuipDTO() { }
	public GuipDTO(int guipId, String memId, int guipTotal, String address, String zipcode, String address2, Date guipDate) {
		super();
		this.guipId = guipId;
		this.memId = memId;
		this.guipTotal = guipTotal;
		this.address = address;
		this.zipcode = zipcode;
		this.address2 = address2;
		this.guipDate = guipDate;
	}
	public int getGuipId() {
		return guipId;
	}
	public void setGuipId(int guipId) {
		this.guipId = guipId;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public int getGuipTotal() {
		return guipTotal;
	}
	public void setGuipTotal(int guipTotal) {
		this.guipTotal = guipTotal;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public Date getGuipDate() {
		return guipDate;
	}
	public void setGuipDate(Date guipDate) {
		this.guipDate = guipDate;
	}
	
}
