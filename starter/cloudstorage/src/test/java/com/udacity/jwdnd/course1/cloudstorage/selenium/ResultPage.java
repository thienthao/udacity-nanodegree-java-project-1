package com.udacity.jwdnd.course1.cloudstorage.selenium;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class ResultPage {

    private final JavascriptExecutor js;

    public ResultPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        js = (JavascriptExecutor) driver;
    }

    @FindBy(id = "success-back-to-home")
    private WebElement backToHomeBtn;

    public void backFromSuccess() {
        js.executeScript("arguments[0].click();", backToHomeBtn);
    }
}
