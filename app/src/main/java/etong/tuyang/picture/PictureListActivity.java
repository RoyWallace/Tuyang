package etong.tuyang.picture;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import etong.tuyang.R;
import etong.tuyang.picture.data.HttpHelper;
import etong.tuyang.picture.data.remote.Gallery;
import etong.tuyang.picture.data.remote.GalleryResult;
import etong.tuyang.util.UIUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by hwt on 2016/5/21.
 */

public class PictureListActivity extends AppCompatActivity {

    public final static String IMAGE_API = "http://tnfs.tngou.net/img";

    public final static String CLASS_ID = "classId";

    private int classId;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private ArrayList<Gallery> imageList = new ArrayList<>();

    private ImageAdapter imageAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        classId = getIntent().getIntExtra(CLASS_ID, 0);

        imageAdapter = new ImageAdapter();
        recyclerView.setAdapter(imageAdapter);

        HttpHelper.getInstance().getGalleryList(classId, 20, classId, new Callback<GalleryResult>() {
            @Override
            public void onResponse(Call<GalleryResult> call, Response<GalleryResult> response) {
                GalleryResult result = response.body();
                if (result != null && result.status && result.tngou != null) {
                    imageList.addAll(result.tngou);
                    imageAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<GalleryResult> call, Throwable t) {

            }
        });

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
