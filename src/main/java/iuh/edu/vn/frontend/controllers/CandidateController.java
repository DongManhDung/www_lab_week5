package iuh.edu.vn.frontend.controllers;

import iuh.edu.vn.backend.models.Candidate;
import iuh.edu.vn.backend.models.CandidateSkill;
import iuh.edu.vn.backend.models.JobSkill;
import iuh.edu.vn.backend.models.mailEntity.Email;
import iuh.edu.vn.backend.repositories.CandidateRepository;
import iuh.edu.vn.backend.repositories.EmailRepository;
import iuh.edu.vn.backend.services.ICandidate;
import iuh.edu.vn.backend.services.ICandidateSkill;
import iuh.edu.vn.backend.services.IJobSkill;
import iuh.edu.vn.backend.services.Impl.CandidateImpl;
import iuh.edu.vn.backend.services.Impl.JobSkillImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/candidates")
public class CandidateController {
    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private CandidateImpl candidateService;

    @Autowired
    private ICandidate iCandidate;

    @Autowired
    private IJobSkill iJobSkill;


    @Autowired
    private ICandidateSkill iCandidateSkill;
    @Autowired
    private EmailRepository emailRepository;


//    @GetMapping({"/listCandidate"})
//    public ModelAndView showCandidateList(){
//        ModelAndView mav = new ModelAndView("candidates/candidates");
//        mav.addObject("candidates", candidateRepository.findAll());
//        return mav;
//    }

    @GetMapping()
    public String listCandidateHaveJobSkillSuitable(Model model,
                                                    HttpSession session,
                                                    @RequestParam("page") Optional<Integer> page,
                                                    @RequestParam("size") Optional<Integer> size)
    {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(12);
        String email = (String) session.getAttribute("email");

        Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("id").ascending());
        Page<JobSkill> jobSkillPage = iJobSkill.findJobSkillSuitableWithCandidate(email, pageable);

        System.out.println("jobSkillPage: " + jobSkillPage.getContent().size());

        model.addAttribute("candidates", iCandidate.getFullNameByEmail(email));
        model.addAttribute("candidateSkills", iCandidateSkill.findCandidateSkillByEmail(email));
        model.addAttribute("jobSkillPage", jobSkillPage);

        int totalPages = jobSkillPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "candidates/candidate_job_list";
    }

    @GetMapping("/inbox")
    public String inbox(Model model, HttpSession session) {
        String email = (String) session.getAttribute("email");

        List<Email> emails = emailRepository.findByReceiver(email);
        model.addAttribute("emails", emails);
        model.addAttribute("candidates", iCandidate.getFullNameByEmail(email));
        return "candidates/inbox/candidate_inbox";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

}
