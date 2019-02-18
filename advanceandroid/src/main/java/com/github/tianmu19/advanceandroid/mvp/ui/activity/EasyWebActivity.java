package com.github.tianmu19.advanceandroid.mvp.ui.activity;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;
import com.github.tianmu19.advanceandroid.R;
import com.github.tianmu19.advanceandroid.app.Constants;
import com.github.tianmu19.advanceandroid.utils.*;
import com.github.tianmu19.advanceandroid.widgets.DialogBuild;

/**
 * Created by cenxiaozhong on 2017/7/22.
 * <p>
 */
public class EasyWebActivity extends BaseAgentWebActivity {

    private String url;
    private Toolbar mToolbar;
    private TextView tvTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String title = getIntent().getStringExtra("title");
        url = getIntent().getStringExtra("url");
        setContentView(R.layout.activity_web);

//        LinearLayout mLinearLayout = (LinearLayout) this.findViewById(R.id.container);
        mToolbar = this.findViewById(R.id.toolbar);
        tvTitle = findViewById(R.id.tv_gun_title);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getActionBar();
        if (null != actionBar) {
            actionBar.setDisplayShowTitleEnabled(false);
        }
        mToolbar.setOverflowIcon(ContextCompat.getDrawable(this, R.drawable.actionbar_more));
        tvTitle.postDelayed(() -> tvTitle.setSelected(true), 1800);
        tvTitle.setText(title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_webview,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // 返回键
                onBackPressed();
                break;
            case R.id.actionbar_share:
                // 分享到
                String shareText = mAgentWeb.getWebCreator().getWebView().getTitle() + url + " (分享自进阶安卓)";
                ShareUtils.share(this, shareText);
                break;
            case R.id.actionbar_cope:
                // 复制链接
                BaseTools.copy(url);
                ToastUtil.showToast("复制成功");
                break;
            case R.id.actionbar_open:
                // 打开链接
                BaseTools.openLink(this, url);
                break;
            case R.id.actionbar_webview_refresh:
                // 刷新页面
                mAgentWeb.getWebCreator().getWebView().reload();
                break;
            case R.id.actionbar_collect:
                // 添加到收藏
                if (UserUtil.isLogin(getBaseContext())) {
                    if (SPUtils.getBoolean(Constants.IS_FIRST_COLLECTURL, true)) {
                        DialogBuild.show(tvTitle, "网址不同于文章，相同网址可多次进行收藏，且不会显示收藏状态。", "知道了", (DialogInterface.OnClickListener) (dialog, which) -> {
                            SPUtils.putBoolean(Constants.IS_FIRST_COLLECTURL, false);
                            collectUrl();
                        });
                    } else {
                        collectUrl();
                    }
                }
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void collectUrl() {
        //
    }

    @NonNull
    @Override
    protected ViewGroup getAgentWebParent() {
        return (ViewGroup) this.findViewById(R.id.container);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mAgentWeb != null && mAgentWeb.handleKeyEvent(keyCode, event)) {
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void setTitle(WebView view, String title) {
    }

    @Override
    protected int getIndicatorColor() {
        return Color.parseColor("#ff0000");
    }

    @Override
    protected int getIndicatorHeight() {
        return 3;
    }

    @Nullable
    @Override
    protected String getUrl() {
        return url;
    }
}