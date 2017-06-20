package com.jmd_news_kotlin.di.module

import android.app.Activity
import android.support.v4.app.Fragment
import com.jmd_news_kotlin.di.scope.FragmentScope
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module
class FragmentModule @Inject constructor(fragment: Fragment) {

    private var mFragment: Fragment = fragment
    @Provides
    @FragmentScope
    fun providerActivity(): Activity {
        return mFragment.activity
    }
}