package iuh.edu.vn.backend.services;

import iuh.edu.vn.backend.models.Job;

import java.util.List;

public interface IJob {
    public List<Job> getJobsByCompanyId(Long companyId);
    public void add(Job job);
    public void update(Job job);
    public void delete(long id);
    public List<Job> findAllJob();
    List<Job> getAllJobAndSkillSuitable(String email);
    Job findById(Long id);
    public List<?> findAllJobDistinct();
}
