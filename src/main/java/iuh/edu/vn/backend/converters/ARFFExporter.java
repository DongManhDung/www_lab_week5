package iuh.edu.vn.backend.converters;

import iuh.edu.vn.frontend.models.CandidateData;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;
import weka.core.converters.ArffSaver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ARFFExporter {

    private final JdbcTemplate jdbcTemplate;

    public ARFFExporter(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Export ARFF file
    public void exportDataToARFF(String relativeFilePath) throws IOException {
        // 1. Lấy dữ liệu từ cơ sở dữ liệu
        String sql = """
            SELECT DISTINCT ca.dob, ca.email, ca.full_name AS CandidateName, ca.phone, 
                            j.job_name, s.skill_name, s.`type`, js.skill_level
            FROM candidate ca
            JOIN candidate_skill ck ON ca.id = ck.can_id
            JOIN skill s ON s.skill_id = ck.skill_id
            JOIN job_skill js ON s.skill_id = js.skill_id
            JOIN job j ON js.job_id = j.job_id
            JOIN company c ON j.company = c.comp_id
            ORDER BY ca.full_name
        """;

        List<CandidateData> candidatesData = jdbcTemplate.query(sql, (rs, rowNum) -> new CandidateData(
                rs.getString("dob"),
                rs.getString("email"),
                rs.getString("CandidateName"),
                rs.getString("phone"),
                rs.getString("job_name"),
                rs.getString("skill_name"),
                rs.getString("type"),
                rs.getString("skill_level")
        ));

        // 2. Tập hợp tất cả giá trị để định nghĩa thuộc tính nominal
        List<String> dobValues = candidatesData.stream().map(CandidateData::getDob).distinct().collect(Collectors.toList());
        List<String> emailValues = candidatesData.stream().map(CandidateData::getEmail).distinct().collect(Collectors.toList());
        List<String> candidateNameValues = candidatesData.stream().map(CandidateData::getCandidateName).distinct().collect(Collectors.toList());
        List<String> phoneValues = candidatesData.stream().map(CandidateData::getPhone).distinct().collect(Collectors.toList());
        List<String> jobNameValues = candidatesData.stream().map(CandidateData::getJobName).distinct().collect(Collectors.toList());
        List<String> skillNameValues = candidatesData.stream().map(CandidateData::getSkillName).distinct().collect(Collectors.toList());
        List<String> typeValues = candidatesData.stream().map(CandidateData::getSkillType).distinct().collect(Collectors.toList());
        List<String> skillLevelValues = candidatesData.stream().map(CandidateData::getSkillLevel).distinct().collect(Collectors.toList());

        // 3. Định nghĩa các thuộc tính (Attributes) với nominal values
        ArrayList<Attribute> attributes = new ArrayList<>();
        attributes.add(new Attribute("dob", new ArrayList<>(dobValues)));
        attributes.add(new Attribute("email", new ArrayList<>(emailValues)));
        attributes.add(new Attribute("CandidateName", new ArrayList<>(candidateNameValues)));
        attributes.add(new Attribute("phone", new ArrayList<>(phoneValues)));
        attributes.add(new Attribute("job_name", new ArrayList<>(jobNameValues)));
        attributes.add(new Attribute("skill_name", new ArrayList<>(skillNameValues)));
        attributes.add(new Attribute("type", new ArrayList<>(typeValues)));
        attributes.add(new Attribute("skill_level", new ArrayList<>(skillLevelValues)));

        // 4. Tạo Instances
        Instances data = new Instances("candidate_data", attributes, candidatesData.size());

        // 5. Thêm dữ liệu vào Instances
        for (CandidateData candidate : candidatesData) {
            double[] instanceValues = new double[attributes.size()];
            instanceValues[0] = attributes.get(0).indexOfValue(candidate.getDob());
            instanceValues[1] = attributes.get(1).indexOfValue(candidate.getEmail());
            instanceValues[2] = attributes.get(2).indexOfValue(candidate.getCandidateName());
            instanceValues[3] = attributes.get(3).indexOfValue(candidate.getPhone());
            instanceValues[4] = attributes.get(4).indexOfValue(candidate.getJobName());
            instanceValues[5] = attributes.get(5).indexOfValue(candidate.getSkillName());
            instanceValues[6] = attributes.get(6).indexOfValue(candidate.getSkillType());
            instanceValues[7] = attributes.get(7).indexOfValue(candidate.getSkillLevel());

            data.add(new DenseInstance(1.0, instanceValues));
        }

        // 6. Ghi dữ liệu vào file ARFF
        ArffSaver saver = new ArffSaver();
        saver.setInstances(data);

        File outputFile = new File(relativeFilePath);
        if (outputFile.exists()) {
            System.out.println("File ARFF đã tồn tại. Đang xóa file cũ...");
            Files.delete(outputFile.toPath());
        }

        if (!outputFile.getParentFile().exists()) {
            outputFile.getParentFile().mkdirs(); // Tạo thư mục nếu chưa tồn tại
        }

        saver.setFile(outputFile);
        saver.writeBatch();

//        System.out.println("File ARFF mới đã được tạo tại: " + outputFile.getAbsolutePath());
    }
}
