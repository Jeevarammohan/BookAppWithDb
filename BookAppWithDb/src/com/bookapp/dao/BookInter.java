package com.bookapp.dao;

import java.util.List;

import com.bookapp.bean.Book;
import com.bookapp.exception.BookNotFoundException;

public interface BookInter {

	void addBook(Book book);

	boolean deleteBook(int bookId) throws BookNotFoundException;

	boolean updateBook(int bookId, double price);

	Book getBookById(int bookId) throws BookNotFoundException;

	List<Book> getAllBooks();

	List<Book> getBookByAuthor(String author) throws BookNotFoundException;

	List<Book> getBookByCategory(String category) throws BookNotFoundException;
//	List<Book> getBooksByLesserPrice(double price) throws BookNotFoundException;

}
