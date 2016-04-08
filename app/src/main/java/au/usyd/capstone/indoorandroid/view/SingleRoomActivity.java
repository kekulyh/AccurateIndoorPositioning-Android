package au.usyd.capstone.indoorandroid.view;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import au.usyd.capstone.indoorandroid.R;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class SingleRoomActivity extends SwipeBackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_room);

        Toolbar toolbar = (Toolbar) findViewById(R.id.floorToolbar);
        String title = getIntent().getStringExtra("title");
//        设置toolbar标题,这样设置要放在setSupportActionBar之前,如果用getSupportActionBar().setTitle()则要放在之后
        toolbar.setTitle(title);
//        设置toolbar为actionbar
        setSupportActionBar(toolbar);
//        给左上角图标的左边加上一个返回的图标
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        设置左上角的图标可以点击
        getSupportActionBar().setHomeButtonEnabled(true);


//        WebView设置
        WebView mWebView = (WebView) findViewById(R.id.singleRoomWebView);
//        获取settings,使webview支持js
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
//        支持两指页面缩放
//        webSettings.setBuiltInZoomControls(true);
//        loadUrl要在setWebViewClient之前调用
        mWebView.loadUrl("http://192.168.0.14:8080/capstone/monitorandroid");
//        mWebView.addJavascriptInterface();
//        设置自己覆写的webclient,在该页面内打开指定url,不跳转浏览器
        mWebView.setWebViewClient(new MyWebViewClient());



    }


    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

                // This is my web site, so do not override; let my WebView load the page
                view.loadUrl(url);
                return true;

        }
    }

    //    toolbar按钮功能,返回键
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

//    必须在调用finish()之后调用overridePendingTransition才会生效
    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        设置退出动画效果
        overridePendingTransition(0, android.R.anim.slide_out_right);

    }
}
