package org.verygroup.sociallogin.config;

import org.verygroup.sociallogin.services.SimpleSocialUsersDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .jdbcAuthentication()
            .dataSource(dataSource)
            .withDefaultSchema();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .formLogin()
                .loginPage("/index#modal")
//                .loginProcessingUrl("/login/authenticate")
//                .failureUrl("/login?param.error=bad_credentials")
        .and()
            .logout()
                .logoutUrl("/exit") // - куда отправлять POST-запрос для выхода
                .logoutSuccessUrl("/index")
                .deleteCookies("JSESSIONID")
        .and()
            .authorizeRequests()
                .antMatchers("/author-pa").authenticated()
                .antMatchers("/**").permitAll()
        .and()
            .rememberMe()
        .and()
            .apply(new SpringSocialConfigurer()
                    .postLoginUrl("/author-pa")
                    .alwaysUsePostLoginUrl(true)
            );
    }

    @Bean
    public SocialUserDetailsService socialUsersDetailService() {
        return new SimpleSocialUsersDetailService(userDetailsService());
    }

}