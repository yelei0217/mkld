package com.kingdee.eas.mkld.sapinterage.app.dto;

import java.io.Serializable;

public class SapRespondDTO  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6189346083677275222L;
	
	private String Date;	//���ڣ�20200101
	private String Time;	//ʱ�䣺120101
	private String Flag;	//S�ɹ�Eʧ��
	private String Message;	//˵��
	
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public String getTime() {
		return Time;
	}
	public void setTime(String time) {
		Time = time;
	}
	public String getFlag() {
		return Flag;
	}
	public void setFlag(String flag) {
		Flag = flag;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
	
}
