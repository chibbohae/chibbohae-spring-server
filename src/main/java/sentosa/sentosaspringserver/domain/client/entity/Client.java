package sentosa.sentosaspringserver.domain.client.entity;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@SQLDelete(sql = "UPDATE client SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?") // 소프트 삭제
public class Client extends BaseEntity {
	@Column
	private String interest;

	@Column
	private String major;

	@Column
	private String university;

	@Builder
	public Client(String name, Integer age, Gender gender, String telephone, String email, String loginId, String loginPassword, String interest, String major, String university) {
		super(name, age, gender, telephone, email, loginId, loginPassword);
		this.interest = interest;
		this.major = major;
		this.university = university;
	}

	public void updateUniversity(String university) {
		this.university = university;
	}

	public void updateMajor(String major) {
		this.major = major;
	}

	public void updateInterest(String interest) {
		this.interest = interest;
	}
}
