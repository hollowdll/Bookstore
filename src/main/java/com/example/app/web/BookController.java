package com.example.app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping("booklist/addbook")
    public String addBook(Model model){
    	model.addAttribute("book", new Book());
        return "addbook";
    }
	
	@PostMapping("booklist/savebook")
    public String saveBook(Book book){
        repository.save(book);
        return "redirect:/booklist";
    }
	
}
