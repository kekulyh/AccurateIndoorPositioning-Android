package au.usyd.capstone.indoorandroid.view;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import au.usyd.capstone.indoorandroid.R;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class SingleRoomActivity extends SwipeBackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_room);

        WebView mWebView = (WebView) findViewById(R.id.singleRoomWebView);
        mWebView.loadUrl("http://www.google.com");

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }
}
