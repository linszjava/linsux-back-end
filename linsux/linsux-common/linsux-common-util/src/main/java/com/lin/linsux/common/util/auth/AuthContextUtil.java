package com.lin.linsux.common.util.auth;

import com.lin.linsux.model.entity.system.SysUser;

/**
 * <p>TODO</p>
 *
 * @author linsz
 * @version v1.0
 * @date 2023/11/10 17:30
 */
public class AuthContextUtil {

    //创建一个SysUser的ThreadLocal对象
    private static ThreadLocal<SysUser> threadLocal = new ThreadLocal<>();

    /**
     * 设置当前线程的SysUser
     * @param sysUser
     */
    public static void setSysUser(SysUser sysUser) {
        threadLocal.set(sysUser);
    }

    /**
     * 获取当前线程的SysUser
     * @return
     */
    public static SysUser getSysUser() {
        return threadLocal.get();
    }

    /**
     * 清除当前线程的SysUser
     */
    public static void clear() {
        threadLocal.remove();
    }
}
