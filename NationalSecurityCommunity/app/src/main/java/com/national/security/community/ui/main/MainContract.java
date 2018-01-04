package com.national.security.community.ui.main;

import com.national.security.community.mvp.CommonView;
import com.national.security.community.mvp.IPresenter;

public class MainContract {

    public interface View extends CommonView {
        void granted();

        void denied_never_ask_again();

        void denied();
    }

    interface Presenter extends IPresenter<View> {
        void loadData(String url);

        void requestPermission();

        void loadHome();
    }
}
