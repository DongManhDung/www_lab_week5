package iuh.edu.vn.backend.machineLearning;

import iuh.edu.vn.backend.enums.SkillLevel;
import iuh.edu.vn.backend.enums.SkillType;
import iuh.edu.vn.backend.models.Candidate;
import iuh.edu.vn.backend.models.Job;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import weka.classifiers.Classifier;
import weka.classifiers.trees.RandomForest;
import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.core.converters.ArffLoader;
import weka.core.converters.ArffSaver;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Add;

import java.io.*;
import java.util.*;

@Service
public class MLService {
    public Instances loadArff(String path) throws IOException {
        ArffLoader loader = new ArffLoader();
        loader.setFile(new File(path));
        return loader.getDataSet();
    }

    public Instances addIsSuitableColumn(Instances data, String jobName, String skillName, String skillLevel) {
        ArrayList<String> suitabilityValues = new ArrayList<>();
        suitabilityValues.add("yes");
        suitabilityValues.add("no");

        Attribute isSuitable = new Attribute("is_suitable", suitabilityValues);
        data.insertAttributeAt(isSuitable, data.numAttributes());

        // Kiểm tra sự tồn tại của các attribute cần thiết
        int jobNameIndex = data.attribute("job_name") != null ? data.attribute("job_name").index() : -1;
        int skillNameIndex = data.attribute("skill_name") != null ? data.attribute("skill_name").index() : -1;
        int skillLevelIndex = data.attribute("skill_level") != null ? data.attribute("skill_level").index() : -1;

        // Kiểm tra nếu bất kỳ attribute nào không tồn tại
        if (jobNameIndex == -1 || skillNameIndex == -1 || skillLevelIndex == -1) {
            throw new IllegalArgumentException("One or more attributes (job_name, skill_name, skill_level) are missing.");
        }

        int skillLevelThreshold = convertSkillLevelToNumber(skillLevel);

        for (int i = 0; i < data.numInstances(); i++) {
            String currentJob = data.instance(i).stringValue(data.attribute("job_name"));
            String currentSkill = data.instance(i).stringValue(data.attribute("skill_name"));
            int currentSkillLevel = convertSkillLevelToNumber(data.instance(i).stringValue(data.attribute("skill_level")));

            if (currentJob.equals(jobName) && currentSkill.equals(skillName) && currentSkillLevel >= skillLevelThreshold) {
                data.instance(i).setValue(data.attribute("is_suitable"), "yes");
            } else {
                data.instance(i).setValue(data.attribute("is_suitable"), "no");
            }
        }

        return data;
    }

    // Save the updated ARFF file
    public void saveUpdatedArff(Instances data, String filePath) throws IOException {
        ArffSaver saver = new ArffSaver();
        saver.setInstances(data);
        saver.setFile(new File(filePath));
        saver.writeBatch();
    }




    private int convertSkillLevelToNumber(String skillLevel) {
        switch (skillLevel.toUpperCase()) {
            case "MASTER":
                return 1;
            case "BEGINNER":
                return 2;
            case "ADVANCED":
                return 3;
            case "PROFESSIONAL":
                return 4;
            case "IMTERMEDIATE":
                return 5;
            default:
                return 0;
        }
    }


    // Lưu mô hình sau khi huấn luyện
    public void saveModel(Classifier model, String modelPath) throws Exception {
        FileOutputStream fos = new FileOutputStream(modelPath);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(model);
        oos.close();
    }
}
