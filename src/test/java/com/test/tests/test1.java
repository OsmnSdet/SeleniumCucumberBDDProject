package com.test.tests;


import com.test.pages.FaceBookLogInPage;
import com.test.pages.BasePage;
import com.test.pages.SignInPage;
import com.test.utilities.Driver;
import org.junit.Test;

public class test1 {

    @Test
    public void test1(){

        BasePage basePage = new BasePage();
        SignInPage signInPage = new SignInPage();
        FaceBookLogInPage faceBookLogInPage = new FaceBookLogInPage();

        /*
        Task-1
● Navigate to https://www.n11.com/
● Log in with facebook
● Verify successful login
         */

        Driver.getDriver().get("https://www.n11.com/");

        basePage.signInButton.click();

        String mainHandle = Driver.getDriver().getWindowHandle();

        signInPage.facebookButton.click();

        System.out.println("mainHandle = " + mainHandle);

        for (String each : Driver.getDriver().getWindowHandles()) {

            Driver.getDriver().switchTo().window(each);

            if (!Driver.getDriver().getTitle().equals("Giriş Yap - n11.com")){
                break;
            }
        }
        faceBookLogInPage.facebookInputEmail.sendKeys("abc@gmail.com");
        faceBookLogInPage.facebookInputPassword.sendKeys("abc123");
        faceBookLogInPage.logInButton.click();

        Driver.getDriver().switchTo().window(mainHandle);

        signInPage.logInButton.click();








    }




}
