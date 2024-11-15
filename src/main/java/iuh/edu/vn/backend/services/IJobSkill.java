package iuh.edu.vn.backend.services;

import iuh.edu.vn.backend.models.JobSkill;
import iuh.edu.vn.backend.ids.JobSkillId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IJobSkill {
    public List<JobSkill> findAllJobAndSkill();
    public void delete(JobSkillId id);
    public void add(JobSkill jobSkill);
    public void update(JobSkill jobSkill);

    Page<JobSkill> findAllJobAndSkillPage(String email, Pageable pageable);

    Page<JobSkill> searchJobsByNameOrCompany(String email, String keyword, Pageable pageable);
//    public List<JobSkill> findJobSkillSuitableWithCandidate(String email);
    Page<JobSkill> findJobSkillSuitableWithCandidate(String email, Pageable pageable);

    JobSkill findById(JobSkillId jobSkillId);
}
