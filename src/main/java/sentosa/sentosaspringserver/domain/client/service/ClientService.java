package sentosa.sentosaspringserver.domain.client.service;

import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sentosa.sentosaspringserver.domain.client.entity.Client;
import sentosa.sentosaspringserver.domain.client.repository.ClientJpaRepository;
import sentosa.sentosaspringserver.global.entity.Gender;

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
}
