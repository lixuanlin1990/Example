package com.lixlop.example.retrofit2;

import android.annotation.SuppressLint;

import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory;

import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitServiceGenerator {

    private final static Retrofit.Builder BUILDER = new Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory.create())
//            .addConverterFactory(KeepConverterFactory.create())
//            .addConverterFactory(new StringConverterFactory())
            .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                    .setLenient()
                    .create()));


    private final static OkHttpClient HTTP_CLIENT = new OkHttpClient.Builder()
            //连接超时时间
//            .connectTimeout(Constants.Config.CONFIG_5_SECONDS, TimeUnit.SECONDS)
            //写操作 超时时间
//            .writeTimeout(Constants.Config.CONFIG_10_SECONDS, TimeUnit.SECONDS)
            //读操作超时时间
//            .readTimeout(Constants.Config.CONFIG_10_SECONDS, TimeUnit.SECONDS)
//            .addInterceptor(new KWCookieInterceptor())
//            .addInterceptor(new KWEncryptInterceptor())
//            .addInterceptor(new KWRequestTimeInterceptor())
//            .addInterceptor(new KWKibanaInterceptor())
            .addInterceptor(new KwHttpsInterceptor())
            // 信任所有证书 add by glfan 2018/01/17
            .sslSocketFactory(createSSLSocketFactory())
            .hostnameVerifier(new TrustAllHostnameVerifier())
            .build();


    public static <S> S createService(Class<S> serviceClass) {
        OkHttpClient.Builder builder = HTTP_CLIENT.newBuilder();
        // 加入抓包控制
        // 有些项目需要抓包，故可以把kwGetContext设置为null，则不会加入no_proxy
//        if (null != KWInternal.getInstance().getAppProxy() && null != KWInternal.getInstance().getAppProxy().kwGetContext()) {
//            // 是否是debug版本
//            boolean debugModel = BuildConfig.DEBUG || BuildConfig.BETA;
//            // 是否已打开抓包
//            boolean allowProxy = PreferencesUtil.getEnableProxy(KWInternal.getInstance().getAppProxy().kwGetContext());
//            // 如果是正式版本且还没有扫码认证开启抓包，则不可以抓包，需要加入禁止抓包的参数
//            if (!debugModel && !allowProxy) {
//                builder = HTTP_CLIENT.newBuilder().proxy(Proxy.NO_PROXY);
//            }
//        }
        OkHttpClient client = builder
                .build();

        Retrofit retrofit = BUILDER.baseUrl("https://www.baidu.com/")
                .client(client)
                .build();
        return retrofit.create(serviceClass);
    }


//    private static class KeepConverterFactory extends Converter.Factory {
//        public static KeepConverterFactory create() {
//            return new KeepConverterFactory();
//        }
//
//        @Override
//        public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
//            if (type instanceof Class<?>
//                    && type == KWKeepRespModel.class) {
//                return new KeepResponseBodyConverter();
//            } else {
//                return null;
//            }
//        }
//
//        private static class KeepResponseBodyConverter implements Converter<ResponseBody, KWKeepRespModel> {
//
//            @Override
//            public KWKeepRespModel convert(@Nullable ResponseBody value) throws IOException {
//                KWKeepRespModel respModel = new KWKeepRespModel();
//                respModel.setBody(value);
//                return respModel;
//            }
//        }
//    }

    /**
     * 默认信任所有的证书
     *
     * @return SSLSocketFactory
     */
    @SuppressLint("TrulyRandom")
    private static SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory sSLSocketFactory = null;
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new TrustAllManager()},
                    new SecureRandom());
            sSLSocketFactory = sc.getSocketFactory();
        } catch (Exception e) {
//            KWLogUtils.e(e.getMessage(), e);
        }
        return sSLSocketFactory;
    }

    private static class TrustAllManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    private static class TrustAllHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }

    private static class KwHttpsInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request source = chain.request();
            if (source.isHttps()) {
                return chain.proceed(source);
            }

            HttpUrl httpUrl = source.url().newBuilder()
                    .scheme("https")
                    .port(443)
                    .build();
            Request request = source.newBuilder()
                    .url(httpUrl)
                    .build();
            return chain.proceed(request);
        }
    }
}
