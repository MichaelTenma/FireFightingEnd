package cn.ltiex.firefighting.end.Response;


public enum ResponseCode {
    SUCCESS(200,"请求成功"),
    FAIL(400,"请求失败"),
    ParamWrong(800,"参数错误"),
    ;

    private final int code;
    private final String msg;

    ResponseCode(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode(){return this.code;}
    public String getMsg(){return this.msg;}
}