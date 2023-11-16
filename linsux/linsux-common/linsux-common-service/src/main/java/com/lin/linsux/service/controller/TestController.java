package com.lin.linsux.service.controller;

import cn.hutool.core.util.RandomUtil;
import com.lin.linsux.model.vo.common.Result;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>TODO</p>
 *
 * @author linsz
 * @version v1.0
 * @date 2023/10/28 15:14
 */
@Tag(name = "首页接口")
@RestController
@RequestMapping("/admin")
public class TestController {
    
    @GetMapping("/getRandomNumber")
    public Result<String> getRandomNumber() {
        String randomNumbers = RandomUtil.randomNumbers(10);
        return Result.build(randomNumbers, 200, "操作成功");
    }
}
