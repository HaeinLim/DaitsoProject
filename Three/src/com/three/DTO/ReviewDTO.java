package com.three.DTO;

import java.util.Date;

public class ReviewDTO {
	private int revId;
	private String memId;
	private int sangId;
	private int orderId;
	private int revStar;
	private String revPipath1;
	private String revPipath2;
	private String revPipath3;
	private String revContent;
	private Date revDate;
	
	public ReviewDTO() { }
	public ReviewDTO(int revId, String memId, int sangId, int orderId, int revStar, String revPipath1, String revPipath2,
			String revPipath3, String revContent, Date revDate) {
		super();
		this.revId = revId;
		this.memId = memId;
		this.sangId = sangId;
		this.orderId = orderId;
		this.revStar = revStar;
		this.revPipath1 = revPipath1;
		this.revPipath2 = revPipath2;
		this.revPipath3 = revPipath3;
		this.revContent = revContent;
		this.revDate = revDate;
	}
	public int getRevId() {
		return revId;
	}
	public void setRevId(int revId) {
		this.revId = revId;
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
	public int getOrderID() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getRevStar() {
		return revStar;
	}
	public void setRevStar(int revStar) {
		this.revStar = revStar;
	}
	public String getRevPipath1() {
		return revPipath1;
	}
	public void setRevPipath1(String revPipath1) {
		this.revPipath1 = revPipath1;
	}
	public String getRevPipath2() {
		return revPipath2;
	}
	public void setRevPipath2(String revPipath2) {
		this.revPipath2 = revPipath2;
	}
	public String getRevPipath3() {
		return revPipath3;
	}
	public void setRevPipath3(String revPipath3) {
		this.revPipath3 = revPipath3;
	}
	public String getRevContent() {
		return revContent;
	}
	public void setRevContent(String revContent) {
		this.revContent = revContent;
	}
	public Date getRevDate() {
		return revDate;
	}
	public void setRevDate(Date revDate) {
		this.revDate = revDate;
	}
	
}
