package fastcampus.projectboard.domain;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
@EntityListeners(AuditingEntityListener.class)
@Entity
public class ArticleComment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Setter @ManyToOne(optional = false) private Article article; //게시글 (id)
	@Setter @Column(nullable = false, length = 500) private String content; //본문
	
	@CreatedDate @Column(nullable = false) private Date createdAt; //생성일시
	@CreatedBy @Column(nullable = false, length = 100) private String createdBy; //생성자
	@LastModifiedDate @Column(nullable = false) private Date modifiedAt; //수정일시
	@LastModifiedBy @Column(nullable = false, length = 100) private String modifiedBy; //수정자
	
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
