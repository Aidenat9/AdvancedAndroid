package com.github.tianmu19.advanceandroid.mvp.model.entity.wanandroid;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jingbin
 * @data 2018/9/27
 * @description
 */

public class CollectUrlBean  {

    /**
     * data : [{"desc":"","icon":"","id":720,"link":"http://www.wanandroid.com/blog/show/2376","name":"Android 本地化翻译插件，解放你的双手！ AndroidLocalizePlugin-玩Android - wanandroid.com","order":0,"userId":1534,"visible":1}]
     * errorCode : 0
     * errorMsg :
     */

    private int errorCode;
    private String errorMsg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        if (data == null) {
            return new ArrayList<>();
        }
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * desc :
         * icon :
         * id : 720
         * link : http://www.wanandroid.com/blog/show/2376
         * name : Android 本地化翻译插件，解放你的双手！ AndroidLocalizePlugin-玩Android - wanandroid.com
         * order : 0
         * userId : 1534
         * visible : 1
         */

        private String desc;
        private String icon;
        private int id;
        private String link;
        private String name;
        private int order;
        private int userId;
        private int visible;

        public String getDesc() {
            return desc == null ? "" : desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getIcon() {
            return icon == null ? "" : icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLink() {
            return link == null ? "" : link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getName() {
            return name == null ? "" : name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getVisible() {
            return visible;
        }

        public void setVisible(int visible) {
            this.visible = visible;
        }
    }
}
