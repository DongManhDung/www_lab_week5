package iuh.edu.vn;

import com.neovisionaries.i18n.CountryCode;
import iuh.edu.vn.backend.enums.SkillLevel;
import iuh.edu.vn.backend.enums.SkillType;
import iuh.edu.vn.backend.ids.CandidateSkillId;
import iuh.edu.vn.backend.ids.JobSkillId;
import iuh.edu.vn.backend.models.*;
import iuh.edu.vn.backend.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.*;

@SpringBootApplication
public class Lab5DongManhDung21099401Application {

    public static void main(String[] args) {
        SpringApplication.run(Lab5DongManhDung21099401Application.class, args);
    }

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private JobSkillRepository jobSkillRepository;

    @Autowired
    private CandidateSkillRepository candidateSkillRepository;

//    @Bean
//    CommandLineRunner initData() {
//        return args -> {
//            String jobNames[] = {"Frontend", "Backend", "Data Analyst", "Business Analyst", "Designer", "Developer"};
//            String skillNames[] = {"React", "Vue", "Angular", "Figma", "React Native", "NextJS", "PHP", "HTML", "CSS", "JavaScript", "C Sharp", "C++", "Jira", "Ajax", "JSP"};
//            String[] companies = {"FPT", "VNG", "VCCorp", "Google", "Facebook", "Amazon", "Apple", "Microsoft", "Samsung", "Nokia", "Huawei", "Xiaomi", "Oppo", "Vivo", "Sony", "LG", "HTC", "Asus", "Acer", "Dell"};
//            String[] candidateNames = {"Nguyen Van A", "Tran Van B", "Le Thi C", "Pham Thi D", "Nguyen Thi E", "Tran Thi F", "Le Van G", "Pham Van H", "Nguyen Van I", "Tran Van K", "Le Thi L", "Pham Thi M", "Nguyen Thi N", "Tran Thi O", "Le Van P", "Pham Van Q", "Nguyen Van R", "Tran Van S", "Le Thi T", "Pham Thi U"};
//            Random rnd = new Random();
//            for (int i = 1; i < 1000; i++) {
//                Address add = new Address("Quang Trung", "HCM",
//                        (short) CountryCode.VN.getNumeric(),
//                        rnd.nextInt(70000, 80000) + "", CountryCode.VN.toString());
//                addressRepository.save(add);
//
//                Long id = (long) i;
//                Address address = addressRepository.findById(id).orElse(null);
//                Candidate can = new Candidate(
//                        LocalDate.of(1998, rnd.nextInt(1, 13), rnd.nextInt(1, 29)),
//                        "email_" + i + "@gmail.com", candidateNames[rnd.nextInt(candidateNames.length)], rnd.nextLong(1111111111L, 9999999999L) + "", address);
//                candidateRepository.save(can);
//            }
//            //Add company
//            for (int i = 1; i <= companies.length; i++) {
//                Company com = new Company(addressRepository.findById((long) i).orElse(null), "www.timviec.vn", "0909090909", companies[i - 1], "company_" + i + "@gmail.com", "About company #" + i);
//                companyRepository.save(com);
//            }
//
//
////            Add job
//            companyRepository.findAll().forEach(company -> {
//                for (int i = 1; i <= 6; i++) {
//                    String randomJobNames = jobNames[rnd.nextInt(jobNames.length)];
//                    Job job = new Job(randomJobNames, "Job description #" + i, company);
//                    jobRepository.save(job);
//                }
//            });
//
//            // Add Skill
//            for (int i = 1; i <= skillNames.length; i++) {
//                SkillType type = SkillType.values()[rnd.nextInt(SkillType.values().length)];
//                Skill skill = new Skill("Skill description #" + i, skillNames[i - 1], type);
//                skillRepository.save(skill);
//            }
//
//            // Add JobSkill
//            jobRepository.findAll().forEach(job -> {
//                List<Skill> skills = skillRepository.findAll();
//                Collections.shuffle(skills); // Shuffle danh sách skill
//                for (int i = 0; i < Math.min(skills.size(), 10); i++) { // Add tối đa 10 skill vào mỗi job
//                    Skill skill = skills.get(i);
//                    JobSkillId jobSkillId = new JobSkillId(job.getId(), skill.getId());
//                    JobSkill jobSkill = new JobSkill(jobSkillId, job, skill, "More info #" + i, SkillLevel.values()[rnd.nextInt(SkillLevel.values().length)]);
//                    jobSkillRepository.save(jobSkill);
//                }
//            });
//
//            // Add CandidateSkill
//            candidateRepository.findAll().forEach(candidate -> {
//                List<Skill> skills = skillRepository.findAll();
//                Collections.shuffle(skills); // Shuffle danh sách skill
//                for (int i = 0; i < Math.min(skills.size(), 5); i++) { // Add tối đa 5 skill vào mỗi candidate
//                    Skill skill = skills.get(i);
//                    CandidateSkillId candidateSkillId = new CandidateSkillId(candidate.getId(), skill.getId());
//                    CandidateSkill candidateSkill = new CandidateSkill(candidateSkillId, candidate, skill, "More info #" + i, SkillLevel.values()[rnd.nextInt(SkillLevel.values().length)]);
//                    candidateSkillRepository.save(candidateSkill);
//                }
//            });
//        };
//    }

    ;
}
