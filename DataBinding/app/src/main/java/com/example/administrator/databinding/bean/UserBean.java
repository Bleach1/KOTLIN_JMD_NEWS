package com.example.administrator.databinding.bean;

import android.util.Log;

/**
 * @description:
 * @author: ljn
 * @time: 2018/3/28
 */
public class UserBean {

    public UserBean(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public final String firstName;
    public final String lastName;

    public static void print(String name) {
        Log.i("ljn", "print: ");
    }
}
