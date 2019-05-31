package com.itboye.cardmanage.widget;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.TimePicker;

public class TimePickerFragment extends DatePickerDialog
        implements TimePickerDialog.OnTimeSetListener {

    public TimePickerFragment(@NonNull Context context, @Nullable OnDateSetListener listener, int year, int month, int dayOfMonth) {
        super(context, AlertDialog.THEME_HOLO_LIGHT, listener, year, month, dayOfMonth);
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {

    }
}
