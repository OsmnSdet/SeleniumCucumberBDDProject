package com.test.pages;

import com.test.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ParfumDeodorantPage {


    public ParfumDeodorantPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(css = "input[placeholder='Marka Ara']")
    public WebElement markaAraInput;

    @FindBy(css = "a[title='Lacoste']")
    public WebElement lacoste;

    @FindBy(css = "div[data-position=\"7\"]")
    public WebElement urun7;



}
