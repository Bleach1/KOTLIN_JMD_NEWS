package com.national.security.community.ui;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ConsoleMessage;
import android.webkit.GeolocationPermissions;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.PermissionRequest;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.national.security.community.BuildConfig;

import java.lang.reflect.Field;

public class CommonWebviewActivity extends AppCompatActivity {

    private WebView webView;
    private static final String url = "";
    private WebSettings settings;

    private ValueCallback<Uri> uploadMessage;
    private ValueCallback<Uri[]> uploadMessageAboveL;
    private final static int FILE_CHOOSER_RESULT_CODE = 10000;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        webView = new WebView(this);
        setContentView(webView);
        initWebViewProps();
        initWebViewSetting();
        initWebViewClient();
        initWebChromeClient();
        JSInteractiveWebView();
        longClickSaveImg();
    }

    private void longClickSaveImg() {

        webView.setOnLongClickListener(v -> {
            WebView.HitTestResult result = ((WebView) v).getHitTestResult();
            if (null == result)
                return false;
            int type = result.getType();
            if (type == WebView.HitTestResult.UNKNOWN_TYPE)
                return false;

            // 这里可以拦截很多类型，我们只处理图片类型就可以了
            switch (type) {
                case WebView.HitTestResult.PHONE_TYPE: // 处理拨号
                    break;
                case WebView.HitTestResult.EMAIL_TYPE: // 处理Email
                    break;
                case WebView.HitTestResult.GEO_TYPE: // 地图类型
                    break;
                case WebView.HitTestResult.SRC_ANCHOR_TYPE: // 超链接
                    break;
                case WebView.HitTestResult.SRC_IMAGE_ANCHOR_TYPE:
                    break;
                case WebView.HitTestResult.IMAGE_TYPE: // 处理长按图片的菜单项
                    // 获取图片的路径
                    String saveImgUrl = result.getExtra();

                    // 跳转到图片详情页，显示图片
                    /*Intent i = new Intent(this, ImageActivity.class);
                    i.putExtra("imgUrl", saveImgUrl);
                    startActivity(i);*/
                    break;
                default:
                    break;
            }
            return true;
        });


    }

    /**
     * js 与 WebView 交互
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void JSInteractiveWebView() {

        //methodName:js方法  parameterValues:参数
        //1. WebView调用JavaScript无参无返回值函数
        webView.loadUrl("javascript:methodName()");
        //2.WebView调用JavScript有参无返回值函数
        webView.loadUrl("javascript:methodName(parameterValues)");
        //3.WebView调用JavaScript有参数有返回值的函数
        webView.evaluateJavascript("methodName()", value -> {

        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FILE_CHOOSER_RESULT_CODE) {
            if (null == uploadMessage && null == uploadMessageAboveL) return;
            Uri result = data == null || resultCode != RESULT_OK ? null : data.getData();
            if (uploadMessageAboveL != null) {
                onActivityResultAboveL(requestCode, resultCode, data);
            } else if (uploadMessage != null) {
                uploadMessage.onReceiveValue(result);
                uploadMessage = null;
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void onActivityResultAboveL(int requestCode, int resultCode, Intent intent) {
        if (requestCode != FILE_CHOOSER_RESULT_CODE || uploadMessageAboveL == null)
            return;
        Uri[] results = null;
        if (resultCode == Activity.RESULT_OK) {
            if (intent != null) {
                String dataString = intent.getDataString();
                ClipData clipData = intent.getClipData();
                if (clipData != null) {
                    results = new Uri[clipData.getItemCount()];
                    for (int i = 0; i < clipData.getItemCount(); i++) {
                        ClipData.Item item = clipData.getItemAt(i);
                        results[i] = item.getUri();
                    }
                }
                if (dataString != null)
                    results = new Uri[]{Uri.parse(dataString)};
            }
        }
        uploadMessageAboveL.onReceiveValue(results);
        uploadMessageAboveL = null;
    }

    /**
     * js 调用android方法
     */
    @JavascriptInterface
    public void show() {

    }

    private void openImageChooserActivity() {
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("image/*");
        startActivityForResult(Intent.createChooser(i, "Image Chooser"),
                FILE_CHOOSER_RESULT_CODE);
    }


    private void initWebChromeClient() {
        webView.setWebChromeClient(new WebChromeClient() {


            //  android 3.0以下：用的这个方法
            public void openFileChooser(ValueCallback<Uri> valueCallback) {
                uploadMessage = valueCallback;
                openImageChooserActivity();
            }

            // android 3.0以上，android4.0以下：用的这个方法
            public void openFileChooser(ValueCallback valueCallback, String acceptType) {
                uploadMessage = valueCallback;
                openImageChooserActivity();
            }

            //android 4.0 - android 4.3  安卓4.4.4也用的这个方法
            public void openFileChooser(ValueCallback<Uri> valueCallback, String acceptType,
                                        String capture) {
                uploadMessage = valueCallback;
                openImageChooserActivity();
            }


            /**
             * 当页面加载的进度发生改变时回调，用来告知主程序当前页面的加载进度。
             * @param view
             * @param newProgress
             */
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }

            @Override
            public void onShowCustomView(View view, int requestedOrientation, CustomViewCallback callback) {
                super.onShowCustomView(view, requestedOrientation, callback);
            }

            @Override
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                super.onGeolocationPermissionsShowPrompt(origin, callback);
            }

            @Override
            public void onGeolocationPermissionsHidePrompt() {
                super.onGeolocationPermissionsHidePrompt();
            }

            /**
             * 该方法在web页面请求某个尚未被允许或拒绝的权限时回调，主程序在此时调用grant(String [])或deny()方法。
             * 如果该方法没有被重写，则默认拒绝web页面请求的权限
             * @param request
             */
            @Override
            public void onPermissionRequest(PermissionRequest request) {
                super.onPermissionRequest(request);
            }

            /**
             * 该方法在web权限申请权限被取消时回调，这时应该隐藏任何与之相关的UI界面。
             * @param request
             */
            @Override
            public void onPermissionRequestCanceled(PermissionRequest request) {
                super.onPermissionRequestCanceled(request);
            }

            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                return super.onConsoleMessage(consoleMessage);
            }

            /**
             * 当我们的Web页面包含视频时，我们可以在HTML里为它设置一个预览图，WebView会在绘制页面时根据它的宽高为它布局。
             * 而当我们处于弱网状态下时，我们没有比较快的获取该图片，那WebView绘制页面时的gitWidth()方法就会报出空指针异常~ 于是app就crash了。
             *这时我们就需要重写该方法，在我们尚未获取web页面上的video预览图时，给予它一个本地的图片，避免空指针的发生。
             * @return
             */
            @Override
            public Bitmap getDefaultVideoPoster() {
                return super.getDefaultVideoPoster();
            }

            /**
             * 重写该方法可以在视频loading时给予一个自定义的View，可以是加载圆环 or something。
             * @return
             */
            @Override
            public View getVideoLoadingProgressView() {
                return super.getVideoLoadingProgressView();
            }

            @Override
            public void getVisitedHistory(ValueCallback<String[]> callback) {
                super.getVisitedHistory(callback);
            }

            @Override
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
                uploadMessageAboveL = filePathCallback;
                openImageChooserActivity();
                return true;
            }

            /**
             * 用来接收web页面的title，我们可以在这里将页面的title设置到Toolbar。
             * @param view
             * @param title
             */
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
            }

            /**
             * 用来接收web页面的icon，我们可以在这里将该页面的icon设置到Toolbar
             * @param view
             * @param icon
             */
            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
                super.onReceivedIcon(view, icon);
            }

            @Override
            public void onReceivedTouchIconUrl(WebView view, String url, boolean precomposed) {
                super.onReceivedTouchIconUrl(view, url, precomposed);
            }

            /**
             * 支持web页面进入全屏模式而存在的（比如播放视频），如果不实现这两个方法，该web上的内容便不能进入全屏模式
             * 该方法在当前页面进入全屏模式时回调，主程序必须提供一个包含当前web内容（视频 or Something）的自定义的View。
             * @param view
             * @param callback
             */
            @Override
            public void onShowCustomView(View view, CustomViewCallback callback) {
                super.onShowCustomView(view, callback);
            }

            /**
             * 该方法在当前页面退出全屏模式时回调，主程序应在这时隐藏之前show出来的View
             */
            @Override
            public void onHideCustomView() {
                super.onHideCustomView();
            }

            @Override
            public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
                return super.onCreateWindow(view, isDialog, isUserGesture, resultMsg);
            }

            @Override
            public void onRequestFocus(WebView view) {
                super.onRequestFocus(view);
            }

            @Override
            public void onCloseWindow(WebView window) {
                super.onCloseWindow(window);
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return true;
            }

            @Override
            public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
                return true;
            }

            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
                return true;
            }

            @Override
            public boolean onJsBeforeUnload(WebView view, String url, String message, JsResult result) {
                return super.onJsBeforeUnload(view, url, message, result);
            }
        });

    }

    private void initWebViewClient() {


        webView.setWebViewClient(new WebViewClient() {


            /**
             * 该方法在加载页面资源时会回调，每一个资源（比如图片）的加载都会调用一次。
             * @param view
             * @param url
             */
            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
            }

            /**
             * 该方法在WebView开始加载页面且仅在Main frame loading（即整页加载）时回调，一次Main frame的加载只会回调该方法一次。
             * 我们可以在这个方法里设定开启一个加载的动画，告诉用户程序在等待网络的响应。
             * @param view
             * @param url
             * @param favicon
             */
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            /**
             * 该方法只在WebView完成一个页面加载时调用一次（同样也只在Main frame loading时调用），我们可以可以在此时关闭加载动画，进行其他操作。
             * @param view
             * @param url
             */
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            /**
             * 这个方法只在与服务器无法正常连接时调用
             * @param view
             * @param request
             * @param error
             */
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
            }

            /**
             * 当服务器返回一个HTTP ERROR并且它的status code>=400时
             * @param view
             * @param request
             * @param errorResponse
             */
            @Override
            public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                super.onReceivedHttpError(view, request, errorResponse);
            }

            /**
             * 当WebView加载某个资源引发SSL错误时会回调该方法，这时WebView要么执行handler.cancel()取消加载，要么执行handler.proceed()方法继续加载（默认为cancel）。
             * 需要注意的是，这个决定可能会被保留并在将来再次遇到SSL错误时执行同样的操作。
             * @param view
             * @param handler
             * @param error
             */
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }

            /**
             * 当WebView需要请求某个数据时，这个方法可以拦截该请求来告知app并且允许app本身返回一个数据来替代我们原本要加载的数据。
             比如你对web的某个js做了本地缓存，希望在加载该js时不再去请求服务器而是可以直接读取本地缓存的js，这个方法就可以帮助你完成这个需求。
             你可以写一些逻辑检测这个request，并返回相应的数据，你返回的数据就会被WebView使用，如果你返回null，WebView会继续向服务器请求。
             * @param view
             * @param request
             * @return
             */
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                return super.shouldInterceptRequest(view, request);
            }


            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    view.loadUrl(request.getUrl().toString());
                } else {
                    view.loadUrl(request.toString());
                }
                return true;
            }

            /**
             * 当WebView得页面Scale值发生改变时回调
             * @param view
             * @param oldScale
             * @param newScale
             */
            @Override
            public void onScaleChanged(WebView view, float oldScale, float newScale) {
                super.onScaleChanged(view, oldScale, newScale);
            }

            /**
             * 默认值为false，重写此方法并return true可以让我们在WebView内处理按键事件。
             * @param view
             * @param event
             * @return
             */
            @Override
            public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
                return super.shouldOverrideKeyEvent(view, event);
            }
        });

    }

    /**
     * WebView Setting
     */
    @SuppressLint("SetJavaScriptEnabled")
    private void initWebViewSetting() {
        settings = webView.getSettings();
        //设置WebView是否可以运行JavaScript
        settings.setJavaScriptEnabled(true);

        String appCacheDir = getApplicationContext().getDir("cache", Context.MODE_PRIVATE).getPath();
        settings.setAppCachePath(appCacheDir);

        //设置WebView是否可以由JavaScript自动打开窗口，默认为false，通常与JavaScript的window.open()配合使用。
        settings.setJavaScriptCanOpenWindowsAutomatically(false);
        //启用或禁用WebView访问文件数据
        settings.setAllowFileAccess(false);
        //缩放
        settings.setSupportZoom(false);
        //显示或不显示缩放按钮
        settings.setBuiltInZoomControls(false);
        //隐藏原生的缩放控件
        settings.setDisplayZoomControls(true);
        //设置WebView是否支持多窗口
        settings.setSupportMultipleWindows(false);
        //指定WebView的页面布局显示形式，调用该方法会引起页面重绘
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        //缓存
        settings.setAppCacheEnabled(false);
        //设置应用缓存路径，这个路径必须是可以让app写入文件的
        settings.setAppCachePath("");
        //缓存模式
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        /*
        *1. LOAD_DEFAULT：默认的缓存使用模式。在进行页面前进或后退的操作时，如果缓存可用并未过期就优先加载缓存，否则从网络上加载数据。这样可以减少页面的网络请求次数。
         2. LOAD_CACHE_ELSE_NETWORK：只要缓存可用就加载缓存，哪怕它们已经过期失效。如果缓存不可用就从网络上加载数据。
         3. LOAD_NO_CACHE：不加载缓存，只从网络加载数据。
         4. LOAD_CACHE_ONLY：不从网络加载数据，只从缓存加载数据。
        比如有网的时候使用LOAD_DEFAULT，离线时使用LOAD_CACHE_ONLY、LOAD_CACHE_ELSE_NETWORK

        * */
        //数据库缓存
        settings.setDatabaseEnabled(false);
        //DOM缓存
        settings.setDomStorageEnabled(false);
        //UserAgent
        settings.setUserAgentString("");
        //设置编码格式，通常都设为“UTF-8”
        settings.setDefaultTextEncodingName("UTF-8");
        //设置标准的字体族，默认“sans-serif”。
        settings.setStandardFontFamily("sans-serif");
        //设置默认填充字体大小
        settings.setDefaultFixedFontSize(18);
        //设置默认字体大小
        settings.setDefaultFontSize(18);
        //设置最小字体
        settings.setMinimumFontSize(10);
        //设置最小逻辑字体
        settings.setMinimumLogicalFontSize(10);
        //设置自适应屏幕，两者合用
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);


        //混合加载
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            settings.setLoadsImagesAutomatically(true);
        } else {
            settings.setLoadsImagesAutomatically(false);
        }
    }

    /**
     * WebView自身方法属性
     */
    @SuppressLint("AddJavascriptInterface")
    private void initWebViewProps() {

        webView.addJavascriptInterface(this, "android");
        //加载本地文件
        webView.loadUrl("file:///android_asset/javascript.html");

        //获取当前的URl
        webView.getUrl();
        //刷新
        webView.reload();
        //用来确认WebView是否还有可向前的历史记录
        webView.canGoForward();
        //以当前的页面为起始点，用来确认WebView的历史记录是否足以后退或前进给定的步数，正数为前进，负数为后退。
        webView.canGoBackOrForward(1);
        //在WebView历史记录后退到上一项
        webView.goBack();
        //在WebView历史记录里前进到下一项
        webView.goForward();
        //以当前页面为起始点，前进或后退历史记录中指定的步数，正数为前进，负数为后退
        webView.goBackOrForward(1);
        //清空网页访问留下的缓存数据。需要注意的时，由于缓存是全局的，所以只要是WebView用到的缓存都会被清空，即便其他地方也会使用到。
        // 该方法接受一个参数，从命名即可看出作用。若设为false，则只清空内存里的资源缓存，而不清空磁盘里的。
        webView.clearCache(true);
        //清除当前webView访问的历史记录
        webView.clearHistory();
        //清除自动完成填充的表单数据。需要注意的是，该方法仅仅清除当前表单域自动完成填充的表单数据，并不会清除WebView存储到本地的数据。
        webView.clearFormData();
        //返回的当前可见区域的顶端距整个页面顶端的距离，也就是当前内容滚动的距离
        webView.getScrollY();
        //返回当前WebView这个容器的高度。其实以上两个方法都属于View
        webView.getHeight();
        //返回整个HTML页面的高度，但该高度值并不等同于当前整个页面的高度，因为WebView有缩放功能，
        // 所以当前整个页面的高度实际上应该是原始HTML的高度再乘上缩放比例。因此，准确的判断方法应该是
        webView.getContentHeight();
        if (webView.getContentHeight() * webView.getScaleY() == (webView.getHeight() + webView.getScrollY())) {
            //到底
        }
        if (webView.getScrollY() == 0) {
            //顶端
        }
        //将WebView展示的页面滑动至顶部
        webView.pageUp(true);
        //将WebView展示的页面滑动至底部
        webView.pageDown(true);
    }

    @SuppressLint("ObsoleteSdkInt")
    @SuppressWarnings("JavaReflectionMemberAccess")
    public void releaseAllWebViewCallback() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            try {
                Field field = WebView.class.getDeclaredField("mWebViewCore");
                field = field.getType().getDeclaredField("mBrowserFrame");
                field = field.getType().getDeclaredField("sConfigCallback");
                field.setAccessible(true);
                field.set(null, null);
            } catch (NoSuchFieldException e) {
                if (BuildConfig.DEBUG) {
                    e.printStackTrace();
                }
            } catch (IllegalAccessException e) {
                if (BuildConfig.DEBUG) {
                    e.printStackTrace();
                }
            }
        } else {
            try {
                @SuppressLint("PrivateApi") Field sConfigCallback = Class.forName("android.webkit.BrowserFrame").getDeclaredField("sConfigCallback");
                if (sConfigCallback != null) {
                    sConfigCallback.setAccessible(true);
                    sConfigCallback.set(null, null);
                }
            } catch (NoSuchFieldException e) {
                if (BuildConfig.DEBUG) {
                    e.printStackTrace();
                }
            } catch (ClassNotFoundException e) {
                if (BuildConfig.DEBUG) {
                    e.printStackTrace();
                }
            } catch (IllegalAccessException e) {
                if (BuildConfig.DEBUG) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onResume() {
        webView.onResume();
        webView.resumeTimers();
        settings.setJavaScriptEnabled(true);
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        settings.setJavaScriptEnabled(false);
    }

    @Override
    protected void onPause() {
        webView.onPause();
        webView.pauseTimers();
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        if (webView != null) {
            webView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            webView.clearHistory();
            webView.loadUrl("about:blank");
            webView.stopLoading();
            webView.setVisibility(View.GONE);
            webView.setWebChromeClient(null);
            webView.setWebViewClient(null);
            ((ViewGroup) webView.getParent()).removeView(webView);
            webView.destroy();
            webView = null;
        }
        releaseAllWebViewCallback();
        super.onDestroy();
    }


}
