package com.example.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "articles")
public class Article {

    @Id
    private String id;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "article_titles", joinColumns = @JoinColumn(name = "article_id"))
    @MapKeyColumn(name = "locale")
    @Column(name = "text", length = 500)
    private Map<String, String> title = new HashMap<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "article_summaries", joinColumns = @JoinColumn(name = "article_id"))
    @MapKeyColumn(name = "locale")
    @Column(name = "text", length = 1000)
    private Map<String, String> summary = new HashMap<>();

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private Author author;

    private String category;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "article_id")
    private List<ArticleTag> tagsList = new ArrayList<>();

    private Instant publishedAt;

    private Instant updatedAt;

    private String imageUrl;

    private Integer readTimeMinutes;

    public Article() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Map<String, String> getTitle() { return title; }
    public void setTitle(Map<String, String> title) { this.title = title; }

    public Map<String, String> getSummary() { return summary; }
    public void setSummary(Map<String, String> summary) { this.summary = summary; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public Author getAuthor() { return author; }
    public void setAuthor(Author author) { this.author = author; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    @JsonProperty("tags")
    public Map<String, List<String>> getTags() {
        Map<String, List<String>> map = new HashMap<>();
        if (tagsList != null) {
            for (ArticleTag tag : tagsList) {
                map.computeIfAbsent(tag.getLocale(), k -> new ArrayList<>()).add(tag.getTagName());
            }
        }
        return map;
    }

    @JsonProperty("tags")
    public void setTags(Map<String, List<String>> tags) {
        this.tagsList = new ArrayList<>();
        if (tags != null) {
            for (Map.Entry<String, List<String>> entry : tags.entrySet()) {
                String locale = entry.getKey();
                for (String val : entry.getValue()) {
                    this.tagsList.add(new ArticleTag(locale, val));
                }
            }
        }
    }

    @com.fasterxml.jackson.annotation.JsonIgnore
    public List<ArticleTag> getTagsList() { return tagsList; }
    public void setTagsList(List<ArticleTag> tagsList) { this.tagsList = tagsList; }

    public Instant getPublishedAt() { return publishedAt; }
    public void setPublishedAt(Instant publishedAt) { this.publishedAt = publishedAt; }

    public Instant getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public Integer getReadTimeMinutes() { return readTimeMinutes; }
    public void setReadTimeMinutes(Integer readTimeMinutes) { this.readTimeMinutes = readTimeMinutes; }
}
