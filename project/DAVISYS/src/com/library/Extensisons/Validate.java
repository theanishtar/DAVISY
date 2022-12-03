/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.extensisons;

/**
 *
 * @author dangt
 */
public class Validate {
    public boolean checkMail(String email) {
        String reEmail = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        if (email.matches(reEmail)) {
            return true;
        }
        return false;
    }
}
