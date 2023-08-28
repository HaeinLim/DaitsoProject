package com.three.DTO;

import java.util.Date;

public class CancleDTO {
	private int guipId;
	private String memId;
	private int guipTotal;
	private Date cancleDate;
	
	public CancleDTO() {
		
	}
	
	public CancleDTO(int guipId, String memId, int guipTotal, Date cancleDate) {
		super();
		this.guipId = guipId;
		this.memId = memId;
		this.guipTotal = guipTotal;
		this.cancleDate = cancleDate;
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
	public Date getCancleDate() {
		return cancleDate;
	}
	public void setCancleDate(Date cancleDate) {
		this.cancleDate = cancleDate;
	}
	
	
	
}
