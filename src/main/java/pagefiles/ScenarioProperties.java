package pagefiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

class ScenarioProperties {

    private Properties properties = new Properties();
    private static ScenarioProperties instance;

    private ScenarioProperties() {
        try {
            properties.load(new FileInputStream(new File("src/main/resources/sbcalculator.properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        instance = this;


    }


    static ScenarioProperties getInstance() {
        return instance == null ? new ScenarioProperties() : instance;
    }

    String getProperty(String key) {
        return properties.getProperty(key);
    }

}
