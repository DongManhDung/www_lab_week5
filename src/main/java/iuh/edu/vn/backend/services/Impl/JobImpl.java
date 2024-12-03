package iuh.edu.vn.backend.services.Impl;

import iuh.edu.vn.backend.models.Job;
import iuh.edu.vn.backend.repositories.JobRepository;
import iuh.edu.vn.backend.services.IJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobImpl implements IJob {

    @Autowired
    private JobRepository jobRepository;

    public List<Job> getJobsByCompanyId(Long companyId){
        return jobRepository.getJobsByCompanyId(companyId);
    }

    @Override
    public void add(Job job) {
        jobRepository.save(job);
    }

    @Override
    public void update(Job job) {
        jobRepository.save(job);
    }

    @Override
    public void delete(long id) {
        jobRepository.deleteById(id);
    }

    @Override
    public List<Job> findAllJob() {
        return jobRepository.findAll();
    }

    @Override
    public List<Job> getAllJobAndSkillSuitable(String email) {
        return jobRepository.getAllJobAndSkillSuitable(email);
    }

    @Override
    public Job findById(Long id) {
        return jobRepository.findByJobId(id);
    }

    @Override
    public List<?> findAllJobDistinct() {
        return jobRepository.findAllJobDistinct();
    }


}
