package com.itboye.cardmanage.ui.fragment;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import com.itboye.cardmanage.BR;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.interfaces.OnMyItemClickListener;
import com.itboye.cardmanage.model.LoanModel;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.utils.ToastUtils;
import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.OnItemBind;
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass;

import java.util.ArrayList;
import java.util.List;

public class LoanFragmentModel extends BaseViewModel {
    //给RecyclerView添加items
    public final ObservableList<LoanModel> observableList = new ObservableArrayList<>();
    public ObservableField<List<LoanModel>> hobbies = new ObservableField<>();
    //给RecyclerView添加ItemBinding
//    public final ItemBinding<LoanModel> itemBinding = ItemBinding.<LoanModel>of(BR.item, R.layout.item_loan).bindExtra(BR.listener, new OnMyItemClickListener() {
//        @Override
//        public void onItemClick(int position, Object item) {
//            ToastUtils.showShort(position + "item 点击了吗" + position);
//        }
//
//    });

    public OnItemBindClass itemBind2=new OnItemBindClass<>().map(LoanModel.class,new OnItemBind<LoanModel>(){

        @Override
        public void onItemBind(ItemBinding itemBinding, final int position, LoanModel item) {
            itemBinding.clearExtras()
                    .set(BR.item, R.layout.item_loan)
                    .bindExtra(BR.listener, new OnMyItemClickListener<LoanModel>() {
                        @Override
                        public void onItemClick(int position232323, LoanModel item) {
                            ToastUtils.showShort(position + "item 点击了吗>>>>>>"+item+"______________"+position232323);
                        }

                        @Override
                        public void onLongClick() {
                            ToastUtils.showShort(position + "item 长按了吗>>>>>>");
                        }
                    });
        }
    });
    public LoanFragmentModel(@NonNull Application application) {
        super(application);
        ArrayList<LoanModel> ar = new ArrayList<>();
        ar.add(new LoanModel(getApplication()));
        ar.add(new LoanModel(getApplication()));
        hobbies.set(ar);

    }

}
