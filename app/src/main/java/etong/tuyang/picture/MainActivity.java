package etong.tuyang.picture;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import etong.tuyang.R;
import etong.tuyang.picture.data.HttpHelper;
import etong.tuyang.picture.data.remote.GalleryClass;
import etong.tuyang.picture.data.remote.GalleryClassResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private List<GalleryClass> galleryClassList = new ArrayList<>();

    private ListAdapter listAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        listAdapter = new ListAdapter();
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.bottom = TypedValue.COMPLEX_UNIT_DIP * 1;
            }
        });
        recyclerView.setAdapter(listAdapter);

        HttpHelper.getInstance().getGalleryClass(new Callback<GalleryClassResult>() {
            @Override
            public void onResponse(Call<GalleryClassResult> call, Response<GalleryClassResult> response) {
                GalleryClassResult result = response.body();
                if (result != null && result.status == true) {
                    galleryClassList.clear();
                    galleryClassList.addAll(result.tngou);
                    listAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<GalleryClassResult> call, Throwable t) {
                Log.e("HttpHelper", t.getMessage());
            }
        });
    }

    private class ListAdapter extends RecyclerView.Adapter<ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View contentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
            return new ViewHolder(contentView);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.bindData(galleryClassList.get(position));
        }

        @Override
        public int getItemCount() {
            return galleryClassList.size();
        }
    }


    private class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
        }

        public void bindData(final GalleryClass c) {
            textView.setText(c.description);
        }
    }
}
