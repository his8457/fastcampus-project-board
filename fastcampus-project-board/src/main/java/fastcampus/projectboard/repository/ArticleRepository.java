package fastcampus.projectboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import fastcampus.projectboard.domain.Article;

@RepositoryRestResource
public interface ArticleRepository extends JpaRepository<Article, Long>{

}
