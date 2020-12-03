package com.pinux.service.user.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pinux.entity.user.User;
import com.pinux.mapper.user.UserMapper;
import com.pinux.service.user.UserService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author pinux
 * @since 2019-11-13
 */
@Service
@CacheConfig(cacheNames = "UserServiceImpl")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User findByUserName(String userName) {
        return this.getOne(new QueryWrapper<User>().eq("username", userName));
    }

    @Override
    @Transactional
    public boolean saveUser(User aa) {
        return this.save(aa);
    }
}
