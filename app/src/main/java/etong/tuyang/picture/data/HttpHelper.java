package etong.tuyang.picture.data;

import android.util.Log;

import com.google.gson.Gson;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.security.cert.CertificateException;
import javax.security.cert.X509Certificate;

import etong.tuyang.picture.data.remote.GalleryClass;
import etong.tuyang.picture.data.remote.GalleryClassResult;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static etong.tuyang.MyApplication.IS_DEV;

/**
 * Created by hwt on 2016/5/21.
 */

public class HttpHelper {

    private static final String TAG = "HttpHelper";
    public static final String BASE_URL = "http://www.tngou.net/tnfs/api/";

    private Retrofit retrofit;
    private ApiService apiService;

    private HttpHelper() {
        //Log拦截器
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        // add your other interceptors …

        // add logging as last interceptor
        httpClient.addInterceptor(logging);  // <-- this is the important line!


        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public static HttpHelper getInstance() {
        return InstanceHolder.httpHelper;
    }

    private static class InstanceHolder {
        public static final HttpHelper httpHelper = new HttpHelper();
    }

    public void getGalleryClass(Callback<GalleryClassResult> callback) {
        Call<GalleryClassResult> userCall = apiService.getGalleryClassList();
        userCall.enqueue(callback);
    }


}
