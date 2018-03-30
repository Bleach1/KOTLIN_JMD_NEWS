package com.example.administrator.databinding.bean;

import android.databinding.ObservableField;
import android.databinding.ObservableInt;

/**
 * 无需继承 BaseObservable
 */
public class PlainUser {
    public final ObservableField<String> firstName = new ObservableField<>();
    public final ObservableField<String> lastName = new ObservableField<>();
    public final ObservableInt age = new ObservableInt();
}
