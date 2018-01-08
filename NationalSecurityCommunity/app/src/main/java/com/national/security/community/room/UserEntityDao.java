
package com.national.security.community.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface UserEntityDao {

    @Query("select * FROM User")
    Flowable<List<UserEntity>> getUserList();

    @Query("select * FROM User WHERE name = :name")
    Flowable<UserEntity> getUserByName(String name);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addUser(UserEntity userEntity);

    @Delete()
    void deleteUser(UserEntity userEntity);

}

