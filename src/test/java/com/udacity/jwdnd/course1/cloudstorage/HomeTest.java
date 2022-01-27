package com.udacity.jwdnd.course1.cloudstorage;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.ArrayList;
import java.util.List;

public class HomeTest {

    @FindBy(id = "logout-button")
    private WebElement logoutButton;


    @FindBy(id = "nav-files-tab")
    private WebElement navFilesTab;
    @FindBy(id = "fileUpload")
    private WebElement fileUpload;
    @FindBy(id = "upload-button")
    private WebElement uploadButton;


    @FindBy(id = "nav-notes-tab")
    private WebElement navNotesTab;
    @FindBy(id = "add-note")
    private WebElement addNote;
    @FindBy(id = "note-tbody")
    private List<WebElement> noteTbody;
    @FindBy(id = "edit-note")
    private WebElement editNote;
    @FindBy(id = "delete-note")
    private WebElement deleteNote;
    @FindBy(id = "noteTitle")
    private List<WebElement> noteTitle;
    @FindBy(id = "note-title")
    private WebElement noteTitleText;
    @FindBy(id = "note-description")
    private WebElement noteDescriptionText;
    @FindBy(id = "noteSubmit")
    private WebElement noteSubmit;
    @FindBy(id = "userTable")
    private WebElement userTable;
    @FindBy(id = "noteModal")
    private WebElement noteModal;


    @FindBy(id = "nav-credentials-tab")
    private WebElement navCredentialsTab;
    @FindBy(id = "add-credential")
    private WebElement addCredential;
    @FindBy(id = "edit-credential")
    private WebElement editCredential;
    @FindBy(id = "delete-credential")
    private WebElement deleteCredential;
    @FindBy(id = "cred-url")
    private WebElement credUrl;
    @FindBy(id = "cred-username")
    private WebElement credUsername;
    @FindBy(id = "cred-password")
    private WebElement credPassword;
    @FindBy(id = "credential-url")
    private WebElement credentialUrlText;
    @FindBy(id = "credential-username")
    private WebElement credentialUsernameText;
    @FindBy(id = "credential-password")
    private WebElement credentialPasswordText;
    @FindBy(id = "credentialSubmit")
    private WebElement credentialSubmit;
    @FindBy(id = "credentialTable")
    private WebElement credentialTable;
    @FindBy(id = "credentialModal")
    private WebElement credentialModal;
    @FindBy(id = "cred-tbody")
    private List<WebElement> credTbody;

    private WebDriver driver;
    private WebDriverWait webDriverWait;
    private final JavascriptExecutor js;


    public HomeTest(WebDriver webDriver){
        PageFactory.initElements(webDriver, this);
        this.js = (JavascriptExecutor) webDriver;
        this.driver = webDriver;
        this.webDriverWait= new WebDriverWait(webDriver, 5);
    }

    public void logOut(){
        webDriverWait.until(ExpectedConditions.elementToBeClickable(logoutButton));
        js.executeScript("arguments[0].click();",logoutButton);
    }

    public void noteTabView(){
        webDriverWait.until(ExpectedConditions.elementToBeClickable(navNotesTab));
        js.executeScript("arguments[0].click();", this.navNotesTab);
    }

    public void waitUntilNoteModalPage(){
        webDriverWait.until(ExpectedConditions.elementToBeClickable(userTable));
    }

    public void addUserNote(String noteTitle, String noteDescription){
        webDriverWait.until(ExpectedConditions.elementToBeClickable(this.addNote));
        js.executeScript("arguments[0].click();",this.addNote);
        js.executeScript("arguments[0].value= '"+ noteTitle +"';",this.noteTitleText);
        js.executeScript("arguments[0].value= '"+ noteDescription +"';",this.noteDescriptionText);
        js.executeScript("arguments[0].click();",this.noteSubmit);
    }

    public List<String> getNoteTitle(){
        List<String>note =new ArrayList<>();
        for (int i=0; i<noteTitle.size(); i++){

            note.add(this.noteTitle.get(i).getText());
        }
        return note;
    }

    public void clickEditButton(){
        webDriverWait.until(ExpectedConditions.elementToBeClickable(noteTbody.get(0)));
        js.executeScript("arguments[0].click();",this.editNote);
    }

    public void editUserNote(String noteTitle, String noteDescription){

        waitUntilNoteModalPage();
//        System.out.println(noteTbody.size());
        clickEditButton();
        //js.executeScript("arguments[0].click();",this.editNote);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(noteModal));
        this.noteTitleText.clear();
        this.noteDescriptionText.clear();
        js.executeScript("arguments[0].value= '"+ noteTitle +"';",this.noteTitleText);
        js.executeScript("arguments[0].value= '"+ noteDescription +"';",this.noteDescriptionText);
        js.executeScript("arguments[0].click();",this.noteSubmit);

    }
    public void deleteUserNote(){
        webDriverWait.until(ExpectedConditions.elementToBeClickable(noteTbody.get(0)));
        js.executeScript("arguments[0].click();",this.deleteNote);
    }


    public void credentialTabView(){
        webDriverWait.until(ExpectedConditions.elementToBeClickable(navCredentialsTab));
        js.executeScript("arguments[0].click();", this.navCredentialsTab);
    }

    public void waitUntilCredentialModalPage(){
        webDriverWait.until(ExpectedConditions.elementToBeClickable(credentialTable));
    }

    public void addUserCredential(String url, String username, String password ){
        webDriverWait.until(ExpectedConditions.elementToBeClickable(this.addCredential));
        js.executeScript("arguments[0].click();",this.addCredential);
        js.executeScript("arguments[0].value= '"+ url +"';",this.credentialUrlText);
        js.executeScript("arguments[0].value= '"+ username +"';",this.credentialUsernameText);
        js.executeScript("arguments[0].value= '"+ password +"';",this.credentialPasswordText);
        js.executeScript("arguments[0].click();",this.credentialSubmit);
    }

    public List<String> getCredentialPasswords(){
        List<String> password =new ArrayList<>();
        for (int i=0; i<credTbody.size(); i++){

            password.add(this.credTbody.get(i).findElement(By.id("cred-password")).getText());
        }
        return password;
    }

    public List<String> getCredentialUsernames(){
        List<String> usernames =new ArrayList<>();
        for (int i=0; i<credTbody.size(); i++){

            usernames.add(this.credTbody.get(i).findElement(By.id("cred-username")).getText());
        }
        return usernames;
    }

    public void clickCredentialButton(){
        webDriverWait.until(ExpectedConditions.elementToBeClickable(credTbody.get(0)));
        js.executeScript("arguments[0].click();",this.editCredential);
    }

    public void editCredentialNote(String url, String username, String password ){

//        waitUntilNoteModalPage();
//        System.out.println(noteTbody.size());
        //js.executeScript("arguments[0].click();",this.editNote);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(credentialModal));
        this.credentialUrlText.clear();
        this.credentialUsernameText.clear();
        this.credentialPasswordText.clear();
        js.executeScript("arguments[0].value= '"+ url +"';",this.credentialUrlText);
        js.executeScript("arguments[0].value= '"+ username +"';",this.credentialUsernameText);
        js.executeScript("arguments[0].value= '"+ password +"';",this.credentialPasswordText);
        js.executeScript("arguments[0].click();",this.credentialSubmit);

    }
    public void deleteCredentialNote(){
        webDriverWait.until(ExpectedConditions.elementToBeClickable(credTbody.get(0)));
        js.executeScript("arguments[0].click();",this.deleteCredential);
    }







}
