package com.example.timepicker.config;

import com.example.timepicker.listener.OnDateSetListener;
import com.example.timepicker.listener.OnSexSetListener;
import com.example.timepicker.wheel.WheelCalendar;

/**
 * Created by jzxiang on 16/5/15.
 */
public class PickerConfig {

    public Type mType = DefaultConfig.TYPE;
    public int mItemBackColor = DefaultConfig.ITEM_BACK_COLOR;
    public int mItemLineColor = DefaultConfig.LINE_COLOR;

    public String mCancelString = DefaultConfig.CANCEL;
    public String mSureString = DefaultConfig.SURE;
    public String mCancelColor = DefaultConfig.CANCEL_COLOR;
    public String mSureColor = DefaultConfig.SURE_COLOR;
    public String mToolbarColor = DefaultConfig.TOOLBAR_COLOR;
    public String mllWheelBackColor = DefaultConfig.LL_WHEEL_BACKCOLOR;
    public String mTitleColor = DefaultConfig.TITLE_COLOR;

    public String mTitleString = DefaultConfig.TITLE;

    public String mCheckedSex = DefaultConfig.CHECKED_SEX;

    public int mWheelTVNormalColor = DefaultConfig.TV_NORMAL_COLOR;
    public int mWheelTVSelectorColor = DefaultConfig.TV_SELECTOR_COLOR;
    public int mWheelTVSize = DefaultConfig.TV_SIZE;
    public boolean cyclic = DefaultConfig.CYCLIC;

    public String mYear = DefaultConfig.YEAR;
    public String mMonth = DefaultConfig.MONTH;
    public String mDay = DefaultConfig.DAY;
    public String mHour = DefaultConfig.HOUR;
    public String mMinute = DefaultConfig.MINUTE;

    /**
     * The min timeMillseconds
     */
    public WheelCalendar mMinCalendar = new WheelCalendar(0);

    /**
     * The max timeMillseconds
     */
    public WheelCalendar mMaxCalendar = new WheelCalendar(0);

    /**
     * The default selector timeMillseconds
     */
    public WheelCalendar mCurrentCalendar = new WheelCalendar(System.currentTimeMillis());

    public OnDateSetListener mCallBack;
    public OnSexSetListener mSexCallBack;
}
