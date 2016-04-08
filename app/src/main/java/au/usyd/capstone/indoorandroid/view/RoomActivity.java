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
import au.usyd.capstone.indoorandroid.domain.Room;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created by LYH on 16/4/5.
 */
public class RoomActivity extends SwipeBackActivity {

    // RecyclerView
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private static Drawable drawableBackground;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        Toolbar toolbar = (Toolbar) findViewById(R.id.roomToolbar);
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
        CollapsingToolbarLayout mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.roomCollapsingToolbarLayout);
        mCollapsingToolbarLayout.setTitle(title);

//        设置CollapsingToolbar上的图片
        ImageView imageView = (ImageView) findViewById(R.id.roomImageView);
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.room_example_sm));

        // 设置背景,decorView为最顶层的view
        drawableBackground = getResources().getDrawable(R.drawable.page_background_repeat);
//        getWindow().getDecorView().setBackgroundDrawable(drawableBackground);
        RecyclerView roomRecyclerView = (RecyclerView) findViewById(R.id.roomRecyclerView);
        roomRecyclerView.setBackgroundDrawable(drawableBackground);

        // init RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.roomRecyclerView);
        Log.e("RoomActivity", "mRecyclerView create");

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        Log.e("RooomActivity", "mLayoutManager");

        // specify an adapter (see also next example)
        mAdapter = new RoomAdapter(this, createList(5));
        mRecyclerView.setAdapter(mAdapter);
        Log.e("RoomActivity", "mAdapter");


    }

//    toolbar按钮功能
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, android.R.anim.slide_out_right);
    }

    private List createList(int size) {

        List result = new ArrayList();
        for (int i = 1; i <= size; i++) {
            Room ci = new Room();
            ci.setRoomImage(getResources().getDrawable(R.drawable.room));
            ci.setRoomName("Room: Room_" + i);
            ci.setRoomDetail("Room Details: Detail_" + i);

            result.add(ci);

        }

        return result;
    }


}
