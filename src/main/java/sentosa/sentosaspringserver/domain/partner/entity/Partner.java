package sentosa.sentosaspringserver.domain.partner.entity;

import org.hibernate.annotations.Comment;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sentosa.sentosaspringserver.global.entity.BaseEntity;
import sentosa.sentosaspringserver.global.entity.Gender;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Where(clause = "deleted_at IS NULL") // 조회 시 삭제된 데이터 제외
@SQLDelete(sql = "UPDATE partner SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?") // 소프트 삭제
public class Partner extends BaseEntity {
	@Column
	@Comment("회사 이름")
	private String company;

	@Column
	@Comment("연차")
	private Integer yearsOfExperience;

	@Column
	@Comment("파트너 직무")
	private String position;

	@Column(columnDefinition = "TEXT")
	@Comment("파트너 자기소개")
	private String bio;

	@Builder
	public Partner(String company, Integer yearsOfExperience, String position, String bio,
		String loginId, String loginPassword, String name, Integer age, Gender gender, String telephone, String email) {
		super(name, age, gender, telephone, email, loginId, loginPassword);
		this.company = company;
		this.yearsOfExperience = yearsOfExperience;
		this.position = position;
		this.bio = bio;
	}
}
