package etong.tuyang.picture.data;

import etong.tuyang.picture.data.remote.Gallery;
import etong.tuyang.picture.data.remote.GalleryClassResult;
import etong.tuyang.picture.data.remote.GalleryResult;
import etong.tuyang.picture.data.remote.PictureResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by hwt on 2016/5/21.
 */

public interface ApiService {

    @GET("classify")
    Call<GalleryClassResult> getGalleryClassList();

    @GET("list")
    Call<GalleryResult> getGalleryList(@Query("page") int page, @Query("rows") int rows, @Query("id") int id);

    @GET("show")
    Call<PictureResult> getPictureList(@Query("id") long id);

}
