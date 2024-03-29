package com.test.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Driver {

    /*
Creating a private constructor so we are closing access to the object of this class from outside the class
 */
    private Driver(){}

    /*
    We make WebDriver private, because we want to close access from outside the class.
    We make it static because we will use it in a static method.
     */

   // private static WebDriver driver;

    private static InheritableThreadLocal<WebDriver> driverPool = new InheritableThreadLocal<>();

    /*
    Create a re-usable utility method which will return same driver instance when we call it
     */
    public static WebDriver getDriver(){
        if (driverPool.get() == null){

            /*
            We read our browserType from configuration.properties.
            This way, we can control which browser is opened from outside our code, configuration.properties.
             */
            String browserType = ConfigurationReader.getProperty("browser");

            /*
            Depending on the BrowserType that will be return from configuration.properties file switch statement will determine the case, and open the matching browser
             */
            switch (browserType){
                case "remote-chrome":
                    try {
                        // assign your grid server address
                        String gridAddress = "54.235.53.73";
                        URL url = new URL("http://" + gridAddress + ":4444/wd/hub");
                        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                        desiredCapabilities.setBrowserName("chrome");
                        driverPool.set(new RemoteWebDriver(url, desiredCapabilities));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case "chrome":
                    driverPool.set(new ChromeDriver());
                    driverPool.get().manage().window().maximize();
                    driverPool.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driverPool.set(new FirefoxDriver());
                    driverPool.get().manage().window().maximize();
                    driverPool.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    break;
                case "chrome_english":
                   ChromeOptions options = new ChromeOptions();
                    Map<String, Object> prefs = new HashMap<String, Object>();
                    prefs.put("intl.accept_languages", "en"); // Set to "en" for English
                    options.setExperimentalOption("prefs", prefs);
                    WebDriverManager.chromedriver().setup();
                 //   options.addArguments("--headless"); // Run Chrome in headless mode
                    driverPool.set(new ChromeDriver(options));
                    driverPool.get().manage().window().maximize();
                    driverPool.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    break;
                    /*
                                    case "chrome_english":
                    ChromeOptions options = new ChromeOptions();
                    Map<String, Object> prefs = new HashMap<String, Object>();
                    prefs.put("intl.accept_languages", "en-GB");
                    options.setExperimentalOption("prefs", prefs);
                    WebDriverManager.chromedriver().setup();
                    //WebDriver driver = new ChromeDriver(options);
                    driver = new ChromeDriver(options);
                    driver.manage().window().maximize();
                    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    break;
                     */
            }

        }
        return driverPool.get();
    }

    /*
    This method will make sure our driver value is always null after using quit() method
     */

    public static void closeDriver(){
        if (driverPool.get() != null){
            driverPool.get().quit(); // this line will terminate the existing session. value will not even be null
            driverPool.remove();
        }
    }
}
