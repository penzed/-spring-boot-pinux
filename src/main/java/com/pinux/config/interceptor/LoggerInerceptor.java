package com.pinux.config.interceptor;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.pinux.entity.logger.Logger;
import com.pinux.service.logger.LoggerService;
import com.pinux.service.user.UserService;
import com.pinux.util.IPUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Date;

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
 * @ClassName LoggerInerceptor
 * @Description TODO
 * @Author pinux
 * @Date 2019/11/6 15:54
 * @Version 1.0
 */
public class LoggerInerceptor implements HandlerInterceptor {

    //请求开始时间标识
    private static final String LOGGER_SEND_TIME = "_send_time";
    //请求日志实体标识
    private static final String LOGGER_ENTITY = "_logger_entity";

    public static final String LOGGER_RETURN = "_logger_return";

    @Autowired
    private LoggerService loggerService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //创建日志实体
        Logger logger = new Logger();
        //获取请求sessionId
        String sessionId = request.getRequestedSessionId();
        //请求路径
        String url = request.getRequestURI();
        //获取请求参数信息
        String paramData = JSON.toJSONString(request.getParameterMap(),
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteMapNullValue);
        //设置客户端ip
        logger.setClientId(IPUtils.getIpAddr(request));
        //设置请求方法
        logger.setMethod(request.getMethod());
        //设置请求类型（json|普通请求）
        logger.setType(request.getHeader("X-Requested-With"));
        //设置请求参数内容json字符串
        logger.setParam(paramData);
        //设置请求地址
        logger.setUrl(url);
        //设置sessionId
        logger.setSessionId(sessionId);
        logger.setCtime(new Date());
        //设置请求开始时间
        request.setAttribute(LOGGER_SEND_TIME, System.currentTimeMillis());
        //设置请求实体到request内，方面afterCompletion方法调用
        request.setAttribute(LOGGER_ENTITY, logger);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //获取请求错误码
        int status = response.getStatus();
        //请求开始时间
        long time = Long.valueOf(request.getAttribute(LOGGER_SEND_TIME).toString());
        //获取本次请求日志实体
        Logger loggerEntity = (Logger) request.getAttribute(LOGGER_ENTITY);
        //设置返回时间
        loggerEntity.setReturnTime(new Date());
        //设置返回错误码
        loggerEntity.setCode(status + "");
        //设置返回值
        loggerEntity.setReturnData(JSON.toJSONString(request.getAttribute(LOGGER_RETURN),
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteMapNullValue));
        loggerEntity.setId(IdUtil.fastUUID());
        //执行将日志写入数据库
        loggerService.save(loggerEntity);
    }
}
