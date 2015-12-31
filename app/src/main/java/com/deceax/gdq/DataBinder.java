package com.deceax.gdq;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DataBinder {

    private DataBinder(){}

    private static final SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a", Locale.getDefault());

    @BindingAdapter("date")
    public static void setDate(TextView textView, Date date) {
        textView.setText(sdf.format(date));
    }

    @BindingAdapter("category")
    public static void setCategory(TextView textView, String category) {
        if (category != null && category.length() > 0) {
            textView.setText(category);
        } else {
            textView.setVisibility(View.GONE);
        }
    }
}
