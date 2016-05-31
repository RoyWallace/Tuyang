package etong.tuyang.picture;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import etong.tuyang.R;
import etong.tuyang.picture.data.remote.Gallery;

/**
 * Created by hwt on 2016/5/23.
 */
public class GalleryListFragment extends Fragment implements GalleryListContract.View {

    public final static String IMAGE_API = "http://tnfs.tngou.net/img";

    private int classId;

    private GalleryListContract.Presenter presenter;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private ArrayList<Gallery> imageList = new ArrayList<>();

    private ImageAdapter imageAdapter;

    public static GalleryListFragment getInstance(int classId) {
        GalleryListFragment fragment = new GalleryListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(GalleryListActivity.CLASS_ID, classId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void setPresenter(GalleryListContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        classId = getArguments().getInt(GalleryListActivity.CLASS_ID);

        imageAdapter = new ImageAdapter();
        recyclerView.setAdapter(imageAdapter);

    }

    @Override
    public void refreshList() {
//        imageList.addAll();
        imageAdapter.notifyDataSetChanged();
    }

    class ImageAdapter extends RecyclerView.Adapter<ImageViewHolder> {

        @Override
        public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View contentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image_list, parent, false);
            return new ImageViewHolder(contentView);
        }

        @Override
        public void onBindViewHolder(ImageViewHolder holder, int position) {
            holder.bindData(imageList.get(position));
        }

        @Override
        public int getItemCount() {
            return imageList.size();
        }
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public ImageViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }

        public void bindData(final Gallery gallery) {
            Glide.with(itemView.getContext()).load(IMAGE_API + gallery.img).into(imageView);
        }
    }
}
