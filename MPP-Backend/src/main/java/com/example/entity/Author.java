package com.example.entity;

import jakarta.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "authors")
public class Author {

    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "author_roles", joinColumns = @JoinColumn(name = "author_id"))
    @MapKeyColumn(name = "locale")
    @Column(name = "role_name", length = 255)
    private Map<String, String> role = new HashMap<>();

    private String avatarUrl;

    public Author() {}

    public Author(String id, String name, Map<String, String> role, String avatarUrl) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.avatarUrl = avatarUrl;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Map<String, String> getRole() { return role; }
    public void setRole(Map<String, String> role) { this.role = role; }

    public String getAvatarUrl() { return avatarUrl; }
    public void setAvatarUrl(String avatarUrl) { this.avatarUrl = avatarUrl; }
}
