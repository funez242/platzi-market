package com.platzi.platzimarket.web.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {
    private static final String KEY = "PL4TZI";


    public String generateToken(UserDetails userDetails){
        return Jwts.builder().setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256,KEY).compact();
    }

    public boolean validateToken(String token, UserDetails userDetails){
        //Validar que el JWT ha sido creado para el usuario que está haciendo la peticion
        //Validar que no haya expirado
        return userDetails.getUsername().equals(extractUsername(token)) && !isTokenExpired(token);

    }
    public String extractUsername(String token){
        return getClaims(token).getSubject();
    }

    public boolean isTokenExpired(String token){
        return getClaims(token).getExpiration().before(new Date());
    }

    ///Para validar si el token está bien firmado
    //y separa el cuerpo del jwt separado por cada uno de los objetos
    private Claims getClaims(String token){
        return Jwts.parser().setSigningKey(KEY)
                .parseClaimsJws(token).getBody();
    }
}
