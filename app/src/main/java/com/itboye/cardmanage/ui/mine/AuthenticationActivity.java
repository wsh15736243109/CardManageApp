package com.itboye.cardmanage.ui.mine;

import android.databinding.Observable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import com.itboye.cardmanage.BR;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.base.BaseMVVMActivity;
import com.itboye.cardmanage.bean.BranchBankBean;
import com.itboye.cardmanage.bean.UserAuthDetailBean;
import com.itboye.cardmanage.databinding.ActivityAuthenticationBinding;
import com.itboye.cardmanage.util.GalleryUtil;
import com.itboye.cardmanage.util.GlideUtil;
import com.yancy.gallerypick.inter.IHandlerCallBack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuthenticationActivity extends BaseMVVMActivity<ActivityAuthenticationBinding, AuthenticationModel> {


    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_authentication;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        //查看认证资料 0
        viewModel.type = getIntent().getIntExtra("type", 0);
        //添加认证 1
        //先查询认证状态
        viewModel.queryAuthStatus();
    }

    @Override
    public void initViewObservable() {
        viewModel.ui.photo1.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                openLibrary(101);
            }
        });
        viewModel.ui.photo2.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                openLibrary(102);
            }
        });
        viewModel.ui.photo3.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                openLibrary(103);
            }
        });
        viewModel.ui.photo4.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                openLibrary(104);
            }
        });

        viewModel.ui.searchBranch.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                if (viewModel.branchBankBeanArrayList != null && viewModel.branchBankBeanArrayList.size() > 0) {
                    //展示所有搜索到的支行
                    showBranchBankList(viewModel.branchBankBeanArrayList);
                }
            }
        });
        viewModel.ui.uiChange.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                setAuthView(viewModel.userAuthDetailBean);
            }
        });
    }

    private void setAuthView(UserAuthDetailBean userAuthDetailBean) {
        GlideUtil.display(AuthenticationActivity.this, userAuthDetailBean.getId_front_img(), binding.riPhoto1);
        GlideUtil.display(AuthenticationActivity.this, userAuthDetailBean.getId_back_img(), binding.riPhoto2);
        GlideUtil.display(AuthenticationActivity.this, userAuthDetailBean.getId_hold_img(), binding.riPhoto3);
        GlideUtil.display(AuthenticationActivity.this, userAuthDetailBean.getBank_front_img(), binding.riPhoto4);


    }

    private void showBranchBankList(ArrayList<BranchBankBean> branchBankBeanArrayList) {
        //初始化map
        Map<String, String> keyValuePair = new HashMap<>();
        for (int i = 0; i < branchBankBeanArrayList.size(); i++) {
            BranchBankBean branchBankBean = branchBankBeanArrayList.get(i);
            keyValuePair.put("name", branchBankBean.getName());
        }
        ListView listView = new ListView(this);
        List<Map<String, String>> list = new ArrayList<>();
        list.add(keyValuePair);
        listView.setAdapter(new SimpleAdapter(this, list, android.R.layout.simple_list_item_1, new String[]{"name"}, new int[]{android.R.id.text1}));
        AlertDialog alert = new AlertDialog.Builder(this).setView(listView).setCancelable(false).create();
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            viewModel.branchNo = branchBankBeanArrayList.get(i).getPaysysbank();
            viewModel.branchBankName.set(branchBankBeanArrayList.get(i).getName());
            alert.dismiss();
        });
        alert.show();
    }

    //选择图片
    private void openLibrary(int requestCode) {
        GalleryUtil.galleryConfig(this, new IHandlerCallBack() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(List<String> photoList) {
                String path = photoList.get(0);
                switch (requestCode) {
                    case 101:
                        GlideUtil.display(AuthenticationActivity.this, path, binding.riPhoto1);
                        break;
                    case 102:
                        GlideUtil.display(AuthenticationActivity.this, path, binding.riPhoto2);
                        break;
                    case 103:
                        GlideUtil.display(AuthenticationActivity.this, path, binding.riPhoto3);
                        break;
                    case 104:
                        GlideUtil.display(AuthenticationActivity.this, path, binding.riPhoto4);
                        break;
                }
                viewModel.uploadImage(path, requestCode);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onError() {

            }
        });
    }

//
//    @Override
//    public void onBackPressed() {
//        switch (viewModel.status) {
//            case PHOTO_IDENTITY:
//                finish();
//                break;
//            case PHOTO_HAND_IDENTITY:
//                viewModel.setFirst();
//                viewModel.status = AuthenticationModel.Status.INIT;
//                break;
//            case PHOTO_CARD:
//                viewModel.status = AuthenticationModel.Status.PHOTO_IDENTITY;
//                viewModel.setSecond();
//                break;
//        }
////        viewModel.setCurrentItem();
////        super.onBackPressed();
//    }
}
