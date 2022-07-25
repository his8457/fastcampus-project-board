package fastcampus.projectboard.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Table(indexes = {
		@Index(columnList = "content"),
		@Index(columnList = "createdAt"),
		@Index(columnList = "createdBy")
})
@Entity
public class ArticleComment extends AuditingFields {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Setter @ManyToOne(optional = false) private Article article; //게시글 (id)
	@Setter @Column(nullable = false, length = 500) private String content; //본문
	
	protected ArticleComment() {}

	public ArticleComment(Article article, String content) {
		this.article = article;
		this.content = content;
	}
	
	public static ArticleComment of(Article article, String content) {
		return new ArticleComment(article, content);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof ArticleComment)) {
			return false;
		}
		ArticleComment other = (ArticleComment) obj;
		return id != null && Objects.equals(id, other.id);
	}
}
