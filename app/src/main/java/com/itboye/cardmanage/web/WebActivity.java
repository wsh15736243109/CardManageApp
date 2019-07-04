package com.itboye.cardmanage.web;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.itboye.cardmanage.base.BaseMVVMActivity;
import com.itboye.cardmanage.databinding.ActivityWebBinding;
import com.itboye.cardmanage.BR;
import com.itboye.cardmanage.R;

public class WebActivity extends BaseMVVMActivity<ActivityWebBinding,WebModel> {

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
        binding.webView.loadUrl(getIntent().getStringExtra("url"));
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
    }
}
