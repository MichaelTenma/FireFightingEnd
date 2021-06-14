package cn.ltiex.firefighting.end.Response;

import lombok.Getter;
import lombok.Setter;

public class BasicResponseEntity<T> {

    private ResponseCode status;

    @Getter
    @Setter
    private T data;

    public BasicResponseEntity(ResponseCode status) {
        this.status = status;
    }

    public BasicResponseEntity(ResponseCode status, T data) {
        this(status);
        this.data = data;
    }

    public void setStatus(ResponseCode responseCode){
        this.status = responseCode;
    }

    public ResponseCode getStatus(){
        return this.status;
    }
}