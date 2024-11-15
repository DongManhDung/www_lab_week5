package iuh.edu.vn.backend.services;

import iuh.edu.vn.backend.models.CandidateSkill;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICandidateSkill {
    List<CandidateSkill> findCandidateSkillByEmail(String email);
}
