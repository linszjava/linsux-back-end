package com.lin.linsux.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.lin.linsux.model.dto.system.SysRoleDto;
import com.lin.linsux.model.entity.system.SysRole;

/**
* @author linsz
* @description 针对表【sys_role(角色)】的数据库操作Service
* @createDate 2023-11-14 18:16:10
*/
public interface SysRoleService extends IService<SysRole> {


    /**
     * 根据传入的roleName(可为空)分页查询Role列表
     */
    PageInfo<SysRole> selectPageByRoleName(SysRoleDto sysRoleDto, Integer pageNum, Integer pageSize);

    /**
     * 根据id逻辑删除角色
     * @param roleId
     */
    boolean removeByIdLogically(Long roleId);
}
