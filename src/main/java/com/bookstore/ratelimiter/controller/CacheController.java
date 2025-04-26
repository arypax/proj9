package com.bookstore.ratelimiter.controller;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/cache")
public class CacheController {

    private final RedisTemplate<String, Object> redisTemplate;

    public CacheController(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Operation(summary = "Показать кэш", description = "Отображает все кэшированные данные из Redis")
    @GetMapping
    public Map<String, Object> showCache() {
        Set<String> keys = redisTemplate.keys("books::*");
        if (keys != null) {
            return keys.stream()
                .collect(Collectors.toMap(
                    key -> key,
                    key -> redisTemplate.opsForValue().get(key)
                ));
        }
        return Map.of();
    }
} 