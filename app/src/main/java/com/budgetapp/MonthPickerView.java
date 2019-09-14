package com.budgetapp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MonthPickerView extends LinearLayout {
    private Calendar calendar;
    private OnMonthChangeListener listener;
    private ImageView previousMonthCaret;
    private ImageView nextMonthCaret;
    private TextView selectedDateLabel;

    public MonthPickerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

        calendar = Calendar.getInstance();

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        inflater.inflate(R.layout.view_monthpicker, this);

        previousMonthCaret = findViewById(R.id.calendar_prev_button);
        nextMonthCaret = findViewById(R.id.calendar_next_button);
        selectedDateLabel = findViewById(R.id.date_display_date);

        updateCalendar(calendar.getTime());

        previousMonthCaret.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.MONTH, -1);
                updateCalendar(calendar.getTime());
                listener.onSelectedMonthChange(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH));
            }
        });

        nextMonthCaret.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.MONTH, 1);
                updateCalendar(calendar.getTime());
                listener.onSelectedMonthChange(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH));
            }
        });

    }

    public void setOnMonthChangedListener(OnMonthChangeListener listener) {
        this.listener = listener;
    }

    public void updateCalendar(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM yyyy");
        selectedDateLabel.setText(simpleDateFormat.format(date.getTime()));
    }

    public interface OnMonthChangeListener {
        void onSelectedMonthChange(int year, int month);
    }
}
