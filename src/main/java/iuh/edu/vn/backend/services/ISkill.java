package iuh.edu.vn.backend.services;

import iuh.edu.vn.backend.models.Skill;

public interface ISkill {
    public void add(Skill skill);
    public void update(Skill skill);
    public void delete(long id);
    Skill findById(Long id);
}
