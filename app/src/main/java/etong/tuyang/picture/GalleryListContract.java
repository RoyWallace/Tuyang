package etong.tuyang.picture;

import etong.tuyang.BasePresenter;
import etong.tuyang.BaseView;

/**
 * Created by hwt on 2016/5/23.
 */
public interface GalleryListContract {

    interface View extends BaseView<Presenter> {
        void refreshList();
    }

    interface Presenter extends BasePresenter {
        void getGalleryList(int classId);
    }

}
