package com.purexua.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.purexua.config.DBUserDetailsManager;
import com.purexua.entity.User;
import com.purexua.mapper.UserMapper;
import com.purexua.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
  @Resource
  private DBUserDetailsManager dbUserDetailsManager;

  @Override
  public void saveUserDetails(User user) {

    UserDetails userDetails = org.springframework.security.core.userdetails.User
        .withDefaultPasswordEncoder()
        .username(user.getUsername()) //自定义用户名
        .password(user.getPassword()) //自定义密码
        .build();
    dbUserDetailsManager.createUser(userDetails);
  }
}
