import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pojo.User;
import java.io.InputStream;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MybatisDemo {

    @Autowired
    private  UserMapper userMapper;

    @Test
    public void fun(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", "xnn");
        List<User> users =userMapper.selectList(queryWrapper);
//        System.out.println(users);
    }
}


