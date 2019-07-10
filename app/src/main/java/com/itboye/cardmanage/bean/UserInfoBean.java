package com.itboye.cardmanage.bean;

import java.io.Serializable;

/**
 * @Author:Create by Mr.w
 * @Date:2019/5/24 22:26
 * @Description:
 */
public class UserInfoBean implements Serializable {

    /**
     * grade_id :
     * id_validate : 0
     * nickname : 手机用户1559802696
     * avatar :
     * idcode : 5kZYe
     * invite_uid : 0
     * id : 27
     * username : m8615736243111
     * mobile : 15736243111
     * country_no : 86
     * last_login_time : 1559802696
     * last_login_ip : 0
     * mobile_auth : 1
     * sid :
     */

    private String grade_id;
    private int id_validate;
    private String nickname;
    private String name = "";
    private String id_no = "";
    private String avatar;
    private String idcode;
    private int invite_uid;
    private int id;
    private String username;
    private String mobile;
    private String country_no;
    private long last_login_time;
    private long last_login_ip;
    private int mobile_auth;
    private String sid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId_no() {
        return id_no;
    }

    public void setId_no(String id_no) {
        this.id_no = id_no;
    }

    public String getGrade_id() {
        return grade_id;
    }

    public void setGrade_id(String grade_id) {
        this.grade_id = grade_id;
    }

    public int getId_validate() {
        return id_validate;
    }

    public void setId_validate(int id_validate) {
        this.id_validate = id_validate;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar.isEmpty() ? "xxx" : avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getIdcode() {
        return idcode;
    }

    public void setIdcode(String idcode) {
        this.idcode = idcode;
    }

    public int getInvite_uid() {
        return invite_uid;
    }

    public void setInvite_uid(int invite_uid) {
        this.invite_uid = invite_uid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCountry_no() {
        return country_no;
    }

    public void setCountry_no(String country_no) {
        this.country_no = country_no;
    }

    public long getLast_login_time() {
        return last_login_time;
    }

    public void setLast_login_time(long last_login_time) {
        this.last_login_time = last_login_time;
    }

    public long getLast_login_ip() {
        return last_login_ip;
    }

    public void setLast_login_ip(long last_login_ip) {
        this.last_login_ip = last_login_ip;
    }

    public int getMobile_auth() {
        return mobile_auth;
    }

    public void setMobile_auth(int mobile_auth) {
        this.mobile_auth = mobile_auth;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
}
