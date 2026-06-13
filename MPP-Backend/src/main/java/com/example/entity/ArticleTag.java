package com.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "article_tags")
public class ArticleTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    private String locale;
    private String tagName;

    public ArticleTag() {}

    public ArticleTag(String locale, String tagName) {
        this.locale = locale;
        this.tagName = tagName;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getLocale() { return locale; }
    public void setLocale(String locale) { this.locale = locale; }

    public String getTagName() { return tagName; }
    public void setTagName(String tagName) { this.tagName = tagName; }
}
