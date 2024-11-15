package iuh.edu.vn.backend.repositories;

import iuh.edu.vn.backend.models.Candidate;
import iuh.edu.vn.backend.models.CandidateSkill;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateSkillRepository extends JpaRepository<CandidateSkill, Long> {
    @Query("select distinct cs from CandidateSkill cs inner join cs.can c INNER JOIN cs.skill s where cs.can.email = ?1")
    List<CandidateSkill> findCandidateSkillByEmail(String email, Pageable pageable);
}
