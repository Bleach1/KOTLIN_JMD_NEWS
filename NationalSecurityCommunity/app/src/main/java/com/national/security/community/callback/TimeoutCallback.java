package com.national.security.community.callback;
import com.kingja.loadsir.callback.Callback;
import com.national.security.community.R;

public class TimeoutCallback extends Callback{
    @Override
    protected int onCreateView() {
        return R.layout.loading;
    }
}
