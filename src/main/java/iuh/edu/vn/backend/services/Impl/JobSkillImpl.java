package iuh.edu.vn.backend.services.Impl;

import iuh.edu.vn.backend.models.Candidate;
import iuh.edu.vn.backend.models.JobSkill;
import iuh.edu.vn.backend.ids.JobSkillId;
import iuh.edu.vn.backend.repositories.JobSkillRepository;
import iuh.edu.vn.backend.services.IJobSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobSkillImpl implements IJobSkill {
    @Autowired
    private JobSkillRepository jobSkillRepository;


    @Override
    public List<JobSkill> findAllJobAndSkill() {
        return jobSkillRepository.findAllJobAndSkill();
    }

    @Override
    public void delete(JobSkillId id) {
        jobSkillRepository.deleteById(id);
    }

    @Override
    public void add(JobSkill jobSkill) {
        jobSkillRepository.save(jobSkill);
    }

    @Override
    public void update(JobSkill jobSkill) {
        jobSkillRepository.save(jobSkill);
    }

    @Override
    public Page<JobSkill> findAllJobAndSkillPage(int pageNo, int pageSize, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return jobSkillRepository.findAllJobAndSkillPage(pageable);
    }

    @Override
    public List<JobSkill> searchJobsByNameOrCompany(String keyword) {
        return jobSkillRepository.findByJobNameContainingIgnoreCaseOrCompanyContainingIgnoreCase(keyword);
    }

//    @Override
//    public List<JobSkill> findJobSkillSuitableWithCandidate(String email) {
//        return jobSkillRepository.findJobSkillSuitableWithCandidate(email);
//    }

    public Page<JobSkill> findJobSkillSuitableWithCandidate(String email, Pageable pageable) {
        return jobSkillRepository.findJobSkillSuitableWithCandidate(email, pageable);
    }

    @Override
    public JobSkill findById(JobSkillId jobSkillId) {
        return jobSkillRepository.findById(jobSkillId).get();
    }
}
