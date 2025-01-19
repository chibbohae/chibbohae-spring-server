package sentosa.sentosaspringserver.domain.partner.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sentosa.sentosaspringserver.common.exception.CustomException;
import sentosa.sentosaspringserver.common.exception.ErrorCode;
import sentosa.sentosaspringserver.domain.partner.dao.PartnerJpaRepository;
import sentosa.sentosaspringserver.domain.partner.dto.PartnerAdminRequestDto;
import sentosa.sentosaspringserver.domain.partner.dto.PartnerAdminResponseDto;
import sentosa.sentosaspringserver.domain.partner.entity.Partner;

@Service
@RequiredArgsConstructor
public class PartnerAdminService {
	private final PartnerJpaRepository partnerRepository;

	@Transactional
	public PartnerAdminResponseDto createPartner(PartnerAdminRequestDto requestDto) {
		// 중복된 LoginId 검증
		if (partnerRepository.existsByLoginId(requestDto.loginId())) {
			throw new CustomException(ErrorCode.DUPLICATE_LOGIN_ID);
		}

		// Partner 엔티티 생성
		Partner partner = Partner.builder()
			.loginId(requestDto.loginId())
			.loginPassword(requestDto.loginPassword()) // TODO: 비밀번호 암호화 필요
			.name(requestDto.name())
			.age(requestDto.age())
			.gender(requestDto.gender())
			.telephone(requestDto.telephone())
			.company(requestDto.company())
			.yearsOfExperience(requestDto.yearsOfExperience())
			.position(requestDto.position())
			.bio(requestDto.bio())
			.build();

		// 엔티티 저장
		Partner savedPartner = partnerRepository.save(partner);

		// 응답 DTO 반환
		return new PartnerAdminResponseDto(savedPartner);
	}
}
