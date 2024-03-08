package com.purexua.config;

import com.alibaba.fastjson2.JSON;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.HashMap;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableMethodSecurity
//@EnableWebSecurity//Spring项目总需要添加此注解，SpringBoot项目中不需要
public class WebSecurityConfig {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    //authorizeRequests()：开启授权保护
    //anyRequest()：对所有请求开启授权保护
    //authenticated()：已认证请求会自动被授权
    http.authorizeRequests(authorize -> authorize
//        .requestMatchers("/user/add").hasAnyAuthority("USER_ADD")
//        .requestMatchers("/user/list").hasAnyAuthority("USER_LIST")
        .requestMatchers("/user/add").hasRole("USER_ADD")
        .anyRequest().authenticated());

    http.formLogin(form -> {
      form
          .loginPage("/login")
          .permitAll()
          .successHandler(new MyAuthenticationSuccessHandler())
          .failureHandler(new MyAuthenticationFailureHandler());//自定义登录页面 无需授权
      //.usernameParameter("myusername");  //自定义用户名参数
      //.passwordParameter("mypassword");  //自定义密码参数
      //.failureUrl("/login?error");  //自定义登录失败页面
    });//表单授权方式
    //.httpBasic(withDefaults());//基本授权方式
    http.logout(logout -> {
      logout
          .logoutSuccessHandler(new MyLogoutSuccessHandler());//自定义注销成功处理器
      //.logoutSuccessUrl("/login");  //自定义注销成功页面
    });//注销授权方式

    http.exceptionHandling(exception -> {
      exception.authenticationEntryPoint(new MyAuthenticationEntryPoint());//请求未认证的接口
      exception.accessDeniedHandler(new MyAccessDeniedHandler());//请求未授权的接口
    });

    //跨域
    http.cors(withDefaults());//开启跨域

    //会话管理
    http.sessionManagement(session -> {
      session
          .maximumSessions(1)
          .expiredSessionStrategy(new MySessionInformationExpiredStrategy());
    });

    http.csrf(AbstractHttpConfigurer::disable);//关闭csrf

    return http.build();
  }

//  @Bean
//  public UserDetailsService userDetailsService() {
//    InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//    manager.createUser( //此行设置断点可以查看创建的user对象
//        User
//            .withDefaultPasswordEncoder()
//            .username("admin") //自定义用户名
//            .password("123456") //自定义密码
//            .roles("USER") //自定义角色
//            .build()
//    );
//    return manager;
//  }

//  @Bean
//  public UserDetailsService userDetailsService() {
//    return new DBUserDetailsManager();
//  }
}
