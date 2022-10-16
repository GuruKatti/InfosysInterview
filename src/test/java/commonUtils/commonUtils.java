package commonUtils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.io.File;
import java.io.IOException;

public class commonUtils {
    public static String TakeScreenshot(String FileName, WebDriver driver, String path)
            throws IOException {
        // Creating instance of File
        File File = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);
        String screenShotPath = path +"\\"+ FileName + ".png";
        File file = new File(screenShotPath);
        FileUtils.copyFile(File, file);
        return screenShotPath;
    }

}
