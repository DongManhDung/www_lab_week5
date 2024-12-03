package iuh.edu.vn.backend.services.Impl;

import iuh.edu.vn.backend.models.Skill;
import iuh.edu.vn.backend.repositories.SkillRepository;
import iuh.edu.vn.backend.services.ISkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillImpl implements ISkill {

    @Autowired
    private SkillRepository skillRepository;

    public void add(Skill skill){
        skillRepository.save(skill);
    }

    public void update(Skill skill){
        skillRepository.save(skill);
    }

    public void delete(long id){
        skillRepository.deleteById(id);
    }

    @Override
    public Skill findById(Long id) {
        return skillRepository.findBySkillId(id);
    }

    @Override
    public List<String> getSkillNameByJobNameOfCompanyEmail(String name, String email) {
        return skillRepository.getSkillNameByJobNameOfCompanyEmail(name, email);
    }
}
