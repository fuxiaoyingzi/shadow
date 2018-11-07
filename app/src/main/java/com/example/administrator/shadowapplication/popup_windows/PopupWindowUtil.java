package com.example.administrator.shadowapplication.popup_windows;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

/**
 * 封装运动类型切换控件
 * <p>
 * Created by XiaoFeng on 2016/12/26.
 */
public class PopupWindowUtil {
    private Context context;
    private PopupWindow popupWindow;


    public PopupWindowUtil(Context context, View view) {
        initView(context, view);
    }

    private void initView(final Context context, View view) {
        this.context = context;
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
    }

    public void setOutsideTouchable(){
        popupWindow.setTouchable(true);
       /* popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });*/
    }

    public void dismissPopup() {
        if (popupWindow != null) {
            popupWindow.dismiss();
            // 关闭后回复背景
            popupWindow.setOnDismissListener(() -> {
                if (context instanceof Activity) {
                    WindowManager.LayoutParams lp = ((Activity) context).getWindow().getAttributes();
                    lp.alpha = 1f;
                    ((Activity) context).getWindow().setAttributes(lp);
                }
            });
        }

    }

    public void showAsDropDown(View v) {
        if (popupWindow != null) {
            // 使整个背景变灰
            if (context instanceof Activity) {
                WindowManager.LayoutParams lp = ((Activity) context).getWindow().getAttributes();
                lp.alpha = 0.5f;
                ((Activity) context).getWindow().setAttributes(lp);
            }

            popupWindow.showAsDropDown(v, 0, 8);
        }
    }

    public void showAsBottom(View v) {
        if (popupWindow != null) {
            // 使整个背景变灰
          /*  if (context instanceof Activity) {
                WindowManager.LayoutParams lp = ((Activity) context).getWindow().getAttributes();
                lp.alpha = 0.5f;
                ((Activity) context).getWindow().setAttributes(lp);
            }*/
            popupWindow.showAtLocation(v, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        }
    }

    public void showAsTop(View v) {
        if (popupWindow != null) {
            popupWindow.showAtLocation(v, Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
        }
    }
    public void dismissTopPopup() {
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
    }
}
