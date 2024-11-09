package iuh.edu.vn.backend.enums;

public enum SkillType {
    SOFT_SKILL("SOFT_SKILL"),
    UNSPECIFIC("UNSPECIFIC"),
    TECHNICAL_SKILL("TECHNICAL_SKILL");

    private final String value;

    SkillType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
