package com.example.resource;

import com.example.entity.Article;
import com.example.service.ArticleService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/api/articles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ArticleResource {

    @Inject
    ArticleService articleService;

    @GET
    public List<Article> getAllArticles() {
        return articleService.getAllArticles();
    }

    @GET
    @Path("/{id}")
    public Response getArticleById(@PathParam("id") String id) {
        return articleService.getArticleById(id)
                .map(article -> Response.ok(article).build())
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    public Response createArticle(Article article) {
        if (article == null || article.getId() == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Article and article ID must not be null")
                    .build();
        }
        Article saved = articleService.saveArticle(article);
        return Response.status(Response.Status.CREATED).entity(saved).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteArticle(@PathParam("id") String id) {
        boolean deleted = articleService.deleteArticle(id);
        if (deleted) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
