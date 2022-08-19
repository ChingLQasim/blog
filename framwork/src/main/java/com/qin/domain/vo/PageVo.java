package com.qin.domain.vo;


import com.qin.domain.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageVo {
    private List<Article>  rows;
    private Long total;
}
