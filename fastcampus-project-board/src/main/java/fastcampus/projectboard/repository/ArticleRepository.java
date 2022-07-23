package fastcampus.projectboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fastcampus.projectboard.domain.Article;

public interface ArticleRepository extends JpaRepository<Article, Long>{

}
