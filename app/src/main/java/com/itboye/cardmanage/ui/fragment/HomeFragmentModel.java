package com.itboye.cardmanage.ui.fragment;

import android.databinding.ObservableField;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.BR;

import android.app.Application;
import android.support.annotation.NonNull;
import com.itboye.cardmanage.model.HomeTopModel;
import com.itboye.cardmanage.ui.home.CardManageActivity;
import com.itboye.cardmanage.ui.home.ReceiveMoneyActivity;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragmentModel extends BaseViewModel {
    public HomeFragmentModel(@NonNull Application application) {
        super(application);
        ArrayList<HomeTopModel> ar = new ArrayList<>();
        ar.add(new HomeTopModel(application).setIcon(R.drawable.ic_jiaoyijilu).setTitle("交易记录"));
        ar.add(new HomeTopModel(application).setIcon(R.drawable.ic_shengjivip).setTitle("升级VIP"));
        ar.add(new HomeTopModel(application).setIcon(R.drawable.ic_caozuobangzhu).setTitle("操作帮助"));
        ar.add(new HomeTopModel(application).setIcon(R.drawable.ic_lianxikefu).setTitle("联系客服"));
        ar.add(new HomeTopModel(application).setIcon(R.drawable.ic_goumaiji).setTitle("购买POS机"));
        ar.add(new HomeTopModel(application).setIcon(R.drawable.ic_changjianwenti).setTitle("常见问题"));
        ar.add(new HomeTopModel(application).setIcon(R.drawable.ic_more).setTitle("更多"));
        hobbies.set(ar);
    }

    //给RecyclerView添加ItemBinding
    public final ItemBinding<HomeTopModel> itemBinding = ItemBinding.of(BR.item, R.layout.item);
    public ObservableField<ArrayList<HomeTopModel>> hobbies = new ObservableField<>();

    public void cardManage(int type) {
        if (type == 0) {
            startActivity(ReceiveMoneyActivity.class);

        } else {
            startActivity(CardManageActivity.class);
        }
    }
}
