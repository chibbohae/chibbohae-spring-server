package sentosa.sentosaspringserver.domain.client.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sentosa.sentosaspringserver.domain.client.entity.Client;

@Repository
public interface ClientJpaRepository extends JpaRepository<Client, Long> {
	Optional<Client> findByLoginId(String loginId);
	Optional<Client> findByEmail(String email);
}
