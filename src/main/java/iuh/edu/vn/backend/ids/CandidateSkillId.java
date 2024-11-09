package iuh.edu.vn.backend.ids;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CandidateSkillId implements Serializable {
    private static final long serialVersionUID = 6995517580443278510L;
    @NotNull
    @Column(name = "can_id", nullable = false)
    private Long canId;

    @NotNull
    @Column(name = "skill_id", nullable = false)
    private Long skillId;

    public CandidateSkillId(Long canId, Long skillId) {
        this.canId = canId;
        this.skillId = skillId;
    }

    public CandidateSkillId(Long canId) {
        this.canId = canId;
    }

    public CandidateSkillId() {
    }

    public Long getCanId() {
        return canId;
    }

    public void setCanId(Long canId) {
        this.canId = canId;
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
        CandidateSkillId entity = (CandidateSkillId) o;
        return Objects.equals(this.skillId, entity.skillId) &&
                Objects.equals(this.canId, entity.canId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skillId, canId);
    }

}