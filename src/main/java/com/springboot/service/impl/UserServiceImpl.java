package com.springboot.service.impl;

import com.springboot.dao.UserDao;
import com.springboot.entity.User;
import com.springboot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = "users")//配置了该数据访问对象中返回的内容将存储于名为users的缓存对象中
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserDao userDao;

    @CachePut(key="#p0.id")//修改数据时使用cacheput
    @Override
    public User addUser(User user) {
        return userDao.save(user);
    }

    @CacheEvict(key="#id")//删除数据时使用
    @Override
    public void deleteUser(String id) {
        userDao.deleteById(id);
    }

    @CachePut(key="#p0.id")
    @Override
    public User modifyUser(User user) {
        return userDao.save(user);
    }

    @Cacheable//(key="#id")//id作为key
    @Override
    public User findById(String id) {
        User user = new User();
        user.setId(id);
        Optional optional = userDao.findOne(Example.of(user));
        //isPresent返回false表示是空对象，true表示不为空，get取出
        if(!optional.isPresent()){
            return null;
        }
        return userDao.findOne(Example.of(user)).get();
    }

    @Cacheable(key="#user.id")
    @Override
    public List<User> findByUser(User user) {
        return userDao.findAll(Example.of(user));
    }

}
