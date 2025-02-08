package sentosa.sentosaspringserver.global.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;

import lombok.Getter;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public abstract class BaseEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Comment("ID")
	private Long id;

	@Column(nullable = false)
	@Comment("이름")
	private String name;

	@Column(nullable = false)
	@Comment("나이")
	private Integer age;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	@Comment("성별")
	private Gender gender;

	@Column(nullable = false, length = 15)
	@Comment("전화번호")
	private String telephone;

	@Column(nullable = false, unique = true)
	@Comment("이메일")
	private String email;

	@Column(unique = true, nullable = false)
	@Comment("로그인 아이디")
	private String loginId;

	@Column(nullable = false)
	@Comment("암호화된 비밀번호")
	private String loginPassword;

	@CreatedDate
	@Column(nullable = false, updatable = false)
	@Comment("생성일")
	private LocalDateTime createdDate;

	@LastModifiedDate
	@Column(nullable = false)
	@Comment("수정일")
	private LocalDateTime modifiedDate;

	@Column(nullable = true)
	@Comment("삭제일 (소프트 삭제)")
	private LocalDateTime deletedAt;

	protected BaseEntity() {
	}

	protected BaseEntity(String name, Integer age, Gender gender, String telephone, String email, String loginId, String loginPassword) {
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.telephone = telephone;
		this.email = email;
		this.loginId = loginId;
		this.loginPassword = loginPassword;
	}
}
