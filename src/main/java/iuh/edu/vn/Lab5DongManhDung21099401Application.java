package iuh.edu.vn;

import com.neovisionaries.i18n.CountryCode;
import iuh.edu.vn.backend.enums.SkillLevel;
import iuh.edu.vn.backend.enums.SkillType;
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
    private CompanyRepository   companyRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private JobSkillRepository jobSkillRepository;

    @Autowired
    private CandidateSkillRepository candidateSkillRepository;

//    @Bean
//    CommandLineRunner initData(){
//        return args -> {
//            String jobNames[] = {"frontend", "backend", "data analyst", "business analyst", "designer", "developer"};
//            String skillNames[] = {"React", "Vue", "Angular", "Figma", "React Native", "NextJS", "PHP", "HTML", "CSS", "JavaScript"};
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
//                        "email_" + i + "@gmail.com", "Name #" + i, rnd.nextLong(1111111111L, 9999999999L) + "", address);
//                candidateRepository.save(can);
//            }
//            //Add company
//            for (int i = 1; i < 20; i ++){
//                Company com = new Company(addressRepository.findById((long) i).orElse(null),"www.timviec.vn", "0909090909", "Company #" + i, "company_" + i + "@gmail.com", "About company #" + i);
//                companyRepository.save(com);
//            }
//
//
////            Add job
//            companyRepository.findAll().forEach(company -> {
//                for(int i = 1; i < 6; i++){
//                    String randomJobNames = jobNames[rnd.nextInt(jobNames.length)];
//                    Job job = new Job(randomJobNames, "Job description #" + i, company);
//                    jobRepository.save(job);
//                }
//            });
//
//                //               Add skill
//                for (int i = 1; i < 20; i++) {
//                    SkillType type = SkillType.values()[rnd.nextInt(SkillType.values().length)];
//                    Skill skill = new Skill("Skill description #" + i, skillNames[rnd.nextInt(skillNames.length)], type);
//                    skillRepository.save(skill);
//                }
//
////                Bug here :))
//                //                Add JobSkill
//                jobRepository.findAll().forEach(job -> {
//                    for (int i = 1; i < 5; i++) {
//                        Skill skill = skillRepository.findById((long) i).orElse(null);
//                        JobSkillId jobSkillId = new JobSkillId(job.getId(), skill.getId());
//                        JobSkill jobSkill = new JobSkill(jobSkillId, job, skill, "More info #" + i, SkillLevel.values()[rnd.nextInt(SkillType.values().length)]);
//                        jobSkillRepository.save(jobSkill);
//                    }
//                });
//
////                Add candidate skill
//                for (int i = 1; i < 50; i++){
//                    Candidate candidate = candidateRepository.findById((long) i).orElse(null);
//                    if(candidate != null){
//                        List<Long> skills = new ArrayList<>();
//                        for(long id = 1; id < 20; id++){
//                            skills.add(id);
//                        }
//                        Collections.shuffle(skills);
//
//                        //Add 5 random skills to candidate
//                        for(int j = 0; j < 5 && j < skills.size(); j++){
//                            Long skillId = skills.get(j);
//                            CandidateSkillId candidateSkillId = new CandidateSkillId(candidate.getId(), skillId);
//
//                            CandidateSkill candidateSkill =
//                                    new CandidateSkill(candidateSkillId, candidate, skillRepository.findById(skillId).orElse(null) , "More info" + i, SkillLevel.values()[rnd.nextInt(SkillLevel.values().length)]);
//                            candidateSkillRepository.save(candidateSkill);
//                        }
//                    }
//                }
//            };
//        };
    }
