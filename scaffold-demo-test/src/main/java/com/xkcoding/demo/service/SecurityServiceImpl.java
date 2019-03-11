package com.xkcoding.demo.service;

import com.xkcoding.log.service.SecurityService;
import com.xkcoding.log.service.impl.DefaultScaffoldSecurityServiceImpl;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 自定义安全操作
 * </p>
 *
 * @package: com.xkcoding.demo.service
 * @description: 自定义安全操作
 * @author: yangkai.shen
 * @date: Created in 2019-03-11 10:43
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
public class SecurityServiceImpl implements SecurityService {
    /**
     * 获取当前用户姓名
     *
     * @param request request
     * @return 当前用户名
     */
    @Override
    public String getCurrentUserName(HttpServletRequest request) {
        return "管理员";
    }
}
