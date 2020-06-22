package cs.fhict.org.moviekeeper.data.remote;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {


    private final static String BASE_URL = "http://www.omdbapi.com/";

    private static Retrofit retrofit = null;
    private static Gson gson = new GsonBuilder().create();

    private static HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
        @Override
        public void log(@NotNull String s) {
            Log.d("OkHttp LOGG",s);
        }
    }).setLevel(HttpLoggingInterceptor.Level.BASIC).setLevel(HttpLoggingInterceptor.Level.BODY);
    private static OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor);


    private static OkHttpClient okHttpClient = okHttpClientBuilder.connectTimeout(2,TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(2, TimeUnit.MINUTES)
                .addInterceptor(httpLoggingInterceptor)
            .retryOnConnectionFailure(false)
                .cache(null)//new Cache(sContext.getCacheDir(),10*1024*1024)
                .build();

    public static <T> T createService(Class<T> serviceClass){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit.create(serviceClass);
    }


}
