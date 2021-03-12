package com.example.ticketing.activity;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    public View findView(int id) {
        return findViewById(id);
    }
}
