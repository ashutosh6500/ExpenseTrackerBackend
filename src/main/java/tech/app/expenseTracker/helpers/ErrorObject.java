package tech.app.expenseTracker.helpers;

import java.util.Date;

public class ErrorObject {
    private Integer statusCode;
    private String msg;
    private Date timeStamp;
    public ErrorObject(){}
    public ErrorObject(Integer statusCode, String msg, Date timeStamp) {
        this.statusCode = statusCode;
        this.msg = msg;
        this.timeStamp = timeStamp;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
}
