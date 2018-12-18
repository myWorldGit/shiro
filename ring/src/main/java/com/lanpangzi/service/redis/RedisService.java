package com.lanpangzi.service.redis;

import org.springframework.data.redis.core.ValueOperations;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RedisService {
    /**
     * 获取key的被杀死的时间
     */
    long getExpireSeconds(String key);
    long getExpireMinutes(String key);

    /**
     *    key 对value
     */
    void putValue(String key ,Object value);
    void putValue(String key, Object value, long timeout);
    Object getValue(String key);

    /**
     *      key  对map
     */
    void putMultiValue(Map<String,Object>map);
    void putMultiValue(Map<String,Object>map,long timeout);
    List<Object> getMultiValue(Collection<String> collection);

    /**
     *   其他的list操作
     */
    Object indexByList(String key,long index);
    long getListSize(String key);
    List<Object> rangeByList(String key,long begin,long end);
    List<Object> rangeByList(String key,long begin);
    /**
     *    双向链表  左插入 杀死
     */
    void leftPopList(String key);

    void leftPushList(String key,Object value);
    void leftPushAllList(String key, Collection<Object> values);
    void leftPushAllList(String key,Object ... objects);
    void leftPushIfPresent(String key, Object object);
    /**
     *      右边插入
     */
    void rightPopList(String key);
    void rightPushList(String key,Object value);
    void rightPushAllList(String key, Collection<Object> values);
    void rightPushAllList(String key,Object ... objects);
    void rightPushIfPresent(String key, Object object);

    /**
     * 左进右出  返回移除的
     */
    Object rightPopAndLeftPush(String listPop, String pushList);
    Object removeList(String key,long count , Object value);

    /**
     * hash
     */
    boolean hasHash(String key,Object value);
    void putHash(String key,Object hkey,Object hvalue);
    void putAllHash(String key ,Map<Object,Object> map);
    Object getHash(String key,Object hkey);
    List<Object> getMultiHashValue(String key,Collection<Object> collection);

    long deleteHash(String key,Object ... objects);
    Map<Object,Object> entriesHash(String key);
    long sizeHash(String key);
    /**
     * set
     */
    void addSet(String key,Object value);
    void moveSet(String set1,Object value,String set2);
    long removeSet(String key ,Object... objects);
    long sizeSet(String key);
    Set<Object> membersSet(String set);
    //取交集
    Set<Object> intersectSet(String set1,String set2);

    //取差集
    Set<Object> differenceSet(String set1,String set2);

    //取并集
    Set<Object> unionSet(String set1,String set2);

}
