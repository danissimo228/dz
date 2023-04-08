package ru.nishpal.practic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public ThirdBean thirdBean() {
        return new ThirdBean();
    }
}
