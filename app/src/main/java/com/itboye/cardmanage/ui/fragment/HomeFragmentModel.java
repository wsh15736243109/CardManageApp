package com.itboye.cardmanage.ui.fragment;

import android.app.Application;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.View;
import com.itboye.cardmanage.BR;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.bean.BannerBean;
import com.itboye.cardmanage.bean.CardBean;
import com.itboye.cardmanage.bean.NoticeBean;
import com.itboye.cardmanage.interfaces.OnMyItemClickListener;
import com.itboye.cardmanage.model.HomeTopModel;
import com.itboye.cardmanage.ui.home.CardManageActivity;
import com.itboye.cardmanage.ui.home.ReceiveMoneyActivity;
import com.itboye.cardmanage.ui.mine.RepaymentPlanActivity;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.bus.RxBus;
import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.OnItemBind;
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass;

import java.util.ArrayList;
import java.util.List;

public class HomeFragmentModel extends BaseViewModel {

    private NoticeBean noticeBean;

    public HomeFragmentModel(@NonNull Application application) {
        super(application);
        initAr2();
        initAr3();
    }

    public void initAr3() {
        ArrayList<CardBean> ar = new ArrayList<>();
        ar.add(new CardBean(getApplication()));
        ar.add(new CardBean(getApplication()));
        ar.add(new CardBean(getApplication()));
        ar.add(new CardBean(getApplication()));
        ar.add(new CardBean(getApplication()));
        ar.add(new CardBean(getApplication()));
        ar.add(new CardBean(getApplication()));
        cardArray.set(ar);
    }


    public OnItemBindClass itemBind = new OnItemBindClass<>().map(CardBean.class, new OnItemBind<CardBean>() {

        @Override
        public void onItemBind(ItemBinding itemBinding, final int position, CardBean item) {
            itemBinding.clearExtras()
                    .set(BR.item, R.layout.item_home_card)
                    .bindExtra(BR.listener, new OnMyItemClickListener<CardBean>() {
                        @Override
                        public void onItemClick(View view, int position232323, CardBean item) {
                        }

                        @Override
                        public void onItemClick(int position, CardBean item) {

                        }

                    });
        }
    });

    public void initAr1(BannerBean bannerBean) {
//        ArrayList<HomeTopModel> ar = new ArrayList<>();
//        if (bannerBean.getList() != null && bannerBean.getList().size() >= 1) {
//            List<BannerBean.ListBean> list = bannerBean.getList();
//            for (int i = 0; i < list.size(); i++) {
//                BannerBean.ListBean listBean = list.get(i);
//                ar.add(new HomeTopModel(getApplication()).setIcon(listBean.getImg_url()).setTitle(listBean.getTitle()));
////                ar.add(new HomeTopModel(getApplication()).setIcon(R.drawable.ic_shengjivip).setTitle("升级VIP"));
////                ar.add(new HomeTopModel(getApplication()).setIcon(R.drawable.ic_caozuobangzhu).setTitle("操作帮助"));
////                ar.add(new HomeTopModel(getApplication()).setIcon(R.drawable.ic_lianxikefu).setTitle("联系客服"));
////                ar.add(new HomeTopModel(getApplication()).setIcon(R.drawable.ic_goumaiji).setTitle("购买POS机"));
////                ar.add(new HomeTopModel(getApplication()).setIcon(R.drawable.ic_changjianwenti).setTitle("常见问题"));
//            }
//
//        }
////        ar.add(new HomeTopModel(getApplication()).setIcon(R.drawable.ic_more).setTitle("更多"));
//        topArray.set(ar);
    }

    public void initAr2() {
        ArrayList<HomeTopModel> ar = new ArrayList<>();
//        ar.add(new HomeTopModel(getApplication()).setIcon(R.drawable.is_home_se_shengbei).setTitle("省呗<font color='" + ContextCompat.getColor(getApplication(), R.color.hint_color_a3a3a3) + "'><br />1000-2000<br />极速到账</font>"));
//        ar.add(new HomeTopModel(getApplication()).setIcon(R.drawable.ic_home_se_360).setTitle("360借条<font color='" + ContextCompat.getColor(getApplication(), R.color.hint_color_a3a3a3) + "'><br />500-20万<br />低费率 快审批</font>"));
//        ar.add(new HomeTopModel(getApplication()).setIcon(R.drawable.ic_home_se_pingan).setTitle("平安普惠<font color='" + ContextCompat.getColor(getApplication(), R.color.hint_color_a3a3a3) + "'><br />最高五万额度<br />极速放款</font>"));
//        ar.add(new HomeTopModel(getApplication()).setIcon(R.drawable.ic_home_se_huanbei).setTitle("还呗<font color='" + ContextCompat.getColor(getApplication(), R.color.hint_color_a3a3a3) + "'><br />3000-50000<br />极速到账</font>"));
//        ar.add(new HomeTopModel(getApplication()).setIcon(R.drawable.ic_more).setTitle("更多产品<br />敬请期待"));
        xiaoEJiSu.set(ar);
    }

    //给RecyclerView添加ItemBinding
    public final ItemBinding<HomeTopModel> itemBinding = ItemBinding.of(BR.item, R.layout.item);
    public ObservableField<ArrayList<HomeTopModel>> topArray = new ObservableField<>();

    public ObservableField<ArrayList<HomeTopModel>> xiaoEJiSu = new ObservableField<>();

    public ObservableField<ArrayList<CardBean>> cardArray = new ObservableField<>();

    public void cardManage(int type) {
        if (type == 0) {
            startActivity(ReceiveMoneyActivity.class);

        } else {
            Bundle bundle = new Bundle();
            bundle.putInt("type", 1);
            startActivity(CardManageActivity.class, bundle);
        }
    }

    public void moreRepaymentPlan() {
        startActivity(RepaymentPlanActivity.class);
    }

    public void toDaikuan(int index) {

        RxBus.getDefault().post(index);

    }

    public void toBanka() {

    }

}
