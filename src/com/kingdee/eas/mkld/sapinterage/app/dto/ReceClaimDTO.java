package com.kingdee.eas.mkld.sapinterage.app.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ReceClaimDTO  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5722762170063874851L;
	
	private String EASID;	//EAS��ˮ��
	private String ZTYPE;	//��������
	private String BUKRS;	//��˾����
	private String BANKN;	//�տ������˺�
	private String WAERS;	//���� 
	private Date BUDAT;	//�տ�����
	private Date BUDAT2;//	�ٴ���������
	private String KUNNR;	//�ͻ�����
	private BigDecimal DMBTR;	//�տ���
	private BigDecimal DMBTR_HK;//	���л�����
	private BigDecimal DMBTR_BZJ;//	���б�֤����
	private BigDecimal DMBTR_YJ;//	����Ѻ����
	private String SGTXT;	//ժҪ
	
	 
	public String getEASID() {
		return EASID;
	}
	public void setEASID(String easid) {
		EASID = easid;
	}
	public String getBUKRS() {
		return BUKRS;
	}
	public void setBUKRS(String bukrs) {
		BUKRS = bukrs;
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
	public Date getBUDAT2() {
		return BUDAT2;
	}
	public void setBUDAT2(Date budat2) {
		BUDAT2 = budat2;
	}
	public String getKUNNR() {
		return KUNNR;
	}
	public void setKUNNR(String kunnr) {
		KUNNR = kunnr;
	}
	public BigDecimal getDMBTR() {
		return DMBTR;
	}
	public void setDMBTR(BigDecimal dmbtr) {
		DMBTR = dmbtr;
	}
	public String getZTYPE() {
		return ZTYPE;
	}
	public void setZTYPE(String ztype) {
		ZTYPE = ztype;
	}
	public String getWAERS() {
		return WAERS;
	}
	public void setWAERS(String waers) {
		WAERS = waers;
	}
	public BigDecimal getDMBTR_HK() {
		return DMBTR_HK;
	}
	public void setDMBTR_HK(BigDecimal dmbtr_hk) {
		DMBTR_HK = dmbtr_hk;
	}
	public BigDecimal getDMBTR_BZJ() {
		return DMBTR_BZJ;
	}
	public void setDMBTR_BZJ(BigDecimal dmbtr_bzj) {
		DMBTR_BZJ = dmbtr_bzj;
	}
	public BigDecimal getDMBTR_YJ() {
		return DMBTR_YJ;
	}
	public void setDMBTR_YJ(BigDecimal dmbtr_yj) {
		DMBTR_YJ = dmbtr_yj;
	}
	public String getSGTXT() {
		return SGTXT;
	}
	public void setSGTXT(String sgtxt) {
		SGTXT = sgtxt;
	}
	
}
