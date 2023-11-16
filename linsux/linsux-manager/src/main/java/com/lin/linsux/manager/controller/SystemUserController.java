package com.lin.linsux.manager.controller;

import com.github.pagehelper.PageInfo;
import com.lin.linsux.manager.service.SysUserService;
import com.lin.linsux.model.dto.system.SysUserDto;
import com.lin.linsux.model.entity.system.SysUser;
import com.lin.linsux.model.vo.common.Result;
import com.lin.linsux.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>TODO</p>
 *
 * @author linsz
 * @version v1.0
 * @date 2023/11/16 12:28
 */
@RestController
@RequestMapping("/admin/system/sysUser")
@Tag(name = "系统用户管理", description = "系统用户管理")
public class SystemUserController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 根据搜索内容分页查询用户列表
     */
    @PostMapping ("/findByPage/{pageNum}/{pageSize}")
    @Operation(summary = "根据搜索内容分页查询用户列表")
    public Result<PageInfo<SysUser>> findByPage(@RequestBody SysUserDto sysUserDto,
                             @PathVariable(value = "pageNum") Integer pageNum,
                             @PathVariable(value = "pageSize") Integer pageSize) {
        PageInfo<SysUser> sysUserPageInfo = sysUserService.selectPageByUserName(sysUserDto, pageNum, pageSize);
        return Result.build(sysUserPageInfo, ResultCodeEnum.SUCCESS);
    }

    /**
     * 添加用户
     */
    @PostMapping("/saveSysUser")
    @Operation(summary = "添加用户")
    public Result<Object> saveSysUser(@RequestBody SysUser sysUser) {
        sysUserService.saveSysUser(sysUser);
        return Result.build(ResultCodeEnum.SUCCESS);
    }



}
