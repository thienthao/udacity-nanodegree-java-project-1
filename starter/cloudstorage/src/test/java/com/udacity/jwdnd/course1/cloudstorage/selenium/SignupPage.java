package com.udacity.jwdnd.course1.cloudstorage.selenium;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {

    @FindBy(id = "username")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;
    @FindBy(id = "firstName")
    private WebElement firstNameInput;

    @FindBy(id = "lastName")
    private WebElement lastNameInput;

    @FindBy(id = "signup-button")
    private WebElement signupButton;

    private final JavascriptExecutor js;

    public SignupPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        js = (JavascriptExecutor) driver;
    }

    public void setFirstName(String firstName) {
        js.executeScript("arguments[0].value='"+ firstName +"';", firstNameInput);
    }

    public void setLastName(String lastName) {
        js.executeScript("arguments[0].value='"+ lastName +"';", lastNameInput);
    }

    public void setUserName(String userName) {
        js.executeScript("arguments[0].value='"+ userName +"';", usernameInput);
    }

    public void setPassword(String password) {
        js.executeScript("arguments[0].value='"+ password +"';", passwordInput);
    }

    public void signup() {
        js.executeScript("arguments[0].click();", signupButton);
    }
}
