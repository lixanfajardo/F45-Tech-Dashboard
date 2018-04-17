package com.android.f45tv.f45techdashboard.Controller;

import android.content.Context;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.f45tv.f45techdashboard.Interfaces.TimerInterface;
import com.android.f45tv.f45techdashboard.R;

import org.w3c.dom.Text;

public class TimerController extends LinearLayout implements TimerInterface {

    Context context;
    TextView minutesText;
    LinearLayout timerFragment;
    CountDownTimer countDownTimer;
    String remainTime;

    public TimerController(Context context) {
        super(context);
        this.context = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.timerview, this);
        this.context = context;

        initComponents();
    }

    private void initComponents()
    {
        timerFragment = (LinearLayout) findViewById(R.id.timerFragment);
    }

    @Override
    public void setMinuteText(String text) {
        minutesText = (TextView) findViewById(R.id.minutesText);
        minutesText.setText(text);
    }



    @Override
    public View getLayout() {
        return this;
    }

    @Override
    public void setTimer(long timeInMillis, long interval) {

        timeInMillis = timeInMillis + 1000;

        countDownTimer = new CountDownTimer(timeInMillis, interval) {
            @Override
            public void onTick(long l) {

                remainTime = String.format("%02d : %02d", java.util.concurrent.TimeUnit.MILLISECONDS.toMinutes(l),
                        java.util.concurrent.TimeUnit.MILLISECONDS.toSeconds(l) - java.util.concurrent.TimeUnit.MINUTES.toSeconds(java.util.concurrent.TimeUnit.MILLISECONDS.toMinutes(l)));
                setMinuteText(remainTime);

            }


            @Override
            public void onFinish() {
                new CountDownTimer(6000, 1000) {
                    @Override
                    public void onTick(long l) {
                        //showAlert();
                    }

                    @Override
                    public void onFinish() {
                        //hideAlert();
                        countDownTimer.start();
                    }
                }.start();
            }
        }.start();


    }






}