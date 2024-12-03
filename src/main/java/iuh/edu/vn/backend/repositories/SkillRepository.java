package iuh.edu.vn.backend.repositories;

import iuh.edu.vn.backend.models.Skill;
import jakarta.websocket.server.PathParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {

    @Query("SELECT s FROM Skill s WHERE s.id = ?1")
    Skill findBySkillId(Long id);

    @Query("SELECT distinct  s.skillName FROM Skill s " +
            "INNER JOIN s.jobSkills js " +
            "INNER JOIN js.job j " +
            "INNER JOIN j.company c " +
            "where j.jobName = :jobName " +
            " and c.email = :email")
    List<String> getSkillNameByJobNameOfCompanyEmail(@Param("jobName") String jobName, @PathParam("email") String email);
}
