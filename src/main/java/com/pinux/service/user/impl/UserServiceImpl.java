package com.pinux.service.user.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pinux.entity.user.User;
import com.pinux.mapper.user.UserMapper;
import com.pinux.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author pinux
 * @since 2019-09-24
 */
@Service
@Transactional
@CacheConfig(cacheNames = "UserServiceImpl")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Override
    @Cacheable
    public List<User> findByName(String aaa) {
        return this.list(new QueryWrapper<User>().eq("name",aaa));
    }
}
