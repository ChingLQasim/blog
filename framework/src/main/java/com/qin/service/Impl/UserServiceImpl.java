package com.qin.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qin.Utils.BeanCopyUtils;
import com.qin.Utils.SecurityUtils;
import com.qin.domain.ResponseResult;
import com.qin.domain.entity.User;
import com.qin.domain.vo.UserInfoVo;
import com.qin.mapper.UserMapper;
import com.qin.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 用户表(SysUser)表服务实现类
 *
 * @author qinli
 * @since 2022-08-26 16:39:49
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public ResponseResult getUserInfo() {
        //获取当前用户id
        Long userId = SecurityUtils.getUserId();
        //根据用户查询用户信息
        User user = getById(userId);
        //封装成vo返回
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVo.class);
        return ResponseResult.okResult(userInfoVo);
    }
}
