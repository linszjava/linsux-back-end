package com.lin.linsux.model.exception;

import cn.hutool.core.util.StrUtil;
import com.lin.linsux.model.vo.common.ResultCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>TODO</p>
 *
 * @author linsz
 * @version v1.0
 * @date 2023/10/30 19:05
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommonException extends RuntimeException {

    private Integer code;

    private String msg;

    private ResultCodeEnum resultCodeEnum;

    public CommonException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public CommonException(ResultCodeEnum resultCodeEnum) {
        this.resultCodeEnum = resultCodeEnum;
        this.code = resultCodeEnum.getCode();
        this.msg = resultCodeEnum.getMessage();
    }


    public CommonException(String msg, Object... args){
        super(StrUtil.format(msg,args));
        this.code = 500;
        this.msg = StrUtil.format(msg,args);
    }

    public CommonException(String msg,Integer code, Object... args){
        super(StrUtil.format(msg,args));
        this.code = code;
        this.msg = StrUtil.format(msg,args);
    }


}
