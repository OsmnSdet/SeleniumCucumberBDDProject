package com.test.pages;

import com.test.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MarkalarPage {

    public MarkalarPage(){

        PageFactory.initElements(Driver.getDriver(),this);

    }

    @FindBy(css = "div.fMenu")
    public WebElement footer;
}
