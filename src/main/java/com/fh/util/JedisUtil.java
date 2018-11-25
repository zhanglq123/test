package com.fh.util;


import redis.clients.jedis.Jedis;

public class JedisUtil {

    public static void set(String key,String value){
        Jedis jedis = null;
        try{
            jedis = RedisPool.getResource();
            jedis.set(key,value);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(null != jedis){
                jedis.close();
                jedis = null;
            }
        }

    }

    public static long incr(String key,int second){
        Jedis jedis = null;
        Long value = 0L ;
        try {
            jedis = RedisPool.getResource();
            value = jedis.incr(key);
            if(value==1){
                jedis.expire(key,second);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(null != jedis){
                jedis.close();
                jedis = null;
            }
        }
        return value;
    }

    public static boolean setNxExist(String key,String value,int second){
        Jedis jedis = null;

        try {
            jedis = RedisPool.getResource();
            Long result = jedis.setnx(key, value);
            if(result == 1){
                jedis.expire(key,second);
                return true;
            }else {
                return false;
            }
        } finally {
            if(null != jedis){
                jedis.close();
                jedis =null;
            }
        }

    }


    public static void del(String key){
        Jedis jedis = null ;
        try{
            jedis = RedisPool.getResource();
            jedis.del(key);
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            if(null != jedis){
                jedis.close();
                jedis = null;
            }
        }

    }

    public static void expire(String key,int value){
        Jedis jedis = null;
        try{
            jedis = RedisPool.getResource();
            jedis.expire(key,value);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(null != jedis){
                jedis.close();
                jedis = null;
            }
        }

    }
    public static String get(String key){
        Jedis jedis = null;
        String value = "";
        try{
            jedis = RedisPool.getResource();
            value = jedis.get(key);
        }catch (Exception e){
            e.printStackTrace();
            return value;
        }finally {
            if(null != jedis){
                jedis.close();
                jedis = null;
            }
        }
        return value;
    }


}
