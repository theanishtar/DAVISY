package com.utils;

import com.entity.TaiKhoanEntity;

public class Auth {
    public static TaiKhoanEntity user = null;
    public static void clear(){
        Auth.user = null;
    }
    
    public static boolean isLogin(){
        return Auth.user != null;
    }
    
    public static boolean isManager(){
        //return Auth.isLogin() && user.getVaiTro();
        return Auth.isLogin() && user.isTrangThai();
    }
}
