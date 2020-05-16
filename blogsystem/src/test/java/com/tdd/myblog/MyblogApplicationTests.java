package com.tdd.myblog;

import com.tdd.myblog.mapper.ArticleMapper;
import com.tdd.myblog.pojo.Article;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
class MyblogApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private ArticleMapper articleMapper;

    /**
     * 对springboot整合mybatis进行测试
     */
    @Test
    public void selectArticleList() {
        List<Article> articleList = articleMapper.selectArticleList();
        Stream.of(articleList).forEach(System.out::println);
    }
}
