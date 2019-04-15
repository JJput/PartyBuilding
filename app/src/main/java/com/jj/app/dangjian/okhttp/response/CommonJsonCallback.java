package com.jj.app.dangjian.okhttp.response;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.jj.app.dangjian.okhttp.exception.OkHttpException;
import com.jj.app.dangjian.okhttp.listener.DisposeDataHandle;
import com.jj.app.dangjian.okhttp.listener.DisposeDataListener;
import com.jj.app.dangjian.okhttp.listener.DisposeHandleCookieListener;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import github.jjput.mvpbaselibrary.base.LogUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.Response;

/**
 * @author vision
 * @function 专门处理JSON的回调
 */
public class CommonJsonCallback implements Callback {

    /**
     * the logic layer exception, may alter in different app
     */
    protected final String RESULT_CODE = "RESULTCODE";
    protected final String RESULT_CODE_VALUE = "0";
    protected final String ERROR_MSG = "msg";
    protected final String EMPTY_MSG = "";
    protected final String COOKIE_STORE = "Set-Cookie";
    // can has the value of
    // set-cookie2

    /**
     * the java layer exception, do not same to the logic error
     */
    protected final int NETWORK_ERROR = -1; // the network relative error
    protected final int JSON_ERROR = -2; // the JSON relative error
    protected final int OTHER_ERROR = -3; // the unknow error
    protected final int MESSAGE_ERROR = 0; // the unknow error

    /**
     * 将其它线程的数据转发到UI线程
     */
    private Handler mDeliveryHandler;
    private DisposeDataListener mListener;
    private Class<?> mClass;

    public CommonJsonCallback(DisposeDataHandle handle) {
        this.mListener = handle.mListener;
        this.mClass = handle.mClass;
        this.mDeliveryHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void onFailure(final Call call, final IOException ioexception) {
        /**
         * 此时还在非UI线程，因此要转发
         */
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                mListener.onFailure(new OkHttpException(NETWORK_ERROR, "请求服务器超时"));
            }
        });
    }

    @Override
    public void onResponse(final Call call, final Response response) {
        String result = null;
        try {
            result = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            LogUtils.e("result = response.body().string();  崩溃");
            return;
        }
        final ArrayList<String> cookieLists = handleCookie(response.headers());
        final String finalResult = result;
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                handleResponse(finalResult);
                /**
                 * handle the cookie
                 */
                if (mListener instanceof DisposeHandleCookieListener) {
                    ((DisposeHandleCookieListener) mListener).onCookie(cookieLists);
                }
            }
        });
    }

    private ArrayList<String> handleCookie(Headers headers) {
        ArrayList<String> tempList = new ArrayList<String>();
        for (int i = 0; i < headers.size(); i++) {
            if (headers.name(i).equalsIgnoreCase(COOKIE_STORE)) {
                tempList.add(headers.value(i));
            }
        }
        return tempList;
    }

    private void handleResponse(Object responseObj) {
//        LogUtils.i(responseObj.toString());
        if (responseObj == null || responseObj.toString().trim().equals("")) {
            mListener.onFailure(new OkHttpException(NETWORK_ERROR, EMPTY_MSG));
            return;
        }
        if (responseObj.toString().indexOf("404") != -1) {
            mListener.onFailure(new OkHttpException(OTHER_ERROR, "连接超时!"));
            return;
        }
        try {
            /**
             * 返回结果处理
             */
            JSONObject result = new JSONObject(responseObj.toString());
            if (result.getString(RESULT_CODE).equals(RESULT_CODE_VALUE)) {
                //                Object obj = ResponseEntityToModule.parseJsonObjectToModule(result, mClass);
                Gson gson = new Gson();
                Object obj = gson.fromJson(responseObj.toString(), mClass);
                if (obj != null) {
                    mListener.onSuccess(obj);
                } else {
                    mListener.onFailure(new OkHttpException(JSON_ERROR, "服务器错误!请联系管理员!"));
                }
            } else {
                mListener.onFailure(new OkHttpException(JSON_ERROR, result.getJSONObject("MSG").toString()));
            }
        } catch (Exception e) {
            mListener.onFailure(new OkHttpException(JSON_ERROR, "服务器错误!请联系管理员!"));
            e.printStackTrace();
        }
    }
}