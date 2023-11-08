package com.test.pages;

import com.test.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SeventhProductPage {

    public SeventhProductPage(){

        PageFactory.initElements(Driver.getDriver(),this);

    }

    @FindBy(css = "a#addToFavouriteWishListBtn")
    public WebElement addFavoriBtn;


    @FindBy(css = "h1.proName")
    public WebElement productName;

    @FindBy(css = "button.addBasketUnify")
    public WebElement addToCart;




}
