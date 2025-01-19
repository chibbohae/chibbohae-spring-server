package sentosa.sentosaspringserver.common.entity;

import java.util.UUID;

import org.hibernate.annotations.Comment;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.Getter;


@MappedSuperclass
@Getter
public class UuidEntity extends BaseEntity {

	@Column(nullable = false, length = 36, unique = true)
	@Comment("UUID")
	private String uuid;

	@PrePersist
	private void onCreate() {
		this.uuid = UUID.randomUUID().toString();
	}
}

