package com.itboye.cardmanage.ui.mine;

import android.os.Bundle;
import com.itboye.cardmanage.BR;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.base.BaseMVVMActivity;
import com.itboye.cardmanage.databinding.ActivitySettingBinding;
import com.itboye.cardmanage.util.CacheUtil;
import com.itboye.cardmanage.util.VersionUtil;

public class SettingActivity extends BaseMVVMActivity<ActivitySettingBinding,SettingModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_setting;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        viewModel.versionName.set("V"+VersionUtil.getVersionName());
        try {
            viewModel.cacheData.set(CacheUtil.getTotalCacheSize(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
