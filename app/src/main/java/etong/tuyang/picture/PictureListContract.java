package etong.tuyang.picture;

import java.util.List;

import etong.tuyang.BasePresenter;
import etong.tuyang.BaseView;
import etong.tuyang.picture.data.remote.Picture;

/**
 * Created by hwt on 2016/6/2.
 */
public interface PictureListContract {

    interface View extends BaseView<Presenter> {
        void refreshList(List<Picture> list);
    }

    interface Presenter extends BasePresenter {
        void getPictureList(long pictureId);
    }
}
