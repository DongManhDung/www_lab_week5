package iuh.edu.vn.backend.repositories;

import iuh.edu.vn.backend.models.JobSkill;
import iuh.edu.vn.backend.ids.JobSkillId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobSkillRepository extends JpaRepository<JobSkill, JobSkillId> {
    @Query("select js from JobSkill js INNER JOIN js.job j INNER JOIN js.skill s")
    List<JobSkill> findAllJobAndSkill();



    @Query("select js from JobSkill js INNER JOIN js.job j INNER JOIN js.skill s where j.company.email = :email order by j.jobName asc")
    Page<JobSkill> findAllJobAndSkillPage(String email,Pageable pageable);


    @Query("SELECT j FROM JobSkill j INNER JOIN j.job job INNER JOIN j.skill s WHERE job.company.email = :email  AND LOWER(j.job.jobName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(j.skill.skillName) LIKE LOWER(CONCAT('%', :keyword, '%')) order by job.jobName asc")
    Page<JobSkill> findByJobNameContainingIgnoreCaseOrCompanyContainingIgnoreCase(@Param("email") String email, @Param("keyword") String keyword, Pageable pageable);



    @Query(value = "select distinct js from JobSkill js INNER JOIN js.job j INNER JOIN js.skill s INNER JOIN js.skill.candidateSkills ck " +
            "where ck.can.email = ?1")
    Page<JobSkill> findJobSkillSuitableWithCandidate(String email, Pageable pageable);



}
