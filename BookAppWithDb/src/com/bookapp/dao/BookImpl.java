package com.bookapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bookapp.bean.Book;
import com.bookapp.exception.AuthorNotFoundException;
import com.bookapp.exception.BookNotFoundException;
import com.bookapp.exception.CategoryNotFoundException;

public class BookImpl implements BookInter {

	@Override
	public void addBook(Book book) {
		Connection conn = ModelDao.openConnection();
		String sqlQuery = "insert into book values(?,?,?,?,?);";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sqlQuery);
			ps.setInt(1, book.getBookId());
			ps.setString(2, book.getTitle());
			ps.setString(3, book.getAuthor());
			ps.setString(4, book.getCategory());
			ps.setDouble(5, book.getPrice());
			ps.execute();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			ModelDao.closeConnection();
		}
	}

	@Override
	public boolean deleteBook(int bookId) throws BookNotFoundException {
		Connection conn = ModelDao.openConnection();
		String sqlQuery = "delete from book where bookId=?";
		PreparedStatement ps = null;
		int flag = 0;
		try {
			ps = conn.prepareStatement(sqlQuery);
			ps.setInt(1, bookId);
			flag = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			ModelDao.closeConnection();
		}
		if (flag == 0) {
			throw new BookNotFoundException("Book Not Found!");
		}
		return true;

	}

	@Override
	public boolean updateBook(int bookId, double price) {
		Connection conn = ModelDao.openConnection();
		String sqlQuery = "update book set price=? where bookId=? ;";
		PreparedStatement ps = null;
		int flag = 0;
		try {
			ps = conn.prepareStatement(sqlQuery);
			ps.setDouble(1, price);
			ps.setInt(2, bookId);

			flag = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			ModelDao.closeConnection();
		}
		if (flag != 0) {
			return true;
		}
		return false;
	}

	@Override
	public Book getBookById(int bookId) throws BookNotFoundException {
		Connection conn = ModelDao.openConnection();
		String sqlQuery = "select * from book where bookId=?;";
		PreparedStatement ps = null;
		Book book = null;
		try {
			ps = conn.prepareStatement(sqlQuery);
			ps.setInt(1, bookId);
			ResultSet rs = ps.executeQuery();
			boolean flag = rs.next();
			if (flag) {
				book = bookCreation(rs);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			ModelDao.closeConnection();
		}
		if (book == null) {
			throw new BookNotFoundException("Book Not Found!");
		}
		return book;
	}

	@Override
	public List<Book> getAllBooks() {
		Connection conn = ModelDao.openConnection();
		String sqlQuery = "select * from book";
		PreparedStatement ps = null;
		List<Book> bookList = new ArrayList<>();
		try {
			ps = conn.prepareStatement(sqlQuery);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bookList.add(bookCreation(rs));

			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			ModelDao.closeConnection();
		}
		return bookList;
	}

	@Override
	public List<Book> getBookByAuthor(String author) throws AuthorNotFoundException {
		Connection conn = ModelDao.openConnection();
		String sqlQuery = "select * from book where author=?";
		PreparedStatement ps = null;
		List<Book> bookList = new ArrayList<>();
		try {
			ps = conn.prepareStatement(sqlQuery);
			ps.setString(1, author);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bookList.add(bookCreation(rs));
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			ModelDao.closeConnection();
		}
		if (bookList.size() == 0) {
			throw new AuthorNotFoundException("Books not found for the given author!");
		}
		return bookList;
	}

	@Override
	public List<Book> getBookByCategory(String category) throws CategoryNotFoundException {
		Connection conn = ModelDao.openConnection();
		String sqlQuery = "select * from book where author=?";
		PreparedStatement ps = null;
		List<Book> bookList = new ArrayList<>();
		try {
			ps = conn.prepareStatement(sqlQuery);
			ps.setString(1, category);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bookList.add(bookCreation(rs));
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			ModelDao.closeConnection();
		}
		if (bookList.size() == 0) {
			throw new CategoryNotFoundException("Books not found for the given category!");
		}
		return bookList;
	}

	private Book bookCreation(ResultSet rs) throws SQLException {
		Book book;
		int id = rs.getInt(1);
		String title = rs.getString(2);
		String author = rs.getString(3);
		String categoryName = rs.getString(4);
		double price = rs.getDouble(5);
		book = new Book(id, title, author, categoryName, price);
		return book;
	}

}
