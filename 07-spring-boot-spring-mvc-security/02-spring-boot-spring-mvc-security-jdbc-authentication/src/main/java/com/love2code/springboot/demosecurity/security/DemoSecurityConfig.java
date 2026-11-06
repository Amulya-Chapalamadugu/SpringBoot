package com.love2code.springboot.demosecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {

   /* //Add support for JDBC .... no more hard coded users
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){

        //No longer hard-coding users...spring will use directly from database

        return new JdbcUserDetailsManager(dataSource);
    }
*/
    //add support for jdbc... for custom tables
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        //define query to retrieve a user by username
        // and the user_id is passed in by the login form
        jdbcUserDetailsManager.setUsersByUsernameQuery("select user_id, pw, active from members where user_id=?");

        //define query to retrieve the authorities/roles by username
        // and the user_id is passed in by the login form
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select user-id, role from roles where user_id=?");

        return jdbcUserDetailsManager;
    }



    //SpringSecurity config to reference custom login form
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http.authorizeHttpRequests(configurer->
                        configurer
                                .requestMatchers("/").hasRole("EMPLOYEE")
                                .requestMatchers("/leaders/**").hasRole("MANAGER")
                                .requestMatchers("/systems/**").hasRole("ADMIN")
                                .anyRequest().authenticated()
                        )
                .formLogin(form->
                        form.loginPage("/showMyLoginPage")
                            .loginProcessingUrl("/authenticateTheUser")
                            //login form should POST data to this url for processing(check user id and password)
                            .permitAll()
                        )
                .logout(logout-> logout.permitAll())
                .exceptionHandling(configurer->
                        configurer
                                .accessDeniedPage("/access-denied"));

        return http.build();
    }

     /* @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails john= User.builder()
                .username("john")
                .password("{noop}test123")
                .roles("EMPLOYEE")
                .build();
        UserDetails mary= User.builder()
                .username("mary")
                .password("{noop}test123")
                .roles("EMPLOYEE","MANAGER")
                .build();
        UserDetails susan= User.builder()
                .username("susan")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER","ADMIN")
                .build();
        return new InMemoryUserDetailsManager(john,mary,susan);


    }
*/
}
