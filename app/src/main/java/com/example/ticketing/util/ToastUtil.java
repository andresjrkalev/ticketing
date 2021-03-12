package com.example.ticketing.util;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
    public static void show(Context context, CharSequence message, int duration) {
        Toast.makeText(context, message, duration).show();
    }
}
