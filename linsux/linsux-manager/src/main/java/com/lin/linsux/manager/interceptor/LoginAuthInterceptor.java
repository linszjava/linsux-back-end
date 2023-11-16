package com.lin.linsux.manager.interceptor;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.lin.linsux.common.util.auth.AuthContextUtil;
import com.lin.linsux.common.util.redis.RedisOperator;
import com.lin.linsux.model.constant.PrefixConst;
import com.lin.linsux.model.entity.system.SysUser;
import com.lin.linsux.model.vo.common.Result;
import com.lin.linsux.model.vo.common.ResultCodeEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

/**
 * <p>TODO</p>
 *
 * @author linsz
 * @version v1.0
 * @date 2023/11/10 17:56
 */
@Component
public class LoginAuthInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisOperator redisOperator;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断是否是HandlerMethod
        String method = request.getMethod();
        if ("OPTIONS".equals(method)) {
            return true;
        }
        // 获取请求路径
        String requestUri = request.getRequestURI();

        // 放行Knife4j的Swagger UI路径
        if (requestUri.contains("swagger-ui.html") || requestUri.contains("/webjars/springfox-swagger-ui/") || requestUri.contains("/swagger-resources")) {
            return true;
        }

        // 放行Knife4j生成API文档的路径
        if (requestUri.contains("/v3/api-docs")) {
            return true;
        }

        //获取token
        String token = request.getHeader("token");
        if (StrUtil.isEmpty(token)) {
            responseNoLoginInfo(response);
            return false;
        }
        //token不为空判断token的合法性
        String userInfoJsonStr = redisOperator.get(PrefixConst.USER_LOGIN + token);
        if (StrUtil.isEmpty(userInfoJsonStr)) {
            responseNoLoginInfo(response);
            return false;
        }
        // 保存到线程中
        SysUser sysUser = JSON.parseObject(userInfoJsonStr, SysUser.class);
        AuthContextUtil.setSysUser(sysUser);

        redisOperator.expire(PrefixConst.USER_LOGIN + token, 30, TimeUnit.MINUTES);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        AuthContextUtil.clear();
    }

    private void responseNoLoginInfo(HttpServletResponse response) {
        Result<Object> resultObj = Result.build(null, ResultCodeEnum.LOGIN_AUTH);
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = null;
        try {
             writer = response.getWriter();
            writer.print(JSON.toJSONString(resultObj));
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            IoUtil.close(writer);
        }
    }
}
