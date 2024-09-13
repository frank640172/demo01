package com.acwing.Mapper;

import com.acwing.Demo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Mapper
public interface UserMapper {

    List<User> selectAll();
}
