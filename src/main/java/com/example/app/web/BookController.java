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

@Controller
public class BookController {
	@Autowired
	private BookRepository repository; 

	@GetMapping(value = {"/", "/index"})
	public String indexPage() {
		return "index";
	}
	
	@GetMapping("/booklist")
	public String showBooklist(Model model) {
		model.addAttribute("books", repository.findAll());
		return "booklist";
	}
	
	@GetMapping("/booklist/addbook")
    public String addBook(Model model){
    	model.addAttribute("book", new Book());
        return "addbook";
    }
	
	@PostMapping("/booklist/savebook")
    public String saveBook(Book book){
        repository.save(book);
        return "redirect:/booklist";
    }
	
	@GetMapping("/booklist/delete/{id}")
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
    	repository.deleteById(bookId);
        return "redirect:/booklist";
    }
	
	@GetMapping("/booklist/edit/{id}")
    public String openBookEditor(@PathVariable("id") Long bookId, Model model) {
		Optional<Book> book = repository.findById(bookId);
		
		if (book.isPresent()) {
			model.addAttribute("book", book);
			return "editbook";
		} else {
			return "redirect:/booklist";
		}
    }
	
	// Need to pass book id from editbook.html.
	// Otherwise book won't have id,
	// and we cannot modify the existing book
	@PostMapping("/booklist/edit/editbook/{id}")
	public String editBook(Book editedBook) {
		System.out.println(editedBook.toString());
		
		repository.save(editedBook);
		
		return "redirect:/booklist";
	}
	
}
