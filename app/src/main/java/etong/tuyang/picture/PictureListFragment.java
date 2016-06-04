package etong.tuyang.picture;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import etong.tuyang.R;
import etong.tuyang.picture.data.remote.Picture;

/**
 * Created by hwt on 2016/6/2.
 */
public class PictureListFragment extends Fragment implements PictureListContract.View {

    public final static String PICTURE_ID = "pictureId";

    public long pictureId;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;


    public static PictureListFragment getInstantce(long pictureId) {
        PictureListFragment fragment = new PictureListFragment();
        Bundle bundle = new Bundle();
        bundle.putLong(PICTURE_ID, pictureId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.common_recycler_view, null);
        ButterKnife.bind(this, contentView);


        return contentView;
    }

    @Override
    public void refreshList(List<Picture> list) {

    }

    @Override
    public void setPresenter(PictureListContract.Presenter presenter) {

    }
}
