package com.lmg_dubai.PLM.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.lmg_dubai.PLM.library.BasePage;
import com.lmg_dubai.PLM.library.GenericLib;
import com.lmg_dubai.PLM.listeners.MyExtentListeners;
import com.lmg_dubai.PLM.util.BrowserActionUtil;

public class PLMTechSpecPage extends BasePage {

	public PLMTechSpecPage(WebDriver driver) {
		super(driver);
	}

	// Element to identify create tech spec button
	@FindBy(xpath = "//td[@id='CREATE_TECH_SPECbtnCtr']")
	private WebElement createTechSpecBtn;

	// Element to identify hidden tabs expand button
	@FindBy(xpath = "//div[@id='Tabs']//tr[@id='Tabsrow']//td[@id='More']//label[contains(text(),'>>')]")
	private WebElement hiddenTabsExpandBtn;

	// Kundan
	@FindBy(xpath = "//input[@id='0_@100_@4_@0_@0_@0_@_srch']")
	private WebElement StyleNoTextField;

	@FindBy(xpath = "//*[@id='chkRowKeys_@0_@-1_@-1_@-1_@0_@-1_@MainSection']")
	private WebElement SelectBSItem;

	@FindBy(xpath = "//label[@title='More Actions']")
	private WebElement SelectMoreAction;

	@FindBy(xpath = "/html[1]/body[1]/div[6]/table[1]/tbody[1]/tr[3]/td[1]/label[1]")
	private WebElement SelectDeleteAction;

	@FindBy(xpath = "//span[@class='k-button fLeft']")
	private WebElement SelectConfirmAction;

	@FindBy(xpath = "//span[@class=' k-icon k-i-close']")
	private WebElement SelectCancelAction;

	@FindBy(xpath = "//label[@id='DROPbtnCtr']")
	private WebElement SelectDropAction;

	private static String visibleTopSectionTabsXpath = "//div[@id='Tabs']//tr[@id='Tabsrow']//td//label[contains(text(),'$visibleTabOption')]";
	private static String hiddenTopSectionTabsXpath = "//div[@id='divChildActBtns_More']//tr//td//label[contains(text(),'$hiddenTabOption')]";

	public void clickOnCreateTechSpecBtn() throws Exception {
		Thread.sleep(3000);
		GenericLib.explicitWait(driver, "//td[@id='CREATE_TECH_SPECbtnCtr']");
		BrowserActionUtil.clickElement(createTechSpecBtn, driver, "Create Tech Spec Button");
		GenericLib.explicitWait(driver, "//td[@id='TECHSPEC_OVERVIEW_SAVEbtnCtr']");
	}

	public void clickOnTopTabByName(String tabToSelect) throws Exception {
		try {
			String visibleTabXpath = visibleTopSectionTabsXpath.replace("$visibleTabOption", tabToSelect);
			WebElement requiredTab = driver.findElement(By.xpath(visibleTabXpath));
			BrowserActionUtil.clickElement(requiredTab, driver, tabToSelect + " Tab");
			Thread.sleep(3000);
		} catch (Exception e) {
			BrowserActionUtil.clickElement(hiddenTabsExpandBtn, driver, "Tabs Expand Button");
			String hiddenTabXpath = hiddenTopSectionTabsXpath.replace("$hiddenTabOption", tabToSelect);
			WebElement hiddenTab = driver.findElement(By.xpath(hiddenTabXpath));
			BrowserActionUtil.clickElement(hiddenTab, driver, tabToSelect + " Tab");
			Thread.sleep(3000);
		}
	}

	public void clickRecentDocument(String index) throws Exception {
		GenericLib.explicitWait(driver,
				"//div[text()='Recently Viewed TechSpecs']/following-sibling::div//a[text()='* Style No - 048 TB24JUL23-2 TESTING']");
		WebElement recentLink = driver.findElement(By.xpath(
				"(//div[text()='Recently Viewed TechSpecs']/following-sibling::div//a[text()='* Style No - 048 TB24JUL23-2 TESTING'])["
						+ index + "]"));
		BrowserActionUtil.clickElement(recentLink, driver, "Recent Link");
	}

	// Kundan
	public void clickOnStyleNo(String index) throws Exception {
		GenericLib.explicitWait(driver, "//input[@id='0_@100_@4_@0_@0_@0_@_srch']");
		BrowserActionUtil.clickElement(StyleNoTextField, driver, "Style No");
		driver.findElement(By.xpath("//input[@id='0_@100_@4_@0_@0_@0_@_srch']")).sendKeys("BS");
		driver.findElement(By.xpath("//input[@id='0_@100_@4_@0_@0_@0_@_srch']")).sendKeys(Keys.ENTER);
		MyExtentListeners.test.info("Clicked on style number");
	}

	public void SelectItemFromStyleNo(String index) throws Exception {
		Thread.sleep(5000);
		GenericLib.explicitWait(driver, "//*[@id='chkRowKeys_@0_@-1_@-1_@-1_@0_@-1_@MainSection']");
		driver.findElement(By.xpath("//*[@id='chkRowKeys_@0_@-1_@-1_@-1_@0_@-1_@MainSection']")).click();
		MyExtentListeners.test.info("Successfully selected item from Style Number");

	}

	public void SelectMoreAction(String index) throws Exception {
		Thread.sleep(5000);
		GenericLib.explicitWait(driver, "//label[@title='More Actions']");
		driver.findElement(By.xpath("//label[@title='More Actions']")).click();
		MyExtentListeners.test.info("Clicked on More Action drop down");
	}

	public void SelectDeleteAction(String index) throws Exception {
		Thread.sleep(5000);
		GenericLib.explicitWait(driver, "/html[1]/body[1]/div[6]/table[1]/tbody[1]/tr[3]/td[1]/label[1]");
		driver.findElement(By.xpath("/html[1]/body[1]/div[6]/table[1]/tbody[1]/tr[3]/td[1]/label[1]")).click();
		MyExtentListeners.test.info("Selected delete from more Action drop down");
	}

	public void SelectConfirmAction(String index) throws Exception {
		Thread.sleep(5000);
		GenericLib.explicitWait(driver, "//span[@class='k-button fLeft']");
		driver.findElement(By.xpath("//span[@class='k-button fLeft']")).click();
		MyExtentListeners.test.info("Selected Confirm Action to delete tech Spec");
	}

	public String verifyStatusOfStyleNumber() throws InterruptedException {
		Thread.sleep(5000);
		GenericLib.explicitWait(driver, "//label[@id='0_@100_@43_@0_@0_@0']']");
		driver.findElement(By.xpath("//label[@id='0_@100_@43_@0_@0_@0']']")).isDisplayed();
		String status = driver.findElement(By.xpath("//label[@id='0_@100_@43_@0_@0_@0']']")).getText();
		return status;

	}

	public String verifyFieldPresentOnTechSpecPage(String fieldvalue) {

		String val = driver.findElement(By.xpath("//label[contains(text(),'" + fieldvalue + "')]")).getText();
		Boolean fieldVal = driver.findElement(By.xpath("//label[contains(text(),'" + fieldvalue + "')]")).isDisplayed();

		MyExtentListeners.test.info(fieldvalue + " is present on Tech spec page.");
		return val;
	}

	public String verifyFieldPresentOnTechSpecPageOperational(String fieldvalue) {

		String val = driver.findElement(By.xpath("//label[normalize-space()='" + fieldvalue + "']")).getText();
		Boolean fieldVal = driver.findElement(By.xpath("//label[normalize-space()='" + fieldvalue + "']"))
				.isDisplayed();

		MyExtentListeners.test.info(fieldvalue + " is present on Tech spec page.");
		return val;
	}

	public void clickOnClearField() {
		driver.findElement(By.xpath("//td[@id='_clearBtnbtnCtr'][1]")).click();
		MyExtentListeners.test.info("Clicked on clear fields button");
	}

	public void clickOnSaveSearch() {
		driver.findElement(By.xpath("//*[@id='_saveSearchBtnbtnCtr']")).click();
		MyExtentListeners.test.info("Clicked on Save search button");
	}

	public void clickOnCancelBtn() {
		driver.findElement(By.xpath("//*[@id='_cancelBtnbtnCtr']")).click();
		MyExtentListeners.test.info("Clicked on Cancel button");
	}

	public void clickOnAdvSearchTechSpec() {
		driver.findElement(By.xpath("//img[@id='_SearchSection_collapImg']")).click();
		MyExtentListeners.test.info("Expand on Advance Search:Tech spec");
	}

	public void navigateToSaveSearchPopUp(String value) {
		driver.findElement(By.xpath("//*[@id='_SRCH_TEXT']")).click();
		driver.findElement(By.xpath("//*[@id='_SRCH_TEXT']")).sendKeys(value);
		MyExtentListeners.test.info("Entered vale in the pop up to save");

		driver.findElement(By.xpath("//*[@id='_SRCH_NAME_DIV']/table/tbody/tr[7]/td[2]/table/tbody/tr/td/div")).click();
		MyExtentListeners.test.info("Clicked on Save button");
	}

	public String VerifyHeaderOfTechSpecListView() {

		String headerVal = driver.findElement(By.xpath("//*[@id='_contentviewerHeaderBar_divWorkArea']/tbody/tr/td"))
				.getText();
		return headerVal;
	}

	public void clickOnFirstRecordOfTechSpecListView() {
		driver.findElement(By.xpath("//*[@id='MainSection_left_table']/tbody/tr[2]/td[4]")).click();

	}

	public String VerifyHeaderOfTechSpecProductOverView() {
		String headerVal = driver.findElement(By.xpath("//*[@id='_tableHeaderBar132']/tbody/tr/td[2]/b")).getText();
		return headerVal;
	}

	// new

	public void SelectAdoptAction(String index) throws Exception {
		Thread.sleep(5000);
		GenericLib.explicitWait(driver, "/html[1]/body[1]/div[6]/table[1]/tbody[1]/tr[1]/td[1]/label[1]");
		driver.findElement(By.xpath("/html[1]/body[1]/div[6]/table[1]/tbody[1]/tr[1]/td[1]/label[1]")).click();
		MyExtentListeners.test.info("Selected Adopt from more Action Drop down");
	}

	public void SelectDropAction(String index) throws Exception {
		Thread.sleep(5000);
		GenericLib.explicitWait(driver, "/html[1]/body[1]/div[6]/table[1]/tbody[1]/tr[4]/td[1]/label[1]");
		driver.findElement(By.xpath("/html[1]/body[1]/div[6]/table[1]/tbody[1]/tr[4]/td[1]/label[1]")).click();
		MyExtentListeners.test.info("Selected Adopt from more Action drop down");
	}

	public void SelectCancelAction(String index) throws Exception {
		Thread.sleep(5000);
		GenericLib.explicitWait(driver, "//span[@class=' k-icon k-i-close']");
		driver.findElement(By.xpath("//span[@class=' k-icon k-i-close']")).click();
		MyExtentListeners.test.info("Selected Cancel from more Action drop down");
	}

	public void SelectReinstatedAction(String index) throws Exception {
		Thread.sleep(5000);
		GenericLib.explicitWait(driver, "/html[1]/body[1]/div[6]/table[1]/tbody[1]/tr[2]/td[1]/label[1]");
		driver.findElement(By.xpath("/html[1]/body[1]/div[6]/table[1]/tbody[1]/tr[2]/td[1]/label[1]")).click();
		MyExtentListeners.test.info("Selected Reinstated from more Action drop down");
	}

	public void SelectAddBOMAction(String index) throws Exception {
		Thread.sleep(5000);
		GenericLib.explicitWait(driver,
				"//div[@id='divChildActBtns_MORE_ACTIONSvirtual']//label[text()='Mass BOM Add & Replace']");
		driver.findElement(
				By.xpath("//div[@id='divChildActBtns_MORE_ACTIONSvirtual']//label[text()='Mass BOM Add & Replace']"))
				.click();
		MyExtentListeners.test.info("Selected Add BOM from more Action drop down");
	}

	public void SelectAddcomponentAction(String index) throws Exception {
		// Thread.sleep(5000);
		GenericLib.explicitWait(driver,
				"//*[@id='massAddReplaceKendoWnd']/div/div/div/mass-add-replace-container/div/div/ul/li[1]/span[2]");
		driver.findElement(By.xpath(
				"//*[@id='massAddReplaceKendoWnd']/div/div/div/mass-add-replace-container/div/div/ul/li[1]/span[2]"))
				.click();
	}

	public void AddNewComponent() throws Exception {

		Thread.sleep(3000);
		GenericLib.explicitWait(driver, "//*[@id='2d600eb6-4340-4291-8738-87c2413363c5-1']/section/content-collapse[1]/div[2]/div/div[1]/component-selection-sections/div/table/tbody/tr[1]/td[1]/a");
		driver.findElement(By.xpath("//*[@id='2d600eb6-4340-4291-8738-87c2413363c5-1']/section/content-collapse[1]/div[2]/div/div[1]/component-selection-sections/div/table/tbody/tr[1]/td[1]/a")).click();
		
		driver.findElement(By.xpath("//*[@id='valsearch_2_@14000_@4_@1_@1_@1_@desc']/span")).click();
		driver.findElement(By.xpath("//*[@id='Show All_Btn']/table/tbody/tr/td")).click();
		driver.findElement(By.xpath("//*[@id='0_1']")).click();
		
		
		//a[@id='expandAllbomsec']
	}
//		
//		public void SelectMassOffer(String index) throws Exception {
//			Thread.sleep(5000);
//			GenericLib.explicitWait(driver, "//div[@id='divChildActBtns_MORE_ACTIONSvirtual']//label[text()='Mass Offer Request']");
//			driver.findElement(By.xpath("//div[@id='divChildActBtns_MORE_ACTIONSvirtual']//label[text()='Mass Offer Request']")).click();
//			MyExtentListeners.test.info("Selected Mass offer from more Action drop down");
//		}
//		
//		public void SelectSupplierOffer(String index) throws Exception {
//			Thread.sleep(5000);
//			GenericLib.explicitWait(driver, "//*[@id=\"detailSection\"]/div/div/div/div/div/div/div");
//			driver.findElement(By.xpath("//*[@id=\"detailSection\"]/div/div/div/div/div/div/div")).click();
//			MyExtentListeners.test.info("Selected Mass offer from more Action drop down");
//		}
		
	public void SelectMassOffer(String index) throws Exception {
		Thread.sleep(5000);
		GenericLib.explicitWait(driver, "//div[@id='divChildActBtns_MORE_ACTIONSvirtual']//label[text()='Mass Offer Request']");
		driver.findElement(By.xpath("//div[@id='divChildActBtns_MORE_ACTIONSvirtual']//label[text()='Mass Offer Request']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id=\"detailSection\"]/div/div/div/div/div/div/div")).click();
		
		driver.findElement(By.xpath("//*[@id='supplierSelect-list']")).click();
		driver.findElement(By.xpath("//*[@id=\"makeRequestBtn\"]")).click();
		
	}
	
}
		

