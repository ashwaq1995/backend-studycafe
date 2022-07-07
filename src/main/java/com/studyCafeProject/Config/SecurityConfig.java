package com.studyCafeProject.Config;


import com.studyCafeProject.Service.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import org.codehaus.jettison.json.JSONObject;



@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    private final MyUserDetailsService myUserDetailsService;

    public SecurityConfig(MyUserDetailsService myUserDetailsService) {
        this.myUserDetailsService = myUserDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().configurationSource(corsConfigurationSource()).
                and().authorizeRequests()

                .antMatchers("/api/v1/user/register/**").permitAll()
                .antMatchers("/api/v1/admin", "/api/v1/user/all", "/api/v1/user/find/{userId}", "/api/v1/user/check/user/{id}", "/api/v1/user/edit/{userId}", "/api/v1/user/delete/{userId}").hasAuthority("ADMIN")
                .antMatchers("/api/v1/user").hasAuthority("USER")

                .antMatchers("/api/v1/user/all","/api/v1/cafe/add/cafe", "/api/v1/cafe/update/cafe", "/api/v1/cafe/delete/cafe/{id}").hasAuthority("ADMIN")
                .antMatchers("/api/v1/cafe/{id}", "/api/v1/cafe/name", "/api/v1/cafe/rate").hasAnyAuthority("ADMIN", "USER")

                .antMatchers("/api/v1/booking/all/bookings","/api/v1/booking/add/booking", "/api/v1/booking/check/booking/{id}", "/api/v1/booking/update/booking", "/api/v1/booking//delete/booking/{id}").hasAnyAuthority("ADMIN", "USER")


                .anyRequest().authenticated()
                .and()
                .logout().logoutUrl("/api/v1/user/logout").invalidateHttpSession(true).deleteCookies("JSESSIONID")
                .and()
                .httpBasic().authenticationEntryPoint(entryPoint());

    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
        configuration.setAllowedOrigins(Collections.singletonList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "OPTIONS"));
        configuration.addAllowedMethod(HttpMethod.TRACE);
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public AuthenticationEntryPoint entryPoint() {
        return new BasicAuthenticationEntryPoint() {
            @Override
            public void commence(HttpServletRequest request, HttpServletResponse response,
                                 AuthenticationException authException) throws IOException {
                JSONObject jsonObject = new JSONObject();
                try {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.setContentType("application/json");
                    jsonObject.put("message", authException.getMessage());
                    response.getWriter()
                            .println(jsonObject);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void afterPropertiesSet() {
                setRealmName("Contact-Keeper");
                super.afterPropertiesSet();
            }
        };
    }

}