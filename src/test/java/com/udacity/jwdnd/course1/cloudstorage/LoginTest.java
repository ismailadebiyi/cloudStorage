package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginTest {

    @FindBy(id = "inputUsername")
    private WebElement inputUsername;

    @FindBy(id = "inputPassword")
    private WebElement inputPassword;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    private final JavascriptExecutor js;



    public LoginTest(WebDriver webDriver){
        PageFactory.initElements(webDriver,this);
        js = (JavascriptExecutor) webDriver;
    }

    public void login(String username, String password){
        js.executeScript("arguments[0].value='"+ username +"';",this.inputUsername);
        js.executeScript("arguments[0].value='"+ password +"';",this.inputPassword);
        js.executeScript("arguments[0].click();",this.loginButton);
    }
}
