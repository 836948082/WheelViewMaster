package com.runtai.wheelviewmaster.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.runtai.wheelviewmaster.R;
import com.runtai.wheelviewmaster.util.DateTimeUtils;
import com.runtai.wheelviewmasterlibrary.WheelView;

import java.util.ArrayList;

/**
 * TimePickerLayout
 */
public class TimePickerLayout extends LinearLayout {

    private WheelView time_day;
    private WheelView time_hour;
    private ArrayList<String> list_1 = new ArrayList<>();
    private ArrayList<String> list_2 = new ArrayList<>();
    private Context context;

    public TimePickerLayout(Context context) {
        this(context, null);
    }

    public TimePickerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        getAreaInfo();
    }

    private void getAreaInfo() {
        list_1 = DateTimeUtils.getDateAndWeek(7);
        list_2 = DateTimeUtils.removeBeforeNowTime(DateTimeUtils.getHourAndMinite());
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        LayoutInflater.from(getContext()).inflate(R.layout.layout_time_picker, this);
        time_day = (WheelView) findViewById(R.id.time_day);
        time_hour = (WheelView) findViewById(R.id.time_hour);

        time_day.setData(list_1);
        time_day.setDefault(0);
        time_hour.setData(list_2);
        time_hour.setDefault(0);

        time_day.setOnSelectListener(new WheelView.OnSelectListener() {
            @Override
            public void endSelect(int id, String text) {
                if (text.equals(""))
                    return;

                if (-1 != id) {
                    String selectProvince = time_day.getSelectedText();
                    if (selectProvince == null || selectProvince.equals(""))
                        return;

                    ArrayList<String> list_2;
                    if (id == 0) {
                        //当天
                        list_2 = DateTimeUtils.removeBeforeNowTime(DateTimeUtils.getHourAndMinite());
                    } else {
                        list_2 = DateTimeUtils.getHourAndMinite();
                    }
                    if (list_2.size() == 0) {
                        return;
                    }
                    time_hour.setData(list_2);
                    time_hour.setDefault(0);
//                    if (city.size() > 1) { //如果长度大于1，默认选中第2项；否则选中第一项
//                        //if city is more than one,show start index == 1
//                        time_hour.setDefault(1);
//                    } else {
//                        time_hour.setDefault(0);
//                    }
                    Log.e("滚动停止", "日期" + text + time_hour.getItemText(0));
                }

            }

            @Override
            public void selecting(int id, String text) {
                Log.e("持续滚动中", "id=" + id + ", text=" + text);
            }
        });

        time_hour.setOnSelectListener(new WheelView.OnSelectListener() {
            @Override
            public void endSelect(int id, String text) {
                if (text.equals(""))
                    return;
                if (-1 != id) {
                    String selectCity = time_hour.getSelectedText();
                    if (selectCity == null || selectCity.equals(""))
                        return;
                    int lastIndex = time_hour.getListSize();
                    if (id > lastIndex) {
                        time_hour.setDefault(lastIndex - 1);
                    }
                    Log.e("滚动停止", "日期" + time_day.getSelectedText() + text);
                }
            }

            @Override
            public void selecting(int id, String text) {
                Log.e("持续滚动中", "id=" + id + ", text=" + text);
            }
        });
    }

    public String getDay() {
        return time_day == null ? "" : time_day.getSelectedText();
    }

    public String getHour() {
        return time_hour == null ? "" : time_hour.getSelectedText();
    }

    /**
     * 设置显示的个数
     *
     * @param number
     */
    public void setWheelViewItemNumber(int number) {
        time_day.setItemNumber(number);
        time_hour.setItemNumber(number);
    }

    /**
     * 设置是否循环滚动
     *
     * @param isCyclic
     */
    public void setCyclic(boolean isCyclic) {
        time_day.setCyclic(isCyclic);
        time_hour.setCyclic(isCyclic);
    }

}
