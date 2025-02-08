package sentosa.sentosaspringserver.domain.partner.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sentosa.sentosaspringserver.global.entity.Gender;
import sentosa.sentosaspringserver.domain.partner.repository.PartnerJpaRepository;
import sentosa.sentosaspringserver.domain.partner.entity.Partner;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PartnerAdminService {
	private final PartnerJpaRepository partnerRepository;

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
		return partnerRepository.save(partner);
	}

	public Optional<Partner> findByLoginId(String loginId) {
		return partnerRepository.findByLoginId(loginId);
	}
}
