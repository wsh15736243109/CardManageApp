package com.itboye.cardmanage.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.media.Image;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.util.SizeUtils;
import me.goldze.mvvmhabit.utils.ToastUtils;


/**
 */

public final class CommonTitleBar extends LinearLayout {

    private View layRoot;
    private View vStatusBar;
    private View layLeft;
    public TextView tvTitle;
    private TextView tvLeft;
    private TextView tvRight;
    private ImageView iconLeft;
    //    private View iconRight;
    private RelativeLayout layRight;
    private boolean backVisible;
    private int leftIcon;
    private String leftText;
    private String titleValue;
    private int titleRightDrawable;
    private int rightIcon;
    private String rightText;
    private int background_color;
    boolean needStatusBarHeight = false;
    private int titleColor;
    private int statusbar_bg;

    public View getLayRoot() {
        return layRoot;
    }

    public void setLayRoot(View layRoot) {
        this.layRoot = layRoot;
    }

    public View getvStatusBar() {
        return vStatusBar;
    }

    public void setvStatusBar(View vStatusBar) {
        this.vStatusBar = vStatusBar;
    }

    public View getLayLeft() {
        return layLeft;
    }

    public void setLayLeft(View layLeft) {
        this.layLeft = layLeft;
    }

    public TextView getTvTitle() {
        return tvTitle;
    }

    public void setTvTitle(TextView tvTitle) {
        this.tvTitle = tvTitle;
    }

    public TextView getTvLeft() {
        return tvLeft;
    }

    public void setTvLeft(TextView tvLeft) {
        this.tvLeft = tvLeft;
    }

    public TextView getTvRight() {
        return tvRight;
    }

    public void setTvRight(TextView tvRight) {
        this.tvRight = tvRight;
    }

    public ImageView getIconLeft() {
        return iconLeft;
    }

    public void setIconLeft(ImageView iconLeft) {
        this.iconLeft = iconLeft;
    }

//    public View getIconRight() {
//        return iconRight;
//    }
//
//    public void setIconRight(View iconRight) {
//        this.iconRight = iconRight;
//    }


    public boolean isNeedStatusBarHeight() {
        return needStatusBarHeight;
    }

    public void setNeedStatusBarHeight(boolean needStatusBarHeight) {
        this.needStatusBarHeight = needStatusBarHeight;
        setTitlebarHeight();
    }

    public RelativeLayout getLayRight() {
        return layRight;
    }

    public void setLayRight(RelativeLayout layRight) {
        this.layRight = layRight;
    }

    public CommonTitleBar(Context context) {
        this(context, null);
    }

    public CommonTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CommonTitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs) {
        setOrientation(HORIZONTAL);
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.title_bar);

        needStatusBarHeight = mTypedArray.getBoolean(R.styleable.title_bar_needStatusBarHeight, false);

        //左边
        backVisible = mTypedArray.getBoolean(R.styleable.title_bar_left_icon_visible, true);
        leftIcon = mTypedArray.getResourceId(R.styleable.title_bar_left_icon_resource, 0);
        leftText = mTypedArray.getString(R.styleable.title_bar_left_text);

        //中间
        titleValue = mTypedArray.getString(R.styleable.title_bar_title_text);
        titleColor = mTypedArray.getColor(R.styleable.title_bar_title_text_color, getResources().getColor(R.color.black));
        titleRightDrawable = mTypedArray.getResourceId(R.styleable.title_bar_title_drawable_right, 0);

        //右边
        rightIcon = mTypedArray.getResourceId(R.styleable.title_bar_right_icon_resource, 0);
        rightText = mTypedArray.getString(R.styleable.title_bar_right_text);

        //背景
        background_color = mTypedArray.getColor(R.styleable.title_bar_background_color, getResources().getColor(R.color.white));
        statusbar_bg = mTypedArray.getColor(R.styleable.title_bar_statusbar_bg, getResources().getColor(R.color.white));
        mTypedArray.recycle();


        View contentView = inflate(getContext(), R.layout.include_titlebar, this);
        layRoot = contentView.findViewById(R.id.lay_transroot);
        vStatusBar = contentView.findViewById(R.id.v_statusbar);
        tvTitle = (TextView) contentView.findViewById(R.id.tv_actionbar_title);
        tvLeft = (TextView) contentView.findViewById(R.id.tv_actionbar_left);
        tvRight = (TextView) contentView.findViewById(R.id.tv_actionbar_right);
        iconLeft = contentView.findViewById(R.id.iv_actionbar_left);
//        iconRight = contentView.findViewById(R.id.v_actionbar_right);

        setLeftValue();
        setTitleValue();
        setRightValue();
        setTitlebarHeight();
        setBg();
    }

    private void setBg() {
        layRoot.setBackgroundColor(background_color);
        //设置状态栏颜色
//        try {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                Window window = ((Activity)getContext()).getWindow();
//                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//                window.setStatusBarColor(getResources().getColor(background_color));
//                //底部导航栏
//                //window.setNavigationBarColor(activity.getResources().getColor(colorResId));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        initSystemBar(true, background_color);
    }

    public void initSystemBar(Boolean isLight, int background_color) {
        isLight = isLightColor(background_color);
        if (Build.VERSION.SDK_INT >= 23) {
            Window window = ((Activity) getContext()).getWindow();
            //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //设置状态栏颜色
            window.setStatusBarColor(background_color);

//            //状态栏颜色接近于白色，文字图标变成黑色
            View decor = window.getDecorView();
            if (isLight) {
                decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            } else {
                decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            }
        }
    }

    public boolean isLightColor(int color) {
        double darkness = 1 - (0.299 * Color.red(color) + 0.587 * Color.green(color) + 0.114 * Color.blue(color)) / 255;
        if (darkness < 0.5) {
            return true; // It's a light color
        } else {
            return false; // It's a dark color
        }
    }

    public void setStatusUI(boolean isLight) {

//        Window window = ((Activity) getContext()).getWindow();
//        //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
//        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
////            //状态栏颜色接近于白色，文字图标变成黑色
//        View decor = window.getDecorView();
//        int ui = decor.getSystemUiVisibility();
////        if (isLight) {
////            //light --> a|=b的意思就是把a和b按位或然后赋值给a,   按位或的意思就是先把a和b都换成2进制，然后用或操作，相当于a=a|b
////            ui |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
////        } else {
//            //dark  --> &是位运算里面，与运算,  a&=b相当于 a = a&b,  ~非运算符
//            ui &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
////        }
//        decor.setSystemUiVisibility(ui);
    }

    private void setTitlebarHeight() {
        setStatusBarHeight(needStatusBarHeight ? SizeUtils.getStatusBarHeight() : 0);
    }

    private void setRightValue() {
//        if (rightIcon == 0) {
//            iconRight.setVisibility(View.INVISIBLE);
//        } else {
//            iconRight.setBackgroundResource(leftIcon);
//            iconRight.setVisibility(View.VISIBLE);
//        }

//        if (rightText == null || "".equalsIgnoreCase(rightText)) {
//            tvRight.setVisibility(View.INVISIBLE);
//        } else {
//            tvRight.setVisibility(View.VISIBLE);
        tvRight.setText(rightText);
//        }

        if (rightIcon != 0) tvRight.setCompoundDrawablesWithIntrinsicBounds(0, 0, rightIcon, 0);
    }

    private void setTitleValue() {
//        if (titleValue == null || "".equalsIgnoreCase(titleValue)) {
//            tvTitle.setVisibility(View.INVISIBLE);
//        } else {
        tvTitle.setVisibility(View.VISIBLE);
        tvTitle.setText(titleValue);
//        }
        tvTitle.setTextColor(titleColor);
        if (titleRightDrawable != 0) {
            tvTitle.setCompoundDrawablesWithIntrinsicBounds(0, 0, titleRightDrawable, 0);
            tvTitle.setCompoundDrawablePadding(6);
        }
    }

    private void setLeftValue() {
        if (leftIcon == 0) {
            iconLeft.setVisibility(View.VISIBLE);
            iconLeft.setImageResource(R.drawable.ic_back);
        } else if (leftIcon == -1) {
            iconLeft.setVisibility(View.GONE);
        } else {
            ((ImageView) iconLeft).setImageResource(leftIcon);
            iconLeft.setVisibility(View.VISIBLE);
        }
        if (leftText == null || "".equalsIgnoreCase(leftText)) {
            tvLeft.setVisibility(View.INVISIBLE);
        } else {
            tvLeft.setVisibility(View.VISIBLE);
            tvLeft.setText(leftText);
        }
        iconLeft.setOnClickListener(view -> {
            try {
                ((Activity) getContext()).finish();
            } catch (Exception e) {

            }
        });
        if (backVisible) {
            iconLeft.setVisibility(View.VISIBLE);
        } else {
            iconLeft.setVisibility(View.GONE);
        }
    }

    public void setTitleDrawableRight(int res) {
        if (res != 0) {
            tvTitle.setCompoundDrawablesWithIntrinsicBounds(0, 0, res, 0);
            tvTitle.setCompoundDrawablePadding(6);
        }
    }

    public void setSearchBarVisible(boolean isVisible) {
    }

    public void setLeftIcon(boolean isVisible, int res) {
        iconLeft.setVisibility(isVisible ? View.VISIBLE : View.GONE);
        if (isVisible) {
            iconLeft.setBackgroundResource(res);
        }
    }

    public void setRight(boolean isVisible) {
//        iconRight.setVisibility(isVisible ? View.VISIBLE : View.GONE);
//        if (isVisible) {
//            iconLeft.setBackgroundResource(res);
//        }
    }

    public void setBarBackgroundColor(int color) {
        layRoot.setBackgroundColor(color);
        initSystemBar(false, (color));
    }

    /**
     * 设置状态栏高度
     *
     * @param statusBarHeight
     */
    public void setStatusBarHeight(int statusBarHeight) {
        ViewGroup.LayoutParams params = vStatusBar.getLayoutParams();
        params.height = statusBarHeight;
        vStatusBar.setLayoutParams(params);
    }

    /**
     * 设置是否需要渐变
     */
    public void setNeedTranslucent() {
        setNeedTranslucent(true, false);
    }

    /**
     * 设置是否需要渐变,并且隐藏标题
     *
     * @param translucent
     */
    public void setNeedTranslucent(boolean translucent, boolean titleInitVisibile) {
        if (translucent) {
            layRoot.setBackgroundDrawable(null);
        }
        if (!titleInitVisibile) {
            tvTitle.setVisibility(View.GONE);
        }
    }

    /**
     * 设置标题
     *
     * @param strTitle
     */
    public void setTitle(String strTitle) {
//        if (!TextUtils.isEmpty(strTitle)) {
        tvTitle.setText(strTitle);
//        } else {
//            tvTitle.setVisibility(View.GONE);
//        }
    }

    /**
     * 设置标题
     *
     * @param strTitle
     */
    public void setLeftTitle(String strTitle) {
//        if (!TextUtils.isEmpty(strTitle)) {
        tvLeft.setText(strTitle);
//        } else {
//            tvLeft.setVisibility(View.GONE);
//        }
    }

    /**
     * 设置数据
     *
     * @param strTitle
     * @param resIdLeft
     * @param strLeft
     * @param resIdRight
     * @param strRight
     * @param listener
     */
    public void setData(String strTitle, int resIdLeft, String strLeft, int resIdRight, String strRight, final OnClickListener listener) {
//        if (!TextUtils.isEmpty(strTitle)) {
//            tvTitle.setText(strTitle);
//        } else {
//            tvTitle.setVisibility(View.GONE);
//        }
//        if (!TextUtils.isEmpty(strLeft)) {
//            tvLeft.setText(strLeft);
//            tvLeft.setVisibility(View.VISIBLE);
//        } else {
//            tvLeft.setVisibility(View.GONE);
//        }
//        if (!TextUtils.isEmpty(strRight)) {
//            tvRight.setText(strRight);
//            tvRight.setVisibility(View.VISIBLE);
//        } else {
//            tvRight.setVisibility(View.GONE);
//        }
//
////        if (resIdLeft == 0) {
////            iconLeft.setVisibility(View.GONE);
////        } else {
////            iconLeft.setBackgroundResource(resIdLeft);
////            iconLeft.setVisibility(View.VISIBLE);
////        }
//
//        if (resIdRight == 0) {
//            iconRight.setVisibility(View.GONE);
//        } else {
//            iconRight.setBackgroundResource(resIdRight);
//            iconRight.setVisibility(View.VISIBLE);
//        }

        if (listener != null) {
            layLeft = findViewById(R.id.lay_actionbar_left);
            layRight = findViewById(R.id.lay_actionbar_right);
            layLeft.setOnClickListener(v -> listener.onClick(layLeft));
            layRight.setOnClickListener(v -> listener.onClick(layRight));
        }
    }

}
