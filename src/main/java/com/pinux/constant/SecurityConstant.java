package com.pinux.constant;

/**
 * @author somebody
 */
public interface SecurityConstant {

    /**
     * token分割
     */
    String TOKEN_SPLIT = "Bearer ";

    /**
     * JWT签名加密key
     */
    String JWT_SIGN_KEY = "wo_music";

    /**
     * token参数头
     */
    String HEADER = "accessToken";

    /**
     * 权限参数头
     */
    String AUTHORITIES = "authorities";

    /**
     * 用户选择JWT保存时间参数头
     */
    String SAVE_LOGIN = "saveLogin";

    /**
     * 用户TOKEN KEY前缀
     */
    String TOKEN_KEY = "WXX:LOGIN:";

    /**
     * 用户TOKEN 有效期 7天
     */
    int TOKEN_TIME = 60*60*24*7;
    /**
     * 用户TICKET前缀
     */
    String TICKET_KEY = "WXX:TICKET:";

    /**
     * 用户TOKEN 有效期 1分钟
     */
    int TICKET_TIME = 60;
}
