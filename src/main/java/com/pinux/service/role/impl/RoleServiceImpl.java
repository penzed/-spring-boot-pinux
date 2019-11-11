package com.pinux.service.role.impl;

import com.pinux.entity.role.Role;
import com.pinux.mapper.role.RoleMapper;
import com.pinux.service.role.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author pinux
 * @since 2019-11-10
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
