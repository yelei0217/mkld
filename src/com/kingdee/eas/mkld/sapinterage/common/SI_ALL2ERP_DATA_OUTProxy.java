package com.kingdee.eas.mkld.sapinterage.common;

public class SI_ALL2ERP_DATA_OUTProxy implements com.kingdee.eas.mkld.sapinterage.common.SI_ALL2ERP_DATA_OUT {
  private String _endpoint = null;
  private com.kingdee.eas.mkld.sapinterage.common.SI_ALL2ERP_DATA_OUT sI_ALL2ERP_DATA_OUT = null;
  
  public SI_ALL2ERP_DATA_OUTProxy() {
    _initSI_ALL2ERP_DATA_OUTProxy();
  }
  
  public SI_ALL2ERP_DATA_OUTProxy(String endpoint) {
    _endpoint = endpoint;
    _initSI_ALL2ERP_DATA_OUTProxy();
  }
  
  private void _initSI_ALL2ERP_DATA_OUTProxy() {
    try {
      sI_ALL2ERP_DATA_OUT = (new com.kingdee.eas.mkld.sapinterage.common.SI_ALL2ERP_DATA_OUTServiceLocator()).getHTTPS_Port();
      if (sI_ALL2ERP_DATA_OUT != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)sI_ALL2ERP_DATA_OUT)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)sI_ALL2ERP_DATA_OUT)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (sI_ALL2ERP_DATA_OUT != null)
      ((javax.xml.rpc.Stub)sI_ALL2ERP_DATA_OUT)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.kingdee.eas.mkld.sapinterage.common.SI_ALL2ERP_DATA_OUT getSI_ALL2ERP_DATA_OUT() {
    if (sI_ALL2ERP_DATA_OUT == null)
      _initSI_ALL2ERP_DATA_OUTProxy();
    return sI_ALL2ERP_DATA_OUT;
  }
  
  public com.kingdee.eas.mkld.sapinterage.common.DT_ALL2ERP_DATA_RECV SI_ALL2ERP_DATA_OUT(com.kingdee.eas.mkld.sapinterage.common.DT_ALL2ERP_DATA_SEND MT_ALL2ERP_DATA_SEND) throws java.rmi.RemoteException{
    if (sI_ALL2ERP_DATA_OUT == null)
      _initSI_ALL2ERP_DATA_OUTProxy();
    return sI_ALL2ERP_DATA_OUT.SI_ALL2ERP_DATA_OUT(MT_ALL2ERP_DATA_SEND);
  }
  
  
}