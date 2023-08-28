package com.three.DTO;

import java.util.Date;

public class SangpumDTO {
	   private int sangId;
	   private int cateId;
	   private String sangName;
	   private int sangPrice;
	   private double sangStar;
	   private String sangPipath;
	   private int sangAmount;
	   private Date sangDate;
	   
	public SangpumDTO() {}

	public SangpumDTO(int sangId, int cateId, String sangName, int sangPrice, double sangStar, String sangPipath,
			int sangAmount, Date sangDate) {
		super();
		this.sangId = sangId;
		this.cateId = cateId;
		this.sangName = sangName;
		this.sangPrice = sangPrice;
		this.sangStar = sangStar;
		this.sangPipath = sangPipath;
		this.sangAmount = sangAmount;
		this.sangDate = sangDate;
	}

	public int getSangId() {
		return sangId;
	}
	
	public void setSangId(int sangId) {
		this.sangId = sangId;
	}
	public int getCateId() {
		return cateId;
	}
	public void setCateId(int cateId) {
		this.cateId = cateId;
	}
	public String getSangName() {
		return sangName;
	}
	public void setSangName(String sangName) {
		this.sangName = sangName;
	}
	public int getSangPrice() {
		return sangPrice;
	}
	public void setSangPrice(int sangPrice) {
		this.sangPrice = sangPrice;
	}
	public double getSangStar() {
		return sangStar;
	}
	public void setSangStar(double sangStar) {
		this.sangStar = sangStar;
	}
	public String getSangPipath() {
		return sangPipath;
	}
	public void setSangPipath(String sangPipath) {
		this.sangPipath = sangPipath;
	}
	public int getSangAmount() {
		return sangAmount;
	}
	public void setSangAmount(int sangAmount) {
		this.sangAmount = sangAmount;
	}
	public Date getSangDate() {
		return sangDate;
	}
	public void setSangDate(Date sangDate) {
		this.sangDate = sangDate;
	}
	   
	}