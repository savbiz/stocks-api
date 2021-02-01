package com.savbiz.payconiq.stock.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Value("${user.password}")
  private String userPassword;

  @Value("${admin.password}")
  private String adminPassword;

  private final LoggingAccessDeniedHandler accessDeniedHandler;

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .authorizeRequests()
        .antMatchers(
            "/js/**",
            "/css/**",
            "/img/**",
            "/webjars/**",
            "/h2-console/**").permitAll()
        .antMatchers("/api/stocks/**").hasAnyRole("USER", "ADMIN")
        .anyRequest().authenticated()
        .and()
        .formLogin()
        .loginPage("/login")
        .permitAll()
        .and()
        .logout()
        .invalidateHttpSession(true)
        .clearAuthentication(true)
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .logoutSuccessUrl("/login?logout")
        .permitAll()
        .and()
        .exceptionHandling()
        .accessDeniedHandler(accessDeniedHandler);

    httpSecurity.csrf().disable();
    httpSecurity.headers().frameOptions().disable();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    final String encodedUserPassword = passwordEncoder().encode(userPassword);
    final String encodedAdminPassword = passwordEncoder().encode(adminPassword);

    auth.inMemoryAuthentication()
        .withUser("user").password(encodedUserPassword).roles("USER")
        .and()
        .withUser("admin").password(encodedAdminPassword).roles("ADMIN");
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
