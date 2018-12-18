package com.lanpangzi.service.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class RedisClientService implements RedisService{
    @Autowired
    RedisTemplate<String ,Object> redisTemplate;
    @Autowired
    ZSetOperations<String,Object> zSetOperations;
    @Autowired
    SetOperations<String,Object> setOperations;
    @Autowired
    HashOperations<String,Object,Object> hashOperations;
    @Autowired
    ValueOperations<String,Object> valueOperations;
    @Autowired
    ListOperations<String,Object> listOperations;

    @Override
    public long getExpireSeconds(String key) {
        return redisTemplate.getExpire(key,TimeUnit.SECONDS);
    }

    @Override
    public long getExpireMinutes(String key) {
        return redisTemplate.getExpire(key,TimeUnit.MINUTES);
    }

    @Override
    public void putValue(String key, Object value) {
        valueOperations.set(key,value);
    }

    @Override
    public void putValue(String key, Object value, long timeout) {
        valueOperations.set(key,value,timeout,TimeUnit.SECONDS);
    }

    @Override
    public Object getValue(String key) {
        return valueOperations.get(key);
    }
    @Override
    public void putMultiValue(Map<String, Object> map) {
        valueOperations.multiSet(map);
    }
    @Override
    public void putMultiValue(Map<String, Object> map, long timeout) {
       for (Map.Entry<String,Object> entry :map.entrySet()){
           valueOperations.set(entry.getKey(),entry.getValue(),timeout);
       }
    }

    @Override
    public List<Object> getMultiValue(Collection<String> collection) {
        return valueOperations.multiGet(collection);
    }

    @Override
    public Object indexByList(String key, long index) {
        return listOperations.index(key,index);
    }

    @Override
    public long getListSize(String key) {
        return listOperations.size(key);
    }

    @Override
    public List<Object> rangeByList(String key, long begin, long end) {
        return listOperations.range(key,begin,end);
    }
    @Override
    public List<Object> rangeByList(String key, long begin) {
        return listOperations.range(key,begin,-1);
    }

    @Override
    public void leftPopList(String key) {
        listOperations.leftPop(key);
    }


    @Override
    public void leftPushList(String key, Object value) {
        listOperations.leftPush(key,value);
    }
    @Override
    public void leftPushAllList(String key, Collection<Object> values) {
        Iterator<Object> iterator= values.iterator();
        while (iterator.hasNext()){
            Object object = iterator.next();
            listOperations.leftPush(key,object);
        }
    }
    @Override
    public void leftPushAllList(String key, Object... objects) {
        listOperations.leftPushAll(key,objects);
    }
    //存在key  才会进行插入
    @Override
    public void leftPushIfPresent(String key, Object object) {
        listOperations.leftPushIfPresent(key,object);
    }

    @Override
    public void rightPopList(String key) {
        listOperations.rightPop(key);
    }


    @Override
    public void rightPushList(String key, Object value) {
        listOperations.rightPush(key,value);
    }

    @Override
    public void rightPushAllList(String key, Collection<Object> values) {
        listOperations.rightPushAll(key,values);
    }

    @Override
    public void rightPushAllList(String key, Object... objects) {
        listOperations.rightPushAll(key,objects);
    }

    @Override
    public void rightPushIfPresent(String key, Object object) {
        listOperations.rightPushIfPresent(key,object);
    }
    //从一个list 移动到另一个list
    @Override
    public Object rightPopAndLeftPush(String listPop, String pushList) {
        return listOperations.rightPopAndLeftPush(listPop,pushList);
    }
        //删除  count  表示删除数量
    @Override
    public Object removeList(String key, long count, Object value) {
        return listOperations.remove(key,count,value);
    }

    @Override
    public boolean hasHash(String key,Object value) {
        return hashOperations.hasKey(key,value);
    }

    @Override
    public void putHash(String key, Object hkey, Object hvalue) {
        hashOperations.put(key,hkey,hvalue);
    }

    @Override
    public void putAllHash(String key, Map<Object, Object> map) {
        hashOperations.putAll(key,map);
    }

    @Override
    public Object getHash(String key, Object hkey) {
        return hashOperations.get(key,hkey);
    }

    @Override
    public List<Object> getMultiHashValue(String key, Collection<Object> collection) {
        return hashOperations.multiGet(key,collection);
    }

    @Override
    public long deleteHash(String key, Object... objects) {
        return hashOperations.delete(key,objects);
    }

    @Override
    public Map<Object, Object> entriesHash(String key) {
        return hashOperations.entries(key);
    }

    @Override
    public long sizeHash(String key) {
        return hashOperations.size(key);
    }

    /**
     * Set
     */
    @Override
    public void addSet(String key, Object value) {
        setOperations.add(key,value);
    }

    @Override
    public void moveSet(String set1, Object value, String set2) {
        setOperations.move(set1,value,set2);
    }

    @Override
    public long removeSet(String key, Object... objects) {
        return setOperations.remove(key,objects);
    }

    @Override
    public long sizeSet(String key) {
        return setOperations.size(key);
    }
    //取前者差集
    @Override
    public Set<Object> differenceSet(String set1, String set2) {
        return setOperations.difference(set1,set2);
    }

    @Override
    public Set<Object> unionSet(String set1, String set2) {
        return setOperations.union(set1,set2);
    }

    @Override
    public Set<Object> intersectSet(String set1, String set2) {
        return setOperations.intersect(set1,set2);
    }

    @Override
    public Set<Object> membersSet(String set) {
        return setOperations.members(set);
    }

}
