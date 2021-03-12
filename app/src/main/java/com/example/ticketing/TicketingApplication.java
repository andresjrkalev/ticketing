package com.example.ticketing;

import android.app.Application;

import com.example.ticketing.component.ApplicationComponent;
import com.example.ticketing.component.DaggerApplicationComponent;
import com.example.ticketing.module.ApplicationModule;

public class TicketingApplication extends Application {
    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();
    }

    public ApplicationComponent getComponent(){
        return component;
    }
}
