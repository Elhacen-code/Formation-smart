package com.example.bestteamproject.service;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.example.bestteamproject.entity.AppUser;

public interface UserServices {

    public ResponseEntity<?> addUser(AppUser userInfo);

    public AppUser fidUserByUsername(String username);

    public void DeleteUser(Integer id);

    public boolean findUserById(Integer id);

    public List<AppUser> getUsers();
}