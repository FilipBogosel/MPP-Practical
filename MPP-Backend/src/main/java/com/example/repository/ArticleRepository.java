package com.example.repository;

import com.example.entity.Article;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ArticleRepository implements PanacheRepositoryBase<Article, String> {
    // Custom query methods can be defined here later
}
