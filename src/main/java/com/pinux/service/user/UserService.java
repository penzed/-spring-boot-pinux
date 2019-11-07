package com.pinux.service.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pinux.entity.user.User;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author pinux
 * @since 2019-09-24
 */
public interface UserService extends IService<User> {


    List<User> findByName(String aaa);
}
