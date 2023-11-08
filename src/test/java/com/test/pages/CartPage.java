package com.test.pages;

import com.test.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {


    public CartPage(){

        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(css = "a.prodDescription")
    public WebElement productInCart;


}
