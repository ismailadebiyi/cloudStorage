package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupTest {

    @FindBy(id = "inputFirstName")
    private WebElement inputFirstName;

    @FindBy(id = "inputLastName")
    private WebElement inputLastName;

    @FindBy(id = "inputUsername")
    private WebElement inputUsername;

    @FindBy(id = "inputPassword")
    private WebElement inputPassword;

    @FindBy(id = "sign-up")
    private WebElement signUp;

    private final JavascriptExecutor js;

    public SignupTest(WebDriver webDriver){
        PageFactory.initElements(webDriver, this);
        js = (JavascriptExecutor) webDriver;
    }

    public void signUp(String firstName, String lastName, String userName, String password){
        js.executeScript("arguments[0].value='"+firstName+"';",this.inputFirstName);
        js.executeScript("arguments[0].value='"+lastName+"';",this.inputLastName);
        js.executeScript("arguments[0].value='"+userName+"';",this.inputUsername);
        js.executeScript("arguments[0].value='"+password+"';",this.inputPassword);
        js.executeScript("arguments[0].click();",this.signUp);
    }
}
