package Redis;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;
import java.util.Map;
import java.util.Set;

/*
redis的使用


 */
public class Redis_Demo_01{

    Jedis jedis = new Jedis("47.97.219.138",6379);


     @Test
     //47.97.129.138
    public void test1(){
         //获取连接
         jedis.auth("195956");

         //操作
        jedis.set("username","zhangsan");
         //断开连接
         jedis.close();
     }

     @Test
     public void test2(){
         jedis.auth("195956");
         jedis.hset("hset","name","xnn");
         jedis.hset("hset","age","18");
         jedis.hset("hset","gender","female");

         Map<String, String> users = jedis.hgetAll("hset");
         Set<String> hset = jedis.hgetAll("hset").keySet();
         for(String key: hset){
             String val = users.get(key);
             System.out.println("key: " + key + "value :" + val);
         }

         jedis.close();
     }

     @Test
    public void test3(){
         jedis.auth("195956");
         jedis.lpush("myList","a","b","c");
         List<String> myList = jedis.lrange("myList", 0, -1);
         for(String str:myList){
             System.out.println(str);
         }
     }

     @Test
    public  void test7()
     {
//配置可以当参数传递给连接池
//         JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//         jedisPoolConfig.setMaxTotal();

         JedisPool jedisPool = new JedisPool();

         Jedis jedis = jedisPool.getResource();

     }
}
