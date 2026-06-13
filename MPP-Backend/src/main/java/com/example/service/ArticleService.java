package com.example.service;

import com.example.entity.Article;
import com.example.repository.ArticleRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ArticleService {

    @Inject
    ArticleRepository articleRepository;

    public List<Article> getAllArticles() {
        return articleRepository.listAll();
    }

    public Optional<Article> getArticleById(String id) {
        return articleRepository.findByIdOptional(id);
    }

    @Transactional
    public Article saveArticle(Article article) {
        articleRepository.persist(article);
        return article;
    }

    @Transactional
    public boolean deleteArticle(String id) {
        return articleRepository.deleteById(id);
    }
}
