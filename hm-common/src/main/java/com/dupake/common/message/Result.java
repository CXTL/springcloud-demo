package com.dupake.common.message;

/**
 * @version V1.0.1
 * @className Result
 * @description 返回结果集, 需要添加则在里面自行增加
 **/
public class Result {

    private String msg;
    private String code;
    private Object data;


    public Result put(String code, Object data) {
        this.code = code;
        this.data = data;
        return this;
    }

    public Result(String code, String msg, Object data) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }


    public static Result ok(String code, String msg, Object data) {
        return new Result(code, msg, data);
    }

    public static Result ok(String code, String msg) {
        return new Result(code, msg);
    }

    public static Result ok(Object data) {
        return ok(BaseResult.SUCCESS.getCode(), BaseResult.SUCCESS.getMessage(), data);
    }

    public static Result ok(Object data, String msg) {
        return ok(BaseResult.SUCCESS.getCode(), msg, data);
    }

    public static Result ok() {
        return ok(BaseResult.SUCCESS.getCode(), BaseResult.SUCCESS.getMessage());
    }

    public Result(String code, String msg) {
        this.msg = msg;
        this.code = code;
    }

    public static Result error(String code, String msg) {

        return new Result(code, msg, null);
    }

    public static Result error() {

        return new Result("-1", "系统异常错误");
    }

    public static Result error(String msg) {

        return new Result("-1", msg);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Result() {
    }


}
