package com.example.demoMyBatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan
public class MyBatisConfig {
    /**
     * @description: TODO
     */
    @Configuration
    @MapperScan("com.example.demo.demoMyBatis.mapper")
    public class MybatisConfig {

    }

}
