package com.kingdee.eas.mkld.sapinterage.rest;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.ssl.SSLContexts;

import com.kingdee.eas.mkld.sapinterage.common.InterfaceResource;

public class HTTPSCertifiedClient extends HTTPSClient {

    public HTTPSCertifiedClient() {

    }

    @Override
    public void prepareCertificate() throws Exception {
        // 获得密匙库
        KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
        FileInputStream instream = new FileInputStream(
                new File(InterfaceResource.sap_Certificate_path));//证书路径
         try {
            // 密匙库的密码
            trustStore.load(instream, InterfaceResource.sap_Certificate_pwd.toCharArray());
        } finally {
            instream.close();
        }

        SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(trustStore, TrustSelfSignedStrategy.INSTANCE)
                .build();
        this.connectionSocketFactory = new SSLConnectionSocketFactory(sslcontext);
    }

}