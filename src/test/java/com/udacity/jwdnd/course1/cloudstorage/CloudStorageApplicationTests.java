package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;


import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	private static WebDriver driver;
	private JavascriptExecutor js;
	private WebDriverWait webDriverWait;
	private EncryptionService encryptionService;


	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		driver = new ChromeDriver();
		this.js = (JavascriptExecutor) driver;
		this.webDriverWait = new WebDriverWait(driver, 5);
	}

	@AfterEach
	public void afterEach() {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test
	public void testUnauthorizedUser() {
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());

		driver.get("http://localhost:" + this.port + "/signup");
		Assertions.assertEquals("Sign Up", driver.getTitle());

		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertFalse(driver.getTitle().equals("Home"));
	}


	@Test
	public void allNoteTest(){
		driver.get("http://localhost:" + this.port + "/signup");
		SignupTest signupTest = new SignupTest(driver);
		signupTest.signUp("Adebiyi", "hadizah", "hadizahadebiyi","adeyinka19");
		webDriverWait.until(ExpectedConditions.titleContains("Sign Up"));
		Assertions.assertEquals(driver.findElement(By.id("success-msg")).getText(), "You successfully signed up! Please continue to the login page.");

		signupTest.signUp("Adebiyi", "hadizah", "hadizahadebiyi","adeyinka19");
		webDriverWait.until(ExpectedConditions.titleContains("Sign Up"));
		Assertions.assertEquals(driver.findElement(By.id("error-msg")).getText(), "Username is not Available");

		driver.get("http://localhost:" + this.port + "/login");
		LoginTest loginTest =new LoginTest(driver);
		loginTest.login("hadizahadebiyi","adeyinka19");
		Assertions.assertEquals(driver.getCurrentUrl(),"http://localhost:" + this.port + "/home");

		webDriverWait.until(ExpectedConditions.titleContains("Home"));
		HomeTest homeTest = new HomeTest(driver);
		homeTest.noteTabView();
		homeTest.addUserNote("My Routine","0400Hrs: Observe Tahajud, '\n' 0430Hrs: Solve code problem '\n' 0500Hrs: Read Quran '\n' 0530Hrs: Observe salat ");
		webDriverWait.until(ExpectedConditions.titleContains("Result"));
		Assertions.assertEquals("Success",driver.findElement(By.id("success")).getText());

		ResultTest resultTest = new ResultTest(driver);
		resultTest.resultPageClick();
		webDriverWait.until(ExpectedConditions.titleContains("Home"));

		homeTest.noteTabView();
		homeTest.addUserNote("Books to read","Boot-spring fundamentals, '\n' Design Patterns '\n' Reactjs with boot spring '\n' Data Structures ");
		webDriverWait.until(ExpectedConditions.titleContains("Result"));
		Assertions.assertEquals("Success",driver.findElement(By.id("success")).getText());


		resultTest.resultPageClick();
		webDriverWait.until(ExpectedConditions.titleContains("Home"));

		homeTest.noteTabView();
		homeTest.addUserNote("Task","Gas Demand, '\n' Agrocowsh Presentation '\n' Crude Distribution ");
		webDriverWait.until(ExpectedConditions.titleContains("Result"));
		Assertions.assertEquals("Success",driver.findElement(By.id("success")).getText());

		resultTest.resultPageClick();
		webDriverWait.until(ExpectedConditions.titleContains("Home"));
		homeTest.noteTabView();
		homeTest.waitUntilNoteModalPage();
		List<String >homeNote =homeTest.getNoteTitle();
		//System.out.println(homeNote.toString());
		Assertions.assertEquals("My Routine", homeNote.get(0));
		Assertions.assertEquals("Books to read", homeNote.get(1));

		webDriverWait.until(ExpectedConditions.titleContains("Home"));
		homeTest.noteTabView();
		homeTest.editUserNote("My Workflow","0400Hrs: Observe Tahajud, '\n' 0430Hrs: Solve code problem '\n' 0500Hrs: Read Quran '\n' 0530Hrs: Observe salat ");
		webDriverWait.until(ExpectedConditions.titleContains("Result"));
		Assertions.assertEquals("Success",driver.findElement(By.id("success")).getText());

		resultTest.resultPageClick();
		webDriverWait.until(ExpectedConditions.titleContains("Home"));

		homeTest.noteTabView();
		homeTest.waitUntilNoteModalPage();
		List<String >homeNote2 =homeTest.getNoteTitle();
		Assertions.assertEquals("My Workflow", homeNote2.get(0));

		homeTest.deleteUserNote();
		webDriverWait.until(ExpectedConditions.titleContains("Result"));
		Assertions.assertEquals("Success",driver.findElement(By.id("success")).getText());

		resultTest.resultPageClick();
		webDriverWait.until(ExpectedConditions.titleContains("Home"));

		homeTest.noteTabView();
		List<String >homeNote3 =homeTest.getNoteTitle();
//		Assertions.assertThrows(NoSuchElementException.class, ()->{
//			homeTest.getNoteTitle();
//		});
		Assertions.assertFalse(homeNote3.contains("My Workflow"));



		homeTest.credentialTabView();
		homeTest.addUserCredential("https://outlook.com/signIn","ismailadebiyi","adebayo247");
		webDriverWait.until(ExpectedConditions.titleContains("Result"));
		Assertions.assertEquals("Success",driver.findElement(By.id("success")).getText());

		resultTest.resultPageClick();
		webDriverWait.until(ExpectedConditions.titleContains("Home"));

//		homeTest.credentialTabView();
//		homeTest.addUserCredential("https://protonmail.com/login","ismailade","adebayo2516");
//		webDriverWait.until(ExpectedConditions.titleContains("Result"));
//		Assertions.assertEquals("Success",driver.findElement(By.id("success")).getText());
//
//
//		resultTest.resultPageClick();
//		webDriverWait.until(ExpectedConditions.titleContains("Home"));

		homeTest.credentialTabView();
		homeTest.waitUntilCredentialModalPage();
		List<String >homeCred =homeTest.getCredentialPasswords();

		//System.out.println(homeNote.toString());
		Assertions.assertTrue(homeCred.get(0).matches("^(([\\w+/]{4}){19}\r\n)*(([\\w+/]{4})*([\\w+/]{4}|[\\w+/]{3}=|[\\w+/]{2}==))$"));
//		Assertions.assertEquals("My Routine", homeNote.get(0));

		homeTest.clickCredentialButton();
		//js.executeScript("arguments[0].click();",this.editNote);
		webDriverWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("credentialModal"))));
//		Assertions.assertEquals("adebayo247",driver.findElement(By.id("cred-username")).getAttribute("value"));

		homeTest.editCredentialNote("https://outlook.com/signIn","ismailadebiyi25","adebayo2501");
		webDriverWait.until(ExpectedConditions.titleContains("Result"));
		Assertions.assertEquals("Success",driver.findElement(By.id("success")).getText());
		resultTest.resultPageClick();
		webDriverWait.until(ExpectedConditions.titleContains("Home"));
		homeTest.credentialTabView();
		homeTest.waitUntilCredentialModalPage();
		List<String >nameCred =homeTest.getCredentialUsernames();
		Assertions.assertEquals("ismailadebiyi25", nameCred.get(0));

		homeTest.deleteCredentialNote();
		webDriverWait.until(ExpectedConditions.titleContains("Result"));
		Assertions.assertEquals("Success",driver.findElement(By.id("success")).getText());
		resultTest.resultPageClick();
		webDriverWait.until(ExpectedConditions.titleContains("Home"));
		homeTest.credentialTabView();
		List<String >nameCred1 =homeTest.getCredentialUsernames();
		Assertions.assertFalse(nameCred1.contains("ismailadebiyi25"));

//		Assertions.assertThrows(NoSuchElementException.class, ()->{
//			homeTest.getCredentialUsernames().contains("ismailadebiyi25");
//		});


		homeTest.logOut();
		webDriverWait.until(ExpectedConditions.titleContains("Login"));
		Assertions.assertEquals(driver.getCurrentUrl(),"http://localhost:" + this.port + "/login");

	}
}
