package com.pinux.entity.role_user;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author pinux
 * @since 2019-11-10
 */
@TableName("pinux_role_user")
public class RoleUser extends Model<RoleUser> {

    private static final long serialVersionUID = 1L;

    private String id;

    private String roleId;

    private String userId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "RoleUser{" +
            "id=" + id +
            ", roleId=" + roleId +
            ", userId=" + userId +
        "}";
    }
}
