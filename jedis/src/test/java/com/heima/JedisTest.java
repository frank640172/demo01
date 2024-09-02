package com.heima;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

public class JedisTest {
    private Jedis jedis;

    @BeforeEach
    void setup(){
        jedis = new Jedis("127.0.0.1",9998);
        jedis.auth("195956");
        jedis.select(0);
    }

    @Test
    void test(){
         jedis.set("s1","123");
    }

    @AfterEach
    void close(){
        if(jedis!=null)
        jedis.close();
    }
}
