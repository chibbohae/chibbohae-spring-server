package sentosa.sentosaspringserver.domain.partner.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.Comment;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Index;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sentosa.sentosaspringserver.common.entity.Gender;
import sentosa.sentosaspringserver.common.entity.UuidEntity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE partner SET deleted = true WHERE id = ?") // 소프트 삭제
@Where(clause = "deleted = false") // 조회 시 삭제된 데이터 제외
@Table(
	name = "partner",
	indexes = @Index(name = "uuid_partner_index", columnList = "uuid")
)
public class Partner extends UuidEntity {
	@Column(unique = true, nullable = false)
	@Comment("로그인 아이디")
	private String loginId;

	@Column(nullable = false)
	@Comment("로그인 비밀번호")
	private String loginPassword;

	@Column(nullable = false)
	@Comment("파트너 이름")
	private String name;

	@Column(nullable = false)
	@Comment("파트너 나이")
	private Integer age;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	@Comment("파트너 성별")
	private Gender gender;

	@Column(nullable = false, length = 15)
	@Comment("파트너 전화번호")
	private String telephone;

	@Column
	@Comment("회사 이름")
	private String company;

	@Column
	@Comment("연차")
	private Integer yearsOfExperience;

	@Column
	@Comment("파트너 직무")
	private String position;

	@Lob
	@Comment("파트너 자기소개")
	private String bio;

	@Setter
	@Column(nullable = false)
	@Comment("소프트 삭제 여부")
	private boolean deleted = false;

	@Column
	@Comment("삭제 일시")
	private LocalDateTime deletedAt;

	@Builder
	public Partner(
		String loginId,
		String loginPassword,
		String name,
		Integer age,
		Gender gender,
		String telephone,
		String company,
		Integer yearsOfExperience,
		String position,
		String bio
	) {
		this.loginId = loginId;
		this.loginPassword = loginPassword;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.telephone = telephone;
		this.company = company;
		this.yearsOfExperience = yearsOfExperience;
		this.position = position;
		this.bio = bio;
	}

}
