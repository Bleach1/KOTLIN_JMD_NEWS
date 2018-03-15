package com.national.security.community.injection.module;

import android.app.DialogFragment;
import android.support.v4.app.Fragment;

import com.national.security.community.injection.scope.FragmentScope;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import dagger.Module;
import dagger.Provides;


@Module
public class FragmentModule {

    private Fragment fragment;
    private DialogFragment dialogFragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    public FragmentModule(DialogFragment fragment) {
        this.fragment = null;
        this.dialogFragment = fragment;
    }

    @Provides
    @FragmentScope
    RxAppCompatActivity provideActivity() {

        if (fragment == null) {
            return (RxAppCompatActivity) dialogFragment.getActivity();
        } else {
            return (RxAppCompatActivity) fragment.getActivity();
        }
    }
}
