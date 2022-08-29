package com.qin.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qin.Utils.BeanCopyUtils;
import com.qin.constants.SystemConstants;
import com.qin.domain.ResponseResult;
import com.qin.domain.entity.Article;
import com.qin.domain.entity.Category;
import com.qin.domain.vo.HotArticleVo;
import com.qin.domain.vo.PageVo;
import com.qin.domain.vo.articleDetailVo;
import com.qin.mapper.ArticleMapper;
import com.qin.service.ArticleService;
import com.qin.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    @Lazy
    private CategoryService categoryService;
    @Override
    public ResponseResult hotArticlesList() {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        //要求为正式文章，即非草稿
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        //按浏览量进行排序
        queryWrapper.orderByDesc(Article::getViewCount);
        //最多只显示10篇文章
        Page<Article> page = new Page(1, 10);
        page(page,queryWrapper);
        List<Article> articles = page.getRecords();

        //bean拷贝
//        List<HotArticleVo> articleVos = new ArrayList<>();
//        articles.forEach(article -> {
//            HotArticleVo vo =new HotArticleVo();
//            BeanUtils.copyProperties(article, vo);
//            articleVos.add(vo);
//        });
        List<HotArticleVo> articlesVos = BeanCopyUtils.copyBeanList(articles, HotArticleVo.class);
        return ResponseResult.okResult(articlesVos);
    }

    //分页查询所有的文章
    @Override
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        //查询条件
        //对应分类id
        //状态是正式发布的
        //是否是置顶的，按照isTop排序
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Objects.nonNull(categoryId)&&categoryId>0 ,Article::getCategoryId, categoryId);
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        queryWrapper.orderByDesc(Article::getIsTop);
        //分页查询
        Page<Article> page = new Page<>(pageNum, pageSize);
        page(page,queryWrapper);
        //查询分类名称
        List<Article> articles = page.getRecords();
        articles.stream()
                    .map(article -> article.setCategoryName(categoryService.getById(article.getCategoryId()).getName()))
                    .collect(Collectors.toList());
        //封装查询结果为Vo
        List<Article> articleListVos = BeanCopyUtils.copyBeanList(page.getRecords(), Article.class);
        PageVo pageVo = new PageVo(articleListVos, page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult getArticleDetail(Long id) {
        //根据id查文章
        Article article = getById(id);
        //转换成Vo
        articleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article, articleDetailVo.class);
        //根据分类id查询分类名
        Long categoryId = articleDetailVo.getCategoryId();
        Category category = categoryService.getById(categoryId);
        if (category!=null){
            articleDetailVo.setCategoryName(category.getName());
        }
        //封装响应返回
        return ResponseResult.okResult(articleDetailVo);
    }
}
