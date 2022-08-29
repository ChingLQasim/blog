package com.qin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qin.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;


/**
 * 用户表(SysUser)表数据库访问层
 *
 * @author qinli
 * @since 2022-08-26 16:40:53
 */
public interface UserMapper extends BaseMapper<User> {

}

