package com.national.security.community.injection.component;

import com.national.security.community.injection.module.FragmentModule;
import com.national.security.community.injection.scope.FragmentScope;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    RxAppCompatActivity getActivity();

    // void inject(DailyFragment dailyFragment);

}
