package etong.tuyang.picture.data;

import android.content.Context;
import android.text.TextUtils;

import java.io.File;
import java.io.IOException;

import etong.tuyang.picture.data.remote.GalleryClassResult;
import etong.tuyang.picture.data.remote.GalleryResult;
import etong.tuyang.picture.data.remote.PictureResult;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hwt on 2016/5/21.
 */

public class HttpHelper {

    private static final String TAG = "HttpHelper";
    public static final String BASE_URL = "http://www.tngou.net/tnfs/api/";

    private Retrofit retrofit;
    private ApiService apiService;

    private HttpHelper() {
    }

    public void init(Context context) {
        //Log拦截器
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        // add your other interceptors …

        // add logging as last interceptor
        httpClient.addInterceptor(logging);  // <-- this is the important line!

        //
        File cacheFile = new File(context.getCacheDir(), "responses");
        final Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb
        httpClient.cache(cache);

        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Response response = chain.proceed(request);

                String cacheControl = request.cacheControl().toString();
                if (TextUtils.isEmpty(cacheControl)) {
                    cacheControl = "public, max-age=60";
                }
                return response.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
                        .build();
            }
        };

        httpClient.addNetworkInterceptor(interceptor);

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

    public void getGalleryList(int page, int count, int id, Callback<GalleryResult> callback) {
        Call<GalleryResult> userCall = apiService.getGalleryList(page, count, id);
        userCall.enqueue(callback);
    }

    public void getPictureList(long pictureId, Callback<PictureResult> callback) {
        Call<PictureResult> userCall = apiService.getPictureList(pictureId);
        userCall.enqueue(callback);
    }


}
