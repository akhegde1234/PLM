package com.lmg_dubai.PLM.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.lmg_dubai.PLM.library.BasePage;
import com.lmg_dubai.PLM.library.ExcelLibrary;
import com.lmg_dubai.PLM.library.GenericLib;
import com.lmg_dubai.PLM.listeners.MyExtentListeners;
import com.lmg_dubai.PLM.util.BrowserActionUtil;

public class PLMLoginPage extends BasePage{

	public PLMLoginPage(WebDriver driver) {
		super(driver);
	}

	//Element to identify user id field
	@FindBy(xpath = "//input[@id='user_id_show']")
	private WebElement userIDField;

	//Element to identify password field
	@FindBy(xpath = "//label[text()='Password']/following-sibling::input[@type='password']")
	private WebElement pwdField;

	//Element to identify submit button
	@FindBy(xpath="//button[@id='formsubmit']")
	private WebElement submitButton;
	
	/**
	 * Method to login to PLM Application
	 * @param sheetName
	 * @param userIDRow
	 * @param userIDcol
	 * @param pwdRow
	 * @param pwdcol
	 */
	public void loginToPLM(String sheetName, int userIDRow, int userIDcol,int pwdRow, int pwdcol) {
		String userID = ExcelLibrary.getExcelData(GenericLib.sTestData, sheetName, userIDRow, userIDcol);
		String pwd = ExcelLibrary.getExcelData(GenericLib.sTestData, sheetName, pwdRow, pwdcol);
		try {
			WebDriverWait wait = new WebDriverWait(driver,20000);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='formsubmit']")));
			BrowserActionUtil.clickElement(userIDField, driver, "User ID Field");
			BrowserActionUtil.clearAndType(userIDField, userID, "User ID Field", driver);
			BrowserActionUtil.clickElement(pwdField, driver, "Password Field");
			BrowserActionUtil.clearAndType(pwdField, pwd, "Password Field", driver);
			BrowserActionUtil.clickElement(submitButton, driver, "Submit Button");
		    try {
			WebDriverWait wait1 = new WebDriverWait(driver,20);
			wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='quickSearchInput']")));
			MyExtentListeners.test.pass("Navigated to Home Page");
		    }catch (Exception e) {
			MyExtentListeners.test.info("Could not navigate to Home Page");
			e.printStackTrace();
		    }
		}catch (Exception e) {
			MyExtentListeners.test.info("Login Page not displayed");
		}
	}
}
