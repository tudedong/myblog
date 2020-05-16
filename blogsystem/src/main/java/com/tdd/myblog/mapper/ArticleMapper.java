package com.tdd.myblog.mapper;

import com.tdd.myblog.pojo.Article;

import java.util.List;

/**
 * @author tudedong
 * @description 文章操作接口
 * @date 2020-05-16 18:36:28
 */
public interface ArticleMapper {

    public List<Article> selectArticleList();
}
