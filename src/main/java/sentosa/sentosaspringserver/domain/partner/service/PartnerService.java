package sentosa.sentosaspringserver.domain.partner.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sentosa.sentosaspringserver.global.entity.Gender;
import sentosa.sentosaspringserver.domain.partner.repository.PartnerJpaRepository;
import sentosa.sentosaspringserver.domain.partner.entity.Partner;
import sentosa.sentosaspringserver.global.entity.KakaoUserInfo;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PartnerService {
	private final PartnerJpaRepository partnerJpaRepository;

	@Transactional
	public Partner createPartner(String name, Integer age, Gender gender, String telephone, String email,
		String loginId, String loginPassword, String company, Integer yearsOfExperience,
		String position, String bio) {
		Partner partner = Partner.builder()
			.name(name)
			.age(age)
			.gender(gender)
			.telephone(telephone)
			.email(email)
			.loginId(loginId)
			.loginPassword(loginPassword)
			.company(company)
			.yearsOfExperience(yearsOfExperience)
			.position(position)
			.bio(bio)
			.build();
		return partnerJpaRepository.save(partner);
	}

	public Optional<Partner> findByLoginId(String loginId) {
		return partnerJpaRepository.findByLoginId(loginId);
	}

	public Optional<Partner> findByEmail(String email) {
		return partnerJpaRepository.findByEmail(email);
	}

	@Transactional
	// FIXME
	public Partner createPartnerFromKakao(KakaoUserInfo userInfo) {
		Partner newPartner = Partner.builder()
			.email(userInfo.getEmail())
			.name(userInfo.getNickname())
			.company("카카오 로그인 사용자") // 기본 값
			.yearsOfExperience(0) // 기본 값
			.position("파트너") // 기본 값
			.bio("카카오 로그인을 통해 생성된 계정입니다.") // 기본 값
			.loginId(userInfo.getEmail()) // 이메일을 로그인 ID로 사용
			.loginPassword("") // 비밀번호 없음
			.age(11)
			.gender(Gender.M)
			.telephone("01056019596")
			.build();

		return partnerJpaRepository.save(newPartner);
	}
}
