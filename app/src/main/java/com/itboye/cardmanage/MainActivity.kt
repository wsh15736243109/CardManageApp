package com.itboye.cardmanage

import android.databinding.DataBindingUtil.setContentView
import android.os.Bundle
import com.itboye.cardmanage.base.BaseActivity
import com.itboye.cardmanage.base.BaseMVVMActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
