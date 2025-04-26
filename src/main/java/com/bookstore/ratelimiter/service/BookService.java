package com.bookstore.ratelimiter.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.bookstore.ratelimiter.model.Book;
import com.bookstore.ratelimiter.repository.BookRepository;

@Service
public class BookService {
    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Cacheable(value = "books", key = "'all'")
    public List<Book> getAllBooks() {
        logger.info("Fetching all books from database");
        try {
            List<Book> books = bookRepository.findAll();
            logger.info("Found {} books", books.size());
            return books;
        } catch (Exception e) {
            logger.error("Error fetching books: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Cacheable(value = "books", key = "#id")
    public Optional<Book> getBookById(Long id) {
        logger.info("Fetching book with id: {}", id);
        try {
            Optional<Book> book = bookRepository.findById(id);
            logger.info("Book found: {}", book.isPresent());
            return book;
        } catch (Exception e) {
            logger.error("Error fetching book with id {}: {}", id, e.getMessage(), e);
            throw e;
        }
    }

    @CacheEvict(value = "books", allEntries = true)
    public Book createBook(Book book) {
        logger.info("Creating new book: {}", book.getTitle());
        try {
            Book savedBook = bookRepository.save(book);
            logger.info("Book created successfully with id: {}", savedBook.getId());
            return savedBook;
        } catch (Exception e) {
            logger.error("Error creating book: {}", e.getMessage(), e);
            throw e;
        }
    }

    @CacheEvict(value = "books", allEntries = true)
    public Optional<Book> updateBook(Long id, Book bookDetails) {
        logger.info("Updating book with id: {}", id);
        try {
            return bookRepository.findById(id)
                .map(existingBook -> {
                    existingBook.setTitle(bookDetails.getTitle());
                    existingBook.setAuthor(bookDetails.getAuthor());
                    existingBook.setIsbn(bookDetails.getIsbn());
                    existingBook.setDescription(bookDetails.getDescription());
                    existingBook.setPrice(bookDetails.getPrice());
                    existingBook.setQuantity(bookDetails.getQuantity());
                    Book updatedBook = bookRepository.save(existingBook);
                    logger.info("Book updated successfully");
                    return updatedBook;
                });
        } catch (Exception e) {
            logger.error("Error updating book with id {}: {}", id, e.getMessage(), e);
            throw e;
        }
    }

    @CacheEvict(value = "books", allEntries = true)
    public void deleteBook(Long id) {
        logger.info("Deleting book with id: {}", id);
        try {
            bookRepository.deleteById(id);
            logger.info("Book deleted successfully");
        } catch (Exception e) {
            logger.error("Error deleting book with id {}: {}", id, e.getMessage(), e);
            throw e;
        }
    }
} 