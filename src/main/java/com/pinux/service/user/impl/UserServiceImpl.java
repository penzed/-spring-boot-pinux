package com.pinux.service.user.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pinux.entity.user.User;
import com.pinux.entity.user.Users;
import com.pinux.mapper.user.UserMapper;
import com.pinux.mapper.user.UsersMapper;
import com.pinux.service.user.UserService;
import com.pinux.thread.UsersThreadQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
    Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UsersMapper usersMapper;

    @Override
    @Cacheable
    public User findByUserName(String userName) {
        return this.getOne(new QueryWrapper<User>().eq("user_name", userName));
    }

    @Override
    @Transactional
    public boolean saveUser(User aa) {
        return this.save(aa);
    }

    @Override
    public List<Users> getUsersList() {
        long start=System.currentTimeMillis();
        List<Users> usersList=new ArrayList<>();
        Integer count= usersMapper.selectCount(null);
        logger.info("查询的用户总数为："+count);
        Integer num = 10000;//每个线程查询10000条
        //需要查询的次数
        int times=count/num;
        if (count%num!=0){
            times=times+1;
        }
        //Callable用于产生结果
        List<Callable<List>> tasks=new ArrayList<>();
        for (int i = 1; i <=times; i++) {
            Callable<List> listCallable = new UsersThreadQuery(i, num);
            tasks.add(listCallable);
        }
        //定义固定长度的线程池  防止线程过多
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        //Future用于获取结果
        List<Future<List>> futures = null;
        try {
            futures = executorService.invokeAll(tasks);

            //处理线程返回结果
            if (futures != null && futures.size() > 0) {
                for (Future<List> future : futures) {
                    if (future!=null&&future.get()!=null){

                    }
                    usersList.addAll(future.get());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        executorService.shutdown();//关闭线程池
        long end = System.currentTimeMillis();
        logger.info("线程查询数据用时:" + (end - start) + "ms,返回的总数为:"+usersList.size());
        return usersList;
    }

}
