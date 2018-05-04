package com.national.security.community.base;

import com.national.security.community.data.db.User;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class BaseRealmBean extends RealmObject {
    @PrimaryKey
    private int id;
    private RealmList<User> tRealmList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RealmList<User> gettRealmList() {
        return tRealmList;
    }

    public void settRealmList(RealmList<User> tRealmList) {
        this.tRealmList = tRealmList;
    }
}
