package com.ggy.resource.service;


import com.ggy.pojo.Resource;
import com.ggy.resource.mapper.ResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourceService {
    @Autowired
    private ResourceMapper resourceMapper;

    public Resource FindById(Long id){
        return resourceMapper.selectById(id.intValue());
    }
}
