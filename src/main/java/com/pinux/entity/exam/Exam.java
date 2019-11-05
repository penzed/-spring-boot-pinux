package com.pinux.entity.exam;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author pinux
 * @since 2019-09-24
 */
@TableName("pinux_exam")
public class Exam extends Model<Exam> {

    private static final long serialVersionUID = 1L;

    private String id;

    private String userId;

    private Integer grade;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "Exam{" +
            "id=" + id +
            ", userId=" + userId +
            ", grade=" + grade +
        "}";
    }
}
