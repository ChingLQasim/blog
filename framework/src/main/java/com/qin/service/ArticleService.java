package com.qin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qin.domain.ResponseResult;
import com.qin.domain.entity.Article;

public interface ArticleService extends IService<Article>{

    //查询热门文章
    ResponseResult hotArticlesList();

    //分类分页查询文章
    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);

    ResponseResult getArticleDetail(Long id);
}
