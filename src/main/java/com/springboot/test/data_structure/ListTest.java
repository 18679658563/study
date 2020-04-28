package com.springboot.test.data_structure;

import com.springboot.entity.User;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/***
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-08-21
 * Time: 下午3:48
 */
public class ListTest {

    public static void main(String[] args) throws Exception {
        User user = new User();
        user.setId("123");
        Map map = new HashMap<>();
        Set set = new HashSet();
        map.put(user,user);
        set.add(user);
        User u = user;
        user.setId("789");
        map.put(u,u);
        set.add(u);
        System.out.println(map.keySet().toString());
        System.out.println(set.toString());
        Map con = new ConcurrentHashMap();

    }
}

