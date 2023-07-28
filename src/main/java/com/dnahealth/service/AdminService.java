package com.dnahealth.service;

import com.dnahealth.entity.AdminEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface AdminService {
    void register(AdminEntity admin, String siteURL);
    boolean verify(String verificationCode);
    void updateResetPasswordToken(String token, String email) throws UsernameNotFoundException;
    AdminEntity getByResetPasswordToken(String token);
    void updatePassword(AdminEntity admin, String newPassword);
    void getNewPasswordWhenForgetOldPassword(String email);
    void sendEmailVerify(AdminEntity admin, String siteURL);
    void sendEmailResetPass(String recipientEmail, String link) throws MessagingException, UnsupportedEncodingException;
}
