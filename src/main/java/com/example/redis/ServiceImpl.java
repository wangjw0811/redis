package com.example.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class ServiceImpl {
    @Autowired
    private RedisTemplate redisTemplate;

    //增删改查String类型 为json类型
    public void saveKV() {
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
//        String key = "test";
//        boolean hasKey = redisTemplate.hasKey(key);//判断是否有
//        if (hasKey) {
//            City city = (City)operations.get(key); //查询
//            log.info("city" + city);
//            return;
//        }
        //赋值
//        City entity = City.builder()
//                .id(1l)
//                .provinceId(2l)
//                .description("测试")
//                .build();
        //保存
//        operations.set(key, entity);
        operations.set("test", "测试");
        log.info("成功");
    }

    public void deleteKeys(String... keys){
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        if(keys.length>0){
            for(int i = 0;i<keys.length;i++){
                String key = keys[i];
                do{
                    Boolean flag = ops.getOperations().delete(key);
                    if(flag){
                        log.info("成功删除:"+key);
                    }
                }while(redisTemplate.hasKey(key));
            }
        }else{
            log.error("未指定需要删除的key");
        }

    }

    public void getKV(){
        Set<String> keys = redisTemplate.keys("*");
        HashMap map = new HashMap();
        for(String key : keys){
            map.put(key,redisTemplate.opsForValue().get(key));
        }
        System.out.println(map);
    }

    //增删改查hash
    public void insertMap() {
        HashOperations operations = redisTemplate.opsForHash();
        String key = "map";
//        int hashKey = 1;
//        boolean hasKey = redisTemplate.hasKey(key);// 判断是否有
//        if (hasKey) {
//            operations.get(key, hashKey); // 查询
//            // operations.getOperations().delete(key);//删除
//            return;
//        }
        // 修改map在插入就可以
        Map<String, String> map = new HashMap<>();
        map.put("1", "qqq");
        map.put("2", "www");
        map.put("3", "eee");
        map.put("4", "rrr");
//        operations.put(key,hashKey, map);
        operations.putAll(key,map);

    }

    //增删改查list
    public void insertList() {
        ListOperations operations = redisTemplate.opsForList();
        String key = "city_" + 44;
        boolean hasKey = redisTemplate.hasKey(key);// 判断是否有
        if (hasKey) {
            operations.range(key, 0, 3);//查询
            operations.getOperations().delete(key);//删除
            return;
        }
        List list = new ArrayList<>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        operations.leftPushAll(key, list);
    }

    //增删改查set
    public void insertSet() {
        SetOperations operations = redisTemplate.opsForSet();
        String key = "city_" + 55;
        boolean hasKey = redisTemplate.hasKey(key);
        if(hasKey) {
            operations.members(key);//查询
            operations.getOperations().delete(key);//删除
            return;
        }
        operations.add(key, "111");
        operations.add(key, "222");
        operations.add(key, "333");
    }
}
