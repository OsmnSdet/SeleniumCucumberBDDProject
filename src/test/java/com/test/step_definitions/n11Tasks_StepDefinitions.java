package com.test.step_definitions;

import com.test.pages.*;
import com.test.utilities.Driver;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class n11Tasks_StepDefinitions {

    BasePage basePage = new BasePage();
    SignInPage signInPage = new SignInPage();
    FaceBookLogInPage faceBookLogInPage = new FaceBookLogInPage();
    ParfumDeodorantPage parfumDeodorantPage = new ParfumDeodorantPage();
    SeventhProductPage seventhProductPage = new SeventhProductPage();
    MarkalarPage markalarPage = new MarkalarPage();
    CartPage cartPage = new CartPage();

    Actions actions = new Actions(Driver.getDriver());
    String productNameInPage;
    List<WebElement> webPageLinks;
    List<String> fileLinks;


    @Given("I navigate to {string}")
    public void iNavigateTo(String URL) {

        Driver.getDriver().get(URL);
    }

    @When("user log in with Facebook")
    public void user_log_in_with_facebook() {

        // user clicks to signIn button on the top right of the page
        basePage.signInButton.click();

        // we saved to main windowHandle
        String mainHandle = Driver.getDriver().getWindowHandle();
        System.out.println("mainHandle = " + mainHandle);

        // user clicks the facebook option for logging
        signInPage.facebookButton.click();

        // after user clicking the facebook button opens new window so we are getting all of the windows
        for (String each : Driver.getDriver().getWindowHandles()) {
            Driver.getDriver().switchTo().window(each);
            if (!Driver.getDriver().getTitle().equals("Giriş Yap - n11.com")){
                break;
            }
        }

        // we pass email and password and click on the login button
        faceBookLogInPage.facebookInputEmail.sendKeys("abc@gmail.com");
        faceBookLogInPage.facebookInputPassword.sendKeys("abc123");
        faceBookLogInPage.logInButton.click();

        Driver.getDriver().switchTo().window(mainHandle);

        signInPage.logInButton.click();


    }

    @Then("verify that user login successfully")
    public void verify_that_user_login_successfully() {

    }

    @When("user navigate to {string} under the {string} tab")
    public void user_navigate_to_under_the_tab(String kisiselBakim, String deodorant) {

        actions.moveToElement(basePage.kozmetikKisiselBakım).perform();
        actions.moveToElement(basePage.parfumDeodorant).click().perform();

    }

    @When("user search for {string} in the Brand filter")
    public void user_search_for_in_the_brand_filter(String string) {

        parfumDeodorantPage.markaAraInput.sendKeys(string);
    }

    @When("user select {string}")
    public void user_select(String string) {

        // Scroll down to “Lacoste” link
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true)",parfumDeodorantPage.lacoste);

        parfumDeodorantPage.lacoste.click();
    }

    @When("user click the 7th product on the search result page")
    public void user_click_the_7th_product_on_the_search_result_page() {
        actions.moveToElement(parfumDeodorantPage.urun7).perform();
        parfumDeodorantPage.urun7.click();
    }

    @When("user add the product to {string}")
    public void user_add_the_product_to(String string) {
        seventhProductPage.addToCart.click();
         productNameInPage = seventhProductPage.productName.getText();
    }

    @When("user navigate to {string}")
    public void user_navigate_to(String string) {
        basePage.cartButton.click();
    }

    @Then("verify that the product title is the same as the product details page")
    public void verify_that_the_product_title_is_the_same_as_the_product_details_page() {

        String productNameInCart = cartPage.productInCart.getText();

        Assert.assertEquals(productNameInPage,productNameInCart);
    }

    @When("user write all the links at the footer to a text file")
    public void user_write_all_the_links_at_the_footer_to_a_text_file() {

        List<WebElement> footerLinks = basePage.footer.findElements(By.tagName("a"));

        // Write the links to a text file
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/test/resources/footer_links.txt"));
            for (WebElement link : footerLinks) {
                writer.write(link.getAttribute("href"));
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @When("user click {string} at the footer")
    public void user_click_at_the_footer(String string) {

        actions.moveToElement(basePage.markalar).perform();
        basePage.markalar.click();

        webPageLinks = markalarPage.footer.findElements(By.tagName("a"));

    }

    @Then("user verify the footer links with the text file")
    public void user_verify_the_footer_links_with_the_text_file() {

        // Read the links from the text file and store them in the fileLinks list.
        fileLinks = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/footer_links.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                fileLinks.add(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("fileLinks = " + fileLinks);

        // Compare the webPageLinks list with the fileLinks list to ensure they match.
        for (WebElement webPageLink : webPageLinks) {
          Assert.assertTrue(fileLinks.contains(webPageLink.getAttribute("href")));
        }
        Assert.assertEquals(webPageLinks.size(), fileLinks.size());

    }

}
