package com.example.demo.respone;

import lombok.Data;

@Data
public class Rep {
    private int status;
    private String msg;
    private String str;

    public Rep(int status, String msg, String str) {
        this.status = status;
        this.msg = msg;
        this.str = str;
    }
}
