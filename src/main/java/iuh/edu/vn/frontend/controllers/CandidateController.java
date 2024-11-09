package iuh.edu.vn.frontend.controllers;

import iuh.edu.vn.backend.models.Candidate;
import iuh.edu.vn.backend.repositories.CandidateRepository;
import iuh.edu.vn.backend.services.ICandidate;
import iuh.edu.vn.backend.services.ICandidateSkill;
import iuh.edu.vn.backend.services.IJob;
import iuh.edu.vn.backend.services.IJobSkill;
import iuh.edu.vn.backend.services.Impl.CandidateImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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


    @GetMapping({"/listCandidate"})
    public ModelAndView showCandidateList(){
        ModelAndView mav = new ModelAndView("candidates/candidates");
        mav.addObject("candidates", candidateRepository.findAll());
        return mav;
    }

    @GetMapping("/listCandidatePage")
    public String showCandidateListPaging(Model model,
                                          @RequestParam("page") Optional<Integer> page,
                                          @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        Page<Candidate> candidatePage= candidateService.findAll(
                currentPage - 1,pageSize,"id","asc");


        model.addAttribute("candidatePage", candidatePage);

        int totalPages = candidatePage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "candidates/candidate-paging";
    }


    @GetMapping({"/listJobSkillVerySuitable", "", "index", "default"})
    public String listCandidateHaveJobSkillSuitable(Model model, HttpSession session){
        String email = (String) session.getAttribute("email");
        model.addAttribute("candidates", iCandidate.getFullNameByEmail(email));
        model.addAttribute("candidateSkills", iCandidateSkill.findCandidateSkillByEmail(email));
        model.addAttribute("jobSkills", iJobSkill.findJobSkillSuitableWithCandidate(email));
        return "candidates/candidate_job_list";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

}
