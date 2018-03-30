package com.example.administrator.databinding.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.administrator.databinding.BR;

/**
 * 双向绑定
 */
public class ObservableUser extends BaseObservable {
    private String firstName;
    private String lastName;

    @Bindable
    public String getFirstName() {
        return firstName;
    }

    @Bindable
    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        notifyPropertyChanged(BR.firstName);
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        notifyPropertyChanged(BR.lastName);
    }
}
