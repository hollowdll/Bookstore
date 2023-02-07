package com.example.app.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.app.domain.Book;
import com.example.app.domain.BookRepository;
import com.example.app.domain.CategoryRepository;

@Controller
public class BookController {
	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@GetMapping(value = { "/", "/index" })
	public String indexPage() {
		return "index";
	}

	@GetMapping("/booklist")
	public String showBooklist(Model model) {
		model.addAttribute("books", bookRepository.findAll());
		return "booklist";
	}

	@GetMapping("/booklist/addbook")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", categoryRepository.findAll());
		return "addbook";
	}

	@PostMapping("/booklist/savebook")
	public String saveBook(Book book) {
		if (book != null) {
			bookRepository.save(book);
		}
		return "redirect:/booklist";
	}

	@GetMapping("/booklist/delete/{id}")
	public String deleteBook(@PathVariable("id") Long bookId, Model model) {
		bookRepository.deleteById(bookId);
		return "redirect:/booklist";
	}

	@GetMapping("/booklist/edit/{id}")
	public String openBookEditor(@PathVariable("id") Long bookId, Model model) {
		Optional<Book> book = bookRepository.findById(bookId);

		if (book.isPresent()) {
			model.addAttribute("book", book);
			model.addAttribute("categories", categoryRepository.findAll());
			return "editbook";
		} else {
			return "redirect:/booklist";
		}
	}

	// Need to pass book id from editbook.html.
	@PostMapping("/booklist/edit/editbook/{id}")
	public String editBook(Book editedBook) {
		if (editedBook != null) {
			System.out.println(editedBook.toString());
			bookRepository.save(editedBook);
		}

		return "redirect:/booklist";
	}

}
