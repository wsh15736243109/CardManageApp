package com.itboye.cardmanage.web;

import android.os.Bundle;
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
        setTitle("常见问题");
        binding.webView.loadUrl("http://www.baidu.com");
        binding.webView.setWebViewClient(new WebViewClient());
    }
}
