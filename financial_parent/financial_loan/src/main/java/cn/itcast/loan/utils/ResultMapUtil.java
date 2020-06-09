package cn.itcast.loan.utils;

import java.util.HashMap;
import java.util.Map;

public class ResultMapUtil {

    //返回正确的集合
    public static Map returnSuccessed(String msg){
        Map map = new HashMap();
        map.put("result", true);
        map.put("code", 0);
        map.put("msg", msg);
        return map;
    }

    public static Map returnSuccessed(String msg, Object o){
        Map returnMap = returnSuccessed(msg);
        returnMap.put("obj", o);
        return returnMap;
    }

    public static Map returnSuccessed(Object o){
        Map returnMap = returnSuccessed("");
        returnMap.put("obj", o);
        return returnMap;
    }

    public static Map returnSuccessed(){
        return returnSuccessed("");
    }

    //返回错误的集合
    public static Map returnFailed(String msg){
        return returnFailed(msg, 1);
    }

    public static Map returnFailed(String msg, Integer code){
        Map map = new HashMap();
        map.put("result", false);
        map.put("code", code);
        map.put("msg", msg);
        return map;
    }
}
