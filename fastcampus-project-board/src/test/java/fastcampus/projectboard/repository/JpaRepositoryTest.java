package fastcampus.projectboard.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import fastcampus.projectboard.config.JpaConfig;
import fastcampus.projectboard.domain.Article;

@DisplayName("JPA 연결 테스트")
@Import(JpaConfig.class)
@DataJpaTest
class JpaRepositoryTest {
	
	private final ArticleRepository articleRepository;
	private final ArticleCommentRepository articleCommnetRepository;
	
	public JpaRepositoryTest(
			@Autowired ArticleRepository articleRepository, 
			@Autowired ArticleCommentRepository articleCommnetRepository) {
		this.articleRepository = articleRepository;
		this.articleCommnetRepository = articleCommnetRepository;
	}
	
	@DisplayName("select 테스트")
	@Test
	void givenTestData_whenSelecting_thenWorksFine() {
		//Given
		
		//When
		List<Article> articles = articleRepository.findAll();
		
		//Then
		assertThat(articles)
				.isNotNull()
				.hasSize(123);
	}
	
	@DisplayName("insert 테스트")
	@Test
	void givenTestData_whenInserting_thenWorksFine() {
		//Given
		long previousCount = articleRepository.count();
		
		//When
		Article savedArticle = articleRepository.save(Article.of("new article", "new content", "#hashtag")); 
		
		//Then
		assertThat(previousCount == articleRepository.count() + 1);
	}

	@DisplayName("update 테스트")
	@Test
	void givenTestData_whenUpdating_thenWorksFine() {
		//Given
		Article article = articleRepository.findById(1L).orElseThrow();
		String updatedHashtag = "#spiringBoot";
		article.setHashtag(updatedHashtag);
		
		//When
		Article savedArticle = articleRepository.saveAndFlush(article); 
		
		//Then
		assertThat(savedArticle).hasFieldOrPropertyWithValue("hashtag", updatedHashtag);
	}

	@DisplayName("delete 테스트")
	@Test
	void givenTestData_whenDeleting_thenWorksFine() {
		//Given
		Article article = articleRepository.findById(1L).orElseThrow();
		long previousArticleCount = articleRepository.count();
		long previousArticleCommentCount = articleCommnetRepository.count();
		long deletedCommentSize = article.getArticleComments().size();
		
		//When
		articleRepository.delete(article); 
		
		//Then
		assertThat(articleRepository.count() == previousArticleCount - 1);
	}
}
