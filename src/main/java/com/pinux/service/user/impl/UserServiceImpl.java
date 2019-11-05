package com.pinux.service.user.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pinux.entity.user.User;
import com.pinux.mapper.user.UserMapper;
import com.pinux.service.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author pinux
 * @since 2019-09-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
