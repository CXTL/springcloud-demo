//package com.dupake.system.security.sms;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.DefaultSecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.stereotype.Component;
//
///**
// * @ClassName SmsCodeAuthenticationSecurityConfig
// * @Description 短信验证的权限核心配置
// * @Author dupake
// * @Date 2020/5/22 16:32
// */
//@Component
//public class SmsCodeAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
//    @Autowired
//    private UserDetailsService userDetailsService;
//    @Autowired
//    private MyAuthenticationSuccessHandler customAuthenticationSuccessHandler;
//    @Autowired
//    private MyAuthenticationFailHandler customAuthenticationFailureHandler;
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        SmsCodeAuthenticationFilter smsCodeAuthenticationFilter = new SmsCodeAuthenticationFilter();
//        smsCodeAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
//        smsCodeAuthenticationFilter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);
//        smsCodeAuthenticationFilter.setAuthenticationFailureHandler(customAuthenticationFailureHandler);
//
//        SmsCodeAuthenticationProvider smsCodeAuthenticationProvider = new SmsCodeAuthenticationProvider();
//        smsCodeAuthenticationProvider.setUserDetailsService(userDetailsService);
//
//        http.authenticationProvider(smsCodeAuthenticationProvider)
//                .addFilterAfter(smsCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//    }
//}