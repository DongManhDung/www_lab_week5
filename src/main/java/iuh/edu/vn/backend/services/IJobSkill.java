package iuh.edu.vn.backend.services;

import iuh.edu.vn.backend.models.JobSkill;
import iuh.edu.vn.backend.ids.JobSkillId;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IJobSkill {
    public List<JobSkill> findAllJobAndSkill();
    public void delete(JobSkillId id);
    public void add(JobSkill jobSkill);
    public void update(JobSkill jobSkill);
    public Page<JobSkill> findAllJobAndSkillPage(int pageNo, int pageSize, String sortBy,
    String sortDirection);
    public List<JobSkill> searchJobsByNameOrCompany(String keyword);
    public List<JobSkill> findJobSkillSuitableWithCandidate(String email);

    JobSkill findById(JobSkillId jobSkillId);
}
