package com.itboye.cardmanage.ui.mine;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import com.itboye.cardmanage.BR;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.adapter.CardListAdapter;
import com.itboye.cardmanage.adapter.MyTranslationAdapter;
import com.itboye.cardmanage.base.BaseMVVMActivity;
import com.itboye.cardmanage.bean.TranslationBean;
import com.itboye.cardmanage.databinding.ActivityCardListBinding;
import com.itboye.cardmanage.interfaces.OnMyItemClickListener;
import com.itboye.cardmanage.model.CardManageModel;
import com.itboye.cardmanage.retrofit.API;
import com.itboye.cardmanage.retrofit.ApiDisposableObserver;
import com.itboye.cardmanage.retrofit.AppUtils;
import com.itboye.cardmanage.retrofit.RetrofitClient;
import io.reactivex.disposables.Disposable;
import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.bus.RxSubscriptions;
import me.goldze.mvvmhabit.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

public class CardListActivity extends BaseMVVMActivity<ActivityCardListBinding, CardListModel> {


    private CardListAdapter adapter;
    ArrayList<CardManageModel> cardManageModelArrayList = new ArrayList<>();
    private int usage;
    private int chooseCount = 1;
    int pageIndex;
    private Disposable mSubscription;
    private HashMap<String, String> map1;
    private HashMap<String, String> map2;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_card_list;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        binding.titleBar.setTitle(getIntent().getStringExtra("title"));
        usage = getIntent().getIntExtra("usage", 1);//卡片类型
        chooseCount = getIntent().getIntExtra("chooseCount", 1);//最多可选择
        binding.rvCardList.setLayoutManager(new LinearLayoutManager(this));
        binding.rvCardList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        map1 = (HashMap<String, String>) getIntent().getSerializableExtra("hash1");
        map2 = (HashMap<String, String>) getIntent().getSerializableExtra("hash2");
        adapter = new CardListAdapter(cardManageModelArrayList, new OnMyItemClickListener() {
            @Override
            public void onItemClick(View view, int position, Object item) {

            }

            @Override
            public void onItemClick(int position, Object item) {
                int checkCount = 0;
                for (int i = 0; i < cardManageModelArrayList.size(); i++) {
                    CardManageModel model = cardManageModelArrayList.get(i);
                    if (model.isCheck()) {
                        if (chooseCount == 1) {
                            cardManageModelArrayList.get(i).setCheck(false);
                        } else
                            checkCount++;
                    }
                }

                if (chooseCount != -1) {
                    if (checkCount >= chooseCount) {
                        if (!cardManageModelArrayList.get(position).isCheck()) {
                            ToastUtils.showShort("最多只能选择" + chooseCount + "项");
                            return;
                        }
                    }
                }

                cardManageModelArrayList.get(position).setCheck(!cardManageModelArrayList.get(position).isCheck());
                adapter.notifyDataSetChanged();
            }
        });
        binding.rvCardList.setAdapter(adapter);
        getCardList();
        binding.tvAdd.setOnClickListener(view -> sure());
        binding.titleBar.getTvRight().setOnClickListener(view -> sure());
    }

    private void sure() {
        ArrayList selectAr = new ArrayList();
        for (CardManageModel model : cardManageModelArrayList) {
            if (model.isCheck()) {
                selectAr.add(model);
            }
        }
        if (selectAr.size() <= 0) {
            ToastUtils.showShort("您还未选择卡片");
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("usage", usage);
        bundle.putSerializable("array", cardManageModelArrayList);
        RxBus.getDefault().post(bundle);
        finish();
    }


    private void getCardList() {
        AppUtils.requestData(RetrofitClient.getInstance().create(API.class).cardList(usage + "", pageIndex + "", "10", "by_UserBankCard_query"), viewModel.getLifecycleProvider(), disposable -> viewModel.showDialog(), new ApiDisposableObserver() {
            @Override
            public void onResult(Object o, String msg, int code) {
                ArrayList<CardManageModel> ar = (ArrayList<CardManageModel>) o;
                if (ar != null && ar.size() > 0) {
                    binding.tvEmpty.setVisibility(View.GONE);
                } else {
                    binding.tvEmpty.setVisibility(View.VISIBLE);
                }
                cardManageModelArrayList.clear();
                cardManageModelArrayList.addAll(ar);
                for (int i = 0; i < cardManageModelArrayList.size(); i++) {
                    if (usage == 1) {
                        cardManageModelArrayList.get(i).setCheck(map1.containsKey(cardManageModelArrayList.get(i).getId()));
                    } else {
                        cardManageModelArrayList.get(i).setCheck(map2.containsKey(cardManageModelArrayList.get(i).getId()));
                    }
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(int code, String msg) {
                binding.tvEmpty.setVisibility(View.VISIBLE);
            }

            @Override
            public void dialogDismiss() {
                dismissDialog();
            }
        });
    }
}
