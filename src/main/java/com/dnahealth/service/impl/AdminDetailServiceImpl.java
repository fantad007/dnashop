package com.dnahealth.service.impl;

import com.dnahealth.entity.AdminEntity;
import com.dnahealth.repository.AdminRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AdminDetailServiceImpl implements UserDetailsService {
    private final AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<AdminEntity> optionalAdmin = adminRepository.findByEmail(email);
        if (optionalAdmin.isEmpty()) {
            throw new UsernameNotFoundException("Không tìm thấy tài khoản!");
        }
        AdminEntity admin = optionalAdmin.get();
        String roleName = adminRepository.getRoleNameById(admin.getId());
        List<GrantedAuthority> grantedList = new ArrayList<>();
        if (roleName != null) {
            GrantedAuthority authority = new SimpleGrantedAuthority(roleName);
            grantedList.add(authority);
        }
        return new User(admin.getEmail(), admin.getEncryptedPassword(), grantedList);
    }
}
