package com.ggy.resource;

import com.ggy.resource.mapper.ResourceMapper;
import com.ggy.resource.service.ResourceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ResourceserviceApplicationTests {
    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private ResourceService resourceService;



    @Test
    void contextLoads() {
    }

    @Test
    public void text1(){
//        System.out.println(resourceMapper.selectById(1l));
        System.out.println(resourceService.FindById(1l));

    }

}
