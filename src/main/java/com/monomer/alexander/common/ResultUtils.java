package com.monomer.alexander.common;


import com.monomer.alexander.enums.ResultEnums;

/**
 * @Author: xiaoyu
 * @Descripstion:
 * @Date:Created in 2018/2/1 13:57
 * @Modified By:
 */
public class ResultUtils {

    public static Result<Object> success(Object obj){
        Result<Object> result= new Result<>();
        result.setCode(ResultEnums.SUCCESS.getCode());
        result.setMsg(ResultEnums.SUCCESS.getMsg());
        result.setResult(obj);
        return result;
    }

    public static Result<Object> success(){
        return success(null);
    }

    public static Result<String> error(Integer code,String msg){
        Result<String> result=new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static Result<String> error(ResultEnums resultEnums){
        Result<String> result=new Result<>();
        result.setCode(resultEnums.getCode());
        result.setMsg(resultEnums.getMsg());
        return result;
    }

}
