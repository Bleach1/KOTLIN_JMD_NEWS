package com.national.security.community.helper;

import com.national.security.community.App;

import io.realm.Realm;

/**
 @ description:  Realm数据库帮助类
 @ author:  ljn
 @ time:  2018/1/2 
 */
public class RealmHelper {
    private static final String DB_NAME = "myRealm.realm";

    private Realm mRealm;

    public RealmHelper(App application) {
    }
}
