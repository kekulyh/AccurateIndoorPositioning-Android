package au.usyd.capstone.indoorandroid.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import au.usyd.capstone.indoorandroid.R;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class SingleRoomActivity extends SwipeBackActivity {

//    private IndoorDataManager mIndoorManager;

    private WebView mWebView;

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

//        初始化WebVeiw
        initWebView();



    }


    private void initWebView(){
//        WebView设置
        mWebView = (WebView) findViewById(R.id.singleRoomWebView);
//        获取settings,使webview支持js
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
//        支持两指页面缩放
        webSettings.setBuiltInZoomControls(true);
//        loadUrl要在setWebViewClient之前调用
        mWebView.loadUrl("http://192.168.0.6:8080/capstone/monitorandroid");
//        mWebView.addJavascriptInterface();
//        设置自己覆写的webclient,在该页面内打开指定url,不跳转浏览器
        mWebView.setWebViewClient(new MyWebViewClient());
        mWebView.setWebChromeClient(new MyWebChromeClient());
    }


//    自定义的WebViewClient,这样可以不跳转至自带浏览器
    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
        }

//    拦截request请求的url
        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
            // 非超链接(如Ajax)请求无法直接添加请求头，现拼接到url末尾,这里拼接一个imei作为示例

            String ajaxUrl = url;
            // 如标识:req=ajax
            if (url.contains("req=ajax")) {
//                ajaxUrl += "&imei=" + imei;
            }
            Log.e("SingleRoomActivity", url);
            return super.shouldInterceptRequest(view, ajaxUrl);
        }
    }


//    自定义的WebChromeClient,覆写onJsAlert,避免alert的title里显示为“：来自file:////”
    private class MyWebChromeClient extends WebChromeClient {
        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {

            final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

            builder.setTitle("Alert")
                    .setMessage(message)
                    .setPositiveButton("OK", null);

            // 不需要绑定按键事件
            // 屏蔽keycode等于84之类的按键
            builder.setOnKeyListener(new DialogInterface.OnKeyListener() {
                public boolean onKey(DialogInterface dialog, int keyCode,KeyEvent event) {
                    Log.v("onJsAlert", "keyCode==" + keyCode + "event="+ event);
                    return true;
                }
            });
            // 禁止响应按back键的事件
            builder.setCancelable(false);
            AlertDialog dialog = builder.create();
            dialog.show();
            result.confirm();// 因为没有绑定事件，需要强行confirm,否则页面会变黑显示不了内容。
            return true;
//            return super.onJsAlert(view, url, message, result);
        }
    }


//    Ajax request 按钮
    public void ajax(View v){
//        调用页面内的该androidAjax方法
        mWebView.loadUrl("javascript:androidAjax()");
    }


//    终止ajax request 按钮
    public void abort(View v){
//        调用页面内的该androidAbort方法
        mWebView.loadUrl("javascript:androidAbort()");
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
//        设置activity退出动画效果
        overridePendingTransition(0, android.R.anim.slide_out_right);
    }

    @Override
    protected void onDestroy() {
        mWebView.loadUrl("javascript:androidAbort()");
        super.onDestroy();
    }
}
