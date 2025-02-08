package sentosa.sentosaspringserver.domain.partner.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sentosa.sentosaspringserver.domain.partner.entity.Partner;

@Repository
public interface PartnerJpaRepository extends JpaRepository<Partner, Long> {
	boolean existsByLoginId(String loginId);

	Optional<Partner> findByLoginId(String loginId);
}
