package iuh.edu.vn.frontend.controllers;

import iuh.edu.vn.backend.enums.SkillLevel;
import iuh.edu.vn.backend.enums.SkillType;
import iuh.edu.vn.backend.ids.JobSkillId;
import iuh.edu.vn.backend.machineLearning.MLService;
import iuh.edu.vn.backend.machineLearning.ModelTrainer;
import iuh.edu.vn.backend.models.*;
import iuh.edu.vn.backend.repositories.CompanyRepository;
import iuh.edu.vn.backend.repositories.JobRepository;
import iuh.edu.vn.backend.repositories.JobSkillRepository;
import iuh.edu.vn.backend.repositories.SkillRepository;
import iuh.edu.vn.backend.services.ICompany;
import iuh.edu.vn.backend.services.IJob;
import iuh.edu.vn.backend.services.IJobSkill;
import iuh.edu.vn.backend.services.ISkill;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import weka.classifiers.Classifier;
import weka.core.Attribute;
import weka.core.Instances;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/company")
@SessionAttributes("arffPath")
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
    @Autowired
    private CompanyRepository companyRepository;


    @GetMapping()
    public String viewCompanyJobs(Model model, HttpSession session, @RequestParam("keyword") Optional<String> keyword, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(12);
        String email = (String) session.getAttribute("email");

        Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("job.jobName").ascending());
        Page<JobSkill> jobSkillPage;

        if (keyword.isPresent() && !keyword.get().isEmpty()) {
            jobSkillPage = iJobSkill.searchJobsByNameOrCompany(email, keyword.get(), pageable);
            model.addAttribute("keyword", keyword.get());
        } else {
            jobSkillPage = iJobSkill.findAllJobAndSkillPage(email, pageable);
        }

        model.addAttribute("companies", iCompany.findNameByEmail(email));
        model.addAttribute("jobSkillPage", jobSkillPage);

        int totalPages = jobSkillPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "companies/company_job_list";
    }


    @GetMapping("/new/{companyId}")
    public String showJobForm(Model model, @PathVariable("companyId") Long companyId) {
        model.addAttribute("companyID", companyRepository.findById(companyId).get());
        model.addAttribute("job", new Job());
        model.addAttribute("skill", new Skill());
        model.addAttribute("jobSkill", new JobSkill());
        model.addAttribute("allSkills", SkillLevel.values());
        model.addAttribute("allSkillTypes", SkillType.values());
        return "companies/create_new_job";
    }

    //    Them trang cap nhat cong viec da chon
    @GetMapping("/update-job/{jobId}/{skillId}/{companyId}")
    public String showUpdateForm(@PathVariable("jobId") Long jobId, @PathVariable("skillId") Long skillId, @PathVariable("companyId") Long companyId, Model model) {
        Job job = iJob.findById(jobId);
        Skill skill = iSkill.findById(skillId);

        JobSkillId jobSkillId = new JobSkillId(jobId, skillId);
        JobSkill jobSkill = iJobSkill.findById(jobSkillId);


        if (job != null && skill != null && jobSkill != null) {
            model.addAttribute("companyID", companyRepository.findById(companyId).get());
            model.addAttribute("job", job);
            model.addAttribute("skill", skill);
            model.addAttribute("jobSkill", jobSkill);
            model.addAttribute("jobSkillId", jobSkillId);
            model.addAttribute("allSkills", SkillLevel.values());
            model.addAttribute("allSkillTypes", SkillType.values());
            return "companies/update_job";
        } else {
            return "redirect:/company";
        }
    }

    @PostMapping("/update-job")
    public String updateJob(@ModelAttribute("job") Job job, @ModelAttribute("skill") Skill skill, @RequestParam(value = "jobId") Long jobId, @RequestParam("companyId") String companyId, @RequestParam(value = "skillId") Long skillId, @RequestParam("skillLevel") SkillLevel skillLevel, @RequestParam("moreInfos") String moreInfos) {

        job.setId(jobId);
        skill.setId(skillId);
        job.setCompany(companyRepository.findById(Long.parseLong(companyId)).get());

        JobSkillId jobSkillId = new JobSkillId(jobId, skillId);
        JobSkill jobSkill = new JobSkill(job, skill, jobSkillId, skillLevel);
        jobSkill.setMoreInfos(moreInfos);

        jobRepository.save(job);
        skillRepository.save(skill);
        jobSkillRepository.save(jobSkill);

        return "redirect:/company";
    }

    //add job
    @PostMapping
    public String saveJob(@RequestParam("jobId") String id, @ModelAttribute("job") Job job, @ModelAttribute("skill") Skill skill, @ModelAttribute("jobSkill") JobSkill jobSkill) {
        JobSkillId jobSkillId = new JobSkillId(job.getId(), skill.getId());
        job.setCompany(companyRepository.findById(Long.parseLong(id)).get());
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

    @GetMapping("/guess-candidate")
    public String gCandidatesForm(Model model,
                                  @RequestParam(name = "jobName", required = false) String jobName,
                                  HttpSession session) {

        String email = (String) session.getAttribute("email");


        model.addAttribute("candidate", new Candidate());
        model.addAttribute("job", new Job());
        model.addAttribute("jobNames", jobRepository.findAllJobDistinct());
        model.addAttribute("skill", new Skill());
        model.addAttribute("selectedJobName", jobName);


        if (jobName != null && !jobName.isEmpty()) {
            System.out.println("Received jobName: " + jobName);
            System.out.println("Received email: " + email);
            System.out.println("Retrieved skillNames: " + skillRepository.getSkillNameByJobNameOfCompanyEmail(jobName, email));

            model.addAttribute("skillNames", skillRepository.getSkillNameByJobNameOfCompanyEmail(jobName, email));
        } else {
            model.addAttribute("skillNames", List.of());
            System.out.println("No skillNames found");
        }

        model.addAttribute("allSkillTypes", SkillType.values());
        model.addAttribute("allSkills", SkillLevel.values());




        return "companies/guessCandidateForm";
    }

    // Dam bao thu muc ton tai
    private void ensureDirectoryExists(String directoryPath) {
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }


    // Machine Learning cau 6
    @PostMapping("/guess-candidate")
    public String processData(
            @RequestParam(name = "jobName") String jobName,
            @RequestParam(name = "skillName") String skillName,
            @RequestParam(name = "skillLevel") String skillLevel,
            Model model) {

        System.out.println("Received jobName: " + jobName);
        System.out.println("Received skillName: " + skillName);
        System.out.println("Received skillLevel: " + skillLevel);

        try {
            String timestamp = String.valueOf(System.currentTimeMillis());
            String modelDirectory = "./data/model/";
            String updatedArffDirectory = "./data/updateLog/";

            // Đảm bảo thư mục tồn tại
            ensureDirectoryExists(modelDirectory);
            ensureDirectoryExists(updatedArffDirectory);

            String modelPath = modelDirectory + "suitability_" + timestamp + ".model";
            String updatedArffPath = updatedArffDirectory + "updated_file_" + timestamp + ".arff";

            MLService service = new MLService();

            Instances data = service.loadArff("./data/company_data.arff");
            if (data == null) {
                model.addAttribute("error", "An error occurred while reading the ARFF file.");
                return "companies/guessCandidateForm";
            }

            Instances updatedData = service.addIsSuitableColumn(data, jobName, skillName, skillLevel);

            // Lọc và lưu file mới đã lọc với is_suitable == yes
            Instances filteredData = new Instances(updatedData, 0);
            for (int i = 0; i < updatedData.numInstances(); i++) {
                String isSuitable = updatedData.instance(i).stringValue(updatedData.attribute("is_suitable"));
                if ("yes".equalsIgnoreCase(isSuitable)) {
                    filteredData.add(updatedData.instance(i));
                }
            }


            service.saveUpdatedArff(filteredData, updatedArffPath);
            System.out.println("Updated ARFF file saved at: " + updatedArffPath);


            // Huấn luyện mô hình và lưu mô hình
            ModelTrainer modelTrainer = new ModelTrainer();
            Classifier trainedModel = modelTrainer.trainModel(updatedData, modelPath);
            service.saveModel(trainedModel, modelPath);
            System.out.println("Model saved at: " + modelPath);

            model.addAttribute("arffPath", updatedArffPath); //Add updatedArffPath to session attribute
            return "redirect:/company/results";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "An error occurred while processing the data.");
            return "companies/guessCandidateForm";
        }
    }

        private int compareSkillLevel(String level1, String level2) {
            List<String> skillLevels = Arrays.asList("BEGINNER", "INTERMEDIATE", "ADVANCED", "PROFESSIONAL", "MASTER");
            int index1 = skillLevels.indexOf(level1.toUpperCase());
            int index2 = skillLevels.indexOf(level2.toUpperCase());
            return Integer.compare(index1, index2);
        }

            @GetMapping("/results")
            public String showResult(
                    @ModelAttribute("arffPath") String arffPath,
                    @RequestParam(defaultValue = "0") int page,
                    @RequestParam(defaultValue = "15") int size,
                    Model model) {
                try {
                    MLService service = new MLService();
                    Instances data = service.loadArff(arffPath);

                    //Check all attributes
                    for (int i = 0; i < data.numAttributes(); i++) {
                        System.out.println("Attribute " + i + ": " + data.attribute(i).name());
                    }

                    if (data != null) {
                        Map<String, Map<String, Object>> candidateMap = new HashMap<>();
                        List<Map<String, Object>> candidates = new ArrayList<>();
                        Map<String, Integer> skillLevelCounts = new HashMap<>();


                        for (int i = 0; i < data.numInstances(); i++) {

                            String isSuitable = data.instance(i).stringValue(data.attribute("is_suitable"));
                            if ("yes".equalsIgnoreCase(isSuitable)) {

                                String email = getStringValue(data, i, "email");

                                String candidateName = getStringValue(data, i, "CandidateName");
                                Map<String, Object> candidateData = candidateMap.getOrDefault(candidateName, new HashMap<>());

                                String jobName = getStringValue(data, i, "job_name");
                                String skillName = getStringValue(data, i, "skill_name");
                                String skillLevel = data.instance(i).stringValue(data.attribute("skill_level"));
                                skillLevelCounts.put(skillLevel, skillLevelCounts.getOrDefault(skillLevel, 0) + 1);



                                if (candidateData.containsKey("skill_level")) {
                                    String currentSkillLevel = (String) candidateData.get("skill_level");

                                    if (compareSkillLevel(skillLevel, currentSkillLevel) > 0) {
                                        candidateData.put("skill_level", skillLevel);
                                        candidateData.put("skill_name", skillName);
                                    }
                                } else {
                                    candidateData.put("skill_level", skillLevel);
                                    candidateData.put("skill_name", skillName);
                                }


                                candidateData.putIfAbsent("email", email);
                                candidateData.putIfAbsent("job_name", jobName);
                                candidateData.putIfAbsent("name", candidateName);
                                candidateData.put("is_suitable", isSuitable);


                                candidateMap.put(candidateName, candidateData);
                            }
                        }


                        candidates.addAll(candidateMap.values());

                        // Paging
                        int totalPages = (int) Math.ceil((double) candidates.size() / size);
                        int start = page * size;
                        int end = Math.min(start + size, candidates.size());
                        List<Map<String, Object>> candidatesPage = candidates.subList(start, end);

                        for (int i = 0; i < candidatesPage.size(); i++) {
                            Map<String, Object> candidate = candidatesPage.get(i);
                            candidate.put("index", start + i + 1);
                        }


                        model.addAttribute("candidates", candidatesPage);
                        model.addAttribute("page", page);
                        model.addAttribute("size", size);
                        model.addAttribute("totalPages", totalPages);
                        model.addAttribute("skillLevelCounts", skillLevelCounts);

                    } else {
                        model.addAttribute("error", "No data available");
                        System.out.println("No data available");
                    }
                } catch (Exception e) {
                    model.addAttribute("error", "An error occurred while processing the ARFF file.");
                    e.printStackTrace();
                    System.out.println("An error occurred while processing the ARFF file.");
                }
                return "companies/results";
            }



        private String getStringValue(Instances data, int instanceIndex, String attributeName) {
            try {
                Attribute attribute = data.attribute(attributeName);
                if (attribute != null) {
                    return data.instance(instanceIndex).stringValue(attribute);
                } else {
                    return "N/A";
                }
            } catch (Exception e) {
                return "N/A";
            }
        }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }


}
