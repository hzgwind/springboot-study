package com.windrain.redistest.service.impl;

import com.windrain.redistest.bean.User;
import com.windrain.redistest.service.CacheService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CacheServiceImpl implements CacheService {
    @Override
    @Cacheable(value = "User",key = "targetClass + ':' + methodName + '_' + #p0.name")
    public int findUserId(User user) {
        return 1;
    }

    @Override
    @Cacheable(value = "user", key = "targetClass + ':' + methodName + '_' + #p0", unless = "#result.size() <= 0")
    public List<User> listUser(int pageNum, int pageSize) {
        List<User> users=new ArrayList<>();
        users.add(new User());
        return null;
    }

    @Override
    public User findUser(User user) {
        return null;
    }

    @Override
    public User save(User user) {
        return null;
    }
}
