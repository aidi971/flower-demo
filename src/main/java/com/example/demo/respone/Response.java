package com.example.demo.respone;


import lombok.Data;

@Data
public class Response {
    private int status;
    private String msg;
    private Object object;

    public Response(int status,String msg,Object object){
        this.status = status;
        this.msg = msg;
        this.object = object;
    }
}
