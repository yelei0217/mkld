package com.kingdee.eas.mkld.sapinterage.app.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class MonthEndNoClaimDTO  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7927825339686936346L;

	private String EASID ; 	//EAS��ˮ��
	private String BUKRS ; 	//��˾����
	private String GJAHR ; 	//���
	private String MONAT ; 	//�·�
	private String WAERS;	//���� 
	private String BANKN ; 	//�տ������˺�
	private Date BUDAT ; 	//�տ�����
	private BigDecimal DMBTR ; 	//�տ���
	private String SGTXT ; 	//ժҪ
	 
	public String getBUKRS() {
		return BUKRS;
	}
	public void setBUKRS(String bukrs) {
		BUKRS = bukrs;
	}
	public String getGJAHR() {
		return GJAHR;
	}
	public void setGJAHR(String gjahr) {
		GJAHR = gjahr;
	}
	public String getMONAT() {
		return MONAT;
	}
	public void setMONAT(String monat) {
		MONAT = monat;
	}
	public String getBANKN() {
		return BANKN;
	}
	public void setBANKN(String bankn) {
		BANKN = bankn;
	}
	public Date getBUDAT() {
		return BUDAT;
	}
	public void setBUDAT(Date budat) {
		BUDAT = budat;
	}
	public BigDecimal getDMBTR() {
		return DMBTR;
	}
	public void setDMBTR(BigDecimal dmbtr) {
		DMBTR = dmbtr;
	}
	public String getEASID() {
		return EASID;
	}
	public void setEASID(String easid) {
		EASID = easid;
	}
	public String getWAERS() {
		return WAERS;
	}
	public void setWAERS(String waers) {
		WAERS = waers;
	}
	public String getSGTXT() {
		return SGTXT;
	}
	public void setSGTXT(String sgtxt) {
		SGTXT = sgtxt;
	}
}
