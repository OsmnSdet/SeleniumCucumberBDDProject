package com.test.pages;

import com.test.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FaceBookLogInPage {

    public FaceBookLogInPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(css = "input#email")
    public WebElement facebookInputEmail;

    @FindBy(css = "#pass")
    public WebElement facebookInputPassword;

    @FindBy(css = "#loginbutton")
    public WebElement logInButton;


}
