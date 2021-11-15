package com.platzi.platzimarket.domain.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

//Esto es un demo, lo que deberia ocurrir es ir a una base de datos para veriicar el correcto inicio de sesion antes de usar nuestro servicio
@Service
public class PlatziUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //Como la contraseña no está codificada le agragamos {noop}
        return new User("admin","{noop}adminjsejs3",new ArrayList<>());//el ArrayList son los tipos de roles que va a tener nuestro usuario
    }
}
