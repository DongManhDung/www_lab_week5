package iuh.edu.vn.frontend.models;

import java.time.LocalDate;

public class CandidateData {
    private final String dob;
    private final String email;
    private final String candidateName;
    private final String phone;
    private final String jobName;
    private final String skillName;
    private final String skillType;
    private final String skillLevel;

    public CandidateData(String dob, String email, String candidateName, String phone,
                         String jobName, String skillName, String skillType, String skillLevel) {
        this.dob = dob;
        this.email = email;
        this.candidateName = candidateName;
        this.phone = phone;
        this.jobName = jobName;
        this.skillName = skillName;
        this.skillType = skillType;
        this.skillLevel = skillLevel;
    }

    public String getDob() {
        return dob;
    }

    public String getEmail() {
        return email;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public String getPhone() {
        return phone;
    }

    public String getJobName() {
        return jobName;
    }

    public String getSkillName() {
        return skillName;
    }

    public String getSkillType() {
        return skillType;
    }

    public String getSkillLevel() {
        return skillLevel;
    }
}
