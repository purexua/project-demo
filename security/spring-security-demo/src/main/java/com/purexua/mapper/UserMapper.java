package com.purexua.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.purexua.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
