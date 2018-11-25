package com.fh.util;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisPool {
    private RedisPool(){}
    private static JedisPool pool;

    private static void initPool(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMinIdle(100);
        jedisPoolConfig.setMaxIdle(100);
        jedisPoolConfig.setTestOnBorrow(true);
        jedisPoolConfig.setTestOnReturn(true);
        jedisPoolConfig.setMaxTotal(1000);
        pool = new JedisPool(jedisPoolConfig,"192.168.1.176",6379);
    }

    static{
        initPool();
    }


    public static Jedis getResource(){
        return pool.getResource();
    }


}
