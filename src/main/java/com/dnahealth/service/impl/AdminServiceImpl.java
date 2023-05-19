package com.dnahealth.service.impl;

import com.dnahealth.entity.AdminEntity;
import com.dnahealth.repository.AdminRepository;
import com.dnahealth.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

@Service
@Slf4j
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    String fromEmailAddress;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void register(AdminEntity admin, String siteURL) {
        String randomCode = RandomString.make(64);
        admin.setEncryptedPassword(bCryptPasswordEncoder.encode(admin.getEncryptedPassword()));
        admin.setVerificationCode(randomCode);
        admin.setEnabled(false);
        adminRepository.save(admin);
        sendEmailVerify(admin, siteURL);
    }

    @Override
    public boolean verify(String verificationCode) {
        AdminEntity admin = adminRepository.findByVerificationCode(verificationCode);
        if (admin == null || admin.isEnabled()) {
            return false;
        } else {
            admin.setVerificationCode(verificationCode);
            admin.setEnabled(true);
            admin.setRole("ROLE_ADMIN");
            adminRepository.save(admin);
            return true;
        }
    }

    @Override
    public void updateResetPasswordToken(String token, String email) throws UsernameNotFoundException {
        Optional<AdminEntity> optionalAdmin = adminRepository.findByEmail(email);
        if (optionalAdmin.isPresent()) {
            AdminEntity admin = optionalAdmin.get();
            admin.setResetPasswordToken(token);
            adminRepository.save(admin);
        } else {
            throw new UsernameNotFoundException("Không tìm thấy thông tin admin có email: " + email);
        }
    }

    @Override
    public AdminEntity getByResetPasswordToken(String token) {
        return adminRepository.findByResetPasswordToken(token);
    }

    @Override
    public void updatePassword(AdminEntity admin, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        admin.setEncryptedPassword(encodedPassword);
        admin.setResetPasswordToken(null);
        adminRepository.save(admin);
    }

    @Override
    public void sendEmailVerify(AdminEntity admin, String siteURL) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            String subject = "VUI LÒNG XÁC MINH ĐĂNG KÝ CỦA BẠN";
            String content = "<div>\n" +
                    "        Xin chào <strong>[[name]]</strong>,\n" +
                    "        <br> Cảm ơn bạn đã đăng ký để trở thành Quản Trị Viên hệ thống của DNAHEALTH. " +
                    "        <br> Chúc bạn sẽ có khoảng thời gian làm việc tuyệt vời và hiệu quả\n" +
                    "        <br> Vui lòng nhấp vào liên kết dưới đây để xác minh đăng ký của bạn:\n" +
                    "        <br>\n" +
                    "        <h3><a href=\"[[URL]]\" target=\"_self\">XÁC MINH</a></h3>\n" +
                    "        Xin cảm ơn,\n" +
                    "        <br> -- DNAHEALTH --\n" +
                    "    </div>";
            helper.setFrom(fromEmailAddress);
            helper.setTo(admin.getEmail());
            helper.setSubject(subject);
            String verifyURL = siteURL + "/verify?code=" + admin.getVerificationCode();
            content = content.replace("[[name]]", admin.getAdminName()).replace("[[URL]]", verifyURL);
            helper.setText("<html><body>" + content + "</html></body>", true);
            mailSender.send(message);
            log.info("Gửi Mail Thành Công...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendEmailResetPass(String recipientEmail, String link) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(fromEmailAddress, "Supporter");
        helper.setTo(recipientEmail);
        String subject = "Here's the link to reset your password";
        String content = "<p>Hello,</p>"
                + "<p>You have requested to reset your password.</p>"
                + "<p>Click the link below to change your password:</p>"
                + "<p><a href=\"" + link + "\">Change my password</a></p>"
                + "<br>"
                + "<p>Ignore this email if you do remember your password, "
                + "or you have not made the request.</p>";
        helper.setSubject(subject);
        helper.setText(content, true);
        mailSender.send(message);
    }
}
