package com.lin.linsux.manager.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lin.linsux.common.util.redis.RedisOperator;
import com.lin.linsux.manager.mapper.SysUserMapper;
import com.lin.linsux.manager.service.SysUserService;
import com.lin.linsux.model.constant.PrefixConst;
import com.lin.linsux.model.dto.system.LoginDto;
import com.lin.linsux.model.dto.system.SysUserDto;
import com.lin.linsux.model.entity.system.SysUser;
import com.lin.linsux.model.enums.CommonEnum;
import com.lin.linsux.model.exception.CommonException;
import com.lin.linsux.model.vo.common.ResultCodeEnum;
import com.lin.linsux.model.vo.system.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author linsz
 * @description 针对表【sys_user(用户表)】的数据库操作Service实现
 * @createDate 2023-10-29 12:23:36
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
        implements SysUserService{

    @Autowired
    private RedisOperator redisOperator;


    /**
     * 实现用户登录
     *
     * @param loginDto
     * @return
     */
    @Override
    public LoginVo login(LoginDto loginDto) {
        // 1. 验证码校验
        String codeValue = redisOperator.get(PrefixConst.USER_LOGIN_VALIDATE_CODE + loginDto.getCodeKey());
        if (ObjectUtil.isEmpty(codeValue) || !StrUtil.equalsIgnoreCase(codeValue, loginDto.getCaptcha())){
            throw new CommonException(ResultCodeEnum.VALIDATECODE_ERROR);
        }
        SysUser sysUser = this.getOne(new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getUserName, loginDto.getUserName())
                .eq(SysUser::getIsDeleted, CommonEnum.NOT_DELETED.getValue()));
        if (ObjectUtil.isEmpty(sysUser)){
            throw new CommonException(ResultCodeEnum.LOGIN_ERROR);
        }
        if (!sysUser.getPassword().equals(DigestUtils.md5DigestAsHex(loginDto.getPassword().getBytes()))){
            throw new CommonException(ResultCodeEnum.LOGIN_ERROR);
        }
        // 生成令牌 token
        String tokenKey = UUID.randomUUID().toString().replace("-", "");

        redisOperator.set(PrefixConst.USER_LOGIN+tokenKey,
                JSON.toJSONString(sysUser),300000000, TimeUnit.MINUTES);
        redisOperator.delete(PrefixConst.USER_LOGIN_VALIDATE_CODE + loginDto.getCodeKey());

        return new LoginVo().setToken(tokenKey).setRefresh_token("");
    }

    /**
     * 获取用户信息
     * @param token
     * @return
     */
    @Override
    public SysUser getUserInfo(String token) {
        String userJson = redisOperator.get(PrefixConst.USER_LOGIN + token);
        return JSON.parseObject(userJson, SysUser.class);
    }

    /**
     * 退出登录
     */
    @Override
    public void logout(String token) {
        redisOperator.delete(PrefixConst.USER_LOGIN + token);
    }

    /**
     * 根据用户名分页查询用户信息
     *
     * @param sysUserDto
     * @param pageNum
     * @param pageSize
     */
    @Override
    public PageInfo<SysUser> selectPageByUserName(SysUserDto sysUserDto, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<SysUser> sysUserList = this.list(new LambdaQueryWrapper<SysUser>()
                .like(ObjectUtil.isNotEmpty(sysUserDto.getKeyword()), SysUser::getUserName, sysUserDto.getKeyword())
                .ge(ObjectUtil.isNotEmpty(sysUserDto.getCreateTimeBegin()), SysUser::getCreateTime, DateUtil.parse(sysUserDto.getCreateTimeBegin()))
                .le(ObjectUtil.isNotEmpty(sysUserDto.getCreateTimeEnd()), SysUser::getCreateTime, DateUtil.parse(sysUserDto.getCreateTimeEnd()))
                .eq(SysUser::getIsDeleted, CommonEnum.NOT_DELETED.getValue()));
        return new PageInfo<>(sysUserList);
    }

    /**
     * 添加用户
     * @param sysUser
     */
    @Override
    public void saveSysUser(SysUser sysUser) {
        boolean isExisted = (this.count(new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getUserName, sysUser.getUserName()))) > 0;
        if (isExisted){
            throw new CommonException(ResultCodeEnum.USER_NAME_IS_EXISTS);
        }
        //用户名尚未存在，加密密码存入数据库
        sysUser.setPassword(DigestUtils.md5DigestAsHex(sysUser.getPassword().getBytes()))
                .setStatus(Integer.valueOf(CommonEnum.ENABLED.getValue()));
        this.save(sysUser);
    }


}