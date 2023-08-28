package com.three.DTO;

public class DetailDTO {
	private int detId;
	private int sangId;
	private String detName;
	private String detPipath1;
	private String detPipath2;
	private String detPipath3;
	private String detPipath4;
	private String detDate;
	
	public DetailDTO() { }
	
	public DetailDTO(int detId, int sangId, String detName, String detPipath1, String detPipath2, String detPipath3,
			String detPipath4, String detDate) {
		super();
		this.detId = detId;
		this.sangId = sangId;
		this.detName = detName;
		this.detPipath1 = detPipath1;
		this.detPipath2 = detPipath2;
		this.detPipath3 = detPipath3;
		this.detPipath4 = detPipath4;
		this.detDate = detDate;
	}

	public int getDetId() {
		return detId;
	}

	public void setDetId(int detId) {
		this.detId = detId;
	}

	public int getSangId() {
		return sangId;
	}

	public void setSangId(int sangId) {
		this.sangId = sangId;
	}

	public String getDetName() {
		return detName;
	}

	public void setDetName(String detName) {
		this.detName = detName;
	}

	public String getDetPipath1() {
		return detPipath1;
	}

	public void setDetPipath1(String detPipath1) {
		this.detPipath1 = detPipath1;
	}

	public String getDetPipath2() {
		return detPipath2;
	}

	public void setDetPipath2(String detPipath2) {
		this.detPipath2 = detPipath2;
	}

	public String getDetPipath3() {
		return detPipath3;
	}

	public void setDetPipath3(String detPipath3) {
		this.detPipath3 = detPipath3;
	}

	public String getDetPipath4() {
		return detPipath4;
	}

	public void setDetPipath4(String detPipath4) {
		this.detPipath4 = detPipath4;
	}

	public String getDetDate() {
		return detDate;
	}

	public void setDetDate(String detDate) {
		this.detDate = detDate;
	}	
	
	
}
