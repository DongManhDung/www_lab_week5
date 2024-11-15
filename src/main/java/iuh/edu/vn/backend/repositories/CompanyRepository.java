package iuh.edu.vn.backend.repositories;

import iuh.edu.vn.backend.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    boolean existsByEmail(String email);

    @Query("SELECT c FROM Company c WHERE c.email = ?1")
    Company findNameByEmail(String email);
}
