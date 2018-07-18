package personal.davino.j2ee;

import org.junit.Test;

import java.io.IOException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class ResourceBundleTest {
    @Test
    public void test() {
        ResourceBundle resourceBundle = null;
        try {
            resourceBundle = new PropertyResourceBundle(this.getClass().getClassLoader().
                    getResourceAsStream("i18n/validation_zh_CN.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String string = resourceBundle.getString("validate.employee.firstName");
        System.out.println(string);
    }
}
