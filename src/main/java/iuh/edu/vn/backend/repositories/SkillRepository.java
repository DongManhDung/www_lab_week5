package iuh.edu.vn.backend.repositories;

import iuh.edu.vn.backend.models.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {

    @Query("SELECT s FROM Skill s WHERE s.id = ?1")
    Skill findBySkillId(Long id);
}
