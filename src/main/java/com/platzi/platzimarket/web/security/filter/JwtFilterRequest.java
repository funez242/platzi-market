package com.platzi.platzimarket.web.security.filter;

import com.platzi.platzimarket.domain.service.PlatziUserDetailsService;
import com.platzi.platzimarket.web.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//Extendemos de OncePerRequestFilter para que este filtro se ejecute cada que haya una peticion
@Component
public class JwtFilterRequest extends OncePerRequestFilter {

    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private PlatziUserDetailsService platziUserDetailsService;

    //Aqui verificamos si lo que viene en el encabezado de la peticion es un JWT
    // y si ese token es correcto
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        String headerNames = request.getHeaderNames().toString();

        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer")){
            //Con substrong quitamos el bearer_, desde la osicion 7 es que viene el JWT
            String jwt =authorizationHeader.substring(7);
            String username = jwtUtil.extractUsername(jwt);

                    //Verificamos que en el contexto no exista ninguna autorizacion para este usuario
            if ( username != null && SecurityContextHolder.getContext().getAuthentication() == null ){
                //Verificamos si el usuario que llego en el jwt existe dentro de nuestro sistema de autentificacion
                UserDetails userDetails = platziUserDetailsService.loadUserByUsername(username);

                //Validamos si el jwt es correcto
                if (jwtUtil.validateToken(jwt,userDetails)){
                    UsernamePasswordAuthenticationToken authToken= new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    //Le ponemos al authToken los detalles de la conexion que estamos recibiendo
                    //hacemos u build details con el request con el fin de que podamos evaluar que mavegador se estaba usando,
                    // a que horario se conecto, que sistema oiperativo tenia, etc.
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }
        filterChain.doFilter(request,response);
    }
}
