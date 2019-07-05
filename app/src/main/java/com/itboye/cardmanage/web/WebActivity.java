package com.itboye.cardmanage.web;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.itboye.cardmanage.base.BaseMVVMActivity;
import com.itboye.cardmanage.databinding.ActivityWebBinding;
import com.itboye.cardmanage.BR;
import com.itboye.cardmanage.R;
import me.goldze.mvvmhabit.utils.KLog;

public class WebActivity extends BaseMVVMActivity<ActivityWebBinding, WebModel> {

    private String url;
    private String TAG = "WEB";

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_web;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        setTitle(getIntent().getStringExtra("title"));
        url = getIntent().getStringExtra("url");
        binding.webView.loadUrl(getIntent().getStringExtra("url"));
        KLog.v(TAG, "网页地址==" + url);
        binding.webView.setWebViewClient(new WebViewClient());
        binding.webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100) {
                    binding.webProgress.setVisibility(View.GONE);
                } else {
                    binding.webProgress.setVisibility(View.VISIBLE);
                    binding.webProgress.setProgress(newProgress);
                }
            }
        });

        WebSettings webSettings = binding.webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
    }
}
