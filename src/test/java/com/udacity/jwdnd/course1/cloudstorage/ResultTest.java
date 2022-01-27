package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class ResultTest {

    @FindBy(tagName = "a")
    private WebElement pageMsgLink;


    private final JavascriptExecutor js;

    public ResultTest(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        js = (JavascriptExecutor) webDriver;
    }

    public void resultPageClick(){
        js.executeScript("arguments[0].click();",pageMsgLink);
    }
}
