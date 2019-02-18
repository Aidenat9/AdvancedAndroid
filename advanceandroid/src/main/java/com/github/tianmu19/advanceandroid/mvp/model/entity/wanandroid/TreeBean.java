package com.github.tianmu19.advanceandroid.mvp.model.entity.wanandroid;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jingbin
 * @data 2018/9/15
 * @description
 */

public class TreeBean  {

    private int errorCode;
    private String errorMsg;
    private List<TreeItemBean> data;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg == null ? "" : errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public List<TreeItemBean> getData() {
        if (data == null) {
            return new ArrayList<>();
        }
        return data;
    }

    public void setData(List<TreeItemBean> data) {
        this.data = data;
    }
}
