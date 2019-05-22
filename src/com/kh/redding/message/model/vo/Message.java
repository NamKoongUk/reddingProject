package com.kh.redding.message.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Message implements Serializable{
	private int messageCode;	//쪽지코드 //pk
	private String mesContent;	//쪽지내용	//not null
	private Date mesDisDate;	//발신일자	//not null
	private Date mesCkDate;		//확인일자
	private int mesLevel;		//쪽지레벨	//1,2
	private String mesWType;	//수신,발신 구분	//10, 20	//not null
	private int mesRefCode;		//참조쪽지번호
	private int cno;			//업체 번호
	
	public Message() {}

	public Message(int messageCode, String mesContent, Date mesDisDate, Date mesCkDate, int mesLevel, String mesWType,
			int mesRefCode, int cno) {
		super();
		this.messageCode = messageCode;
		this.mesContent = mesContent;
		this.mesDisDate = mesDisDate;
		this.mesCkDate = mesCkDate;
		this.mesLevel = mesLevel;
		this.mesWType = mesWType;
		this.mesRefCode = mesRefCode;
		this.cno = cno;
	}

	public int getMessageCode() {
		return messageCode;
	}

	public void setMessageCode(int messageCode) {
		this.messageCode = messageCode;
	}

	public String getMesContent() {
		return mesContent;
	}

	public void setMesContent(String mesContent) {
		this.mesContent = mesContent;
	}

	public Date getMesDisDate() {
		return mesDisDate;
	}

	public void setMesDisDate(Date mesDisDate) {
		this.mesDisDate = mesDisDate;
	}

	public Date getMesCkDate() {
		return mesCkDate;
	}

	public void setMesCkDate(Date mesCkDate) {
		this.mesCkDate = mesCkDate;
	}

	public int getMesLevel() {
		return mesLevel;
	}

	public void setMesLevel(int mesLevel) {
		this.mesLevel = mesLevel;
	}

	public String getMesWType() {
		return mesWType;
	}

	public void setMesWType(String mesWType) {
		this.mesWType = mesWType;
	}

	public int getMesRefCode() {
		return mesRefCode;
	}

	public void setMesRefCode(int mesRefCode) {
		this.mesRefCode = mesRefCode;
	}

	public int getCno() {
		return cno;
	}

	public void setCno(int cno) {
		this.cno = cno;
	}

	@Override
	public String toString() {
		return "Message [messageCode=" + messageCode + ", mesContent=" + mesContent + ", mesDisDate=" + mesDisDate
				+ ", mesCkDate=" + mesCkDate + ", mesLevel=" + mesLevel + ", mesWType=" + mesWType + ", mesRefCode="
				+ mesRefCode + ", cno=" + cno + "]";
	}
	
	
	
	
}