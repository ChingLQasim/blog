package com.qin.service;

import com.qin.domain.ResponseResult;
import com.qin.domain.entity.User;

public interface BlogLoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}
