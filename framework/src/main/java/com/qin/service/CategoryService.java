package com.qin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qin.domain.ResponseResult;
import com.qin.domain.entity.Category;


/**
 * 分类表(Category)表服务接口
 *
 * @author qinli
 * @since 2022-08-18 19:29:15
 */
public interface CategoryService extends IService<Category> {

    ResponseResult getCategoryList();
}
