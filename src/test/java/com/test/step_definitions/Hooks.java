package com.test.step_definitions;

/*
In this class we will be able to pass pre & post-conditions to each scenario and each step
 */

import com.test.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {
    // import from io.cucumber.java NOT from junit
  //  @Before(order = 1)
    public void setupScenario() {
        System.out.println("=====Setting up browser using cucumber @Before");
    }

  //  @Before(value = "@login", order = 2)
    public void setupScenarioForLogins() {
        System.out.println("=====This will only apply to scenarios with @login tag");
    }

   // @Before(value = "@db", order = 0)
    public void setupForDatabaseScenarios() {
        System.out.println("=====This will only apply to scenarios with @db tag");
    }

    @After
    public void teardownScenario(Scenario scenario) {

        // If scenario fails , take the screen shot
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }


       Driver.closeDriver();
        //System.out.println("=====Closing browser using cucumber @After");
        //System.out.println("=====Scenario ended / Take screenshot if failed!");
    }

   // @BeforeStep
    public void setupStep() {
        System.out.println("---------------> applying setup using @BeforeStep");
    }

  //  @AfterStep
    public void afterStep() {
        System.out.println("---------------> applying teardown using @AfterStep");
    }


}
