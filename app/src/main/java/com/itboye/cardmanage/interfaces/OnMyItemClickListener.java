package com.itboye.cardmanage.interfaces;

import android.view.View;

public interface OnMyItemClickListener<T> {
    void onItemClick(View view,int position, T item);

    void onItemClick(int position, T item);
}
