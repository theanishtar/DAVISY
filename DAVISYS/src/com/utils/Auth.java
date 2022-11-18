package com.utils;

import com.entity.TaIKhoanEntity;

public class Auth {
    public static TaIKhoanEntity user = null;
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
