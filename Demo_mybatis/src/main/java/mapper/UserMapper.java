package mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import pojo.User;

import java.util.List;

@Component
public interface UserMapper extends BaseMapper<User> {
         List<User> getAllUsers();
}
