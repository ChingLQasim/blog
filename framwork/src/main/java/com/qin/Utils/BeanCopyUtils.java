package com.qin.Utils;

import com.qin.domain.entity.Article;
import com.qin.domain.vo.HotArticleVo;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class BeanCopyUtils {

    public static <T> T copyBean(Object source, Class<T> clazz){
        //创建对象
        T result = null;
        try {
            result = clazz.newInstance();
            //实现属性拷贝
            BeanUtils.copyProperties(source, result);
            return result;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static <V,T> List<T> copyBeanList(List<V> list, Class<T> clazz){
        return  list.stream()
                .map(o -> copyBean(o, clazz))
                .collect(Collectors.toList());
    }


    public static void main(String[] args) {
        Article article = new Article();
        article.setId(1L);
        article.setTitle("new");

        HotArticleVo hotArticleVo = copyBean(article, HotArticleVo.class);
        System.out.println(hotArticleVo.toString());
    }
}
