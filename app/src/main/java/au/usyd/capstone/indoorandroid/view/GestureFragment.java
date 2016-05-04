package au.usyd.capstone.indoorandroid.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import org.xwalk.core.XWalkPreferences;
import org.xwalk.core.XWalkSettings;
import org.xwalk.core.XWalkView;

import au.usyd.capstone.indoorandroid.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GestureFragment.OnGestureFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GestureFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GestureFragment extends Fragment {

    // WebView
    private WebView mWebView;

    // XWalkView
    private XWalkView mXWalkView;

    private static Drawable drawableBackground;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnGestureFragmentInteractionListener mListener;

    public GestureFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GestureFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GestureFragment newInstance(String param1, String param2) {
        GestureFragment fragment = new GestureFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

//        设置fragment背景
        drawableBackground = getResources().getDrawable(R.drawable.page_background_repeat);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gesture, container, false);

        // init webview
//        initWebView(view);

        // init XWalkView
        initXWalkView(view);

        Button btnStart = (Button) view.findViewById(R.id.gestureStartButton);
        Button btnStop = (Button) view.findViewById(R.id.gestureStopButton);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mXWalkView.load("javascript:start()", null);
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mXWalkView.load("javascript:stop()", null);
            }
        });


        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onGestureFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnGestureFragmentInteractionListener) {
            mListener = (OnGestureFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    // init XWalkView
    private void initXWalkView(View view){
        mXWalkView = (XWalkView) view.findViewById(R.id.gestureXWalkView);

        // turn on debugging
        XWalkPreferences.setValue(XWalkPreferences.REMOTE_DEBUGGING, true);

        // 获取setting
        XWalkSettings mXWalkSettings = mXWalkView.getSettings();

        // 支持两指缩放
        mXWalkSettings.setBuiltInZoomControls(true);

        mXWalkView.load("http://192.168.0.6:8080/capstone/gestureandroid", null);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mXWalkView != null) {
            mXWalkView.pauseTimers();
            mXWalkView.onHide();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mXWalkView != null) {
            mXWalkView.resumeTimers();
            mXWalkView.onShow();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mXWalkView != null) {
            mXWalkView.onDestroy();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        if (mXWalkView != null) {
            mXWalkView.onActivityResult(requestCode, resultCode, data);
        }
    }

    //    // init webview
//    private void initWebView(View view){
////        WebView设置
//        mWebView = (WebView) view.findViewById(R.id.gestureWebView);
////        获取settings,使webview支持js
//        WebSettings webSettings = mWebView.getSettings();
//        webSettings.setJavaScriptEnabled(true);
////        支持两指页面缩放
//        webSettings.setBuiltInZoomControls(true);
////        loadUrl要在setWebViewClient之前调用
//        mWebView.loadUrl("http://192.168.0.6:8080/capstone/gesture");
////        mWebView.addJavascriptInterface();
////        设置自己覆写的webclient,在该页面内打开指定url,不跳转浏览器
//        mWebView.setWebViewClient(new MyWebViewClient());
//        mWebView.setWebChromeClient(new MyWebChromeClient());
//    }


    // 自定义的WebViewClient,这样可以不跳转至自带浏览器
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


    // 自定义的WebChromeClient,覆写onJsAlert,避免alert的title里显示为“：来自file:////”
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



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnGestureFragmentInteractionListener {
        // TODO: Update argument type and name
        void onGestureFragmentInteraction(Uri uri);
    }
}
