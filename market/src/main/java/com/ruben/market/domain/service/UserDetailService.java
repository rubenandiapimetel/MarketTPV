package com.ruben.market.domain.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


    /* Aqui creamos mediante la funcion loaduserbyname una credencial que luego
    usaremos en la clase Securityconfig.
     */
@Service
public class UserDetailService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User("ruben", "{noop}0000", new ArrayList<>());
    }
}
