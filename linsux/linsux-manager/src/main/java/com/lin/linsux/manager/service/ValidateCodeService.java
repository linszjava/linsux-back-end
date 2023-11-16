package com.lin.linsux.manager.service;

import com.lin.linsux.model.vo.system.ValidateCodeVo;

/**
 * <p>TODO</p>
 *
 * @author linsz
 * @version v1.0
 * @date 2023/11/1 17:05
 */
public interface ValidateCodeService {

    /**
     * 生成验证码 use huTool-captcha
     * @return
     */
    ValidateCodeVo generateValidateCode() ;
}
