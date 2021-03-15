package com.pinux.thread;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pinux.entity.user.Users;
import com.pinux.mapper.user.UsersMapper;
import com.pinux.util.BeanContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　　┣┓
 * 　　┃　　　　　　　┏┛
 * 　　┗┓┓┏━┳┓┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 *
 * @ClassName Orderthread
 * @Description TODO
 * @Author pengzengyi
 * @Date 2021/3/11 16:11
 * @Version 1.0
 */
public class UsersThreadQuery implements Callable<List> {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    Integer pageNo;
    Integer pageSize;

    private UsersMapper usersMapper = BeanContext.getBean(UsersMapper.class);

    public UsersThreadQuery(Integer pageNo, Integer pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    @Override
    public List<Users> call() throws Exception {
        Page<Users> page = new Page<>(pageNo, pageSize);
        logger.info("当前线程是:" + Thread.currentThread().getName());
        List<Users> usersList = usersMapper.selectPage(page, new QueryWrapper<>()).getRecords();
        return usersList;
    }

}