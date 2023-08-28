//package com.newproduct.springboot.config;
//
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .anyRequest()
//                .permitAll()
//                .and()
//                .formLogin()
//                .permitAll()
//                .and()
//                .httpBasic()
//                .and()
//                //支持跨域访问
//                .cors()
//                .configurationSource(corsConfigurationSource())
//                .and()
//                .csrf()
//                .disable();
//    }
//
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowCredentials(true);
//        configuration.setAllowedOrigins(Collections.singletonList("*"));
//        configuration.setAllowedMethods(Collections.singletonList("*"));
//        configuration.setAllowedHeaders(Collections.singletonList("*"));
//        configuration.setMaxAge(Duration.ofHours(1));
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
//
//}
