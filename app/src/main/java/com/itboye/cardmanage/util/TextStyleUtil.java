package com.itboye.cardmanage.util;

import android.text.Html;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;
import com.itboye.cardmanage.R;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TextAppearanceSpan;
import android.widget.TextView;
import com.itboye.cardmanage.app.App;

public class TextStyleUtil {
    public static void setDifferentSizeForTextView(int startPo, int endPo, String msg, TextView textView) {
        SpannableString styledText = new SpannableString(Html.fromHtml(msg));
        styledText.setSpan(new TextAppearanceSpan(App.getInstance(), R.style.style0), startPo, endPo, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        styledText.setSpan(new TextAppearanceSpan(App.getInstance(), R.style.style1), 0, startPo, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        styledText.setSpan(new ForegroundColorSpan(App.getInstance().getResources().getColor(R.color.black)), startPo, endPo, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(styledText, TextView.BufferType.SPANNABLE);
    }
}
