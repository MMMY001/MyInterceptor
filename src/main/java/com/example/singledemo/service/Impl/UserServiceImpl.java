package com.example.singledemo.service.Impl;

import com.example.singledemo.dao.UserMapper;
import com.example.singledemo.service.UserService;
import com.example.singledemo.util.JwtUtil;
import com.example.singledemo.util.redisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private redisUtil redisUtil;

    private JwtUtil jwtUtil;


    @Override
    public String selectByUnameAndPassword(String uname, String password) {

        int userId;

        userId = userMapper.selectByUnameAndPassword(uname, password);
        String token = null;
        System.out.println(userId);

        if (userId!=0) {
            try {
                Long nowTime=System.currentTimeMillis();
                token=jwtUtil.createToken(uname,nowTime);
                redisUtil.saveMsg(uname,token);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return (token);
        } else {
            return null;
        }
    }
}
