package com.itboye.cardmanage.base;

import android.app.Activity;
import android.os.Bundle;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class BaseActivity extends SwipeBackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}
