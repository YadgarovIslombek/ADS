package com.A1tech.ApiClient;
import android.content.Context;
import java.util.concurrent.TimeUnit;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
//    private static final String BASE_URL = "http://192.168.1.110:8580/ads/";
//    private static final String BASE_URL = "http://83.69.136.24:8580/";
 private static final String BASE_URL = "https://dd183817-f822-4755-9401-abd8c4957005.mock.pstmn.io/";
 private static RetrofitClient mInstance;
 private static Retrofit retrofit;
    private static Retrofit getRetrofit(){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                //.baseUrl("http://api.larntech.net/")
                .client(okHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    private static Interceptor provideLoggingInterceptor(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }
    private static OkHttpClient okHttpClient(){
        return new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30,TimeUnit.SECONDS)
                .readTimeout(30,TimeUnit.SECONDS)
                .addNetworkInterceptor(provideLoggingInterceptor())
                .build();
    }
    public static InterfacesApi getData(Context applicationContext){
        InterfacesApi interfaces = getRetrofit().create(InterfacesApi.class);
        return interfaces;
    }
}

