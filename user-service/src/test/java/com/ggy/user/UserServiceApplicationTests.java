package com.ggy.user;

import com.ggy.fegin.ResourceClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceApplicationTests {
    @Autowired
    private ResourceClient resourceClient;

    @Test
    void contextLoads() {
    }
    @Test
    public void test1(){
        long i=1;
//        System.out.println(resourceClient.index());
        System.out.println(resourceClient.findResourceById(i));
    }

}
