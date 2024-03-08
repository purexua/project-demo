package com.purexua.controller;

import com.purexua.entity.User;
import com.purexua.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

  @Resource(name = "userServiceImpl")
  public UserService userService;

  @GetMapping("/list")
  public List<User> getList() {
    return userService.list();
  }

  @PostMapping("/add")
  public void add(@RequestBody User user) {
    userService.saveUserDetails(user);
  }


}
