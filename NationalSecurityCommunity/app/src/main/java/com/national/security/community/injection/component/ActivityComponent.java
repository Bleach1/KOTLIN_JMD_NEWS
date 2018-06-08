package com.national.security.community.injection.component;

import com.national.security.community.injection.module.ActivityModule;
import com.national.security.community.injection.scope.ActivityScope;
import com.national.security.community.ui.main.MainActivity;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    RxAppCompatActivity getActivity();

    void inject(MainActivity mainActivity);

}
