package iuh.edu.vn.frontend.controllers;

import iuh.edu.vn.backend.repositories.CandidateRepository;
import iuh.edu.vn.backend.repositories.CompanyRepository;
import iuh.edu.vn.backend.services.ICompany;
import iuh.edu.vn.backend.services.Impl.CandidateImpl;
import iuh.edu.vn.backend.services.Impl.CompanyImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private CompanyRepository   companyRepository;

    @Autowired
    private CompanyImpl companyImpl;

    @Autowired
    private CandidateImpl candidateImpl;

    @Autowired
    private ICompany iCompany;

    @PostMapping("/check-role")
    public String checkUserRole(@RequestParam("email") String email, @RequestParam("role") String role, Model model, HttpSession session) {
        if (role.equals("company")) {
            if (companyImpl.existsByEmail(email)) {
                session.setAttribute("email", email);
                return "redirect:/company";
            } else {
                model.addAttribute("error", "Email does not exist in the company system!");
                return "index";
            }
        } else if (role.equals("candidate")) {
            if (candidateImpl.existsByEmail(email)) {
                session.setAttribute("email", email);
                return "redirect:/candidates/listJobSkillVerySuitable";
            } else {
                model.addAttribute("error", "Email does not exist in the candidate system!");
                return "index";
            }
        }
        model.addAttribute("error", "Vai trò không hợp lệ.");
        return "index";
    }
}
