package com.national.security.community.base;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class BaseRealmBean<T> extends RealmObject {
    @PrimaryKey
    private int id;
    private RealmList<T> tRealmList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RealmList<T> gettRealmList() {
        return tRealmList;
    }

    public void settRealmList(RealmList<T> tRealmList) {
        this.tRealmList = tRealmList;
    }
}
