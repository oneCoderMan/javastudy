package com.java.study.javastudy.model;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @Author： yijun
 * @DATE: 2023/9/17 11:32
 * @Description
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResponse {
    private static final String SUCCESS_MESSAGE = "success";
    private String requestId;
    private long serverTime;
    private String resultCode;
    private Object data;

    public static CommonResponse createSuccessResponse(Object data) {
        CommonResponse res = new CommonResponse();
        res.setResultCode(SUCCESS_MESSAGE);
        res.setData(data);
        return res;
    }
    public static CommonResponse createFailureResponse(String errCode) {
        CommonResponse res = new CommonResponse();
        res.setResultCode(errCode);
        return res;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public long getServerTime() {
        return serverTime;
    }

    public void setServerTime(long serverTime) {
        this.serverTime = serverTime;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
