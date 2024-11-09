package iuh.edu.vn.backend.models;

import iuh.edu.vn.backend.enums.SkillType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "skill")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skill_id", nullable = false)
    private Long id;

    @Size(max = 255)
    @Column(name = "skill_description")
    private String skillDescription;

    @Size(max = 255)
    @Column(name = "skill_name")
    private String skillName;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private SkillType type;

    @OneToMany(mappedBy = "skill")
    private Set<CandidateSkill> candidateSkills = new LinkedHashSet<>();

    @OneToMany(mappedBy = "skill")
    private Set<JobSkill> jobSkills = new LinkedHashSet<>();

    public Skill() {
    }

    public Skill( String skillDescription, String skillName, SkillType type) {
        this.skillDescription = skillDescription;
        this.skillName = skillName;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSkillDescription() {
        return skillDescription;
    }

    public void setSkillDescription(String skillDescription) {
        this.skillDescription = skillDescription;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public SkillType getType() {
        return type;
    }

    public void setType(SkillType type) {
        this.type = type;
    }

    public Set<CandidateSkill> getCandidateSkills() {
        return candidateSkills;
    }

    public void setCandidateSkills(Set<CandidateSkill> candidateSkills) {
        this.candidateSkills = candidateSkills;
    }

    public Set<JobSkill> getJobSkills() {
        return jobSkills;
    }

    public void setJobSkills(Set<JobSkill> jobSkills) {
        this.jobSkills = jobSkills;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "type=" + type +
                ", skillName='" + skillName + '\'' +
                ", skillDescription='" + skillDescription + '\'' +
                ", id=" + id +
                '}';
    }
}