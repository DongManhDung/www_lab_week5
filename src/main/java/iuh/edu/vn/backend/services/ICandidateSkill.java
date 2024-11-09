package iuh.edu.vn.backend.services;

import iuh.edu.vn.backend.models.CandidateSkill;

import java.util.List;

public interface ICandidateSkill {
    List<CandidateSkill> findCandidateSkillByEmail(String email);
}
