package iuh.edu.vn.backend.repositories;

import iuh.edu.vn.backend.models.CandidateSkill;
import iuh.edu.vn.backend.models.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> getJobsByCompanyId(Long companyId);

    @Query("select j from Job j INNER JOIN " +
            "j.jobSkills js INNER JOIN " +
            "js.skill s INNER JOIN " +
            "s.candidateSkills cs INNER JOIN cs.can c ")
    public List<Job> getAllJobAndSkillSuitable(String email);

    @Query("select j from Job j where j.id = :id")
    public Job findByJobId(@Param("id") Long id);

    @Query("select distinct j.jobName from Job j")
    public List<?> findAllJobDistinct();
}
