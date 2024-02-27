package com.qa.pages;

import com.qa.BaseTest;
import com.qa.utils.TestUtils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class LoginPage extends BaseTest {
	TestUtils utils = new TestUtils();

	@AndroidFindBy (xpath = "//android.widget.TextView[@text=\"Continue\"]")
	@iOSXCUITFindBy (id = "test-Username")
	private WebElement continueBtn;

	@AndroidFindBy (xpath = "//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_button\"]")
	@iOSXCUITFindBy (id = "test-Username")
	private WebElement allowNotification;

	@AndroidFindBy (accessibility = "More")
	@iOSXCUITFindBy (id = "test-Password")
	private WebElement moreBtn;

	@AndroidFindBy (xpath = "//android.widget.TextView[@text=\"Log In\"]")
	@iOSXCUITFindBy (id = "test-Password")
	private WebElement loginBtnMore;

	@AndroidFindBy (xpath = "//android.widget.ScrollView/android.widget.EditText[1]")
	@iOSXCUITFindBy (id = "test-Username")
	private WebElement usernameTxtFld;

	@AndroidFindBy (xpath = "//android.widget.ScrollView/android.widget.EditText[2]")
	@iOSXCUITFindBy (id = "test-Password")
	private WebElement passwordTxtFld;
	
	@AndroidFindBy (xpath = "(//android.widget.TextView[@text=\"Log In\"])[2]")
	@iOSXCUITFindBy (id = "test-LOGIN")
	private WebElement loginBtn;
	
	@AndroidFindBy (xpath = "//android.widget.TextView[@text=\"Enter a valid email address.\"]")
	@iOSXCUITFindBy (xpath = "//XCUIElementTypeOther[@name=\"test-Error message\"]/child::XCUIElementTypeStaticText")
	private WebElement errTxtEmailInvalid;

	@AndroidFindBy (xpath = "//android.widget.TextView[@text=\"This email is not associated with an account. Sign up or try again.\"]")
	@iOSXCUITFindBy (xpath = "//XCUIElementTypeOther[@name=\"test-Error message\"]/child::XCUIElementTypeStaticText")
	private WebElement errTxtEmailNotRegister;

	@AndroidFindBy (xpath = "//android.widget.TextView[@text=\"Email and password don’t match.\"]")
	@iOSXCUITFindBy (xpath = "//XCUIElementTypeOther[@name=\"test-Error message\"]/child::XCUIElementTypeStaticText")
	private WebElement errTxtEmailPassword;

	public void pressContinueBtn() {
		click(continueBtn, "press Continue button");
	}

	public void allowNotification() {
		click(allowNotification, "allow Notification");
	}

	public void pressMoreBtn() {
		click(moreBtn, "press More button");
	}

	public void pressLoginBtnMore() {
		click(loginBtnMore, "press Login button switch to Login screen");
	}

	public LoginPage enterUserName(String username) {
		clear(usernameTxtFld);
		sendKeys(usernameTxtFld, username, "login with " + username);
		return this;
	}

	public LoginPage enterPassword(String password) {
		clear(passwordTxtFld);
		sendKeys(passwordTxtFld, password, "password is " + password);
		return this;
	}

	public ProductsPage pressLoginBtn() {
		click(loginBtn, "press login button");
		return new ProductsPage();
	}

	public ProductsPage login(String username, String password) {
		pressContinueBtn();
		allowNotification();
		pressMoreBtn();
		pressLoginBtnMore();
		enterUserName(username);
		enterPassword(password);
		return pressLoginBtn();
	}

	public String getErrTxtEmailInvalid() {
		String err = getText(errTxtEmailInvalid, "error text is - ");
		return err;
	}

	public String getErrTxtEmailRegister() {
		String err = getText(errTxtEmailNotRegister, "error text is - ");
		return err;
	}

	public String getErrTxtEmailPassword() {
		String err = getText(errTxtEmailPassword, "error text is - ");
		return err;
	}



}
