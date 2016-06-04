package etong.tuyang.picture;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import etong.tuyang.R;
import etong.tuyang.picture.data.remote.Gallery;
import etong.tuyang.util.UIUtil;

/**
 * Created by hwt on 2016/5/23.
 */
public class GalleryListFragment extends Fragment implements GalleryListContract.View {

    public final static String IMAGE_API = "http://tnfs.tngou.net/img";

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private int classId;

    private String imageSizeString;

    private GalleryListContract.Presenter presenter;

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
        View contentView = inflater.inflate(R.layout.common_recycler_view, null);
        ButterKnife.bind(this, contentView);
        return contentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        classId = getArguments().getInt(GalleryListActivity.CLASS_ID);

        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.top = getResources().getDimensionPixelOffset(R.dimen.activity_vertical_margin);
            }
        });
        recyclerView.setBackgroundColor(0xff565656);

        imageAdapter = new ImageAdapter();
        recyclerView.setAdapter(imageAdapter);

        imageSizeString = getImageSize();

        presenter.getGalleryList(classId);


    }

    @Override
    public void refreshList(List<Gallery> list) {
        imageList.clear();
        imageList.addAll(list);
        imageAdapter.notifyDataSetChanged();
    }

    class ImageAdapter extends RecyclerView.Adapter<ImageViewHolder> {

        @Override
        public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View contentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gallery_list, parent, false);
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

        ImageView coverImageView;

        TextView titleTextView;

        TextView sizeTextView;

        public ImageViewHolder(View itemView) {
            super(itemView);
            coverImageView = (ImageView) itemView.findViewById(R.id.coverImageView);
            titleTextView = (TextView) itemView.findViewById(R.id.titleTextView);
            sizeTextView = (TextView) itemView.findViewById(R.id.sizeTextView);
        }

        public void bindData(final Gallery gallery) {
            Glide.with(itemView.getContext()).load(IMAGE_API + gallery.img).into(coverImageView);
            titleTextView.setText(gallery.title);
            sizeTextView.setText(String.format("%s 张", gallery.size));
        }
    }

    public String getImageSize() {
        int width = UIUtil.getScreenWidth(getActivity());
        return String.format("_%sx%s", width, width / 2);
    }

}
