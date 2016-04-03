package au.usyd.capstone.indoorandroid.view;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import au.usyd.capstone.indoorandroid.R;
import au.usyd.capstone.indoorandroid.domain.Building;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class FloorActivity extends SwipeBackActivity {

    // RecyclerView
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private static Drawable drawableBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor);

        Toolbar toolbar = (Toolbar) findViewById(R.id.floorToolbar);
        String title = getIntent().getStringExtra("title");
//        设置toolbar标题,这样设置要放在setSupportActionBar之前,如果用getSupportActionBar().setTitle()则要放在之后
//        toolbar.setTitle(title);
//        设置toolbar为actionbar
        setSupportActionBar(toolbar);
//        给左上角图标的左边加上一个返回的图标
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        设置左上角的图标可以点击
        getSupportActionBar().setHomeButtonEnabled(true);

//        将title设置在CollapsiingToolbarLayout上
        CollapsingToolbarLayout mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.floorCollapsingToolbarLayout);
        mCollapsingToolbarLayout.setTitle(title);

//        设置CollapsingToolbar上的图片
        ImageView imageView = (ImageView) findViewById(R.id.floorImageView);
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.building_example_sm));

        // 设置fragment背景
        drawableBackground = getResources().getDrawable(R.drawable.page_background_repeat);

        // init RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.floorRecyclerView);
        Log.e("FloorActivity", "mRecyclerView create");

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        Log.e("FloorActivity", "mLayoutManager");

        // specify an adapter (see also next example)
        mAdapter = new BuildingAdapter(this, createList(20));
        mRecyclerView.setAdapter(mAdapter);
        Log.e("FloorActivity", "mAdapter");


    }

//    toolbar按钮功能
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
//                // app icon in action bar clicked; go home
//                Intent intent = new Intent(this, MainActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent);
//                return true;
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private List createList(int size) {

        List result = new ArrayList();
        for (int i = 1; i <= size; i++) {
            Building ci = new Building();
            ci.setBuildingImage(getResources().getDrawable(R.drawable.floor));
            ci.setBuildingName("Floor: Floor_" + i);
            ci.setBuildingOpeningTime("Floor Details: Detail_" + i);

            result.add(ci);

        }

        return result;
    }

}
