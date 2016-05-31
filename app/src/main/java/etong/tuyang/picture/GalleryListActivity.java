package etong.tuyang.picture;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import etong.tuyang.R;


/**
 * Created by hwt on 2016/5/21.
 */

public class GalleryListActivity extends AppCompatActivity {

    public final static String CLASS_ID = "classId";

    private int classId;

    private GalleryListPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_frame);

        classId = getIntent().getIntExtra(CLASS_ID, -1);
        if (classId == -1) {

            return;
        }
        GalleryListFragment fragment = GalleryListFragment.getInstance(classId);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment);

        presenter = new GalleryListPresenter(fragment);
    }

}
