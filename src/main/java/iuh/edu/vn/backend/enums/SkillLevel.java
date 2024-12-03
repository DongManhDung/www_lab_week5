package iuh.edu.vn.backend.enums;

public enum SkillLevel {
    BEGINNER("BEGINNER"),
    INTERMEDIATE("INTERMEDIATE"),
    ADVANCED("ADVANCED"),
    PROFESSIONAL("PROFESSIONAL"),
    MASTER("MASTER");

    private final String value;

    SkillLevel(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }

    public static SkillLevel fromString(String value) {
        if (value == null) {
            return null;
        }
        String valueUpper = value.toUpperCase();
        for (SkillLevel level : SkillLevel.values()) {
            if (level.getValue().equals(valueUpper)) {
                return level;
            }
        }
        return null;
    }
}
