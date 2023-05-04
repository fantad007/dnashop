package com.dna.shop.entity;

import com.dna.shop.code.AdminCodes;
import com.dna.shop.entity.base.BaseAuditEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "admin")
public class AdminEntity extends BaseAuditEntity {
    @Column(name = "email")
    private String email;
    @Column(name = "encrypted_password")
    private String encryptedPassword;
    @Column(name = "admin_name")
    private String adminName;
    @Column(name = "verification_code", length = 64)
    private String verificationCode;
    private boolean enabled;
    @Column(name = "reset_password_token", length = 30)
    private String resetPasswordToken;
    @Column(name = "is_delete")
    private Integer isDelete = AdminCodes.DeleteFlag.NORMAL.getCode();
    @Column(name = "role")
    private String role;
}
