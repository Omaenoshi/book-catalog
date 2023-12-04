package com.example.bookcatalog.controller;

import com.example.bookcatalog.model.Book;
import com.example.bookcatalog.service.BookService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<?> createBook(@RequestBody Book book) {
        try {
            Book createdBook = bookService.createBook(book);
            return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
        } catch (EntityNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity<Book> updateBook(@RequestBody Book book) {
        Book updatedBook = bookService.updateBook(book.getId(), book);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        try {
            bookService.deleteBookById(id);
            return ResponseEntity.ok("Book with ID " + id + " has been deleted.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
