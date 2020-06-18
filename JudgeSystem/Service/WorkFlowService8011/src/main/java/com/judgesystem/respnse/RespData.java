package com.judgesystem.respnse;

import lombok.*;

/**
 * 公共返回参数
 *
 * @param <T>
 * @author dgb
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class RespData<T> {

    private int code;

    private String msg;

    private T data;


    public static <T> RespData<T> ok(T data) {

        return RespData.sucess().body(data);
    }

    public static RspDataBuilder sucess() {

        return status(RspStatus.SUCESS);
    }

    public static RspDataBuilder failed() {

        return status(RspStatus.FAILED);
    }

    public static RspDataBuilder invalid() {

        return status(RspStatus.INVALID);
    }

    public static RspDataBuilder servererror() {

        return status(RspStatus.SERVERERROR);
    }

    public static RspDataBuilder status(RspStatus rspStatus) {

        if (rspStatus == null) {
            throw new IllegalArgumentException("RspStatus must not be null");
        }
        return new DefaultRspDataBuilder(rspStatus);
    }

    public enum RspStatus {

        RELOGIN(-1, "重新登录"),

        SUCESS(0, "成功"),

        FAILED(1, "失败"),

        INVALID(2, "参数校验错误"),

        SERVERERROR(3, "服务异常"),

        REQFORMATERROR(4, "请求报文解析异常"),

        VALIDCODEERROR(5, "验证码校验异常"),

        ACCESSEERROR(6, "无访问权限"),

        LOGINERROR(7, "登录失败");

        private int code;

        private String msg;

        RspStatus(int code, String message) {

            this.code = code;
            this.msg = message;
        }
    }

    public interface RspDataBuilder {

        <T> RespData<T> build();

        <T> RespData<T> body(T body);

        RspDataBuilder error(Throwable e);

        RspDataBuilder appendMsg(String msg);
    }

    private static class DefaultRspDataBuilder implements RspDataBuilder {

        private final int code;

        private String msg;

        public DefaultRspDataBuilder(RspStatus rspStatus) {

            this.code = rspStatus.code;
            this.msg = rspStatus.msg;
        }

        @Override
        public <T> RespData<T> build() {
            return body(null);
        }

        @Override
        public <T> RespData<T> body(T body) {

            return new RespData<>(this.code, this.msg, body);
        }

        @Override
        public RspDataBuilder error(Throwable e) {

            this.msg = e.getMessage();
            return this;
        }

        @Override
        public RspDataBuilder appendMsg(String msg) {

            this.msg = msg;
            return this;
        }
    }
}
