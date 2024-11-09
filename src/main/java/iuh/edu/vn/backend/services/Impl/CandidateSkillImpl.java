package iuh.edu.vn.backend.services.Impl;

import iuh.edu.vn.backend.models.CandidateSkill;
import iuh.edu.vn.backend.repositories.CandidateSkillRepository;
import iuh.edu.vn.backend.services.ICandidateSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateSkillImpl implements ICandidateSkill {

    @Autowired
    private CandidateSkillRepository candidateSkillRepository;
    @Override
    public List<CandidateSkill> findCandidateSkillByEmail(String email) {
        return candidateSkillRepository.findCandidateSkillByEmail(email);
    }
}
