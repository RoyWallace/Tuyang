package etong.tuyang.picture;

import java.util.List;

import etong.tuyang.BasePresenter;
import etong.tuyang.BaseView;
import etong.tuyang.picture.data.remote.Gallery;

/**
 * Created by hwt on 2016/5/23.
 */
public interface GalleryListContract {

    interface View extends BaseView<Presenter> {
        void refreshList(List<Gallery> list);
    }

    interface Presenter extends BasePresenter {
        void getGalleryList(int classId);
    }

}
