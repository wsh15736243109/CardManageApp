package com.itboye.cardmanage.model;

import android.app.Application;
import android.databinding.ObservableField;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import me.goldze.mvvmhabit.base.BaseViewModel;

public class HomeTopModel extends BaseViewModel {
    public ObservableField<Drawable> drawableObservableField = new ObservableField<>();
    public String title;

    public HomeTopModel(@NonNull Application application) {
        super(application);
    }

    public HomeTopModel setIcon(int icon) {
        drawableObservableField.set(getApplication().getResources().getDrawable(icon));
        return this;
    }

    public String getTitle() {
        return title;
    }

    public HomeTopModel setTitle(String title) {
        this.title = title;
        return this;
    }
}
