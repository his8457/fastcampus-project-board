package fastcampus.projectboard.domain;

import java.util.Date;

public class Article {
	
	private long id;
	private String title; //제목
	private String content; //본문
	private String hashtag; //해시태그
	
	private String creatAt; //생성자
	private Date createBy; //생성일시
	private String modifiedAt; //수정자
	private Date modifiedBy; //수정일시
}
