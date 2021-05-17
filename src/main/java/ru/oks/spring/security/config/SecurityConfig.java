package ru.oks.spring.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.oks.spring.security.jwt.JwtFilter;

/**
 * Конфиг для аутентификации и авторизации.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtFilter jwtFilter;

    /**
     * Конфигурация. Добавление страниц с различным доступом.
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()//отключение базовой аутентификации
                .csrf().disable() //отключение (защиты) от межсайтовой подделки запроса
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)//достали данные из токена и получили пользователя
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//не хранится сессия пользователя, т.к. есть токен
                .and()
                .authorizeRequests()
                .antMatchers(
                        "/simple_values",
                        "/gettime",
                        "/hello",
                        "/user/reg",
                        "/user/auth",
                        "/url/**",
                        "/files/**",
                        "/filesdb/**").permitAll()
                .antMatchers("note/**").authenticated()
                .antMatchers("/user/{login}/**").access("#login==authentication.name")
                .anyRequest().authenticated();
    }

    //для генерации хеша
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
