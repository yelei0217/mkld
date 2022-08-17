package com.kingdee.eas.mkld.sapinterage.rest;


import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
 
public abstract class HTTPSClient extends HttpClientBuilder {
    private CloseableHttpClient client;
    protected ConnectionSocketFactory connectionSocketFactory;
 
    /**
     * ��ʼ��HTTPSClient
     * 
     * @return ���ص�ǰʵ��
     * @throws Exception
     */
    public CloseableHttpClient init() throws Exception {
        this.prepareCertificate();
        this.regist();
 
        return this.client;
    }
 
    /**
     * ׼��֤����֤
     * 
     * @throws Exception
     */
    public abstract void prepareCertificate() throws Exception;
 
    /**
     * ע��Э��Ͷ˿�, �˷���Ҳ���Ա�������д
     */
    protected void regist() {
        // ����Э��http��https��Ӧ�Ĵ���socket���ӹ����Ķ���
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", this.connectionSocketFactory)
                .build();
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        HttpClients.custom().setConnectionManager(connManager);
 
        // �����Զ����httpclient����
        this.client = HttpClients.custom().setConnectionManager(connManager).build();
    }
}