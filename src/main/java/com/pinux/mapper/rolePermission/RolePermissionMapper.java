package com.pinux.mapper.rolePermission;

import com.pinux.entity.permission.Permission;
import com.pinux.entity.rolePermission.RolePermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author pinux
 * @since 2019-11-14
 */
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

    @Select("select p.id,p.url,p.name from t_role r left join t_user_role ur on(r.id = ur.role_id) left join t_user u on(u.id = ur.user_id) left join t_role_permission rp on(rp.role_id = r.id) left join t_permission p on(p.id = rp.permission_id ) where u.user_name = #{userName}")
    List<Permission> findByUserName(String userName);
}
