package iuh.edu.vn.backend.services;

import iuh.edu.vn.backend.models.Skill;

import java.util.List;

public interface ISkill {
    public void add(Skill skill);
    public void update(Skill skill);
    public void delete(long id);
    Skill findById(Long id);
    List<String> getSkillNameByJobNameOfCompanyEmail(String name, String email);
}
