package iuh.edu.vn.backend.repositories;

import iuh.edu.vn.backend.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    boolean existsByEmail(String email);
    Company findNameByEmail(String email);
}
