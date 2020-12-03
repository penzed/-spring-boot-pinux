package com.pinux.config.security;

import com.alibaba.fastjson.JSONObject;
import com.pinux.constant.SecurityConstant;
import com.pinux.service.user.UserService;
import com.pinux.util.ResponseUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: ZENGGY
 * @date: 2019/2/22 11:18
 * @Description: (用一句话描述该文件做什么)
 */

public class AuthorizationInterceptor implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IgnoredUrlsProperties ignoredUrlsProperties;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private UserService userService;

    /*
     * 该方法将在整个请求完成之后执行， 主要作用是用于清理资源的，
     * 该方法也只能在当前Interceptor的preHandle方法的返回值为true时才会执行。
     */
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception exception)
            throws Exception {
        System.out.println("AuthorizationInterceptor afterCompletion --> ");

    }

    /*
     * 该方法将在Controller的方法调用之后执行， 方法中可以对ModelAndView进行操作 ，
     * 该方法也只能在当前Interceptor的preHandle方法的返回值为true时才会执行。
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView mv) throws Exception {
        System.out.println("AuthorizationInterceptor postHandle --> ");

    }

    /*
     * preHandle方法是进行处理器拦截用的，该方法将在Controller处理之前进行调用，
     * 该方法的返回值为true拦截器才会继续往下执行，该方法的返回值为false的时候整个请求就结束了。
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("AuthorizationInterceptor preHandle --> ");
        //获取请求的路径进行判断
        String servletPath = request.getServletPath();
        System.out.println(servletPath);
        List<String> IGNORE_URI = ignoredUrlsProperties.getUrls();
        PathMatcher pathMatcher = new AntPathMatcher();
//        if (pathMatcher.match("/service/**", servletPath)){
////            String head = request.getHeader(SecurityConstant.HEADER);
////            JSONObject headObject = JSONObject.parseObject(head);
////            String platformId = headObject.getString(SecurityConstant.PLATFORM_ID);
////            String seq = headObject.getString(SecurityConstant.SEQ);
////            String platformPwd = headObject.getString(SecurityConstant.PLATFORM_PWD);
//            //String datetime = headObject.getString(SecurityConstant.DATETIME);
//            String platformId = request.getHeader(SecurityConstant.PLATFORM_ID);
//            String seq = request.getHeader(SecurityConstant.SEQ);
//            String platformPwd = request.getHeader(SecurityConstant.PLATFORM_PWD);
//            String datetime = request.getHeader(SecurityConstant.DATETIME);
//            if(StringUtils.isEmpty(platformId) || StringUtils.isEmpty(seq) || StringUtils.isEmpty(platformPwd) || StringUtils.isEmpty(datetime)){
//                resultJson(response, JSON.toJSONString(ResponseVO.error(Response.PARAM_ERROR.getRecode(),Response.PARAM_ERROR.getMessage())));
//                return false;
//            }
//            if(platformId.length() != 7){
//                resultJson(response, JSON.toJSONString(ResponseVO.error(Response.PLATFORMID_ERROR.getRecode(),Response.PLATFORMID_ERROR.getMessage())));
//                return false;
//            }
//            ExternalAccount externalAccount = externalAccountMapper.selectById(platformId);
//            if(externalAccount == null){
//                resultJson(response, JSON.toJSONString(ResponseVO.error(Response.EXIST_ERROR.getRecode(),Response.EXIST_ERROR.getMessage())));
//                return false;
//            }
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//            long minute = 0;
//            try {
//                Date datetimeDate = sdf.parse(datetime);
//                Date nowDate  = new Date();
//                long datetimeTimeLong = datetimeDate.getTime();
//                long nowTimeLong = nowDate.getTime();
//                // 结束时间-开始时间 = 天数
//                minute = Math.abs((nowTimeLong-datetimeTimeLong)/(60*1000));
//
//            } catch (ParseException e) {
//                e.printStackTrace();
//                minute = -1;
//                resultJson(response, JSON.toJSONString(ResponseVO.error(Response.FORM_ERROR.getRecode(),Response.FORM_ERROR.getMessage())));
//                return false;
//            }
//            if(minute > 30){
//                resultJson(response, JSON.toJSONString(ResponseVO.error(Response.TIMEOUT_ERROR.getRecode(),Response.TIMEOUT_ERROR.getMessage())));
//                return false;
//            }
//            String dbPlatformPwd = externalAccount.getKeyPwd();
//            if(!platformPwd.equals(MD5.getMD5Str(seq + dbPlatformPwd))){
//                resultJson(response, JSON.toJSONString(ResponseVO.error(Response.ENCRYPT_ERROR.getRecode(),Response.ENCRYPT_ERROR.getMessage())));
//                return false;
//            }
//            return true;
//        }
        // 判断请求是否需要拦截
        for (String s : IGNORE_URI) {
            if (pathMatcher.match(s, servletPath)) {
                return true;
            }

        }
        String authorities = request.getHeader(SecurityConstant.HEADER);
        String tokenKey = "PINUX::USER::TOKEN" + authorities;
        String userStr = redisTemplate.opsForValue().get(tokenKey);
        if (StringUtils.isEmpty(userStr)) {
            ResponseUtil.out(response, ResponseUtil.resultMap(false, 403, "您的登录信息已过期，请重新登录"));
            logger.info("用户登录超时，需要重新登录");
            return false;
        }




        if (redisTemplate.getExpire(tokenKey, TimeUnit.MINUTES) < 15) {
            redisTemplate.opsForValue().set(tokenKey, userStr, 2L, TimeUnit.HOURS);
        } else {
            redisTemplate.opsForValue().set(tokenKey, userStr, 2L, TimeUnit.HOURS);
        }

        return true;
    }

    private void resultJson(HttpServletResponse response, String json) throws IOException {
        // 输出响应数据
        // HttpResponseUtils.writeUTF8(response, json);

    }

}
