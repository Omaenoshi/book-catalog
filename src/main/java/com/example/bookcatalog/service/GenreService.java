package com.example.bookcatalog.service;

import com.example.bookcatalog.model.Genre;
import com.example.bookcatalog.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenreService {
    private final GenreRepository genreRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }
    public Genre createGenre(Genre genre) {
        return genreRepository.save(genre);
    }
}
