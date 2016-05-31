package etong.tuyang.picture;

import etong.tuyang.picture.data.HttpHelper;
import etong.tuyang.picture.data.remote.GalleryResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hwt on 2016/5/23.
 */
public class GalleryListPresenter implements GalleryListContract.Presenter {

    private GalleryListContract.View galleryListView;

    public GalleryListPresenter(GalleryListContract.View galleryListView) {
        this.galleryListView = galleryListView;
        galleryListView.setPresenter(this);
    }

    @Override
    public void start() {
//        getGalleryList();
    }

    @Override
    public void getGalleryList(int classId) {

        HttpHelper.getInstance().getGalleryList(0, 20, classId, new Callback<GalleryResult>() {
            @Override
            public void onResponse(Call<GalleryResult> call, Response<GalleryResult> response) {
                GalleryResult result = response.body();
                if (result != null && result.status && result.tngou != null) {

                }
            }

            @Override
            public void onFailure(Call<GalleryResult> call, Throwable t) {

            }
        });
    }
}
