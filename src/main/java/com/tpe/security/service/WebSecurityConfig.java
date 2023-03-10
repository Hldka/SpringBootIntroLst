package com.tpe.security.service;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.dao.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.method.configuration.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.*;

@Configuration//configuration class'im
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)//method seviye securty sagliyor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {// get methodu calissa da update e izin vermiyor
        http.csrf().disable().
                authorizeHttpRequests().
                antMatchers("/","index.html","/css/*","/js/*","/register").permitAll().// bunlari muaf tut tregister eklemem lazim
                and().
                authorizeRequests().antMatchers("/students/**").hasRole("ADMIN").// class seviyesinde admin 'in yapacaklari belirliyorum
                anyRequest().//bunun disindaki bütün requestleri
                authenticated().
                and().
                httpBasic();  // Basic Auth
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);// bu deger ne kadar yüksekse islem uzuyor

    }

    @Bean
    public DaoAuthenticationProvider authProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }
}
