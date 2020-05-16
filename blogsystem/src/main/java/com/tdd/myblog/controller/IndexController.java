package com.tdd.myblog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tdd.myblog.mapper.ArticleMapper;
import com.tdd.myblog.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author tudedong
 * @description
 * @date 2020-05-16 19:12:56
 */
@Controller
public class IndexController {

    @Autowired
    private ArticleMapper articleMapper;

    @RequestMapping("/index")
    public String toIndexPage(Model model,@RequestParam(required = false,defaultValue="1",value="pageNum")Integer pageNum,
                              @RequestParam(defaultValue="2",value="pageSize")Integer pageSize){

        try{
            //设置默认当前页
            if(pageNum == null || pageNum <= 0){
                pageNum = 1;
            }
            //设置默认每页条数
            if(pageSize == null || pageSize <= 0){
                pageSize = 2;
            }
            //引入分页插件，传入第几页和每页条数，默认查询所有记录
            PageHelper.startPage(pageNum,pageSize);
            //紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
            List<Article> articleList = articleMapper.selectArticleList();
            //使用PageInfo包装查询后的结果,结果list类型是Page<E>
            PageInfo<Article> pageInfo = new PageInfo<Article>(articleList,pageSize);
            //使用model传参数回前端
            model.addAttribute("pageInfo",pageInfo);
            model.addAttribute("articleList",articleList);
        }finally {
            //清理 ThreadLocal 存储的分页参数,保证线程安全
            PageHelper.clearPage();
        }
        return "client/index";
    }
}
