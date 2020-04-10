package cn.lxg.community.service;

import cn.lxg.community.mapper.UserMapper;
import cn.lxg.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user) {
        User dbUser = userMapper.selectOneByAccountId(user.getAccountId());
        if(dbUser == null) {//不存在
            userMapper.insertUser(user);
        } else {
            dbUser.setAvatarUrl(user.getAvatarUrl());
            dbUser.setGmtModified(System.currentTimeMillis());
            dbUser.setToken(user.getToken());
            dbUser.setName(user.getName());
            userMapper.updateUser(dbUser);
        }
    }
}
