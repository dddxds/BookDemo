//package com.ggy.gateway.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///***
// *  @title: CrosConfig
// *  @author: GaoGuiYun
// *  @version: 1.0.0
// *  @create: 2023-04-13 8:18
// ***/
//@Configuration
//
//public class CrosConfig implements WebMvcConfigurer {
//
//
//
//
//
//    @Bean
//
//    public CorsFilter corsFilter() {
//
//        CorsConfiguration config = new CorsConfiguration();
//
//        config.addAllowedOrigin("*");
//
//        config.addAllowedMethod("*");
//
//        config.addAllowedHeader("*");
//
//        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
//
//        configSource.registerCorsConfiguration("/**", config);
//
//        return new CorsFilter(configSource);
//
//    }
//
//}