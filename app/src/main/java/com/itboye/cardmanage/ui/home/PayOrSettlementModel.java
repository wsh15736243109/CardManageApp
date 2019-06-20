package com.itboye.cardmanage.ui.home;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itboye.cardmanage.BR;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.adapter.CardManageItemAdapter;
import com.itboye.cardmanage.interfaces.OnMyItemClickListener;
import com.itboye.cardmanage.model.CardManageModel;
import com.itboye.cardmanage.retrofit.API;
import com.itboye.cardmanage.retrofit.ApiDisposableObserver;
import com.itboye.cardmanage.retrofit.AppUtils;
import com.itboye.cardmanage.retrofit.RetrofitClient;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.utils.ToastUtils;
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass;

import java.util.ArrayList;

public class PayOrSettlementModel extends BaseViewModel {

    public TextView tab1;
    public TextView tab2;

    public final ObservableList<com.itboye.cardmanage.model.CardManageModel>
            observableList = new ObservableArrayList<>();
    public ObservableField<Integer> isEmpty = new ObservableField<>(View.GONE);
    public CardManageItemAdapter adapter;

    public PayOrSettlementModel(@NonNull Application application) {
        super(application);
    }

    //添加卡
    public void addCard() {
        startActivity(AddCardActivity.class);
    }

    public void getCardList(String cardUse, int pageIndex) {
        AppUtils.requestData(RetrofitClient.getInstance().create(API.class).cardList(cardUse, pageIndex + "", "10", "by_UserBankCard_query"), getLifecycleProvider(), disposable -> showDialog(), new ApiDisposableObserver() {
            @Override
            public void onResult(Object o, String msg, int code) {
                ArrayList<CardManageModel> ar = (ArrayList<CardManageModel>) o;
                if (ar != null && ar.size() > 0) {
                    isEmpty.set(View.GONE);
                } else {
                    isEmpty.set(View.VISIBLE);
                }
                observableList.addAll(ar);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(int code, String msg) {
                isEmpty.set(View.VISIBLE);
            }

            @Override
            public void dialogDismiss() {
                dismissDialog();
            }
        });
    }

//
//    public OnItemBindClass itemBinding = new OnItemBindClass<>().map(com.itboye.cardmanage.model.CardManageModel.class, (itemBinding, position, item) -> itemBinding.clearExtras()
//            .set(BR.item, R.layout.item_card_manage)
//            .bindExtra(BR.listener, new OnMyItemClickListener<com.itboye.cardmanage.model.CardManageModel>() {
//                @Override
//                public void onItemClick(int position, com.itboye.cardmanage.model.CardManageModel item) {
//                    ToastUtils.showShort(position + "item 点击了吗>>>>>>" + item + "______________" + position);
//                }
//
//                @Override
//                public void onLongClick() {
//                    ToastUtils.showShort(position + "item 长按了吗>>>>>>");
//                }
//            }));
}
