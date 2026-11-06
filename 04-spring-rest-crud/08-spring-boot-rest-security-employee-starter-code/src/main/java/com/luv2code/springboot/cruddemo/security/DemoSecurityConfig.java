package com.luv2code.springboot.cruddemo.security;

import org.hibernate.boot.jaxb.internal.stax.ConfigurationEventReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
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

 /*   @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails john = User.builder()
                .username("john")
                .password("{noop}text123")
                .roles("EMPLOYEE")
                .build();

        UserDetails mary = User.builder()
                .username("mary")
                .password("{noop}text345")
                .roles("EMPLOYEE","MANAGER")
                .build();

        UserDetails amanda = User.builder()
                .username("amanda")
                .password("{noop}text234")
                .roles("EMPLOYEE","MANAGER","ADMIN")
                .build();
        return new InMemoryUserDetailsManager(john,mary,amanda);

        //Since we defined our users here, Spring boot will not use username and passowrd
        //from application.props file
        //Test this code in postman once

    }
*/

    //Add support for jdbc...no hard code is required
    //adding spring security for database
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){

        //This data source is configured by spring boot
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        //Define query to retrieve user by username i.e, How to find Users
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select user_id,pw,active from members where user_id = ?"
        );
        //define query to retrieve authorities or roles by username i.e, how to find roles
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select user_id, role from roles where user_id = ?"
        );
    return jdbcUserDetailsManager;
        //jdbcUserDetailsManager tells spring to use JDBC authentication with our data source
    }

    //Code for Roles based Access

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(configurer->
                configurer
                        .requestMatchers(HttpMethod.GET,"/api/employees").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET,"/api/employees/**").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST,"/api/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT,"/api/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PATCH,"/api/employees/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE,"/api/employees/**").hasRole("ADMIN"));

        //use http basic Authentication
        http.httpBasic(Customizer.withDefaults());

        //disable csrf
        http.csrf(csrf-> csrf.disable());

        return http.build();

    }

}
