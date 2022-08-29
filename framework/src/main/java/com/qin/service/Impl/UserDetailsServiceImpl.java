package com.qin.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.qin.domain.entity.LoginUser;
import com.qin.domain.entity.User;
import com.qin.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询用户信息
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUserName, username);
        User user = userMapper.selectOne(lambdaQueryWrapper);
        //判断是否查询到用户， 若没有查询到则抛出异常
        Optional.ofNullable(user).orElseThrow(() -> new UsernameNotFoundException("用户不存在"));
        //返回用户信息
        //查询权限信息封装
        return new LoginUser(user);
    }
}
