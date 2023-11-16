package com.lin.linsux.manager.controller;

import com.github.pagehelper.PageInfo;
import com.lin.linsux.manager.service.SysRoleService;
import com.lin.linsux.model.dto.system.SysRoleDto;
import com.lin.linsux.model.entity.system.SysRole;
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
 * @date 2023/11/14 18:13
 */
@RestController
@RequestMapping("/admin/system/sysRole")
@Tag(name = "角色接口控制器")
public class SystemRoleController {

    @Autowired
    private SysRoleService sysRoleService;


    @PostMapping("/findByPage/{pageNum}/{pageSize}")
    @Operation(summary = "根据输入的角色名分页查询角色列表")
    public Result<PageInfo<SysRole>> findByPage(@RequestBody SysRoleDto sysRoleDto,
                                                @PathVariable(value = "pageNum") Integer pageNum,
                                                @PathVariable(value = "pageSize") Integer pageSize) {
        return Result.build(sysRoleService.selectPageByRoleName(sysRoleDto, pageNum, pageSize), ResultCodeEnum.SUCCESS);
    }

    /**
     * 添加角色
     */
    @PostMapping("/saveSysRole")
    @Operation(summary = "添加角色")
    public Result saveSysRole(@RequestBody SysRole sysRole) {
        sysRoleService.save(sysRole);
        return Result.build(ResultCodeEnum.SUCCESS);
    }

    /**
     * 修改角色
     */
    @PutMapping("/updateSysRole")
    @Operation(summary = "修改角色")
    public Result updateSysRole(@RequestBody SysRole sysRole) {
        sysRoleService.updateById(sysRole);
        return Result.build(ResultCodeEnum.SUCCESS);
    }

    /**
     * 根据id逻辑删除角色
     */
    @DeleteMapping("/deleteSysRole/{roleId}")
    @Operation(summary = "根据id逻辑删除角色")
    public Result deleteSysRole(@PathVariable(value = "roleId") Long roleId) {
        sysRoleService.removeByIdLogically(roleId);
        return Result.build(ResultCodeEnum.SUCCESS);
    }





}
