package com.example.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisApplicationTests {
    @Autowired ServiceImpl service;

    @Test
    public void contextLoads() {
//        service.saveKV();
//        service.deleteKeys("1","test");
//        service.getKV();
        service.insertMap();
    }

}

