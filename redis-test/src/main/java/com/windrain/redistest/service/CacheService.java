package com.windrain.redistest.service;

import com.windrain.redistest.bean.User;

import java.util.List;

public interface CacheService {
    public int findUserId(User user);
    public List<User> listUser(int pageNum, int pageSize);
    public User findUser(User user);
    public User save(User user);
}
