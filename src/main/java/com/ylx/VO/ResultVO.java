package com.ylx.VO;

import lombok.Data;

/**
 * http请求返回的最外层对象
 * Created by LJH
 * 2018-10-12 14:13
 */
@Data
public class ResultVO<T> {

    /** 错误码. */
    private Integer code;

    /** 提示信息. */
    private String msg;

    /** 具体内容. */
    private T data;
}
