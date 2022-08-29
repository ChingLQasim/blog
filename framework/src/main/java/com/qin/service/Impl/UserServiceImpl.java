package com.qin.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qin.domain.entity.User;
import com.qin.mapper.UserMapper;
import com.qin.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 用户表(SysUser)表服务实现类
 *
 * @author qinli
 * @since 2022-08-26 16:39:49
 */
@Service("sysUserService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
