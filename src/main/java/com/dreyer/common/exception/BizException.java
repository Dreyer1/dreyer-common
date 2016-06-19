package com.dreyer.common.exception;

import org.apache.log4j.Logger;

/**
 * @author: Dreyer
 * @date: 16/3/20 上午11:35
 * @description 业务异常基类, 所有业务异常都必须继承此异常
 * 定义异常时,需要先确定异常所属模块.例如用户模块：添加用户报错 可以定义为 [10010001] 前四位数为系统模块编号，后4位为错误代码 ,唯一
 */
public class BizException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 71608229067961620L;

    private Logger logger = Logger.getLogger(BizException.class);

    /**
     * 会话超时异常
     */
    public static final BizException SESSION_TIME_OUT = new BizException("会话超时", 9004001);


    /**
     * 异常信息
     */
    public String msg;

    /**
     * 异常码
     */
    public int code;

    public BizException() {
        super();
    }

    public BizException(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizException(Throwable cause) {
        super(cause);
    }

    public BizException(String message) {
        super(message);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    /**
     * 打印异常信息
     */
    public void print() {
        logger.info("==>BizException, code:" + this.code + ", msg:" + this.msg);
    }


}
