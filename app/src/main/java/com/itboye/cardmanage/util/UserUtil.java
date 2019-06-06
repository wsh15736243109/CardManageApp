package com.itboye.cardmanage.util;

import com.itboye.cardmanage.bean.UserInfoBean;
import com.itboye.cardmanage.config.SpKey;
import me.goldze.mvvmhabit.utils.SPUtils;

import java.io.IOException;
import java.io.StreamCorruptedException;

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
            UserInfoBean user = null;
            try {
                Object obj = SerializableUtil.str2Obj(userInfoJson);
                if (obj != null) {
                    user = (UserInfoBean) obj;
                }

            } catch (StreamCorruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return user;
        }

    }

    /**
     * 保存用户ID
     *
     * @param user 用户
     */
    public static synchronized void saveUser(UserInfoBean user) {
//        System.out.println("Message== " + user.getUser_id());
        String str = "";
        try {
            str = SerializableUtil.obj2Str(user);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        SPUtils.getInstance().put(SpKey.USER_BEAN, str);
    }

    public static void clearUserInfo() {
        SPUtils.getInstance().put(SpKey.USER_BEAN, "");
    }
}
