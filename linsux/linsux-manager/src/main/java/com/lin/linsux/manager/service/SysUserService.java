package com.lin.linsux.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.lin.linsux.model.dto.system.LoginDto;
import com.lin.linsux.model.dto.system.SysUserDto;
import com.lin.linsux.model.entity.system.SysUser;
import com.lin.linsux.model.vo.system.LoginVo;

/**
 * @author linsz
 * @description 针对表【sys_user(用户表)】的数据库操作Service
 * @createDate 2023-10-29 12:23:36
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 实现用户登录
     * @param loginDto
     * @return
     */
     LoginVo login(LoginDto loginDto);

    /**
     * 获取用户信息
     * @param token
     * @return
     */
    SysUser getUserInfo(String token);

    /**
     * 退出登录
     * @param token
     */
    void logout(String token);

    /**
     * 根据用户名分页查询用户信息
     */
    PageInfo<SysUser> selectPageByUserName(SysUserDto sysUserDto, Integer pageNum, Integer pageSize);
}
