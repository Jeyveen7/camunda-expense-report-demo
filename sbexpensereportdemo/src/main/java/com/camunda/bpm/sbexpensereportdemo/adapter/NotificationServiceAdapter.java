package com.camunda.bpm.sbexpensereportdemo.adapter;

public interface NotificationServiceAdapter<T> {

    void send(T notification);
}
