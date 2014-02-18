package open.dolphin.delegater;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * OreOreSSSL
 * 
 * @author masuda, Masuda Naika
 */
public class OreOreSSL {
    
    private static SSLContext sslContext;
    private static HostnameVerifier hostnameVerifier;

    public static SSLContext getSslContext() throws KeyManagementException, NoSuchAlgorithmException {
        
        if (sslContext == null) {
            sslContext = SSLContext.getInstance("TLS");
            TrustManager[] certs = {new OreOreTrustManager()};
            sslContext.init(null, certs, new SecureRandom());
        }
        return sslContext;
    }

    public static HostnameVerifier getVerifier() {
        
        if (hostnameVerifier == null) {
            hostnameVerifier = new OreOreHostnameVerifier();
        }
        return hostnameVerifier;
    }

    private static class OreOreHostnameVerifier implements HostnameVerifier {

        @Override
        public boolean verify(String string, SSLSession ssls) {
            return true;
        }

    }

    private static class OreOreTrustManager implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] xcs, String string) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] xcs, String string) throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }
}