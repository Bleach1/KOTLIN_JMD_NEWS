package com.national.security.community.injection.module;

import android.support.v4.app.Fragment;

import com.national.security.community.injection.scope.FragmentScope;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import dagger.Module;
import dagger.Provides;


@Module
public class FragmentModule {

    private Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @FragmentScope
    RxAppCompatActivity provideActivity() {
        return (RxAppCompatActivity) fragment.getActivity();
    }
}
