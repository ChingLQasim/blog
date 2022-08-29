package com.qin.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class articleDetailVo extends ArticleListVo{
    //文章内容
    private String content;

}
