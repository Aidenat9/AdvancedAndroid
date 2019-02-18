package com.github.tianmu19.advanceandroid.utils;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.widget.Toast;


import com.github.tianmu19.advanceandroid.app.MyApp;
import me.drakeet.support.toast.ToastCompat;

/**
 * @author sunwei
 *         邮箱：tianmu19@gmail.com
 *         时间：2018/12/4 16:45
 * 单例Toast
 * An Android library to hook and fix Toast BadTokenException
 */

public class ToastUtil {

    private static ToastCompat mToast;

    @SuppressLint("ShowToast")
    public static void showToast(String text) {
        if (!TextUtils.isEmpty(text)) {
            if (mToast == null) {
                mToast = ToastCompat.makeText(MyApp.instance, text, Toast.LENGTH_SHORT);
            } else {
                mToast.cancel();
                mToast = ToastCompat.makeText(MyApp.instance, text, Toast.LENGTH_SHORT);
            }
            mToast.setDuration(Toast.LENGTH_SHORT);
            mToast.setText(text);
            mToast.show();
        }
    }

    @SuppressLint("ShowToast")
    public static void showToastLong(String text) {
        if (!TextUtils.isEmpty(text)) {
            if (mToast == null) {
                mToast = ToastCompat.makeText(MyApp.instance, text, Toast.LENGTH_LONG);
            } else {
                mToast.cancel();
                mToast = ToastCompat.makeText(MyApp.instance, text, Toast.LENGTH_LONG);
            }
            mToast.setDuration(Toast.LENGTH_LONG);
            mToast.setText(text);
            mToast.show();
        }
    }

}
