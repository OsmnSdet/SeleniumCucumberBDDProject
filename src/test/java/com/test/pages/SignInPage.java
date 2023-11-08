package com.test.pages;

import com.test.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage {


    public SignInPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }


    @FindBy(css = "div.external-login>div")
    public WebElement facebookButton;

    @FindBy(css = "#loginButton")
    public WebElement logInButton;






}
