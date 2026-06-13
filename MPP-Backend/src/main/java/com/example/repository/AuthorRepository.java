package com.example.repository;

import com.example.entity.Author;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AuthorRepository implements PanacheRepositoryBase<Author, String> {
    // Custom query methods can be defined here later
}
