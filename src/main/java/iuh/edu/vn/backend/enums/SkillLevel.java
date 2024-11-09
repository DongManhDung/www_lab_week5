package iuh.edu.vn.backend.enums;

public enum SkillLevel {
    MASTER("MASTER"),
    BEGINNER("BEGINNER"),
    ADVANCED("ADVANCED"),
    PROFESSIONAL("PROFESSIONAL"),
    IMTERMEDIATE("IMTERMEDIATE");

    private final String value;

    SkillLevel(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
