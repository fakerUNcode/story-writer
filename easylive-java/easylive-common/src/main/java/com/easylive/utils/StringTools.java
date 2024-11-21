package com.easylive.utils;
import com.easylive.exception.BusinessException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class StringTools {

    public static void checkParam(Object param) {
        try {
            Field[] fields = param.getClass().getDeclaredFields();
            boolean notEmpty = false;
            for (Field field : fields) {
                String methodName = "get" + StringTools.upperCaseFirstLetter(field.getName());
                Method method = param.getClass().getMethod(methodName);
                Object object = method.invoke(param);
                if (object != null && object instanceof java.lang.String && !StringTools.isEmpty(object.toString())
                        || object != null && !(object instanceof java.lang.String)) {
                    notEmpty = true;
                    break;
                }
            }
            if (!notEmpty) {
                throw new BusinessException("多参数更新，删除，必须有非空条件");
            }
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("校验参数是否为空失败");
        }
    }

    public static String upperCaseFirstLetter(String field) {
        if (isEmpty(field)) {
            return field;
        }
        //如果第二个字母是大写，第一个字母不大写
        if (field.length() > 1 && Character.isUpperCase(field.charAt(1))) {
            return field;
        }
        return field.substring(0, 1).toUpperCase() + field.substring(1);
    }

    public static boolean isEmpty(String str) {
        if (null == str || "".equals(str) || "null".equals(str) || "\u0000".equals(str)) {
            return true;
        } else if ("".equals(str.trim())) {
            return true;
        }
        return false;
    }

    //生成数字＋字母的随机
    public static final String getRandomString(Integer count){
        return RandomStringUtils.random(count,true,true);
    }
    //纯数字的随机
    public static final String getRandomNumber(Integer count){
        return RandomStringUtils.random(count,false,true);
    }

    //md5加密密码
    public static final String encodeByMd5(String orginString){
        return StringTools.isEmpty(orginString)?null: DigestUtils.md5Hex(orginString);

    }

    //文件路径检测方法
    public static boolean pathIsOk(String path) {
        if(StringTools.isEmpty(path)){
            return true;
        }
        //防止路径注入攻击（如 ../../ 越级访问文件系统）
        if(path.contains("../") || path.contains("..\\")){
            return false;
        }
        return true;
    }

    //文件后缀名提取方法
    public static String getFileSuffix(String fileName){
        if(StringTools.isEmpty(fileName) || !fileName.contains(".")){
            return null;
        }
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        return suffix;
    }
}
