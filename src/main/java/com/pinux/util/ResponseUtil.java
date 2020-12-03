package com.pinux.util;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author somebody
 */
public class ResponseUtil {

    private static Logger logger = LoggerFactory.getLogger(ResponseUtil.class);

    /**
     * 使用response输出JSON
     *
     * @param response
     * @param resultMap
     */
    public static void out(ServletResponse response, Map<String, Object> resultMap) {

        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            out = response.getWriter();
            out.println(JSONObject.toJSON(resultMap));
        } catch (Exception e) {
            logger.info(e + "输出JSON出错");
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }

    public static Map<String, Object> resultMap(boolean flag, Integer code, String msg) {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("success", flag);
        resultMap.put("message", msg);
        resultMap.put("code", code);
        resultMap.put("timestamp", System.currentTimeMillis());
        return resultMap;
    }

    public static Map<String, Object> resultMap(boolean flag, Integer code, String msg, Object data) {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("success", flag);
        resultMap.put("message", msg);
        resultMap.put("code", code);
        resultMap.put("timestamp", System.currentTimeMillis());
        resultMap.put("result", data);
        return resultMap;
    }

    public static void writeUTF8(ServletResponse response, String data)
            throws IOException {
        if (response != null && data != null) {
            HttpServletResponse resp = (HttpServletResponse) response;
            resp.setContentType("application/json;charset=UTF-8");
            resp.setCharacterEncoding("utf-8");
            PrintWriter pw = resp.getWriter();
            pw.write(data);
            pw.flush();
            pw.close();
        }
    }
}
