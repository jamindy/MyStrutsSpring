package com.jamindy.IOC.util;

import com.jamindy.IOC.autowire.ByNameAutowire;

/**
 * Created by admin on 15-4-30.
 */
public class IocUtil {

    /**
     * get the class type of obj
     * @param obj
     * @return
     */
    public static Class<?> getClass(Object obj){
        if(obj instanceof Integer){
            return Integer.TYPE;
        }else if(obj instanceof Boolean){
            return Boolean.TYPE;
        }else if(obj instanceof Long){
            return Long.TYPE;
        }else if(obj instanceof Short){
            return Short.TYPE;
        }else if(obj instanceof Double){
            return Double.TYPE;
        }else if(obj instanceof Character){
            return Character.TYPE;
        }else if(obj instanceof Byte){
            return Byte.TYPE;
        }

        return obj.getClass();
    }


    public static Object getValue(String className,String data){
        if(isType(className,"Integer")){
            return Integer.parseInt(data);
        }else if(isType(className,"Boolean")){
            return Boolean.valueOf(data);
        }else if(isType(className,"Long")){
            return Long.parseLong(data);
        }else if(isType(className,"Short")){
            return Short.valueOf(data);
        }else if(isType(className,"Double")){
            return Double.valueOf(data);
        }else if(isType(className,"Float")){
            return Float.valueOf(data);
        }else if(isType(className,"Character")){
            return data.charAt(0);
        }else if(isType(className,"Byte")){
            return Byte.valueOf(data);
        }

        return data;
    }

    private static boolean isType(String className,String type){
        if(className.indexOf(type) != -1){
            return true;
        }
        return false;
    }
}
