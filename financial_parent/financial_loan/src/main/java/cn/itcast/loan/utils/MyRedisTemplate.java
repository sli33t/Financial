package cn.itcast.loan.utils;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.Map;

public class MyRedisTemplate extends RedisTemplate<String, Map> {

    public void put(String key, Map map){
        super.opsForValue().set(key, map);
    }

    public Map get(String key){
        Class<? extends Map> aClass = super.opsForValue().get(key).getClass();
        ThreadLocal threadLocal = new ThreadLocal();
        threadLocal.notify();
        return null;
    }



}
