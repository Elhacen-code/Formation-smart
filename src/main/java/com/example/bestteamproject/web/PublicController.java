package com.example.bestteamproject.web;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.bestteamproject.dao.AppUserRepository;
import com.example.bestteamproject.dto.AuthRequest;
import com.example.bestteamproject.dto.AuthResponse;
import com.example.bestteamproject.dto.RegisterModel;
import com.example.bestteamproject.entity.AppUser;
import com.example.bestteamproject.security.JwtService;
import com.example.bestteamproject.service.UserServices;
import com.example.bestteamproject.utils.Messages;
import com.example.bestteamproject.web.ControllerApi.PublicControllerApi;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@CrossOrigin("*")
public class PublicController implements PublicControllerApi {

    @Autowired
    private UserServices userService;

    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    // REGISTER
    @Override
    public ResponseEntity<?> register(@RequestBody RegisterModel registerModel) {
        // Check if email, username, and password are not null or empty
        if (registerModel.getEmail() == null || registerModel.getEmail().isEmpty() ||
                registerModel.getUsername() == null || registerModel.getUsername().isEmpty() ||
                registerModel.getPassword() == null || registerModel.getPassword().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(Messages.REGISTER_REQUIREMENTS);
        }

        // Check if password is greater than 4 characters
        if (registerModel.getPassword().length() < 4) {
            return ResponseEntity.badRequest()
                    .body(Messages.SHORT_PASSWORD);
        }

        // Check if email is in valid syntax
        if (!isValidMail(registerModel.getEmail())) {
            return ResponseEntity.badRequest().body(Messages.EMAIL_SYNTAX_ERR);
        }

        // Check if username or email already exists
        Optional<AppUser> existingUser = appUserRepository.findByUsername(registerModel.getUsername());
        if (existingUser.isPresent() || appUserRepository.findByEmail(registerModel.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Messages.CONFLICT);
        }

        AppUser userInfo = AppUser.builder().username(registerModel.getUsername()).email(registerModel.getEmail())
                .phoneNumber(registerModel.getPhoneNumber()).nni(registerModel.getNni())
                .password(registerModel.getPassword()).build();
        // If all checks pass, add the new user
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(userInfo));
    }

    // LOGIN
    @Override
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        if (authentication.isAuthenticated()) {
            String jwt = jwtService.generateToken(authRequest.getUsername());
            AppUser userInfo = appUserRepository.findByUsername(authRequest.getUsername()).get();
            try {

                    return ResponseEntity.status(HttpStatus.OK).body(new AuthResponse(jwt,
                            AppUser.builder().username(userInfo.getUsername()).address(userInfo.getAddress())
                                    .createdAt(userInfo.getCreatedAt()).updatedAt(userInfo.getUpdatedAt())
                                    .email(userInfo.getEmail())
                                    .nni(userInfo.getNni())
                                    .phoneNumber(userInfo.getPhoneNumber()).build()));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.LOCKED).body(Messages.VERIFICATION_MAIL_REQUIRED);
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    public Boolean isValidMail(String email) {
        String regex = "^([a-zA-Z0-9._%+-]+)@([a-zA-Z0-9.-]+)\\.([a-zA-Z]{2,})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}