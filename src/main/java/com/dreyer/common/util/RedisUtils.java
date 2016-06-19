package com.dreyer.common.util;

import org.apache.log4j.Logger;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;


/**
 * @author: Dreyer
 * @date: 16/6/4 下午11:25
 * @description Redis缓存工具类
 */
public class RedisUtils {

    private static Logger logger = Logger.getLogger(RedisUtils.class);

    /**
     * 默认缓存时间
     */
    private static final int DEFAULT_CACHE_SECONDS = 60 * 60 * 1;// 单位秒 设置成一个钟

    /**
     * 连接池
     **/
    private static ShardedJedisPool shardedJedisPool;


    /**
     * 释放redis资源
     *
     * @param jedis
     */
    private static void releaseResource(ShardedJedis jedis) {
        if (jedis != null) {
            shardedJedisPool.returnResource(jedis);
        }
    }


    /**
     * 保存一个对象到Redis中(缓存过期时间:使用此工具类中的默认时间) . <br/>
     *
     * @param key    键 . <br/>
     * @param object 缓存对象 . <br/>
     * @return true or false . <br/>
     * @throws Exception
     */
    public static Boolean save(Object key, Object object) {
        return save(key, object, DEFAULT_CACHE_SECONDS);
    }

    /**
     * 保存一个对象到redis中并指定过期时间
     *
     * @param key     键 . <br/>
     * @param object  缓存对象 . <br/>
     * @param seconds 过期时间（单位为秒）.<br/>
     * @return true or false .
     */
    public static Boolean save(Object key, Object object, int seconds) {
        ShardedJedis jedis = null;
        try {
            jedis = shardedJedisPool.getResource();
            jedis.set(SerializeUtils.serialize(key), SerializeUtils.serialize(object));
            jedis.expire(SerializeUtils.serialize(key), seconds);
            return true;
        } catch (Exception e) {
            logger.error("Cache保存失败：" + e);
            return false;
        } finally {
            releaseResource(jedis);
        }
    }

    /**
     * 根据缓存键获取Redis缓存中的值.<br/>
     *
     * @param key 键.<br/>
     * @return Object .<br/>
     * @throws Exception
     */
    public static Object get(Object key) {
        ShardedJedis jedis = null;
        try {
            jedis = shardedJedisPool.getResource();
            byte[] obj = jedis.get(SerializeUtils.serialize(key));
            return obj == null ? null : SerializeUtils.unSerialize(obj);
        } catch (Exception e) {
            logger.error("Cache获取失败：" + e);
            return null;
        } finally {
            releaseResource(jedis);
        }
    }

    /**
     * 根据缓存键清除Redis缓存中的值.<br/>
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static Boolean del(Object key) {
        ShardedJedis jedis = null;
        try {
            jedis = shardedJedisPool.getResource();
            jedis.del(SerializeUtils.serialize(key));
            return true;
        } catch (Exception e) {
            logger.error("Cache删除失败：" + e);
            return false;
        } finally {
            releaseResource(jedis);
        }
    }

    /**
     * 根据缓存键清除Redis缓存中的值.<br/>
     *
     * @param keys
     * @return
     * @throws Exception
     */
    public static Boolean del(Object... keys) {
        ShardedJedis jedis = null;
        try {
            jedis = shardedJedisPool.getResource();
            jedis.del(SerializeUtils.serialize(keys));
            return true;
        } catch (Exception e) {
            logger.error("Cache删除失败：" + e);
            return false;
        } finally {
            releaseResource(jedis);
        }
    }

    /**
     * @param key
     * @param seconds 超时时间（单位为秒）
     * @return
     */
    public static Boolean expire(Object key, int seconds) {

        ShardedJedis jedis = null;
        try {
            jedis = shardedJedisPool.getResource();
            jedis.expire(SerializeUtils.serialize(key), seconds);
            return true;
        } catch (Exception e) {
            logger.error("Cache设置超时时间失败：" + e);
            return false;
        } finally {
            releaseResource(jedis);
        }
    }

    /**
     * 添加一个内容到指定key的hash中
     *
     * @param key
     * @param field
     * @param value
     * @return
     */
    public static Boolean addHash(String key, Object field, Object value) {
        ShardedJedis jedis = null;
        try {
            jedis = shardedJedisPool.getResource();
            jedis.hset(SerializeUtils.serialize(key), SerializeUtils.serialize(field), SerializeUtils.serialize(value));
            return true;
        } catch (Exception e) {
            logger.error("Cache保存失败：" + e);
            return false;
        } finally {
            releaseResource(jedis);
        }
    }

    /**
     * 从指定hash中拿一个对象
     *
     * @param key
     * @param field
     * @return
     */
    public static Object getHash(Object key, Object field) {
        ShardedJedis jedis = null;
        try {
            jedis = shardedJedisPool.getResource();
            byte[] obj = jedis.hget(SerializeUtils.serialize(key), SerializeUtils.serialize(field));
            return SerializeUtils.unSerialize(obj);
        } catch (Exception e) {
            logger.error("Cache读取失败：" + e);
            return null;
        } finally {
            releaseResource(jedis);
        }
    }

    /**
     * 从hash中删除指定filed的值
     *
     * @param key
     * @param field
     * @return
     */
    public static Boolean delHash(Object key, Object field) {
        ShardedJedis jedis = null;
        try {
            jedis = shardedJedisPool.getResource();
            long result = jedis.hdel(SerializeUtils.serialize(key), SerializeUtils.serialize(field));
            return result == 1 ? true : false;
        } catch (Exception e) {
            logger.error("Cache删除失败：" + e);
            return null;
        } finally {
            releaseResource(jedis);
        }
    }


    /**
     * 判断一个key是否存在
     *
     * @param key
     * @return
     */
    public static Boolean exists(Object key) {
        ShardedJedis jedis = null;
        Boolean result = false;
        try {
            jedis = shardedJedisPool.getResource();
            result = jedis.exists(SerializeUtils.serialize(key));
            return result;
        } catch (Exception e) {
            logger.error("Cache获取失败：" + e);
            return false;
        } finally {
            releaseResource(jedis);
        }
    }

    public static ShardedJedisPool getShardedJedisPool() {
        return shardedJedisPool;
    }

    public static void setShardedJedisPool(ShardedJedisPool shardedJedisPool) {
        RedisUtils.shardedJedisPool = shardedJedisPool;
    }
}
