package etong.tuyang.picture.data;

import etong.tuyang.picture.data.remote.GalleryClassResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by hwt on 2016/5/21.
 */

public interface ApiService {

    @GET("classify")
    Call<GalleryClassResult> getGalleryClassList();

}
