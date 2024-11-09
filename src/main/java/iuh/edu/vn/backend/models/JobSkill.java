package iuh.edu.vn.backend.models;

import iuh.edu.vn.backend.enums.SkillLevel;
import iuh.edu.vn.backend.ids.JobSkillId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
@Table(name = "job_skill")
public class JobSkill {
    @EmbeddedId
    private JobSkillId id;

    @MapsId("jobId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;

    @MapsId("skillId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "skill_id", nullable = false)
    private Skill skill;

    @Size(max = 1000)
    @Column(name = "more_infos", length = 1000)
    private String moreInfos;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "skill_level", nullable = false)
    private SkillLevel skillLevel;





    public JobSkillId getId() {
        return id;
    }

    public void setId(JobSkillId id) {
        this.id = id;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
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


    public JobSkill() {
    }

    public JobSkill(JobSkillId id, Job job, Skill skill, String moreInfos, SkillLevel skillLevel) {
        this.id = id;
        this.job = job;
        this.skill = skill;
        this.moreInfos = moreInfos;
        this.skillLevel = skillLevel;
    }

    public JobSkill(Job job, Skill skill, JobSkillId id, SkillLevel skillLevel) {
        this.job = job;
        this.skill = skill;
        this.id = id;
        this.skillLevel = skillLevel;
    }

    @Override
    public String toString() {
        return "JobSkill{" +
                "job=" + job +
                ", id=" + id +
                ", skill=" + skill +
                ", moreInfos='" + moreInfos + '\'' +
                ", skillLevel=" + skillLevel +
                '}';
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JobSkill jobSkill)) return false;

        return Objects.equals(id, jobSkill.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}