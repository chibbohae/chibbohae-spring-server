package sentosa.sentosaspringserver.domain.client.service;

import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sentosa.sentosaspringserver.domain.client.entity.Client;
import sentosa.sentosaspringserver.domain.client.repository.ClientJpaRepository;
import sentosa.sentosaspringserver.global.entity.Gender;
import sentosa.sentosaspringserver.global.entity.KakaoUserInfo;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClientService {

	private final ClientJpaRepository clientJpaRepository;

	@Transactional
	public Client createClient(String name, Integer age, Gender gender, String telephone, String email,
		String loginId, String loginPassword, String interest, String major,
		String university) {
		Client client = Client.builder()
			.name(name)
			.age(age)
			.gender(gender)
			.telephone(telephone)
			.email(email)
			.loginId(loginId)
			.loginPassword(loginPassword)
			.interest(interest)
			.major(major)
			.university(university)
			.build();
		return clientJpaRepository.save(client);
	}

	public Optional<Client> findByLoginId(String loginId) {
		return clientJpaRepository.findByLoginId(loginId);
	}

	public Optional<Client> findByEmail(String email) {
		return clientJpaRepository.findByEmail(email);
	}

	// FIXME
	@Transactional
	public Client createClientFromKakao(KakaoUserInfo kakaoUserInfo) {
		Client client = Client.builder()
			.email(kakaoUserInfo.getEmail())
			.name(kakaoUserInfo.getNickname())
			.age(11) // ✅ age 필드는 Integer 타입이므로 "" 대신 null 사용
			.telephone("000-0000-0000") // ✅ 기본 전화번호 설정 (필요시 수정)
			.gender(Gender.M) // ✅ 기본 성별 설정 (필요시 수정)
			.loginId(kakaoUserInfo.getEmail()) // ✅ 로그인 ID는 이메일로 설정
			.loginPassword("") // ✅ 카카오는 비밀번호가 없으므로 빈 값 처리
			.interest("카카오 로그인 사용자") // ✅ 기본 관심사 설정 (필요시 수정)
			.major("카카오 유저") // ✅ 기본 전공 설정 (필요시 수정)
			.university("카카오 로그인 대학") // ✅ 기본 대학 설정 (필요시 수정)
			.build();

		return clientJpaRepository.save(client);
	}
}
