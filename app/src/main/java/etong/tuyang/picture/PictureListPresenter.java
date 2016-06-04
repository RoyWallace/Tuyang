package etong.tuyang.picture;

import etong.tuyang.BasePresenter;
import etong.tuyang.picture.data.HttpHelper;
import etong.tuyang.picture.data.remote.GalleryResult;
import etong.tuyang.picture.data.remote.PictureResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hwt on 2016/6/2.
 */
public class PictureListPresenter implements PictureListContract.Presenter {

    private PictureListContract.View view;

    public PictureListPresenter(PictureListContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {

    }

    public void getPictureList(long pictureId) {
        HttpHelper.getInstance().getPictureList(pictureId, new Callback<PictureResult>() {
            @Override
            public void onResponse(Call<PictureResult> call, Response<PictureResult> response) {
                PictureResult result = response.body();
                if (result != null && result.status && result.list != null) {
                    view.refreshList(result.list);
                }
            }

            @Override
            public void onFailure(Call<PictureResult> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

}
