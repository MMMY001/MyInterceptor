package com.example.singledemo.service;

import org.springframework.stereotype.Service;

@Service
public interface UserService {

    String selectByUnameAndPassword(String uname,String password);



}
