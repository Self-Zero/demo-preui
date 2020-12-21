package com.oac.project.system.user.mapper;

import com.oac.framework.shiro.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    /**
     * 根据userCode 查询用户
     *
     * @param usercode
     * @return
     */
    User queryByName(String usercode);

}
