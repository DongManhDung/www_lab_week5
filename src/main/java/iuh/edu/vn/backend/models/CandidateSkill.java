package iuh.edu.vn.backend.models;

import iuh.edu.vn.backend.enums.SkillLevel;
import iuh.edu.vn.backend.ids.CandidateSkillId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "candidate_skill")
public class CandidateSkill {
    @EmbeddedId
    private CandidateSkillId id;

    @MapsId("canId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "can_id", nullable = false)
    private Candidate can;

    @MapsId("skillId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "skill_id", nullable = false)
    private Skill skill;

    @Size(max = 1000)
    @Column(name = "more_infos", length = 1000)
    private String moreInfos;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "skill_level", nullable = false)
    private SkillLevel skillLevel;

    public CandidateSkill() {
    }

    public CandidateSkill(CandidateSkillId id, Candidate can, Skill skill, String moreInfos, SkillLevel skillLevel) {
        this.id = id;
        this.can = can;
        this.skill = skill;
        this.moreInfos = moreInfos;
        this.skillLevel = skillLevel;
    }

    public CandidateSkillId getId() {
        return id;
    }

    public void setId(CandidateSkillId id) {
        this.id = id;
    }

    public Candidate getCan() {
        return can;
    }

    public void setCan(Candidate can) {
        this.can = can;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public String getMoreInfos() {
        return moreInfos;
    }

    public void setMoreInfos(String moreInfos) {
        this.moreInfos = moreInfos;
    }

    public SkillLevel getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(SkillLevel skillLevel) {
        this.skillLevel = skillLevel;
    }


    @Override
    public String toString() {
        return "CandidateSkill{" +
                "id=" + id +
                ", can=" + can +
                ", skill=" + skill +
                ", moreInfos='" + moreInfos + '\'' +
                ", skillLevel=" + skillLevel +
                '}';
    }
}