package com.lin.linsux.manager.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lin.linsux.manager.service.SysRoleService;
import com.lin.linsux.manager.mapper.SysRoleMapper;
import com.lin.linsux.model.dto.system.SysRoleDto;
import com.lin.linsux.model.entity.system.SysRole;
import com.lin.linsux.model.enums.CommonEnum;
import com.lin.linsux.model.exception.CommonException;
import com.lin.linsux.model.vo.common.ResultCodeEnum;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author linsz
* @description 针对表【sys_role(角色)】的数据库操作Service实现
* @createDate 2023-11-14 18:16:10
*/
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole>
    implements SysRoleService{

    /**
     * 根据传入的roleName(可为空)分页查询Role列表
     *
     * @param sysRoleDto
     * @param pageNum
     * @param pageSize
     */
    @Override
    public PageInfo<SysRole> selectPageByRoleName(SysRoleDto sysRoleDto, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<SysRole> sysRoleList = this.list(new LambdaQueryWrapper<SysRole>()
                .like(ObjectUtil.isNotEmpty(sysRoleDto.getRoleName()), SysRole::getRoleName, sysRoleDto.getRoleName())
                .eq(SysRole::getIsDeleted, CommonEnum.NOT_DELETED.getValue())
                .orderByDesc(SysRole::getId));
        return new PageInfo<>(sysRoleList);
    }

    /**
     * 根据id逻辑删除角色
     *
     * @param roleId
     */
    @Override
    public boolean removeByIdLogically(Long roleId) {
        SysRole sysRole = this.getById(roleId);
        if (ObjectUtil.isEmpty(sysRole)){
            throw new CommonException(ResultCodeEnum.DATA_ERROR);
        }
        sysRole.setIsDeleted(Integer.valueOf(CommonEnum.DELETED.getValue()));
        return this.updateById(sysRole);
    }
}




