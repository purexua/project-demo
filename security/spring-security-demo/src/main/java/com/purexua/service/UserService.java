package com.purexua.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.purexua.entity.User;


public interface UserService extends IService<User> {
  void saveUserDetails(User user);
}
