package com.example.app;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.app.domain.Category;
import com.example.app.domain.CategoryRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CategoryRepositoryTest {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Test
	public void findCategoryByName() {
		List<Category> categories = categoryRepository.findByName("Horror");
		
		assertThat(categories).hasSize(1);
		assertThat(categories.get(0).getName()).isEqualTo("Horror");
	}
	
	@Test
	public void createCategory() {
		Category category = new Category("Test category");
		categoryRepository.save(category);
		assertThat(category.getCategoryId()).isNotNull();
		assertThat(categoryRepository.findById(category.getCategoryId())).isNotEmpty();
	}
	
	@Test
	public void deleteCategory() {
		List<Category> categories = categoryRepository.findByName("Horror");
		assertThat(categories).hasSizeGreaterThan(0);
		categoryRepository.delete(categories.get(0));
		
		List<Category> categoriesAfterDeletion = categoryRepository.findByName("Horror");
		assertThat(categoriesAfterDeletion).hasSize(0);
	}
	
}
