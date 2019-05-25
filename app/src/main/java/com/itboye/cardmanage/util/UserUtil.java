package com.itboye.cardmanage.util;

import com.itboye.cardmanage.bean.UserInfoBean;
import com.itboye.cardmanage.config.SpKey;
import me.goldze.mvvmhabit.utils.SPUtils;

import static me.goldze.mvvmhabit.utils.StringUtils.isEmpty;


/**
 * @Author:Create by Mr.w
 * @Date:2019/5/24 22:25
 * @Description:
 */
public class UserUtil {
    public static UserInfoBean getUserInfo() {
        String userInfoJson = SPUtils.getInstance().getString(SpKey.USER_BEAN);
        if (isEmpty(userInfoJson)) {
            return null;
        } else {
            //解析UserInfo
            return new UserInfoBean();
        }

    }
}
