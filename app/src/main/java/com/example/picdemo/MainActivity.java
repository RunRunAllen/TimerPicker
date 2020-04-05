package com.example.picdemo;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.timepicker.config.Type;
import com.example.timepicker.listener.OnDateSetListener;
import com.example.timepicker.listener.OnSexSetListener;
import com.example.timepicker.ui.SexPickerDialog;
import com.example.timepicker.ui.TimePickerDialog;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, OnDateSetListener, OnSexSetListener {

    private TextView title;
    private TimePickerDialog mDialogYearMonth;

    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private TextView sex;
    private SexPickerDialog mSex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = findViewById(R.id.tv_title);
        sex = findViewById(R.id.tv_sex);
        title.setOnClickListener(this);
        sex.setOnClickListener(this);

        mDialogYearMonth = new TimePickerDialog.Builder()
                .setType(Type.YEAR_MONTH)
                .setYearText("")
                .setMaxMillseconds(System.currentTimeMillis())
                .setTitleStringId(getString(R.string.app_name))
                .setCallBack(this)
                .build();
        mSex = new SexPickerDialog.Builder()
                .setType(Type.SEX)
                .setTitleStringId(getString(R.string.app_name))
                .setCallBack(this)
                .build();

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_title) {
            mDialogYearMonth.show(getSupportFragmentManager(), "haha");

        } else {
            mSex.show(getSupportFragmentManager(), "lala");
        }

    }

    public String getDateToString(long time) {
        Date d = new Date(time);
        return sf.format(d);
    }

    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
        String text = getDateToString(millseconds);
        title.setText(text);
    }

    @Override
    public void onSexSet(SexPickerDialog sexPickerDialog, String text) {
        sex.setText(text);
    }
}
