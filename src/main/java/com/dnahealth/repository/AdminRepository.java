package com.dnahealth.repository;

import com.dnahealth.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity, Long> {
    @Query(value = "select * from admin " +
            "where email = :email and is_delete = 0", nativeQuery = true)
    Optional<AdminEntity> findByEmail(String email);

    @Query(value = "select a.role from admin a where a.id = ?1", nativeQuery = true)
    String getRoleNameById(Long id);

    @Query(value = "select * from admin a where a.verification_code = ?1", nativeQuery = true)
    AdminEntity findByVerificationCode(String code);

    AdminEntity findByResetPasswordToken(String token);
}
