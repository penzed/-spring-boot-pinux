package com.pinux.service.user;

import com.pinux.entity.role.Role;
import com.pinux.entity.user.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author pinux
 * @since 2019-11-10
 */
public interface UserInfoService extends IService<UserInfo> {

    UserInfo findByName(String aaa);


    void saveUserInfo(UserInfo user, List<Role> roles);
}
