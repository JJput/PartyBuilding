package com.jj.app.dangjian.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * @作者: JJ
 * @创建时间: 2019/4/11 3:46 PM
 * @Version 1.0
 * @描述:
 */
public class DialogUtils {


    public static AlertDialog.Builder showErrorDialog(Context context, String content, DialogInterface.OnClickListener listener) {
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(context);
        normalDialog.setTitle("警告!");
        normalDialog.setMessage(content);
        normalDialog.setPositiveButton("确定", listener);

        return normalDialog;
    }

    public static AlertDialog.Builder showTitleDialog(Context context,String title, String content, DialogInterface.OnClickListener listener) {
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(context);
        normalDialog.setTitle(title);
        normalDialog.setMessage(content);
        normalDialog.setPositiveButton("确定", listener);

        return normalDialog;
    }


}
