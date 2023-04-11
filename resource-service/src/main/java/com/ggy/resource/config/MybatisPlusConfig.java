package com.ggy.resource.config;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;

import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/***
 *  @title: MybatisPlus
 *  @author: GaoGuiYun
 *  @version: 1.0.0
 *  @create: 2023-04-04 21:46
 ***/
@Configuration
@MapperScan("com.ggy.resource.mapper")
public class MybatisPlusConfig {
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
    /**
     * description: 自定义ID主键生成器 <br>
     * date: 2023/4/11 11:38 <br>
     *
     * @return com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator
     */
    @Bean
    public DefaultIdentifierGenerator defaultIdentifierGenerator() {
        // 以免多线程批量插入，ID主键生成有重复，主键冲突错误
        // 1-31 随机数
        long workerId = RandomUtil.randomLong(1, 31);
        // 1-31 随机数
        long dataCenterId = RandomUtil.randomLong(1, 31);

        return new DefaultIdentifierGenerator(workerId, dataCenterId);
    }

}