package com.example.bestteamproject.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.bestteamproject.dao.AppUserRepository;
import com.example.bestteamproject.dao.RoleRepository;
import com.example.bestteamproject.entity.AppUser;
import com.example.bestteamproject.entity.Role;
import com.example.bestteamproject.service.UserServices;
import java.util.List;

@Service
public class UserServiceImpl implements UserServices {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public ResponseEntity<?> addUser(AppUser userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        Role roleUse = roleRepository.findById(2).get();
        userInfo.setRole(roleUse);
        appUserRepository.save(userInfo);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public AppUser fidUserByUsername(String username) {
        return appUserRepository.findByUsername(username).get();
    }

    @Override
    public void DeleteUser(Integer id) {
        appUserRepository.deleteById(id);
    }

    @Override
    public boolean findUserById(Integer id) {
        if (appUserRepository.findById(id).isPresent()) {
            return true;
        }
        return false;
    }

    @Override
    public List<AppUser> getUsers() {
        return appUserRepository.findAll();
    }

    public AppUser getUserById(Integer id) {
        if (appUserRepository.findById(id).isPresent()) {
            return appUserRepository.findById(id).get();
        }
        return null;
    }
}