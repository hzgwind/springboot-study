package com.windrain.redistest.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    public RedisUtil(RedisTemplate<String,Object> redisTemplate){
        this.redisTemplate=redisTemplate;
    }

    /**
     * 指定缓存失效时间
     * @param key 键
     * @param time 时间
     * @return
     */
    public boolean expire(String key,long time){
        try{
            if (time>0){
                redisTemplate.expire(key,time, TimeUnit.SECONDS);
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 获取过期时间
     * @param key 键，不能为null
     * @return 返回0代表永久有效
     */
    public long getExpire(String key){
        return redisTemplate.getExpire(key);
    }

    /**
     * 判断键是否存在
     * @param key 键
     * @return
     */
    public boolean hasKey(String key){
        try {
            return redisTemplate.hasKey(key);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除缓存
     * @param key
     */
    public void del(String ... key){
        if(key!=null&&key.length>0){
            if (key.length==1){
                redisTemplate.delete(key[0]);
            }else{
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }

        }
    }


    //===========================String==============================//
    /**
     * 普通缓存放入
     * @param key 键
     * @param value 值
     * @return
     */
    public boolean set(String key,Object value){
        try {
            redisTemplate.opsForValue().set(key,value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 普通缓存放入并设置时间
     * @param key 键
     * @param value 值
     * @param time 时间（秒），大于0，小于或等于为无期限
     * @return
     */
    public boolean set(String key,Object value,long time){
        try {
            if (time>0){
                redisTemplate.opsForValue().set(key,value,time,TimeUnit.SECONDS);
            }else{
                set(key, value);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 递增
     * @param key 键
     * @param delta 要增加几(大于0)
     * @return
     */
    public long incr(String key, long delta){
        if(delta<0){
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 递减
     * @param key 键
     * @param delta 要减少几(小于0)
     * @return
     */
    public long decr(String key, long delta){
        if(delta<0){
            throw new RuntimeException("递减因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, -delta);
    }

    //=============================Map===============================//

    /**
     * 获取map里面的值
     * @param key 键
     * @param item 项
     * @return 值
     */
    public Object hget(String key,String item){
        return redisTemplate.opsForHash().get(key,item);
    }

    /**
     * 获取hashKey对应的所有键值
     * @param key 键
     * @return
     */
    public Map<Object,Object> hmget(String key){
        return redisTemplate.opsForHash().entries(key);
    }
}
