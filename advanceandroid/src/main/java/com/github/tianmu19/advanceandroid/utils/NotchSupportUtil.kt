@file:Suppress("UNREACHABLE_CODE")

package com.github.tianmu19.advanceandroid.utils

import android.app.Activity
import android.content.Context
import android.os.Build
import android.util.Log
import com.github.tianmu19.advanceandroid.app.getTopActivity

/**
 * @author sunwei
 *邮箱：tianmu19@gmail.com
 *时间：2019/2/20 9:17
 *包名：com.github.tianmu19.advanceandroid.utils
 *<p>description:            </p>
 */
object NotchSupportUtil {

    //----------------------huawei
    fun hasNotchAtHuawei(context: Context): Boolean {
        var ret = false
        try {
            val classLoader = context.getClassLoader()
            val HwNotchSizeUtil = classLoader.loadClass("com.huawei.android.util.HwNotchSizeUtil")
            val get = HwNotchSizeUtil.getMethod("hasNotchInScreen")
            ret = get.invoke(HwNotchSizeUtil) as Boolean
        } catch (e: ClassNotFoundException) {
            Log.e("Notch", "hasNotchAtHuawei ClassNotFoundException")
        } catch (e: NoSuchMethodException) {
            Log.e("Notch", "hasNotchAtHuawei NoSuchMethodException")
        } catch (e: Exception) {
            Log.e("Notch", "hasNotchAtHuawei Exception")
        } finally {
            return ret
        }
    }

    //获取刘海尺寸：width、height
    //int[0]值为刘海宽度 int[1]值为刘海高度
    fun getNotchSizeAtHuawei(context: Context): IntArray {
        var ret = intArrayOf(0, 0)
        try {
            val cl = context.classLoader
            val HwNotchSizeUtil = cl.loadClass("com.huawei.android.util.HwNotchSizeUtil")
            val get = HwNotchSizeUtil.getMethod("getNotchSize")
            ret = get.invoke(HwNotchSizeUtil) as IntArray
        } catch (e: ClassNotFoundException) {
            Log.e("Notch", "getNotchSizeAtHuawei ClassNotFoundException")
        } catch (e: NoSuchMethodException) {
            Log.e("Notch", "getNotchSizeAtHuawei NoSuchMethodException")
        } catch (e: Exception) {
            Log.e("Notch", "getNotchSizeAtHuawei Exception")
        } finally {
            return ret
        }
    }

    //------------------------vivo
    val VIVO_NOTCH = 0x00000020//是否有刘海
    val VIVO_FILLET = 0x00000008//是否有圆角
//    vivo不提供接口获取刘海尺寸，目前vivo的刘海宽为100dp,高为27dp。

    fun hasNotchAtVivo(context: Context): Boolean {
        var ret = false
        try {
            val classLoader = context.classLoader
            val FtFeature = classLoader.loadClass("android.util.FtFeature")
            val method = FtFeature.getMethod("isFeatureSupport", Int::class.javaPrimitiveType)
            ret = method.invoke(FtFeature, VIVO_NOTCH) as Boolean
        } catch (e: ClassNotFoundException) {
            Log.e("Notch", "hasNotchAtVivo ClassNotFoundException")
        } catch (e: NoSuchMethodException) {
            Log.e("Notch", "hasNotchAtVivo NoSuchMethodException")
        } catch (e: Exception) {
            Log.e("Notch", "hasNotchAtVivo Exception")
        } finally {
            return ret
        }
    }
//------------------------oppo
    /**
     * OPPO不提供接口获取刘海尺寸，目前其有刘海屏的机型尺寸规格都是统一的。不排除以后机型会有变化。
    其显示屏宽度为1080px，高度为2280px。刘海区域则都是宽度为324px, 高度为80px。
     */
    fun hasNotchAtOPPO(context: Context): Boolean {
        return context.packageManager.hasSystemFeature("com.oppo.feature.screen.heteromorphism")
    }


    /**小米手机获取刘海高度
     * 其他手机也可以通过这个方法来间接避开刘海屏，但是有可能有些手机的刘海屏高度会高于状态栏的高度，所以这个方法获取到的结果并不一定安全。
     */
    fun getStatusBarHeight(context: Context): Int {
        var statusBarHeight = 0
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            statusBarHeight = context.resources.getDimensionPixelSize(resourceId)
        }
        return statusBarHeight
    }

    /**
     * 是否是刘海屏幕
     * true fasle
     */
    fun hasNotch(context: Context): Boolean {
        val maunfacturer = Build.MANUFACTURER
        when (maunfacturer) {
            "XIAOMI", "xiaomi" -> return hasNotchAtXiaomi(context)
            "HUAWEI", "huawei" -> return hasNotchAtHuawei(context)
            "VIVO", "vivo" -> return hasNotchAtVivo(context)
            "OPPO" -> return hasNotchAtOPPO(context)
            else -> return isAndroidP(context.getTopActivity())
        }
        return false
    }


    /**
     * Android P 刘海屏判断
     * @param activity
     * @return
     */
    fun isAndroidP(context: Activity): Boolean {
        val decorView = context.window.decorView
        if (decorView != null && Build.VERSION.SDK_INT >= 28) {
            val windowInsets = decorView.rootWindowInsets
            if (windowInsets != null) {
                val disCutoutCount = windowInsets.displayCutout
                return null != disCutoutCount
            }
        }
        return false
    }


    /**
     * 小米刘海屏判断.
     * @return 0 if it is not notch ; return 1 means notch
     * @throws IllegalArgumentException if the key exceeds 32 characters
     */
    //--------------小米
    fun hasNotchAtXiaomi(context: Context): Boolean {
        var ret = false
        var result: Int
        try {
            val classLoader = context.classLoader
            val SystemProperties = classLoader.loadClass("android.os.SystemProperties")
            val paramTypes = arrayOfNulls<Class<*>>(2)
            paramTypes[0] = String::class.javaPrimitiveType
            paramTypes[1] = Int::class.javaPrimitiveType
            val getInt = SystemProperties.getMethod("getInt", *paramTypes)
            val params = arrayOfNulls<Any>(2)
            params[0] = "ro.miui.notch"
            params[1] = 0
            result = getInt.invoke(SystemProperties, params) as Int
            return 1 == result

        } catch (e: ClassNotFoundException) {
            Log.e("Notch", "hasNotchAtVivo ClassNotFoundException")
        } catch (e: NoSuchMethodException) {
            Log.e("Notch", "hasNotchAtVivo NoSuchMethodException")
        } catch (e: Exception) {
            Log.e("Notch", "hasNotchAtVivo Exception")
        } finally {
            return ret
        }

        return false
    }


    /**
     * 获取刘海屏幕的信息
     */
    fun getNotchParams(activity: Activity) {
        if(Build.VERSION.SDK_INT >= 28){
            val decorView2 = activity.window.decorView
            decorView2.post {
                val displayCutout = decorView2.rootWindowInsets.displayCutout
                Log.e("TAG", "安全区域距离屏幕左边的距离 SafeInsetLeft:" + displayCutout.safeInsetLeft)
                Log.e("TAG", "安全区域距离屏幕右部的距离 SafeInsetRight:" + displayCutout.safeInsetRight)
                Log.e("TAG", "安全区域距离屏幕顶部的距离 SafeInsetTop:" + displayCutout.safeInsetTop)
                Log.e("TAG", "安全区域距离屏幕底部的距离 SafeInsetBottom:" + displayCutout.safeInsetBottom)

                val rects = displayCutout.boundingRects
                if (rects == null || rects.size == 0) {
                    Log.e("TAG", "不是刘海屏")
                } else {
                    Log.e("TAG", "刘海屏数量:" + rects.size)
                    for (rect in rects) {
                        Log.e("TAG", "刘海屏区域：$rect")
                    }
                }
            }
        }
    }


}