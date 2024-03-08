package com.purexua.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.purexua.entity.User;
import com.purexua.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class DBUserDetailsManager implements UserDetailsManager, UserDetailsPasswordService {

  @Resource
  private UserMapper userMapper;

  @Override
  public UserDetails updatePassword(UserDetails user, String newPassword) {
    return null;
  }

  @Override
  public void createUser(UserDetails user) {
    User user1 = new User();
    user1.setUsername(user.getUsername());
    user1.setPassword(user.getPassword());
    user1.setEnabled(true);
    userMapper.insert(user1);
  }

  @Override
  public void updateUser(UserDetails user) {

  }

  @Override
  public void deleteUser(String username) {

  }

  @Override
  public void changePassword(String oldPassword, String newPassword) {

  }

  @Override
  public boolean userExists(String username) {
    return false;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("username", username);
    User user = userMapper.selectOne(queryWrapper);
    if (user == null) {
      throw new UsernameNotFoundException(username);
    } else {
//      Collection<GrantedAuthority> authorities = new ArrayList<>();
//      authorities.add((GrantedAuthority) () -> "ROLE_USER");
//      authorities.add((GrantedAuthority) () -> "ROLE_ADMIN");
//      return new org.springframework.security.core.userdetails.User(
//          user.getUsername(),
//          user.getPassword(),
//          user.getEnabled(),
//          true, //用户账号是否过期
//          true, //用户凭证是否过期
//          true, //用户是否未被锁定
//          authorities); //权限列表
      return org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
          .password(user.getPassword())
          .disabled(!user.getEnabled())
          .credentialsExpired(false)
          .accountLocked(false)
          .roles("USER", "ADMIN")
          .build();
    }

  }
}
