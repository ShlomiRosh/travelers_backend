package com.travelers.travelers_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Lazy
@Component
public class Redis {

    @Autowired
    private RedisTemplate redisTemplate;

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Object get(String key){
        return key==null?null:redisTemplate.opsForValue().get(key);
    }

    public boolean set(String key,Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
            //return redisTemplate.opsForValue().setIfAbsent(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public static void main(String[] args) {
		/*JedisPool jedisPool = new JedisPool(null,"localhost",6379,100,"123456");
		Jedis jedis = jedisPool.getResource();
		//r.get("", RedisConstants.datebase4);
		jedis.select(RedisConstants.datebase4);
		Set<String> str =  jedis.keys("*");
		for (String string : str) {
			System.out.println(string);
		}*/
    }
}
