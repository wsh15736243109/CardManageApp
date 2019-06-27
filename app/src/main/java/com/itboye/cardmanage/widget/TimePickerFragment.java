package com.itboye.cardmanage.widget;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.TimePicker;

import java.lang.reflect.Field;

import static com.itboye.cardmanage.retrofit.AppUtils.getSDKVersionNumber;

public class TimePickerFragment extends DatePickerDialog
        implements TimePickerDialog.OnTimeSetListener {

    public TimePickerFragment(@NonNull Context context, @Nullable OnDateSetListener listener, int year, int month, int dayOfMonth) {
        super(context, AlertDialog.THEME_HOLO_LIGHT, listener, year, month, dayOfMonth);
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {

    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        int SDKVersion = getSDKVersionNumber();// 获取系统版本
        DatePicker dp = findDatePicker((ViewGroup) getWindow().getDecorView());// 设置弹出年月日
        if (dp != null) {
            if (SDKVersion < 11) {
                ((ViewGroup) dp.getChildAt(0)).getChildAt(1).setVisibility(
                        View.GONE);

            } else if (SDKVersion > 14) {
                //只显示年月
                ((ViewGroup) ((ViewGroup) dp.getChildAt(0)).getChildAt(0))
                        .getChildAt(1).setVisibility(View.GONE);//.getChildAt(0)
                //只显示年日
                //                ((ViewGroup) ((ViewGroup) dp.getChildAt(0)).getChildAt(0))
                //                .getChildAt(2).setVisibility(View.GONE);
                //只显示年月
                //                ((ViewGroup) ((ViewGroup) dp.getChildAt(0)).getChildAt(0))
                //                .getChildAt(1).setVisibility(View.GONE);
                //显示月日
                //                ((ViewGroup) ((ViewGroup) dp.getChildAt(0)).getChildAt(0))
                //                .getChildAt(0).setVisibility(View.GONE);

            }

        }

    }

    /**
     * 75      * 从当前Dialog中查找DatePicker子控件
     * 76      *
     * 77      * @param group
     * 78      * @return
     * 79
     */
    private DatePicker findDatePicker(ViewGroup group) {
        if (group != null) {
            for (int i = 0, j = group.getChildCount(); i < j; i++) {
                View child = group.getChildAt(i);
                if (child instanceof DatePicker) {
                    return (DatePicker) child;
                } else if (child instanceof ViewGroup) {
                    DatePicker result = findDatePicker((ViewGroup) child);
                    if (result != null)
                        return result;
                }
            }
        }
        return null;
    }
}
