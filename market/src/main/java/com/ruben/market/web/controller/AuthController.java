package com.ruben.market.web.controller;

import com.ruben.market.domain.dto.AuthenticationRequest;
import com.ruben.market.domain.dto.AuthenticationResponse;
import com.ruben.market.domain.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailService userDetailService;

    @PostMapping("/authenticate")
    //Este metodo se encarga de reponder un json webn token JWT
    public ResponseEntity<AuthenticationResponse> createToken(@RequestBody AuthenticationRequest request){

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails userDetails = userDetailService.loadUserByUsername(request.getUsername());

        return
    }
}
