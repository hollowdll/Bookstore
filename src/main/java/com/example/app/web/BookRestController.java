package com.example.app.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.app.domain.Book;
import com.example.app.domain.BookRepository;
import com.example.app.domain.CategoryRepository;

@Controller
@RequestMapping("/api")
public class BookRestController {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	// Get all
	@GetMapping("/books")
	public @ResponseBody List<Book> getAllBooks() {
		return (List<Book>) bookRepository.findAll();
	}

	// Get by id
	@GetMapping("/book/{id}")
	public @ResponseBody Optional<Book> getBookById(@PathVariable("id") Long bookId) {
		return bookRepository.findById(bookId);
	}

	// Add new
	@PostMapping("/books")
	public @ResponseBody Book addBook(@RequestBody Book newBook) {
		if (newBook.getCategory() == null) {
			newBook.setCategory(categoryRepository.findByName("Horror").get(0));
		}

		Book savedBook = bookRepository.save(newBook);
		return savedBook;
	}

}
