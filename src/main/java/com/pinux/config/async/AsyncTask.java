package com.pinux.config.async;

import cn.hutool.core.util.RandomUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

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
 * @ClassName AsyncTask
 * @Description TODO
 * @Author pinux
 * @Date 2019/11/8 2:05
 * @Version 1.0
 */
@Component
public class AsyncTask {
    Logger logger= LoggerFactory.getLogger(this.getClass());

    @Async("asyncTaskExecutor")
    public Future<String> doTaskOne() throws Exception {
        logger.info("开始做任务一");
        long start = System.currentTimeMillis();
        Thread.sleep(1000);
        long end = System.currentTimeMillis();
        logger.info("完成任务一，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<>("任务一完成");
    }

    @Async("asyncTaskExecutor")
    public Future<String> doTaskTwo() throws Exception {
        logger.info("开始做任务二");
        long start = System.currentTimeMillis();
        Thread.sleep(1000);
        long end = System.currentTimeMillis();
        logger.info("完成任务二，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<>("任务二完成");
    }

    @Async("asyncTaskExecutor")
    public Future<String> doTaskThree() throws Exception {
        logger.info("开始做任务三");
        long start = System.currentTimeMillis();
        Thread.sleep(1000);
        long end = System.currentTimeMillis();
        logger.info("完成任务三，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<>("任务三完成");
    }
}
