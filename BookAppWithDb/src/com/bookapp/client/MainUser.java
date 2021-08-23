package com.bookapp.client;

import java.util.List;
import java.util.Scanner;

import com.bookapp.dao.BookInter;
import com.bookapp.bean.Book;
import com.bookapp.dao.BookImpl;
import com.bookapp.exception.BookNotFoundException;

public class MainUser {

	public static void main(String[] args) throws BookNotFoundException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the choice:\n1.Admin \n2.Customer");
		int choiceOption = scanner.nextInt();
		switch (choiceOption) {
		case 1:
			System.out.println("Enter the password: ");
			String password = scanner.next();
			if (password.equals("admin")) {
				System.out.println(
						"Enter the choice:\n1.Add Book \n2.Delete Book by Id \n3.Update price of the book by Id ");
				int choice = scanner.nextInt();
				BookInter bookService = new BookImpl();
				switch (choice) {
				case 1: {
					System.out.println("Enter the bookId:");
					int bookId = scanner.nextInt();
					System.out.println("Enter the title:");
					String title = scanner.next();
					System.out.println("Enter the Author name:");
					String author = scanner.next();
					System.out.println("Enter the Category:");
					String category = scanner.next();
					System.out.println("Enter the price:");
					double price = scanner.nextDouble();
					System.out.println("Book Added.......");
					bookService.addBook(new Book(bookId, title, author, category, price));
					break;
				}
				case 2: {
					System.out.println("Enter the bookId:");
					int bookId = scanner.nextInt();
					try {
						System.out.println("Book Deleted......." + bookService.deleteBook(bookId));
					} catch (BookNotFoundException e) {
						System.out.println(e.getMessage());
					}
					break;
				}
				case 3: {
					System.out.println("Enter the bookId:");
					int bookId = scanner.nextInt();
					System.out.println("Enter the price that needs to be updated:");
					double price = scanner.nextDouble();
					System.out.println("Book Updated......." + bookService.updateBook(bookId, price));
					break;
				}
				}
			} else {
				System.out.println("Sorry! Wrong Password! Try again");
			}
			break;
		case 2:
			System.out.println(
					"\n1.Show All Books\n2.Show Book By Id \n3.Show Books by Author name \n4.Show Books by Category");
			int choice = scanner.nextInt();
			BookInter bookService = new BookImpl();
			switch (choice) {
			case 1: {
				List<Book> bookList = bookService.getAllBooks();
				for (Book book : bookList) {
					System.out.println(book);
				}
				break;
			}
			case 2:
				System.out.println("Enter the book id:");
				int bookId = scanner.nextInt();
				try {
					System.out.println(bookService.getBookById(bookId));
				} catch (BookNotFoundException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 3:
				System.out.println("Enter the author name:");
				String authorName = scanner.next();
				try {
					System.out.println(bookService.getBookByAuthor(authorName));
				} catch (BookNotFoundException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 4:
				System.out.println("Enter the category:");
				String category = scanner.next();
				try {
					System.out.println(bookService.getBookByCategory(category));
				} catch (BookNotFoundException e) {
					System.out.println(e.getMessage());
				}
				break;

			}
			break;
		}

		scanner.close();
	}

}
