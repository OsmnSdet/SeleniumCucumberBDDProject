package com.test.pages;


import com.test.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/*
In this class we will store WebElements common to all pages
 */
public class BasePage {
    public BasePage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(css= "a.btnSignIn")
    public WebElement signInButton;

    @FindBy(xpath = "//button[.='Order']")
    public WebElement order;

    @FindBy(xpath = "//button[.='Logout']")
    public WebElement logoutButton;

    @FindBy(linkText = "Kozmetik & Kişisel Bakım")
    public WebElement kozmetikKisiselBakım;

    @FindBy(linkText = "Parfüm & Deodorant")
    public WebElement parfumDeodorant;

    @FindBy(css = "i.iconsBasketWhite")
    public WebElement cartButton;

    @FindBy(css = "div.fMenu")
    public WebElement footer;

    @FindBy(css = "a[title='Markalar']")
    public WebElement markalar;




}
