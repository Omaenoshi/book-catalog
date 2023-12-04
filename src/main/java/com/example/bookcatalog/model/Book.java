package com.example.bookcatalog.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @ManyToMany
    @JoinTable(
            name = "book_genre",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres = new HashSet<>();

    public Book() {}

    public Book(Long id, String name, String description, Set<Genre> genres) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.genres = genres;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }
    public void addGenre(Genre genre) {
        if (!genres.contains(genre)) {
            genres.add(genre);
            genre.addBook(this);
        }
    }

    public void removeGenre(Genre genre) {
        if (genres.contains(genre)) {
            genres.remove(genre);
            genre.removeBook(this);
        }
    }
}
