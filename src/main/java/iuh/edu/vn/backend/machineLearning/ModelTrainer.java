package iuh.edu.vn.backend.machineLearning;

import weka.classifiers.Classifier;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.SerializationHelper;

public class ModelTrainer {
    public Classifier trainModel(Instances data, String modelPath) throws Exception {
        // Set the class attribute
        data.setClass(data.attribute("is_suitable"));

        // Train model
        J48 tree = new J48();
        tree.buildClassifier(data);

        // Save model
        saveModel(tree, modelPath);

        return tree;
    }

    private void saveModel(Classifier model, String modelPath) throws Exception {
        weka.core.SerializationHelper.write(modelPath, model);
    }
}
