package com.example.singledemo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

@Configuration
public class redisUtil {

    @Autowired
    private StringRedisTemplate redisTemplate;


    /**
     * 存储
     * @param userkey
     * @param msg
     * @throws Exception
     */
    public void saveMsg(String userkey,String msg) throws Exception{
        // redis中key过期时间
        long time=7200;
        redisTemplate.opsForValue().set(userkey,msg,time, TimeUnit.SECONDS);

    }


    /**
     * 获取
     * @param key
     * @return
     */
    public String getMsg(String key){

        String msg;
        msg=redisTemplate.opsForValue().get(key);
        return msg;
    }


    public long getExp(String key){
        return redisTemplate.getExpire(key,TimeUnit.SECONDS);
    }

    /**
     * 删除
     * @param key
     */
    public void deletMsg(String key){
        redisUtil redisUtil= new redisUtil();
        redisUtil.getExp(key);
        redisTemplate.delete(key);
    }



}
