package com.py.search.service;

import com.py.search.pojo.Article;
import com.py.search.dao.ArticleDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ArticleService {
    @Resource(name = "articleDao")
    private ArticleDao dao;

    public void save(Article article) {
        dao.save(article);
    }

    public Page<Article> findByKeyWord(String keyWord, int currentPage, int pageSize) {
        Pageable pageable = PageRequest.of(currentPage - 1, pageSize);
        return dao.findByTitleOrContentLike(keyWord, keyWord, pageable);
    }
}
