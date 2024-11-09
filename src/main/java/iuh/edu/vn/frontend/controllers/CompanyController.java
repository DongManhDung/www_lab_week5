package iuh.edu.vn.frontend.controllers;

import iuh.edu.vn.backend.enums.SkillLevel;
import iuh.edu.vn.backend.enums.SkillType;
import iuh.edu.vn.backend.ids.JobSkillId;
import iuh.edu.vn.backend.models.*;
import iuh.edu.vn.backend.repositories.JobRepository;
import iuh.edu.vn.backend.repositories.JobSkillRepository;
import iuh.edu.vn.backend.repositories.SkillRepository;
import iuh.edu.vn.backend.services.ICompany;
import iuh.edu.vn.backend.services.IJob;
import iuh.edu.vn.backend.services.IJobSkill;
import iuh.edu.vn.backend.services.ISkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private IJobSkill iJobSkill;

    @Autowired
    private IJob iJob;

    @Autowired
    private ISkill iSkill;

    @Autowired
    private ICompany iCompany;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private JobSkillRepository jobSkillRepository;

    @GetMapping()
    public String viewCompanyJobs(Model model, @RequestParam(value = "keyword", required = false) String keyword) {
        List<JobSkill> jobs;
        if (keyword != null && !keyword.isEmpty()) {
            jobs = iJobSkill.searchJobsByNameOrCompany(keyword);
        } else {
            jobs = iJobSkill.findAllJobAndSkill();
        }
        model.addAttribute("jobs", jobs);
        model.addAttribute("keyword", keyword);
        return "companies/company_job_list";
    }

    @GetMapping("/new")
    public String showJobForm(Model model) {
        model.addAttribute("job", new Job());
        model.addAttribute("skill", new Skill());
        model.addAttribute("jobSkill", new JobSkill());
        model.addAttribute("allSkills", SkillLevel.values());
        model.addAttribute("allSkillTypes", SkillType.values());
        return "companies/create_new_job";
    }

//    Them ham cap nhat cong viec da chon
    @GetMapping("/update-job/{jobId}/{skillId}")
    public String showUpdateForm(@PathVariable("jobId") Long jobId, @PathVariable("skillId") Long skillId, Model model) {
        Job job = iJob.findById(jobId);
        Skill skill = iSkill.findById(skillId);

        JobSkillId jobSkillId = new JobSkillId(jobId, skillId);
        JobSkill jobSkill = iJobSkill.findById(jobSkillId);

        if (job != null && skill != null && jobSkill != null) {
            model.addAttribute("job", job);
            model.addAttribute("skill", skill);
            model.addAttribute("jobSkill", jobSkill);
            model.addAttribute("allSkills", SkillLevel.values());
            model.addAttribute("allSkillTypes", SkillType.values());
            return "companies/update_job";
        } else {
            return "redirect:/company";
        }
    }

    //Update job theo jobID, skillID va jobSkillID da chon
    @PostMapping("/update-job/{jobId}/{skillId}")
    public String updateJob(@PathVariable("jobId") Long jobId, @PathVariable("skillId") Long skillId, @ModelAttribute("job") Job job, @ModelAttribute("skill") Skill skill, @ModelAttribute("jobSkill") JobSkill jobSkill) {
        JobSkillId jobSkillId = new JobSkillId(jobId, skillId);
        iJob.update(job);
        iSkill.update(skill);
        iJobSkill.update(new JobSkill(job, skill, jobSkillId, jobSkill.getSkillLevel()));
        return "redirect:/company";
    }

    @PostMapping
    public String saveJob(@ModelAttribute("job") Job job, @ModelAttribute("skill") Skill skill, @ModelAttribute("jobSkill") JobSkill jobSkill) {
        JobSkillId jobSkillId = new JobSkillId(job.getId(), skill.getId());
        jobRepository.save(job);
        skillRepository.save(skill);
        jobSkillRepository.save(new JobSkill(job, skill, jobSkillId, jobSkill.getSkillLevel()));
        return "redirect:/company";
    }

    //Delete
    @GetMapping("/delete/{jobId}/{skillId}")
    public String deleteJob(@PathVariable("jobId") Long jobId, @PathVariable("skillId") Long skillId) {
        JobSkillId jobSkillId = new JobSkillId(jobId, skillId);
        iJobSkill.delete(jobSkillId);
        return "redirect:/company";
    }

    //Paging Controller
    @GetMapping("/listJobPage")
    public String showJobListPaging(Model model,
                                          @RequestParam("page") Optional<Integer> page,
                                          @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(12);
        Page<JobSkill> jobPage = (Page<JobSkill>) iJobSkill.findAllJobAndSkillPage(currentPage - 1, pageSize, "id" , "asc");


        model.addAttribute("jobPage", jobPage);

        int totalPages = jobPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "companies/company_job_list_paging";
    }


}
