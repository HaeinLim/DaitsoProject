package com.three.DTO;

import java.util.Date;

public class QnaDTO {
	private int qnaId;
	private String memId;
	private int sangId;
	private int depth;
	private int bunId;
	private int bunOrder;
	private String qnaContent;
	private Date qnaDate;
	public QnaDTO() { }
	public QnaDTO(int qnaId, String memId, int sangId, int depth, int bunId, int bunOrder, String qnaContent,
			Date qnaDate) {
		super();
		this.qnaId = qnaId;
		this.memId = memId;
		this.sangId = sangId;
		this.depth = depth;
		this.bunId = bunId;
		this.bunOrder = bunOrder;
		this.qnaContent = qnaContent;
		this.qnaDate = qnaDate;
	}
	public int getQnaId() {
		return qnaId;
	}
	public void setQnaId(int qnaId) {
		this.qnaId = qnaId;
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
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public int getBunId() {
		return bunId;
	}
	public void setBunId(int bunId) {
		this.bunId = bunId;
	}
	public int getBunOrder() {
		return bunOrder;
	}
	public void setBunOrder(int bunOrder) {
		this.bunOrder = bunOrder;
	}
	public String getQnaContent() {
		return qnaContent;
	}
	public void setQnaContent(String qnaContent) {
		this.qnaContent = qnaContent;
	}
	public Date getQnaDate() {
		return qnaDate;
	}
	public void setQnaDate(Date qnaDate) {
		this.qnaDate = qnaDate;
	}
}
