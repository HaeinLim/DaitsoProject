package com.three.DTO;

public class CateDTO {
	private int cateId;
	private String cateName;
	private int bigId;
	public CateDTO(int cateId, String cateName, int bigId) {
		super();
		this.cateId = cateId;
		this.cateName = cateName;
		this.bigId = bigId;
	}
	public int getCateId() {
		return cateId;
	}
	public void setCateId(int cateId) {
		this.cateId = cateId;
	}
	public String getCateName() {
		return cateName;
	}
	public void setCateName(String cateName) {
		this.cateName = cateName;
	}
	public int getBigId() {
		return bigId;
	}
	public void setBigId(int bigId) {
		this.bigId = bigId;
	}
	public CateDTO() { }
	
}
