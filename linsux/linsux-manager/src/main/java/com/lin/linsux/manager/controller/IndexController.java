package com.lin.linsux.manager.controller;

import com.lin.linsux.common.util.auth.AuthContextUtil;
import com.lin.linsux.manager.service.SysUserService;
import com.lin.linsux.manager.service.ValidateCodeService;
import com.lin.linsux.model.dto.system.LoginDto;
import com.lin.linsux.model.entity.system.SysUser;
import com.lin.linsux.model.vo.common.Result;
import com.lin.linsux.model.vo.common.ResultCodeEnum;
import com.lin.linsux.model.vo.system.LoginVo;
import com.lin.linsux.model.vo.system.ValidateCodeVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>TODO</p>
 *
 * @author linsz
 * @version v1.0
 * @date 2023/10/29 02:23
 */
@Tag(name = "用户接口控制器")
@RestController
@RequestMapping("/admin/system/index")
public class IndexController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private ValidateCodeService validateCodeService;



    @Operation(summary = "用户登录接口")
    @PostMapping("/login")
    public Result<LoginVo> login(@RequestBody LoginDto loginDto) {
        LoginVo loginVo = sysUserService.login(loginDto);
        return Result.build(loginVo, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "用户信息查询")
    @GetMapping("/getUserInfo")
    public Result<SysUser> getUserInfo() {
        return Result.build(AuthContextUtil.getSysUser(), ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "生成验证码")
    @GetMapping("/generateValidateCode")
    public Result<ValidateCodeVo> generateValidateCode() {
        return Result.build(validateCodeService.generateValidateCode(),
                ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "用户退出登录")
    @GetMapping("/logout")
    public Result<Object> logout(@RequestHeader(name = "token")String token) {
        sysUserService.logout(token);
        return Result.build(ResultCodeEnum.SUCCESS);
    }




}
