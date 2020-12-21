package com.oac.project.system.user.service.impl;

import com.oac.framework.shiro.domain.User;
import com.oac.project.system.user.mapper.UserMapper;
import com.oac.project.system.user.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * 根据userCode 查询用户
     *
     * @param usercode
     * @return
     */
    @Override
    public User queryByName(String usercode) {
        User user = userMapper.queryByName(usercode);
        return user;
    }

}
