package com.oac.project.system.user.service;

import com.oac.framework.shiro.domain.User;

public interface UserService {

    /**
     * 根据userCode 查询用户
     *
     * @param usercode
     * @return
     */
    User queryByName(String usercode);

}
