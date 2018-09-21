package com.national.security.community.ui;

import android.content.pm.ActivityInfo;
import android.view.View;
import android.widget.ImageView;

import com.national.security.community.R;
import com.national.security.community.base.BaseActivity;
import com.national.security.community.utils.network.NetWorkUtil;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import butterknife.BindView;

/**
 * @description: 播放视频
 * @author: ljn
 * @time: 2018/8/9
 */
public class VideoPlayerActivity extends BaseActivity {

    @BindView(R.id.video_player)
    StandardGSYVideoPlayer player;
    private static final String SOURCE = "http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4";
    private OrientationUtils orientationUtils;

    @Override
    protected void onNetworkConnected(NetWorkUtil.NetType type) {

    }

    @Override
    protected void onNetworkDisConnected() {

    }

    @Override
    protected void initInject() {

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_video_player;
    }

    @Override
    protected void initEventAndData() {
        player.setUp(SOURCE, true, "First Blood");
        //封面
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(R.drawable.some);
        player.setThumbImageView(imageView);
        //增加title
        player.getTitleTextView().setVisibility(View.VISIBLE);
        //设置返回键
        player.getBackButton().setVisibility(View.VISIBLE);

        player.setSpeed(2.0f);
        //设置旋转
        orientationUtils = new OrientationUtils(this, player);
        player.getFullscreenButton().setOnClickListener((v) -> player.startWindowFullscreen(this, true, true));
//设置全屏按键功能,这是使用的是选择屏幕，而不是全屏
        // player.getFullscreenButton().setOnClickListener(v -> orientationUtils.resolveByClick());
        //是否可以滑动调整
        player.setIsTouchWiget(true);
        //设置返回按键功能
        player.getBackButton().setOnClickListener(v -> onBackPressed());
        player.startPlayLogic();
    }

    @Override
    protected void onResume() {
        super.onResume();
        player.onVideoResume(true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        player.onVideoPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GSYVideoManager.releaseAllVideos();
        if (orientationUtils != null) {
            orientationUtils.releaseListener();
        }
    }

    @Override
    public void showMsg(String msg) {

    }


    @Override
    public void onBackPressed() {
        //先返回正常状态
        if (orientationUtils.getScreenType() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            player.getFullscreenButton().performClick();
            return;
        }
        //释放所有
        player.setVideoAllCallBack(null);
        super.onBackPressed();
    }
}
