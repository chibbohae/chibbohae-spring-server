package sentosa.sentosaspringserver.domain.client.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sentosa.sentosaspringserver.domain.client.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
	boolean existsByLoginId(String loginId);

	Optional<Client> findByLoginId(String loginId);
}
