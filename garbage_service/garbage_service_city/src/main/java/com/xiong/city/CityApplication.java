package com.xiong.city;

import com.xiong.common.utils.ServiceAop;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.xiong.city.dao")
public class CityApplication {
    public static void main(String[] args) {
        SpringApplication.run(CityApplication.class, args);
    }

    @Bean
    public ServiceAop serviceAop() {
        return new ServiceAop();
    }
}
