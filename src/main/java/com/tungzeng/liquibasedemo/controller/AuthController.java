package com.tungzeng.liquibasedemo.controller;

import com.tungzeng.liquibasedemo.dto.JWTAuthDto;
import com.tungzeng.liquibasedemo.dto.LoginDto;
import com.tungzeng.liquibasedemo.dto.RegisterDto;
import com.tungzeng.liquibasedemo.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {

        String token = authService.login(loginDto);

        JWTAuthDto jwtAuthResponse = new JWTAuthDto();
        jwtAuthResponse.setAccessToken(token);

        return ResponseEntity.ok(jwtAuthResponse);

    }

    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto) {

        int response = authService.register(registerDto);
        if(response == 3) {
            return new ResponseEntity<>("Username already exist!", HttpStatus.BAD_REQUEST);
        }
        else if(response == 2){
            return new ResponseEntity<>("Email already exist!", HttpStatus.BAD_REQUEST);
        }
        else{
            return new ResponseEntity<>("User registered successfully!", HttpStatus.CREATED);
        }

    }

}
