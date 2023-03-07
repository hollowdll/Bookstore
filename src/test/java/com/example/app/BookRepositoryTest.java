package com.example.app;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.app.domain.Book;
import com.example.app.domain.BookRepository;
import com.example.app.domain.CategoryRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BookRepositoryTest {

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Test
	public void findBookByTitle() {
		List<Book> books = bookRepository.findByTitle("Test book");
		
		assertThat(books).hasSize(1);
		assertThat(books.get(0).getTitle()).isEqualTo("Test book");
	}
	
	@Test
	public void createBook() {
		Book book = new Book(
			"Book",
			"Author",
			"123-456-789",
			2023,
			30,
			categoryRepository.findByName("Horror").get(0)
		);
		bookRepository.save(book);
		assertThat(book.getId()).isNotNull();
		assertThat(bookRepository.findById(book.getId())).isNotEmpty();
	}
	
	@Test
	public void deleteBook() {
		List<Book> testBooks = bookRepository.findByTitle("Test book");
		assertThat(testBooks).hasSizeGreaterThan(0);
		bookRepository.delete(testBooks.get(0));
		
		List<Book> testBooksAfterDeletion = bookRepository.findByTitle("Test book");
		assertThat(testBooksAfterDeletion).hasSize(0);
	}
	
}
