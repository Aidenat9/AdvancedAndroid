package com.github.tianmu19.baselibrary.ui.base

import android.content.Context
import android.os.Bundle
import android.os.IBinder
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_base.*


class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.github.tianmu19.baselibrary.R.layout.activity_base)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    /**
     * 点击EditText时弹出软键盘，然后在点击空白处或者其他控件的时候隐藏软键盘。
     * 这个需求在平板电脑上非常实用，因为屏幕大，用户不可能每次都点左下角去隐藏，并且点击空白处更容易
     */
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if(ev!!.action==MotionEvent.ACTION_DOWN){
            val view = currentFocus
            if(isShouldHideInput(view, ev)){
                hideSoftInput(view!!.windowToken)
            }
        }

        return super.dispatchTouchEvent(ev)
    }

    private fun hideSoftInput(windowToken: IBinder?) {
        if (windowToken != null) {
            val im = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            im!!.hideSoftInputFromWindow(
                windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }

    private fun isShouldHideInput(view: View?, ev: MotionEvent): Boolean {
        if(null!=view&&(view is EditText)){
            val location = intArrayOf(0, 0)
            view.getLocationInWindow(location)
            val left = location[0]
            val top = location[1]
            val right = left+view.width
            val bottom = top+view.height
            // 点击EditText的事件，忽略它。
            return !(ev.x > left && ev.x < right
                    && ev.y > top && ev.y < bottom)

        }// 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditView上，和用户用轨迹球选择其他的焦点
        return false
    }

}
