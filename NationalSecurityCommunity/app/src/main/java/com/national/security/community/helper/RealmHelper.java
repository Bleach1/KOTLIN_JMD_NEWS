package com.national.security.community.helper;

import com.national.security.community.App;

import io.realm.Realm;
import io.realm.RealmObject;

/**
 * @ description:  Realm数据库帮助类 https://www.jianshu.com/p/28912c2f31db
 * @ author:  ljn
 * @ time:  2018/1/2
 */
public class RealmHelper {


    private Realm mRealm;

    public RealmHelper(App application) {
        mRealm = Realm.getDefaultInstance();
    }

    public boolean insertOrUpdate(RealmObject object) {
        try {
            mRealm.beginTransaction();
            mRealm.insertOrUpdate(object);
            mRealm.commitTransaction();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            mRealm.cancelTransaction();
            return false;
        }
    }


    public boolean insert(RealmObject list) {
        try {
            mRealm.beginTransaction();
            mRealm.insert(list);
            mRealm.commitTransaction();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            mRealm.cancelTransaction();
            return false;
        }
    }


}
