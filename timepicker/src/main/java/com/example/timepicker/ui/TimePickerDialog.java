package com.example.timepicker.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.timepicker.R;
import com.example.timepicker.config.PickerConfig;
import com.example.timepicker.config.Type;
import com.example.timepicker.listener.OnDateSetListener;
import com.example.timepicker.wheel.WheelCalendar;

import java.util.Calendar;

/**
 * Created by jzxiang on 16/4/19.
 */
public class TimePickerDialog extends DialogFragment implements View.OnClickListener {
    private PickerConfig mPickerConfig;
    private TimeWheel mTimeWheel;
    private long mCurrentMillSeconds;
    private Context mContext;
    private Activity mActivity;

    private static TimePickerDialog newInstance(PickerConfig pickerConfig) {
        TimePickerDialog timePickerDialog = new TimePickerDialog();
        timePickerDialog.initialize(pickerConfig);
        return timePickerDialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
        if (mActivity != null) {
            mContext = mActivity.getApplicationContext();
            mActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        int height = getResources().getDimensionPixelSize(R.dimen.picker_height);
        Dialog dialog = getDialog();
        if (dialog != null) {
            Window window = dialog.getWindow();
            if (window != null) {
                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, height);//Here!
                window.setGravity(Gravity.BOTTOM);
            }
        }
    }

    private void initialize(PickerConfig pickerConfig) {
        mPickerConfig = pickerConfig;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(mActivity, R.style.Dialog_NoTitle);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(initView());
        return dialog;
    }

    private View initView() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.timepicker_layout, null);
        TextView cancel = (TextView) view.findViewById(R.id.tv_cancel);
        View llWheel = view.findViewById(R.id.linear_wheel);
        cancel.setOnClickListener(this);
        TextView sure = (TextView) view.findViewById(R.id.tv_sure);
        sure.setOnClickListener(this);
        TextView title = (TextView) view.findViewById(R.id.tv_title);
        View toolbar = view.findViewById(R.id.toolbar);
        title.setText(mPickerConfig.mTitleString);
        title.setTextColor(Color.parseColor(mPickerConfig.mTitleColor));
        cancel.setText(mPickerConfig.mCancelString);
        cancel.setTextColor(Color.parseColor(mPickerConfig.mCancelColor));
        sure.setText(mPickerConfig.mSureString);
        sure.setTextColor(Color.parseColor(mPickerConfig.mSureColor));
        toolbar.setBackgroundColor(Color.parseColor(mPickerConfig.mToolbarColor));
        llWheel.setBackgroundColor(Color.parseColor(mPickerConfig.mllWheelBackColor));
        mTimeWheel = new TimeWheel(mContext, view, mPickerConfig);
        return view;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv_cancel) {
            dismiss();
        } else if (i == R.id.tv_sure) {
            sureClicked();
        }
    }

    /*
     * @desc This method returns the current milliseconds. If current milliseconds is not set,
     *       this will return the system milliseconds.
     * @param none
     * @return long - the current milliseconds.
     */
    public long getCurrentMillSeconds() {
        if (mCurrentMillSeconds == 0)
            return System.currentTimeMillis();

        return mCurrentMillSeconds;
    }

    /*
     * @desc This method is called when onClick method is invoked by sure button. A Calendar instance is created and
     *       initialized.
     * @param none
     * @return none
     */
   private void sureClicked() {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();

        calendar.set(Calendar.YEAR, mTimeWheel.getCurrentYear());
        calendar.set(Calendar.MONTH, mTimeWheel.getCurrentMonth() - 1);
        calendar.set(Calendar.DAY_OF_MONTH, mTimeWheel.getCurrentDay());
        calendar.set(Calendar.HOUR_OF_DAY, mTimeWheel.getCurrentHour());
        calendar.set(Calendar.MINUTE, mTimeWheel.getCurrentMinute());

        mCurrentMillSeconds = calendar.getTimeInMillis();
        if (mPickerConfig.mCallBack != null) {
            mPickerConfig.mCallBack.onDateSet(this, mCurrentMillSeconds);
        }
        dismiss();
    }

    public static class Builder {
        PickerConfig mPickerConfig;

        public Builder() {
            mPickerConfig = new PickerConfig();
        }

        public Builder setType(Type type) {
            mPickerConfig.mType = type;
            return this;
        }

        public Builder setCancelStringId(String left) {
            mPickerConfig.mCancelString = left;
            return this;
        }

        public Builder setCancelColor(String color) {
            mPickerConfig.mCancelColor = color;
            return this;
        }

        public Builder setTitleColor(String color) {
            mPickerConfig.mTitleColor = color;
            return this;
        }

        public Builder setSureColor(String color) {
            mPickerConfig.mSureColor = color;
            return this;
        }

        public Builder setSureStringId(String right) {
            mPickerConfig.mSureString = right;
            return this;
        }

        public Builder setTitleStringId(String title) {
            mPickerConfig.mTitleString = title;
            return this;
        }

        public Builder setWheelItemTextNormalColor(int color) {
            mPickerConfig.mWheelTVNormalColor = color;
            return this;
        }

        public Builder setWheelItemTextSelectorColor(int color) {
            mPickerConfig.mWheelTVSelectorColor = color;
            return this;
        }

        public Builder setWheelSelectItemBackColor(int color) {
            mPickerConfig.mItemBackColor = color;
            return this;
        }

        public Builder setWheelSelectItemLineColor(int color) {
            mPickerConfig.mItemLineColor = color;
            return this;
        }

        public Builder setWheelItemTextSize(int size) {
            mPickerConfig.mWheelTVSize = size;
            return this;
        }

        public Builder setCyclic(boolean cyclic) {
            mPickerConfig.cyclic = cyclic;
            return this;
        }

        public Builder setMinMillseconds(long millseconds) {
            mPickerConfig.mMinCalendar = new WheelCalendar(millseconds);
            return this;
        }

        public Builder setMaxMillseconds(long millseconds) {
            mPickerConfig.mMaxCalendar = new WheelCalendar(millseconds);
            return this;
        }

        public Builder setCurrentMillseconds(long millseconds) {
            mPickerConfig.mCurrentCalendar = new WheelCalendar(millseconds);
            return this;
        }

        public Builder setYearText(String year) {
            mPickerConfig.mYear = year;
            return this;
        }

        public Builder setMonthText(String month) {
            mPickerConfig.mMonth = month;
            return this;
        }

        public Builder setDayText(String day) {
            mPickerConfig.mDay = day;
            return this;
        }


        public Builder setHourText(String hour) {
            mPickerConfig.mHour = hour;
            return this;
        }

        public Builder setMinuteText(String minute) {
            mPickerConfig.mMinute = minute;
            return this;
        }

        public Builder setCallBack(OnDateSetListener listener) {
            mPickerConfig.mCallBack = listener;
            return this;
        }

        public TimePickerDialog build() {
            return newInstance(mPickerConfig);
        }

    }


}
