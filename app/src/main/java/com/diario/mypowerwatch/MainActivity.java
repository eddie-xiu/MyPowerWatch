package com.diario.mypowerwatch;

import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.BoxInsetLayout;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends WearableActivity {

    private static final SimpleDateFormat AMBIENT_DATE_FORMAT =
            new SimpleDateFormat("HH:mm", Locale.US);

    private RelativeLayout mContainerView;
    private TextView mTextView;
    private TextView mClockView;
    private Button mButton1;
    private Button mButton2;
    private Button mButton3;
    private Button mButton4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setAmbientEnabled();

        mContainerView = (RelativeLayout) findViewById(R.id.container);
        mTextView = (TextView) findViewById(R.id.text);
        mClockView = (TextView) findViewById(R.id.clock);
        mButton1 = (Button) findViewById(R.id.button1);
        mButton2 = (Button) findViewById(R.id.button2);
        mButton3 = (Button) findViewById(R.id.button3);
        mButton4 = (Button) findViewById(R.id.button4);
    }

    public void recordData(View view) {
        //mButtonGood.setVisibility(View.GONE);
        //mTextView.setText("@string/Question2_TeacherFeeling");
        if(mTextView.getText().equals(getText(R.string.Welcome_HowAreYou))) {
            mTextView.setText(getApplicationContext().getText(R.string.Question2_TeacherFeeling));
        }
        else if (mTextView.getText().equals(getText(R.string.Question2_TeacherFeeling))) {
            mTextView.setText(getApplicationContext().getText(R.string.Question3_BodyFeeling));
            mButton1.setText("Tired");
            mButton2.setText("Awake");
            mButton3.setText("ToRun");
            mButton4.setText("ToFlip");
        }
        else if (mTextView.getText().equals(getText(R.string.Question3_BodyFeeling))) {
            mTextView.setText(getApplicationContext().getText(R.string.Question4_StressFeeling));
            mButton1.setText("relaxed");
            mButton2.setText("Tense");
            mButton3.setText("Stressed");
            mButton4.setText("strsOut");
        }

    }

    @Override
    public void onEnterAmbient(Bundle ambientDetails) {
        super.onEnterAmbient(ambientDetails);
        updateDisplay();
    }

    @Override
    public void onUpdateAmbient() {
        super.onUpdateAmbient();
        updateDisplay();
    }

    @Override
    public void onExitAmbient() {
        updateDisplay();
        super.onExitAmbient();
    }

    private void updateDisplay() {
        if (isAmbient()) {
            mContainerView.setBackgroundColor(getResources().getColor(android.R.color.black));
            mTextView.setTextColor(getResources().getColor(android.R.color.white));
            mClockView.setVisibility(View.VISIBLE);

            mClockView.setText(AMBIENT_DATE_FORMAT.format(new Date()));
        } else {
            mContainerView.setBackground(null);
            mTextView.setTextColor(getResources().getColor(android.R.color.black));
            mClockView.setVisibility(View.GONE);
        }
    }
}
