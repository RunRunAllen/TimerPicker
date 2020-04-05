package com.example.timepicker.listener;


import com.example.timepicker.ui.TimePickerDialog;

/**
 * Created by jzxiang on 16/4/20.
 */
public interface OnDateSetListener {

    void onDateSet(TimePickerDialog timePickerView, long millseconds);
}
