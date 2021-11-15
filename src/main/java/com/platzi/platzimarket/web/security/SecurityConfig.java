package com.platzi.platzimarket.web.security;

import com.platzi.platzimarket.domain.service.PlatziUserDetailsService;
import com.platzi.platzimarket.web.security.filter.JwtFilterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private PlatziUserDetailsService platziUserDetailsService;
    private JwtFilterRequest jwtFilterRequest;
    @Autowired
    public SecurityConfig(PlatziUserDetailsService platziUserDetailsService, JwtFilterRequest jwtFilterRequest) {
        this.platziUserDetailsService = platziUserDetailsService;
        this.jwtFilterRequest = jwtFilterRequest;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //super.configure(auth);
        auth.userDetailsService(platziUserDetailsService);
        //Esto es un demo, lo que deberia ocurrir es ir a una base de datos para veriicar el correcto inicio de sesion antes de usar nuestro servicio
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        /*Todas las peticiones sin importar loq ue haya antes ** las permita*/
        http.csrf().disable().authorizeRequests().antMatchers("/**/authenticate").permitAll()
                .anyRequest().authenticated().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtFilterRequest, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Bean//para elegirlo como gestor de autentificacion
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }



//Allow swagger
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui",
                "/swagger-resources/**", "/configuration/security",
                "/swagger-ui.html", "/webjars/**");
    }
}
