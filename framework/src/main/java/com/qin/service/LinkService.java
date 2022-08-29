package com.qin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qin.domain.ResponseResult;
import com.qin.domain.entity.Link;


/**
 * 友链(Link)表服务接口
 *
 * @author qinli
 * @since 2022-08-19 14:23:16
 */
public interface LinkService extends IService<Link> {

    ResponseResult getAllLink();
}
