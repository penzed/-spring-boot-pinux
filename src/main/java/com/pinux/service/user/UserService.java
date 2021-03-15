package com.pinux.service.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pinux.entity.user.User;
import com.pinux.entity.user.Users;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author pinux
 * @since 2019-11-13
 */
public interface UserService extends IService<User> {

    User findByUserName(String userName);

    boolean saveUser(User aa);

    List<Users> getUsersList();
}
