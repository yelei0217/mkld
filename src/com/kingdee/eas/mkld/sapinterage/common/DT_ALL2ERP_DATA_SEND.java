/**
 * DT_ALL2ERP_DATA_SEND.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.kingdee.eas.mkld.sapinterage.common;

public class DT_ALL2ERP_DATA_SEND  implements java.io.Serializable {
    private java.lang.String IF_TYPE;

    private java.lang.String IF_TRANSACTION_ID;

    private java.lang.String SEQUENCE;

    private java.lang.String IF_FINISHED_FLAG;

    private java.lang.String SYS_SRC;

    private java.lang.String TIMESTAMP_SRC;

    private java.lang.String SYS_TAR;

    private java.lang.String TIMESTAMP_PO;

    private java.lang.String IF_JSON_DATA;

    public DT_ALL2ERP_DATA_SEND() {
    }

    public DT_ALL2ERP_DATA_SEND(
           java.lang.String IF_TYPE,
           java.lang.String IF_TRANSACTION_ID,
           java.lang.String SEQUENCE,
           java.lang.String IF_FINISHED_FLAG,
           java.lang.String SYS_SRC,
           java.lang.String TIMESTAMP_SRC,
           java.lang.String SYS_TAR,
           java.lang.String TIMESTAMP_PO,
           java.lang.String IF_JSON_DATA) {
           this.IF_TYPE = IF_TYPE;
           this.IF_TRANSACTION_ID = IF_TRANSACTION_ID;
           this.SEQUENCE = SEQUENCE;
           this.IF_FINISHED_FLAG = IF_FINISHED_FLAG;
           this.SYS_SRC = SYS_SRC;
           this.TIMESTAMP_SRC = TIMESTAMP_SRC;
           this.SYS_TAR = SYS_TAR;
           this.TIMESTAMP_PO = TIMESTAMP_PO;
           this.IF_JSON_DATA = IF_JSON_DATA;
    }


    /**
     * Gets the IF_TYPE value for this DT_ALL2ERP_DATA_SEND.
     * 
     * @return IF_TYPE
     */
    public java.lang.String getIF_TYPE() {
        return IF_TYPE;
    }


    /**
     * Sets the IF_TYPE value for this DT_ALL2ERP_DATA_SEND.
     * 
     * @param IF_TYPE
     */
    public void setIF_TYPE(java.lang.String IF_TYPE) {
        this.IF_TYPE = IF_TYPE;
    }


    /**
     * Gets the IF_TRANSACTION_ID value for this DT_ALL2ERP_DATA_SEND.
     * 
     * @return IF_TRANSACTION_ID
     */
    public java.lang.String getIF_TRANSACTION_ID() {
        return IF_TRANSACTION_ID;
    }


    /**
     * Sets the IF_TRANSACTION_ID value for this DT_ALL2ERP_DATA_SEND.
     * 
     * @param IF_TRANSACTION_ID
     */
    public void setIF_TRANSACTION_ID(java.lang.String IF_TRANSACTION_ID) {
        this.IF_TRANSACTION_ID = IF_TRANSACTION_ID;
    }


    /**
     * Gets the SEQUENCE value for this DT_ALL2ERP_DATA_SEND.
     * 
     * @return SEQUENCE
     */
    public java.lang.String getSEQUENCE() {
        return SEQUENCE;
    }


    /**
     * Sets the SEQUENCE value for this DT_ALL2ERP_DATA_SEND.
     * 
     * @param SEQUENCE
     */
    public void setSEQUENCE(java.lang.String SEQUENCE) {
        this.SEQUENCE = SEQUENCE;
    }


    /**
     * Gets the IF_FINISHED_FLAG value for this DT_ALL2ERP_DATA_SEND.
     * 
     * @return IF_FINISHED_FLAG
     */
    public java.lang.String getIF_FINISHED_FLAG() {
        return IF_FINISHED_FLAG;
    }


    /**
     * Sets the IF_FINISHED_FLAG value for this DT_ALL2ERP_DATA_SEND.
     * 
     * @param IF_FINISHED_FLAG
     */
    public void setIF_FINISHED_FLAG(java.lang.String IF_FINISHED_FLAG) {
        this.IF_FINISHED_FLAG = IF_FINISHED_FLAG;
    }


    /**
     * Gets the SYS_SRC value for this DT_ALL2ERP_DATA_SEND.
     * 
     * @return SYS_SRC
     */
    public java.lang.String getSYS_SRC() {
        return SYS_SRC;
    }


    /**
     * Sets the SYS_SRC value for this DT_ALL2ERP_DATA_SEND.
     * 
     * @param SYS_SRC
     */
    public void setSYS_SRC(java.lang.String SYS_SRC) {
        this.SYS_SRC = SYS_SRC;
    }


    /**
     * Gets the TIMESTAMP_SRC value for this DT_ALL2ERP_DATA_SEND.
     * 
     * @return TIMESTAMP_SRC
     */
    public java.lang.String getTIMESTAMP_SRC() {
        return TIMESTAMP_SRC;
    }


    /**
     * Sets the TIMESTAMP_SRC value for this DT_ALL2ERP_DATA_SEND.
     * 
     * @param TIMESTAMP_SRC
     */
    public void setTIMESTAMP_SRC(java.lang.String TIMESTAMP_SRC) {
        this.TIMESTAMP_SRC = TIMESTAMP_SRC;
    }


    /**
     * Gets the SYS_TAR value for this DT_ALL2ERP_DATA_SEND.
     * 
     * @return SYS_TAR
     */
    public java.lang.String getSYS_TAR() {
        return SYS_TAR;
    }


    /**
     * Sets the SYS_TAR value for this DT_ALL2ERP_DATA_SEND.
     * 
     * @param SYS_TAR
     */
    public void setSYS_TAR(java.lang.String SYS_TAR) {
        this.SYS_TAR = SYS_TAR;
    }


    /**
     * Gets the TIMESTAMP_PO value for this DT_ALL2ERP_DATA_SEND.
     * 
     * @return TIMESTAMP_PO
     */
    public java.lang.String getTIMESTAMP_PO() {
        return TIMESTAMP_PO;
    }


    /**
     * Sets the TIMESTAMP_PO value for this DT_ALL2ERP_DATA_SEND.
     * 
     * @param TIMESTAMP_PO
     */
    public void setTIMESTAMP_PO(java.lang.String TIMESTAMP_PO) {
        this.TIMESTAMP_PO = TIMESTAMP_PO;
    }


    /**
     * Gets the IF_JSON_DATA value for this DT_ALL2ERP_DATA_SEND.
     * 
     * @return IF_JSON_DATA
     */
    public java.lang.String getIF_JSON_DATA() {
        return IF_JSON_DATA;
    }


    /**
     * Sets the IF_JSON_DATA value for this DT_ALL2ERP_DATA_SEND.
     * 
     * @param IF_JSON_DATA
     */
    public void setIF_JSON_DATA(java.lang.String IF_JSON_DATA) {
        this.IF_JSON_DATA = IF_JSON_DATA;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DT_ALL2ERP_DATA_SEND)) return false;
        DT_ALL2ERP_DATA_SEND other = (DT_ALL2ERP_DATA_SEND) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.IF_TYPE==null && other.getIF_TYPE()==null) || 
             (this.IF_TYPE!=null &&
              this.IF_TYPE.equals(other.getIF_TYPE()))) &&
            ((this.IF_TRANSACTION_ID==null && other.getIF_TRANSACTION_ID()==null) || 
             (this.IF_TRANSACTION_ID!=null &&
              this.IF_TRANSACTION_ID.equals(other.getIF_TRANSACTION_ID()))) &&
            ((this.SEQUENCE==null && other.getSEQUENCE()==null) || 
             (this.SEQUENCE!=null &&
              this.SEQUENCE.equals(other.getSEQUENCE()))) &&
            ((this.IF_FINISHED_FLAG==null && other.getIF_FINISHED_FLAG()==null) || 
             (this.IF_FINISHED_FLAG!=null &&
              this.IF_FINISHED_FLAG.equals(other.getIF_FINISHED_FLAG()))) &&
            ((this.SYS_SRC==null && other.getSYS_SRC()==null) || 
             (this.SYS_SRC!=null &&
              this.SYS_SRC.equals(other.getSYS_SRC()))) &&
            ((this.TIMESTAMP_SRC==null && other.getTIMESTAMP_SRC()==null) || 
             (this.TIMESTAMP_SRC!=null &&
              this.TIMESTAMP_SRC.equals(other.getTIMESTAMP_SRC()))) &&
            ((this.SYS_TAR==null && other.getSYS_TAR()==null) || 
             (this.SYS_TAR!=null &&
              this.SYS_TAR.equals(other.getSYS_TAR()))) &&
            ((this.TIMESTAMP_PO==null && other.getTIMESTAMP_PO()==null) || 
             (this.TIMESTAMP_PO!=null &&
              this.TIMESTAMP_PO.equals(other.getTIMESTAMP_PO()))) &&
            ((this.IF_JSON_DATA==null && other.getIF_JSON_DATA()==null) || 
             (this.IF_JSON_DATA!=null &&
              this.IF_JSON_DATA.equals(other.getIF_JSON_DATA())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getIF_TYPE() != null) {
            _hashCode += getIF_TYPE().hashCode();
        }
        if (getIF_TRANSACTION_ID() != null) {
            _hashCode += getIF_TRANSACTION_ID().hashCode();
        }
        if (getSEQUENCE() != null) {
            _hashCode += getSEQUENCE().hashCode();
        }
        if (getIF_FINISHED_FLAG() != null) {
            _hashCode += getIF_FINISHED_FLAG().hashCode();
        }
        if (getSYS_SRC() != null) {
            _hashCode += getSYS_SRC().hashCode();
        }
        if (getTIMESTAMP_SRC() != null) {
            _hashCode += getTIMESTAMP_SRC().hashCode();
        }
        if (getSYS_TAR() != null) {
            _hashCode += getSYS_TAR().hashCode();
        }
        if (getTIMESTAMP_PO() != null) {
            _hashCode += getTIMESTAMP_PO().hashCode();
        }
        if (getIF_JSON_DATA() != null) {
            _hashCode += getIF_JSON_DATA().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DT_ALL2ERP_DATA_SEND.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.milkground.cn", "DT_ALL2ERP_DATA_SEND"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IF_TYPE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IF_TYPE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IF_TRANSACTION_ID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IF_TRANSACTION_ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SEQUENCE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SEQUENCE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IF_FINISHED_FLAG");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IF_FINISHED_FLAG"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SYS_SRC");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SYS_SRC"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TIMESTAMP_SRC");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TIMESTAMP_SRC"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SYS_TAR");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SYS_TAR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TIMESTAMP_PO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TIMESTAMP_PO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IF_JSON_DATA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IF_JSON_DATA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
