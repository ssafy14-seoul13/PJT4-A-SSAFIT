package com.ssafy.ssafit.model.dto;

import java.time.LocalDate;

public class Review {
	private int no;
	private String title;
	private String content;
	private String writer;
	private String vno;	// 영상no
	private int viewCnt;// 조회수
	private LocalDate created_at;
	private LocalDate updated_at;
	
	public Review() {}

	public Review(int no, String title, String content, String writer, String vno, int viewCnt, LocalDate created_at,
			LocalDate updated_at) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.vno = vno;
		this.viewCnt = viewCnt;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getVno() {
		return vno;
	}

	public void setVno(String vno) {
		this.vno = vno;
	}

	public int getViewCnt() {
		return viewCnt;
	}

	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}

	public LocalDate getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDate created_at) {
		this.created_at = created_at;
	}

	public LocalDate getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(LocalDate updated_at) {
		this.updated_at = updated_at;
	}

	@Override
	public String toString() {
		return "Review [no=" + no + ", title=" + title + ", content=" + content + ", writer=" + writer + ", vno=" + vno
				+ ", viewCnt=" + viewCnt + ", created_at=" + created_at + ", updated_at=" + updated_at + "]";
	}
	
}
