package com.jj.app.dangjian.view.webview;


import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jj.app.dangjian.R;
import com.jj.app.dangjian.view.BaseActivity;
import com.tencent.sonic.sdk.BuildConfig;
import com.tencent.sonic.sdk.SonicConfig;
import com.tencent.sonic.sdk.SonicEngine;
import com.tencent.sonic.sdk.SonicRuntime;
import com.tencent.sonic.sdk.SonicSession;
import com.tencent.sonic.sdk.SonicSessionClient;
import com.tencent.sonic.sdk.SonicSessionConfig;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import github.jjput.mvpbaselibrary.base.IPresenter;

/**
 * @作者: JJ
 * @创建时间: 2019/4/11 11:05 PM
 * @Version 1.0
 * @描述:
 */
public class WebsiteActivity extends BaseActivity {

    public static final String TITLE_NAME = "title";
    public static final String URL = "url";

    @BindView(R.id.website_wb)
    WebView webView;

    private SonicSession sonicSession;


    @Override
    protected int getLayoutId() {
        return R.layout.website_activity;
    }

    @Override
    protected IPresenter initInjector() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        //        String url = "https://www.weibo.com/";
        String url = intent.getStringExtra(URL);
        getTitleName().setText(intent.getStringExtra(TITLE_NAME));
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        //步骤1：如有必要初始化声音引擎，或者也许当应用程序创建u能做到这一点
        if (!SonicEngine.isGetInstanceAllowed()) {
            SonicEngine.createInstance(new SonicRuntimeImpl(getApplication()), new SonicConfig.Builder().build());
        }
        SonicSessionClientImpl sonicSessionClient = null;
        //步骤2：创建SonicSession
        sonicSession = SonicEngine.getInstance().createSession(url, new SonicSessionConfig.Builder().build());
        if (null != sonicSession) {
            sonicSession.bindClient(sonicSessionClient = new SonicSessionClientImpl());
        } else {
            //只有在同一个声音会话已经运行时才会发生，
            //可以将以下代码注释为默认模式。
        }
        //步骤3：BindWebView为sessionClient和bindClient为SonicSession
        //在现实世界中，init流可能花费很长的时间在启动
        //运行时，初始化CONFIGS ....

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (sonicSession != null) {
                    sonicSession.getSessionClient().pageFinish(url);
                }
            }

            @TargetApi(21)
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                return shouldInterceptRequest(view, request.getUrl().toString());
            }

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                if (sonicSession != null) {
                    //第6步：致电sessionClient.requestResource当主机允许应用程序
                    //返回本地数据。
                    return (WebResourceResponse) sonicSession.getSessionClient().requestResource(url);
                }
                return null;
            }
        });

        WebSettings webSettings = webView.getSettings();

        //步骤4：绑定javascript
        //注意：如果api级别低于17（android 4.2），addJavascriptInterface有安全性
        //问题，请使用x5或参见https://developer.android.com/reference/android/webkit/
        // WebView.html＃addJavascriptInterface（java.lang.Object，java.lang.String）
        webSettings.setJavaScriptEnabled(true);
        webView.removeJavascriptInterface("searchBoxJavaBridge_");
        //        intent.putExtra(SonicJavaScriptInterface.PARAM_LOAD_URL_TIME, System.currentTimeMillis());
        //        webView.addJavascriptInterface(new SonicJavaScriptInterface(sonicSessionClient, intent), "sonic");
        // init webview设置
        webSettings.setAllowContentAccess(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setSavePassword(false);
        webSettings.setSaveFormData(false);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        //步骤5：webview已经准备就绪，只需告诉会话客户端绑定
        if (sonicSessionClient != null) {
            sonicSessionClient.bindWebView(webView);
            sonicSessionClient.clientReady();
        } else { //默认模式
            webView.loadUrl(url);
        }
    }

    @Override
    protected void initView() {
        super.initView();
        //        Intent intent = getIntent();
        //        getTitleName().setText(intent.getStringExtra(TITLE_NAME));
        //        String url = intent.getStringExtra(URL);
        //        LogUtils.i(TAG, url);
        //        mAgentWeb = AgentWeb.with(this)
        //                .setAgentWebParent(mll, new LinearLayout.LayoutParams(-1, -1))
        //                .useDefaultIndicator()
        //                .createAgentWeb()
        //                .ready()
        //                .go(url);
    }


    public class SonicSessionClientImpl extends SonicSessionClient {

        private WebView webView;

        public void bindWebView(WebView webView) {
            this.webView = webView;
        }

        public WebView getWebView() {
            return webView;
        }

        @Override
        public void loadUrl(String url, Bundle extraData) {
            webView.loadUrl(url);
        }

        @Override
        public void loadDataWithBaseUrl(String baseUrl, String data, String mimeType, String encoding, String historyUrl) {
            webView.loadDataWithBaseURL(baseUrl, data, mimeType, encoding, historyUrl);
        }


        @Override
        public void loadDataWithBaseUrlAndHeader(String baseUrl, String data, String mimeType, String encoding, String historyUrl, HashMap<String, String> headers) {
            loadDataWithBaseUrl(baseUrl, data, mimeType, encoding, historyUrl);
        }

        public void destroy() {
            if (null != webView) {
                webView.destroy();
                webView = null;
            }
        }

    }

    public class SonicRuntimeImpl extends SonicRuntime {

        public SonicRuntimeImpl(Context context) {
            super(context);
        }

        /**
         * 获取用户UA信息，这里的返回值会放在header的UserAgent中
         *
         * @return
         */
        @Override
        public String getUserAgent() {
            return "";
        }

        /**
         * 获取用户ID信息，避免多个用户切换可能使用到相同的缓存
         *
         * @return
         */
        @Override
        public String getCurrentUserAccount() {
            return "sonic-demo-master";
        }

        @Override
        public String getCookie(String url) {
            CookieManager cookieManager = CookieManager.getInstance();
            return cookieManager.getCookie(url);
        }

        @Override
        public void log(String tag, int level, String message) {
            switch (level) {
                case Log.ERROR:
                    Log.e(tag, message);
                    break;
                case Log.INFO:
                    Log.i(tag, message);
                    break;
                default:
                    Log.d(tag, message);
            }
        }

        @Override
        public Object createWebResourceResponse(String mimeType, String encoding, InputStream data, Map<String, String> headers) {
            WebResourceResponse resourceResponse = new WebResourceResponse(mimeType, encoding, data);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                resourceResponse.setResponseHeaders(headers);
            }
            return resourceResponse;
        }

        @Override
        public void showToast(CharSequence text, int duration) {

        }

        @Override
        public void notifyError(SonicSessionClient client, String url, int errorCode) {

        }

        // 这里可以设置某个url是否为SonicUrl，如果指定为不是，则不会通过Sonic的方式加载url。
        @Override
        public boolean isSonicUrl(String url) {
            return true;
        }

        @Override
        public boolean setCookie(String url, List<String> cookies) {
            if (!TextUtils.isEmpty(url) && cookies != null && cookies.size() > 0) {
                CookieManager cookieManager = CookieManager.getInstance();
                for (String cookie : cookies) {
                    cookieManager.setCookie(url, cookie);
                }
                return true;
            }
            return false;
        }

        // 判断网络连接情况
        @Override
        public boolean isNetworkValid() {
            return true;
        }

        @Override
        public void postTaskToThread(Runnable task, long delayMillis) {
            Thread thread = new Thread(task, "SonicThread");
            thread.start();
        }

        @Override
        public File getSonicCacheDir() {
            if (BuildConfig.DEBUG) {
                String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "sonic/";
                File file = new File(path.trim());
                if (!file.exists()) {
                    file.mkdir();
                }
                return file;
            }
            return super.getSonicCacheDir();
        }

        @Override
        public String getHostDirectAddress(String url) {
            return null;
        }
    }
}
