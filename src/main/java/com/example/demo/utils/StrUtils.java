package com.example.demo.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StrUtils {
    public static List<String> getMsg(String msg) {

        List<String> list = new ArrayList<String>();
        Pattern p = Pattern.compile("(\\()([0-9a-zA-Z\\.\\/\\=])*(\\))");
        Matcher m = p.matcher(msg);
        while (m.find()) {
            list.add(m.group(0).substring(1, m.group().length() - 1));
        }
        return list;
    }


}

