package com.lin.linsux.manager.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.core.util.RandomUtil;
import com.lin.linsux.common.util.redis.RedisOperator;
import com.lin.linsux.manager.service.ValidateCodeService;
import com.lin.linsux.model.constant.PrefixConst;
import com.lin.linsux.model.vo.system.ValidateCodeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * <p>TODO</p>
 *
 * @author linsz
 * @version v1.0
 * @date 2023/11/1 17:06
 */
@Service
public class ValidateCodeServiceImpl implements ValidateCodeService {

    @Autowired
    private RedisOperator redisOperator;
    /**
     * 生成验证码 use huTool-captcha
     *
     * @return
     */
    @Override
    public ValidateCodeVo generateValidateCode() {
        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(150, 48, 4, 20);

        String codeValue = circleCaptcha.getCode(); // save to redis
        String imageBase64 = circleCaptcha.getImageBase64();

        String codeKey = RandomUtil.randomNumbers(32);
        redisOperator.set(PrefixConst.USER_LOGIN_VALIDATE_CODE+codeKey, codeValue, 5, TimeUnit.MINUTES);


        ValidateCodeVo validateCodeVo = new ValidateCodeVo();
        validateCodeVo.setCodeKey(codeKey)
                .setCodeValue(PrefixConst.DATA_IMAGE_PNG +imageBase64);
        return validateCodeVo;
    }
}
