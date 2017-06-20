package com.jmd_news_kotlin.utils

import io.realm.Realm
import io.realm.Realm.getInstance
import io.realm.RealmConfiguration

object RealmHelper {

    private val mRealm: Realm = getInstance(RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .name(Constants.DB_NAME)
            .build())

}