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
import com.example.timepicker.listener.OnSexSetListener;

public class SexPickerDialog extends DialogFragment implements View.OnClickListener {
    private PickerConfig mPickerConfig;
    private Context mContext;
    private Activity mActivity;
    private SexWheel mSexWheel;

    private static SexPickerDialog newInstance(PickerConfig pickerConfig) {
        SexPickerDialog sexPickerDialog = new SexPickerDialog();
        sexPickerDialog.initialize(pickerConfig);
        return sexPickerDialog;
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
        View view = inflater.inflate(R.layout.sexpick_layout, null);
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
        mSexWheel = new SexWheel(mContext, view, mPickerConfig);
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

    private void sureClicked() {
        String text = mSexWheel.getCurrentItem();
        if (mPickerConfig.mSexCallBack != null) {
            mPickerConfig.mSexCallBack.onSexSet(this, text);
        }
        dismiss();
    }

    public static class Builder {
        PickerConfig mPickerConfig;

        public Builder() {
            mPickerConfig = new PickerConfig();
        }

        public SexPickerDialog.Builder setType(Type type) {
            mPickerConfig.mType = type;
            return this;
        }

        public SexPickerDialog.Builder setCancelStringId(String left) {
            mPickerConfig.mCancelString = left;
            return this;
        }

        public SexPickerDialog.Builder setCancelColor(String color) {
            mPickerConfig.mCancelColor = color;
            return this;
        }

        public SexPickerDialog.Builder setTitleColor(String color) {
            mPickerConfig.mTitleColor = color;
            return this;
        }

        public SexPickerDialog.Builder setCheckedSex(String sex) {
            mPickerConfig.mCheckedSex = sex;
            return this;
        }

        public SexPickerDialog.Builder setSureColor(String color) {
            mPickerConfig.mSureColor = color;
            return this;
        }

        public SexPickerDialog.Builder setSureStringId(String right) {
            mPickerConfig.mSureString = right;
            return this;
        }

        public SexPickerDialog.Builder setTitleStringId(String title) {
            mPickerConfig.mTitleString = title;
            return this;
        }

        public SexPickerDialog.Builder setWheelItemTextNormalColor(int color) {
            mPickerConfig.mWheelTVNormalColor = color;
            return this;
        }

        public SexPickerDialog.Builder setWheelItemTextSelectorColor(int color) {
            mPickerConfig.mWheelTVSelectorColor = color;
            return this;
        }

        public SexPickerDialog.Builder setWheelSelectItemBackColor(int color) {
            mPickerConfig.mItemBackColor = color;
            return this;
        }

        public SexPickerDialog.Builder setWheelSelectItemLineColor(int color) {
            mPickerConfig.mItemLineColor = color;
            return this;
        }

        public SexPickerDialog.Builder setWheelItemTextSize(int size) {
            mPickerConfig.mWheelTVSize = size;
            return this;
        }

        public SexPickerDialog.Builder setCyclic(boolean cyclic) {
            mPickerConfig.cyclic = cyclic;
            return this;
        }

        public SexPickerDialog.Builder setCallBack(OnSexSetListener listener) {
            mPickerConfig.mSexCallBack = listener;
            return this;
        }

        public SexPickerDialog build() {
            return newInstance(mPickerConfig);
        }

    }


}
