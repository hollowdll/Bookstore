package com.example.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.app.domain.Book;
import com.example.app.domain.BookRepository;
import com.example.app.domain.Category;
import com.example.app.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookstoreInitializer(BookRepository bookRepository, CategoryRepository categoryRepository) {
		return (args) -> {
			log.info("Save a couple of categories");
			categoryRepository.save(new Category("Horror"));
			categoryRepository.save(new Category("Romance"));
			categoryRepository.save(new Category("Fairy tale"));
			categoryRepository.save(new Category("Science"));
			categoryRepository.save(new Category("Fantasy"));

			log.info("Save a couple of books");
			bookRepository.save(new Book("Test book", "Test author", "123-456-789", 1999, 40,
					categoryRepository.findByName("Horror").get(0)));
			bookRepository.save(new Book("Test book 2", "Test author 2", "987-654-00", 2000, 35,
					categoryRepository.findByName("Fantasy").get(0)));
			bookRepository.save(new Book("Scam book", "Scammer123", "123123-123", 2023, 666.666,
					categoryRepository.findByName("Romance").get(0)));
			bookRepository.save(new Book("Software Engineering", "Author123", "999-666-333", 2015, 45,
					categoryRepository.findByName("Science").get(0)));

			log.info("Fetch all books with all data");
			for (Book book : bookRepository.findAll()) {
				log.info(book.toString());
			}

			log.info("Fetch all books by id and title");
			for (Book book : bookRepository.findAll()) {
				log.info("ID = " + book.getId() + ", Title = " + book.getTitle());
			}

		};
	}

}
