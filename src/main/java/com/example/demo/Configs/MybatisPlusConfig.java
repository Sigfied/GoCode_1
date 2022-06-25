package com.example.demo.Configs;

import com.example.demo.Injector.EasySqlInjector;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author GYJ
 */
@Component
public class MybatisPlusConfig {

    @Bean
    public EasySqlInjector easySqlInjector () {
        return new EasySqlInjector();
    }
}
