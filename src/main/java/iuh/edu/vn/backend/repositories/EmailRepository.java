package iuh.edu.vn.backend.repositories;

import iuh.edu.vn.backend.models.mailEntity.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmailRepository extends JpaRepository<Email, Long> {
    List<Email> findByReceiver(String receiver);
}
