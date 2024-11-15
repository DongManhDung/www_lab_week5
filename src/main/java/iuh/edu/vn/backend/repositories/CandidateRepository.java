package iuh.edu.vn.backend.repositories;

import iuh.edu.vn.backend.models.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    boolean existsByEmail(String email);

    @Query("SELECT distinct c FROM Candidate c WHERE c.email = ?1")
    public List<Candidate> getFullNameByEmail(String email);


}
