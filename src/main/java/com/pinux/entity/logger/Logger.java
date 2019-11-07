package com.pinux.entity.logger;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author pinux
 * @since 2019-11-06
 */
@TableName("pinux_logger")
public class Logger extends Model<Logger> {

    private static final long serialVersionUID = 1L;

    private String id;

    private String clientId;

    private String url;

    private String type;

    private String method;

    private String param;

    private String sessionId;

    private Date ctime;

    private Date returnTime;

    private String code;

    private String returnData;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }
    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getReturnData() {
        return returnData;
    }

    public void setReturnData(String returnData) {
        this.returnData = returnData;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "Logger{" +
            "id=" + id +
            ", clientId=" + clientId +
            ", url=" + url +
            ", type=" + type +
            ", method=" + method +
            ", param=" + param +
            ", sessionId=" + sessionId +
            ", ctime=" + ctime +
            ", returnTime=" + returnTime +
            ", code=" + code +
        "}";
    }
}
