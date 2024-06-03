package com.example.myweb.board;

import java.util.Objects;

public class BoardDTO {
	/*
	SEQ INT PRIMARY KEY AUTO_INCREMENT,
    TITLE VARCHAR(200),
    WRITER VARCHAR(20),
    CONTENT VARCHAR(2000),
    REGDATE DATE DEFAULT NOW(),
    CNT INT DEFAULT 0
    */
	private int seq;
	public BoardDTO() {
		this(0,"","","","",0);
	}
	public BoardDTO(int seq, String title, String writer, String content, String regDate, int cnt) {
		this.seq = seq;
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.regDate = regDate;
		this.cnt = cnt;
	}
	public BoardDTO(int seq) {
		this.seq = seq;
	}
	private String title;

	@Override
	public int hashCode() {
		return Objects.hash(seq);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BoardDTO other = (BoardDTO) obj;
		return seq == other.seq;
	}
	public String toString() {
		return "BoardDTO [seq=" + seq + ", title=" + title + ", writer=" + writer + ", content=" + content
				+ ", regDate=" + regDate + ", cnt=" + cnt + "]";
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	private String writer;
	private String content;
	private String regDate;
	private int cnt;
}
