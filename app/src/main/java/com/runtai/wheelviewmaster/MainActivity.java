package com.runtai.wheelviewmaster;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.runtai.wheelviewmaster.util.TimeUtil;
import com.runtai.wheelviewmaster.view.CityPickerLayout;
import com.runtai.wheelviewmaster.view.FromToTimePicker;
import com.runtai.wheelviewmaster.view.TimePickerLayout;

public class MainActivity extends AppCompatActivity {

    FromToTimePicker fromToTimePicker;
    CityPickerLayout cityPicker;
    TimePickerLayout timePicker;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    public void initView(){
        textView = (TextView) findViewById(R.id.result);

        fromToTimePicker = (FromToTimePicker) findViewById(R.id.fromToTimePicker);
        fromToTimePicker.setOnResultListener(new FromToTimePicker.OnResultListener() {
            @Override
            public void onConfirm(int fromHour, int fromMinute, int toHour, int toMinute) {
                textView.setText("From " + fromHour + ":" + fromMinute + " To " + toHour + ":" + toMinute);
            }

            @Override
            public void onCancel() {
                textView.setText("Canceled");
            }
        });
        fromToTimePicker.setCurrentDate(TimeUtil.getTimeString(), TimeUtil.addTime(TimeUtil.getTimeString(), "8:00").getTime());

        cityPicker = (CityPickerLayout) findViewById(R.id.cityPicker);
        cityPicker.setWheelViewItemNumber(5);
        cityPicker.setCyclic(false);

        timePicker = (TimePickerLayout) findViewById(R.id.timePicker);
        timePicker.setWheelViewItemNumber(5);
        timePicker.setCyclic(false);
    }

}
