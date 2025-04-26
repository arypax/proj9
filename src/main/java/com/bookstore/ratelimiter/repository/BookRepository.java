package com.bookstore.ratelimiter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.ratelimiter.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
} 