package com.qa.tests;

import com.qa.BaseTest;
import com.qa.pages.LoginPage;
import com.qa.pages.ProductsPage;
import com.qa.utils.TestUtils;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.InputStream;
import java.lang.reflect.Method;

public class LoginTests extends BaseTest{
	LoginPage loginPage;
	ProductsPage productsPage;
	JSONObject loginUsers;
	TestUtils utils = new TestUtils();
	
	  @BeforeClass
	  public void beforeClass() throws Exception {
			InputStream datais = null;
		  try {
			  String dataFileName = "data/loginUsers.json";
			  datais = getClass().getClassLoader().getResourceAsStream(dataFileName);
			  JSONTokener tokener = new JSONTokener(datais);
			  loginUsers = new JSONObject(tokener);
		  } catch(Exception e) {
			  e.printStackTrace();
			  throw e;
		  } finally {
			  if(datais != null) {
				  datais.close();
			  }
		  }
		  closeApp();
		  launchApp();
	  }

	  @AfterClass
	  public void afterClass() {
	  }
	  
	  @BeforeMethod
	  public void beforeMethod(Method m) {
		  utils.log().info("\n" + "****** starting test:" + m.getName() + "******" + "\n");
		  loginPage = new LoginPage();
	  }

	  @AfterMethod
	  public void afterMethod() {		  
	  }
	@Test
	public void TC_01_invalidUserName() {
		loginPage.pressContinueBtn();
		loginPage.allowNotification();
		loginPage.pressMoreBtn();
		loginPage.pressLoginBtnMore();
		loginPage.enterUserName(loginUsers.getJSONObject("invalidUser").getString("username"));

		String actualErrTxt = loginPage.getErrTxtEmailInvalid();
		String expectedErrTxt = getStrings().get("err_invalid_username");

		Assert.assertEquals(actualErrTxt, expectedErrTxt);
	}
	  @Test
	  public void TC_02_invalidUserName() {
		  loginPage.enterUserName(loginUsers.getJSONObject("notRegisterUser").getString("username"));
		  loginPage.enterPassword(loginUsers.getJSONObject("notRegisterUser").getString("password"));
		  loginPage.pressLoginBtn();
		  String actualErrTxt = loginPage.getErrTxtEmailRegister();
		  String expectedErrTxt = getStrings().get("err_not_register_username");
		  
		  Assert.assertEquals(actualErrTxt, expectedErrTxt);
	  }
	  
	  @Test
	  public void TC_03_invalidPassword() {

		  loginPage.enterUserName(loginUsers.getJSONObject("invalidPassword").getString("username"));
		  loginPage.enterPassword(loginUsers.getJSONObject("invalidPassword").getString("password"));
		  loginPage.pressLoginBtn();
		  		  
		  String actualErrTxt = loginPage.getErrTxtEmailPassword();
		  String expectedErrTxt = getStrings().get("err_invalid_username_or_password");
		  
		  Assert.assertEquals(actualErrTxt, expectedErrTxt);
	  }
	  
	  @Test
	  public void TC_04_successfulLogin() {
		  loginPage.enterUserName(loginUsers.getJSONObject("validUser").getString("username"));
		  loginPage.enterPassword(loginUsers.getJSONObject("validUser").getString("password"));
		  productsPage = loginPage.pressLoginBtn();
		  		  
		  String actualProductTitle = productsPage.getTitle();		  
		  String expectedProductTitle = getStrings().get("product_title");
		  
		  Assert.assertEquals(actualProductTitle, expectedProductTitle);
	  }
}