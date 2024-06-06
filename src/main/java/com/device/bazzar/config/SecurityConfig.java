package com.device.bazzar.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;

@Configuration
  public class SecurityConfig {
//    @Autowired
//    UserDetailsService userDetailsService;
//
//    @Bean
//    DaoAuthenticationProvider daoAuthenticationProvider(){
//        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
//        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
//        return daoAuthenticationProvider;
//    }
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//        http.
//                csrf().disable()
//                .cors().disable()
//                .authorizeHttpRequests().anyRequest().authenticated().and().httpBasic();
//
//
//    }

//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails normal =  User.builder()
//                .username("Abhi")
//                .password(passwordEncoder().encode("12345"))
//                .roles("NORMAL")
//                .build();
//        UserDetails admin = User.builder()
//                .username("Avi")
//                .password(passwordEncoder().encode("00000"))
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(normal, admin);
//
//    }
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//      }

}
