package com.qin.controller;

import com.qin.domain.ResponseResult;
import com.qin.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    /*
    热门文章列表
     */
    @GetMapping("hotArticleList")
    public ResponseResult hotArticleList(){
        ResponseResult result = articleService.hotArticlesList();
        return result;
    }

    /*
    获取文章列表
     */
    @GetMapping("articleList")
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId){
        return articleService.articleList(pageNum, pageSize, categoryId);

    }




}
