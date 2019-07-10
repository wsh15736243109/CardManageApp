package com.itboye.cardmanage.config;

/**
 * @Author:Create by Mr.w
 * @Date:2019/5/24 22:28
 * @Description:
 */
public class Global {
    public static final String NORMAL_PROBLEM = "/#/faq";//常见问题web
    public static final String CONTACT_CUSTOMER = "/#/contact";//联系客服
    public static final String ABOUT_US = "#/aboutus";//关于我们
    public static final String DOWNLOAD_APP = "#/download";//更新APP
    public static final String UPDATE_VIP = "";//升级VIP
    public static final String OPERATE_HELP = "#/operationProcess";//操作流程
    public static String HEAD = "http://";
    //    public static String HOST = "api.361fit.cn";//测试环境
    public static String HOST = "api.byg577.com";//正式环境
    //    public static String WEB_HOST = "admin.361fit.cn";
    public static String WEB_HOST = "admin.byg577.com";

    public static String COUNTRY_NO = "86";
    public static String BASEURL = HEAD + HOST + "/";
    public static String BASE_WEB_URL = HEAD + WEB_HOST + "/";
    public static String client_secret = "df45c46ca6df63e7d5b38bfb7d61b5fc";
    public static String client_id = "by04esfI0fYuD5";
    public static String IMAGEURL = HEAD + HOST;
    public static String WEBURL = BASEURL + "web/";
    public static String H5URL = BASE_WEB_URL + "h5/";
//    public static String client_id = "04esfI0fYuD5";
}
