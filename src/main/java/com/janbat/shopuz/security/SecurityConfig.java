/**
 * @file SecurityConfig.java
 * @brief Klasa SecurityConfig konfigurująca zabezpieczenia aplikacji.
 *
 * Ta klasa zawiera konfigurację zabezpieczeń aplikacji, w tym zarządzanie autoryzacją, uwierzytelnianiem i szyfrowaniem haseł.
 */

package com.janbat.shopuz.security;

import com.janbat.shopuz.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @brief Klasa SecurityConfig konfigurująca zabezpieczenia aplikacji.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * @brief Konfiguruje łańcuch filtrów zabezpieczeń.
     *
     * @param http Obiekt HttpSecurity do konfiguracji zabezpieczeń.
     * @return Skonfigurowany łańcuch filtrów zabezpieczeń.
     * @throws Exception W przypadku błędów konfiguracji.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/register","/login", "/home","/product/**","/add-product","/logout","/add-opinion/**", "/edit-product/**","/cart","/cart/**","/code","/code/**","discount-codes").permitAll()
                        .requestMatchers("/static/**", "/css/**", "/js/**", "/images/**", "/rate-product/**").permitAll()
                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/rate-product/**, /product/**")
                )
                .exceptionHandling(exception -> exception
                        .defaultAuthenticationEntryPointFor(
                                (request, response, authException) -> response.sendRedirect("/home"),
                                new AntPathRequestMatcher("/**")
                        )
                );
                /*.formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/home", true)
                        .failureUrl("/login?error=true")
                        .permitAll()
                )*/
                /*.logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout=true")
                        .permitAll()
                );*/
        return http.build();
    }

    /**
     * @brief Tworzy bean CustomUserDetailsService.
     * @return Obiekt CustomUserDetailsService.
     */
    @Bean
    public CustomUserDetailsService customUserDetailsService() {
        return new CustomUserDetailsService();
    }

    /**
     * @brief Tworzy bean BCryptPasswordEncoder.
     * @return Obiekt BCryptPasswordEncoder.
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
