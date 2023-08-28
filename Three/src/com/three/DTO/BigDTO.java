package com.three.DTO;

public class BigDTO {
	private int bigId;
	private String bigName;
	public BigDTO() {}
	public BigDTO(int bigId, String bigName) {
		super();
		this.bigId = bigId;
		this.bigName = bigName;
	}
	public int getBigId() {
		return bigId;
	}
	public void setBigId(int bigId) {
		this.bigId = bigId;
	}
	public String getBigName() {
		return bigName;
	}
	public void setBigName(String bigName) {
		this.bigName = bigName;
	}

}
