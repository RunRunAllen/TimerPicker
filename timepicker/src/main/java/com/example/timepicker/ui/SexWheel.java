package com.example.timepicker.ui;

import android.content.Context;
import android.view.View;

import com.example.timepicker.R;
import com.example.timepicker.adapter.ArrayWheelAdapter;
import com.example.timepicker.config.DefaultConfig;
import com.example.timepicker.config.PickerConfig;
import com.example.timepicker.wheel.OnWheelChangedListener;
import com.example.timepicker.wheel.WheelView;

public class SexWheel {

    private WheelView sex;
    private Context mContext;
    private ArrayWheelAdapter customWheelSexAdapter;
    private final String[] sexArray;


    public SexWheel(Context context, View view, PickerConfig mPickerConfig) {
        mContext = context;
        sexArray = mContext.getResources().getStringArray(R.array.sex);
        initialize(view, mPickerConfig);
    }

    private void initialize(View view, PickerConfig mPickerConfig) {
        sex = (WheelView) view.findViewById(R.id.sex);
        sex.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {


            }
        });
        customWheelSexAdapter = new ArrayWheelAdapter(mContext, sexArray);
        sex.setViewAdapter(customWheelSexAdapter);
        if (mPickerConfig.mCheckedSex!=null&&mPickerConfig.mCheckedSex.equals(DefaultConfig.CHECKED_SEX)) {
            sex.setCurrentItem(1);
        } else {
            sex.setCurrentItem(0);
        }
    }

    public String getCurrentItem() {
        int currentItem = sex.getCurrentItem();
        return customWheelSexAdapter.getItemText(currentItem).toString();

    }
}
