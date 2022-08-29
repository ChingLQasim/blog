package com.qin.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qin.Utils.BeanCopyUtils;
import com.qin.constants.SystemConstants;
import com.qin.domain.ResponseResult;
import com.qin.domain.entity.Article;
import com.qin.domain.entity.Category;
import com.qin.domain.vo.CategoryVo;
import com.qin.mapper.CategoryMapper;
import com.qin.service.ArticleService;
import com.qin.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 分类表(Category)表服务实现类
 *
 * @author qinli
 * @since 2022-08-18 19:29:17
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Autowired
    private ArticleService articleService;

    @Override
    public ResponseResult getCategoryList() {
        //查询文章表,状态为已经发布
        LambdaQueryWrapper<Article> articleQueryWrapper = new LambdaQueryWrapper<>();
        articleQueryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        List<Article> articlesList = articleService.list(articleQueryWrapper);
        //获取文章Id,并且对文章Id去重
        Set<Long> catergoryIds = articlesList.stream()
                .map(Article::getCategoryId)
                .collect(Collectors.toSet());
        //查询分类表
        List<Category> categoryList = listByIds(catergoryIds);
        List<Category> categorySet = categoryList.stream()
                .filter(category -> SystemConstants.CATEGORY_STATUS_NORMAL.equals(category.getStatus()))
                .collect(Collectors.toList());
        //封装Vo
        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(categorySet, CategoryVo.class);
        return ResponseResult.okResult(categoryVos);
    }
}
