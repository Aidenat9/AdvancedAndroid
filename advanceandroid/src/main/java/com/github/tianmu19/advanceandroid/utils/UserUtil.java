package com.github.tianmu19.advanceandroid.utils;

import android.content.Context;
import com.github.tianmu19.advanceandroid.app.Constants;

/**
 * @author jingbin
 * @data 2018/5/7
 * @Description 处理用户登录问题
 */

public class UserUtil {


    /**
     * 是否登录，没有进入登录页面
     */
    public static boolean isLogin(Context context) {
        boolean isLogin = SPUtils.getBoolean(Constants.IS_LOGIN, false);
        if (!isLogin) {
            ToastUtil.showToastLong("请先登录~");
//            LoginActivity.start(context);
            return false;
        } else {
            return true;
        }
    }
}
