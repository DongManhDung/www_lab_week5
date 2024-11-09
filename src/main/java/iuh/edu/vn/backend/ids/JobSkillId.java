package iuh.edu.vn.backend.ids;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class JobSkillId implements Serializable {
    private static final long serialVersionUID = -7983905330416614200L;
    @NotNull
    @Column(name = "job_id", nullable = false)
    private Long jobId;

    @NotNull
    @Column(name = "skill_id", nullable = false)
    private Long skillId;

    public JobSkillId(Long jobId, Long skillId) {
        this.jobId = jobId;
        this.skillId = skillId;
    }

    public JobSkillId(Long jobId) {
        this.jobId = jobId;
    }

    public JobSkillId() {
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        JobSkillId entity = (JobSkillId) o;
        return Objects.equals(this.jobId, entity.jobId) &&
                Objects.equals(this.skillId, entity.skillId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobId, skillId);
    }

}