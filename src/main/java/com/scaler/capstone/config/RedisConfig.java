package com.scaler.capstone.config;

import com.scaler.capstone.models.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

// https://redis.io/docs/latest/operate/oss_and_stack/install/install-redis/install-redis-on-mac-os/
@Configuration
public class RedisConfig {

    @Bean
    RedisTemplate<String, Product> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Product> template = new RedisTemplate<>();

        template.setConnectionFactory(connectionFactory);
        return template;
    }
}
