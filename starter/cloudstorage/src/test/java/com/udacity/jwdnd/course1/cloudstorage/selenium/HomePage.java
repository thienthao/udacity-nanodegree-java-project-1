package com.udacity.jwdnd.course1.cloudstorage.selenium;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    @FindBy(id = "logout-button")
    private WebElement logoutButton;

    @FindBy(id = "new-note-btn")
    private WebElement newNoteBtn;

    @FindBy(id = "new-credential-btn")
    private WebElement newCredentialBtn;

    @FindBy(id = "note-add-title")
    private WebElement txtNoteTitle;
    @FindBy(id = "note-add-description")
    private WebElement txtNoteDescription;

    @FindBy(id = "note-edit-title")
    private WebElement txtEditNoteTitle;

    @FindBy(id = "note-edit-description")
    private WebElement txtEditNoteDescription;

    @FindBy(id = "nav-notes-tab")
    private WebElement noteTab;

    @FindBy(id = "nav-credentials-tab")
    private WebElement credentialTab;

    @FindBy(id = "save-note-btn")
    private WebElement saveNoteBtn;

    @FindBy(id = "save-edit-note-btn")
    private WebElement saveEditNoteBtn;

    @FindBy(id = "note-title-tbl")
    private WebElement tblNoteTitle;

    @FindBy(id = "note-description-tbl")
    private WebElement tblNoteDescription;

    @FindBy(id = "edit-note-btn")
    private WebElement editNoteBtn;

    @FindBy(id = "edit-credential-btn")
    private WebElement editCredentialBtn;

    @FindBy(id = "delete-note-btn")
    private WebElement deleteNoteBtn;

    @FindBy(id = "delete-credential-btn")
    private WebElement deleteCredentialBtn;

    @FindBy(id = "credential-add-url")
    private WebElement txtCredentialUrl;

    @FindBy(id = "credential-edit-url")
    private WebElement txtEditCredentialUrl;

    @FindBy(id = "credential-add-username")
    private WebElement txtCredentialUsername;

    @FindBy(id = "credential-edit-username")
    private WebElement txtEditCredentialUsername;

    @FindBy(id = "credential-add-password")
    private WebElement txtCredentialPassword;

    @FindBy(id = "credential-edit-password")
    private WebElement txtEditCredentialPassword;

    @FindBy(id = "credential-add-submit")
    private WebElement saveCredentialBtn;

    @FindBy(id = "save-edit-credential-submit")
    private WebElement saveEditCredentialBtn;

    @FindBy(id = "tbl-credential-url")
    private WebElement tblCredentialUrl;

    @FindBy(id = "tbl-credential-username")
    private WebElement tblCredentialUsername;

    @FindBy(id = "tbl-credential-password")
    private WebElement tblCredentialPassword;

    private final JavascriptExecutor js;

    private final WebDriverWait wait;

    private WebDriver webDriver;

    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        js = (JavascriptExecutor) webDriver;
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
    }

    public void logout() {
        js.executeScript("arguments[0].click();", logoutButton);
    }

    public void openEditNoteModal() {
        js.executeScript("arguments[0].click();", editNoteBtn);
    }

    public void openEditCredentialModal() {
        js.executeScript("arguments[0].click();", editCredentialBtn);
    }

    public void deleteNote() {
        js.executeScript("arguments[0].click();", deleteNoteBtn);
    }

    public void deleteCredential() {
        js.executeScript("arguments[0].click();", deleteCredentialBtn);
    }

    public void openAddNoteModal() {
        js.executeScript("arguments[0].click();", newNoteBtn);
    }

    public void openAddCredentialModal() {
        js.executeScript("arguments[0].click();", newCredentialBtn);
    }

    public void setNoteTitle(String title) {
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.findElement(By.id("note-add-title")).sendKeys(title);
    }

    public void setCredentialUrl(String url) {
        js.executeScript("arguments[0].value='" + url + "';", txtCredentialUrl);
    }

    public void setEditCredentialUrl(String url) {
        js.executeScript("arguments[0].value='" + url + "';", txtEditCredentialUrl);
    }

    public void setCredentialUsername(String username) {
        js.executeScript("arguments[0].value='" + username + "';", txtCredentialUsername);
    }

    public void setEditCredentialUsername(String username) {
        js.executeScript("arguments[0].value='" + username + "';", txtEditCredentialUsername);
    }

    public void setCredentialPassword(String password) {
        js.executeScript("arguments[0].value='" + password + "';", txtCredentialPassword);
    }

    public void setEditCredentialPassword(String password) {
        js.executeScript("arguments[0].value='" + password + "';", txtEditCredentialPassword);
    }

    public void editNoteTitle(String newNoteTitle) {
        wait.until(ExpectedConditions.elementToBeClickable(txtEditNoteTitle)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(txtEditNoteTitle)).sendKeys(newNoteTitle);
    }

    public void editNoteDescription(String newNoteDescription) {
        wait.until(ExpectedConditions.elementToBeClickable(txtEditNoteDescription)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(txtEditNoteDescription)).sendKeys(newNoteDescription);
    }

    public void toNoteTab() {
        js.executeScript("arguments[0].click();", noteTab);
    }

    public void toCredentialTab() {
        js.executeScript("arguments[0].click();", credentialTab);
    }

    public void setNoteDescription(String descrption) {
        js.executeScript("arguments[0].value='"+ descrption +"';", txtNoteDescription);
    }

    public void addNote() {
        js.executeScript("arguments[0].click();", saveNoteBtn);
    }

    public void saveEditNote() {
        js.executeScript("arguments[0].click();", saveEditNoteBtn);
    }

    public void addCredential() {
        js.executeScript("arguments[0].click();", saveCredentialBtn);
    }

    public void editCredential() {
        js.executeScript("arguments[0].click();", saveEditCredentialBtn);
    }

    public boolean noNotes(WebDriver driver) {
        return !isElementPresent(By.id("note-title-tbl"), driver) && !isElementPresent(By.id("note-description-tbl"), driver);
    }

    public boolean noCredentials(WebDriver driver) {
        return !isElementPresent(By.id("tbl-credential-url"), driver) &&
                !isElementPresent(By.id("tbl-credential-username"), driver) &&
                !isElementPresent(By.id("tbl-credential-password"), driver);
    }

    public boolean isElementPresent(By locatorKey, WebDriver driver) {
        try {
            driver.findElement(locatorKey);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public Note getNoteFromTable() {
        String title = wait.until(ExpectedConditions.elementToBeClickable(tblNoteTitle)).getText();
        String description = tblNoteDescription.getText();

        return new Note() {{setTitle(title); setDescription(description);}};
    }

    public Credential getCredentialFromTable() {
        String url = wait.until(ExpectedConditions.elementToBeClickable(tblCredentialUrl)).getText();
        String username = tblCredentialUsername.getText();
        String password = tblCredentialPassword.getText();

        return new Credential() {{setUrl(url); setUsername(username); setPassword(password);}};
    }
}
