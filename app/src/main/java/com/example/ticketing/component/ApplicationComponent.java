package com.example.ticketing.component;

import com.example.ticketing.activity.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

import com.example.ticketing.module.ApplicationModule;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(MainActivity mainActivity);
}
