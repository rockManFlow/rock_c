package com.kuark.tool.advance.advance20201105util.formal;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

/**
 * json与Object的转换
 */
public class ObjectToJson {
    private static final Logger logger=Logger.getLogger(ObjectToJson.class);

    //实体(非List集合实体)转换成json串
    public static String object2JsonStr(Object ob){
        JSONObject json=JSONObject.fromObject(ob);
        return json.toString();
    }
    //list转json串
    public static String list2JsonStr(Object ob){
        JSONArray json= JSONArray.fromObject(ob);
        return json.toString();
    }
    //json串转换成json对象
    public static JSONObject string2Json(String st){
        JSONObject jsonObject = JSONObject.fromObject(st);
        return jsonObject;
    }
    //json对象转换成指定实体
    public static Object jsonObject2Object(JSONObject json,Class c){
        return JSONObject.toBean(json,c);
    }
}
