package com.lmg_dubai.PLM.pages;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.lmg_dubai.PLM.library.BasePage;
import com.lmg_dubai.PLM.library.ExcelLibrary;
import com.lmg_dubai.PLM.library.GenericLib;
import com.lmg_dubai.PLM.listeners.MyExtentListeners;
import com.lmg_dubai.PLM.util.BrowserActionUtil;

public class PLMTechSpecOverviewPage extends BasePage{

	public PLMTechSpecOverviewPage(WebDriver driver) {
		super(driver);
	}
	
	//Element to identify search button in popup
	@FindBy(xpath="//td//span[@id='Search_Btn']")
	private WebElement popupSearchBtn;

	//Element to identify first record in search list
	@FindBy(xpath="//tr[contains(@id,'searchListRow')]")
	private WebElement firstSearchRecord;

	//Element to identify Save Button
	@FindBy(xpath="//td[@id='TECHSPEC_OVERVIEW_SAVEbtnCtr']")
	private WebElement saveBtn;

	//Element to identify previous month button in calendar popup
	@FindBy(xpath="//td[@class='cpMonthNavigation']//a[contains(text(),'<<')]")
	private WebElement prevMonthBtn;

	//Element to identify next month button in calendar popup
	@FindBy(xpath="//td[@class='cpMonthNavigation']//a[contains(text(),'>>')]")
	private WebElement nextMonthBtn;

	//Element to identify previous year button in calendar popup
	@FindBy(xpath="//td[@class='cpYearNavigation']//a[contains(text(),'<<')]")
	private WebElement prevYearBtn;

	//Element to identify next year button in calendar popup
	@FindBy(xpath="//td[@class='cpYearNavigation']//a[contains(text(),'>>')]")
	private WebElement nextYearBtn;

	//Element to identify current year in calendar popup
	@FindBy(xpath="//td[@class='cpYearNavigation']//span[text()]")
	private WebElement currYear;

	//Element to identify current month in calendar popup
	@FindBy(xpath="//td[@class='cpMonthNavigation']//span[text()]")
	private WebElement currMonth;

	//Element to identify color info color code 
	@FindBy(xpath="//input[@id='7_@13700_@10_@0_@0_@-1']")
	private WebElement colorInfoColorCode;

	//Element to identify color info family/shade search icon
	@FindBy(xpath="//img[@id='valsearch_7_@13700_@12_@0_@0_@-1_@desc']")
	private WebElement colorInfoFamilyShadeSearchIcon;

	//Element to identify Status Field-ReadOnly
	@FindBy(xpath="//label[text()='Status']/following-sibling::input[@id='3_@100_@43_@0_@0_@0']")
	private WebElement statusField;

	//Element to identify Request No. Field-ReadOnly
	@FindBy(xpath="//label[text()='Request No']/following-sibling::input[@id='3_@100_@2_@0_@0_@0']")
	private WebElement requestNumberField;


	private static String searchPopupDropdownLabelXpath="//div[@id='searchBarDiv']//td[@class='searchLabels' and text()='$popupLabelName']";
	private static String searchPopupDropdownXpath="(//td//select[@class='searchOpDropDown'])[$dropdownIndex]";
	private static String searchPopupTextFieldXpath="(//td//input[@class='searchInput'])[$dropdownIndex]";
	private static String labelValueXpath="//label[contains(text(),'$labelName')]/following-sibling::input";
	private static String colorInformationLabelXpath="//table[@id='132_7_tabDetail']//tr//td[@class='clslabelheader']//label[contains(text(),'$labelName')]";
	private static String anchorTextButtonXpath="//a[contains(text(),'$textValue')]";
	private static String floatingTableXpath="//table[@id='detail_section_right_table']//label[contains(text(),'$headerLabel')]/parent::td";
	private static String floatingTableFieldXpath="(//table[@id='detail_section_right_table']//tr[@class='row1']//td)[$fieldIndex]";
	private static String popupFieldByIDAttribute = "//input[contains(@id,'$idAttribute')]";
	
	/**
	 * Method to scroll and enter text in input field based on label name
	 * @param sheetName
	 * @param labelRow
	 * @param labelCol
	 * @param valueRow
	 * @param valueCol
	 * @throws Exception
	 */
	public void enterTextInInputFieldAfterScrolling(String sheetName, int labelRow, int labelCol, int valueRow, int valueCol) throws Exception {
		String valueToEnter= ExcelLibrary.getExcelData(GenericLib.sTestData, sheetName, valueRow, valueCol);
		String labelName= ExcelLibrary.getExcelData(GenericLib.sTestData, sheetName, labelRow, labelCol);
		System.out.println("Value to enter is:"+valueToEnter);
		System.out.println("Label Name is:"+labelName);
		WebElement inputField = driver.findElement(By.xpath("//label[contains(text(),'"+labelName+"')]/following-sibling::input"));
		BrowserActionUtil.scrollToElement(driver, inputField);
		BrowserActionUtil.clickElement(inputField, driver, labelName+" field");
		BrowserActionUtil.clearAndType(inputField, valueToEnter, labelName+" field", driver);
	}
	
	public void enterDateInDateInputFieldAfterScrolling(String sheetName, int labelRow, int labelCol, int valueRow, int valueCol) throws Exception {
		String valueToEnter= ExcelLibrary.getExcelData(GenericLib.sTestData, sheetName, valueRow, valueCol);
		String labelName= ExcelLibrary.getExcelData(GenericLib.sTestData, sheetName, labelRow, labelCol);
		System.out.println("Value to enter is:"+valueToEnter);
		System.out.println("Label Name is:"+labelName);
		WebElement inputField = driver.findElement(By.xpath("//label[contains(text(),'"+labelName+"')]/following-sibling::div//input"));
		BrowserActionUtil.scrollToElement(driver, inputField);
		BrowserActionUtil.clickElement(inputField, driver, labelName+" field");
		BrowserActionUtil.clearAndType(inputField, valueToEnter, labelName+" field", driver);
	}

	/**
	 * Method to handle select based dropdown in popup and pick specific dropdown option 
	 * @param sheetName1
	 * @param popupDropdownLabelNameRow
	 * @param popupDropdownLabelNameCol
	 * @param searchTextRow
	 * @param searchTextCol
	 * @param sheetName2
	 * @param dropdownOptionRow
	 * @param dropdownOptionCol
	 * @throws Exception
	 */
	public void handlePopupDropdownAndEnterSearchText(String sheetName1, int popupDropdownLabelNameRow, int popupDropdownLabelNameCol, int searchTextRow, int searchTextCol, String sheetName2, int dropdownOptionRow, int dropdownOptionCol ) throws Exception{
		String popupDropdownLabelName = ExcelLibrary.getExcelData(GenericLib.sTestData, sheetName1, popupDropdownLabelNameRow, popupDropdownLabelNameCol);
		String textOptionInDropdown = ExcelLibrary.getExcelData(GenericLib.sTestData, sheetName2, dropdownOptionRow, dropdownOptionCol);
		String searchText = ExcelLibrary.getExcelData(GenericLib.sTestData, sheetName1, searchTextRow, searchTextCol);
		//Popup handling-Dropdown and text field
		String reqdLabelXpath=searchPopupDropdownLabelXpath.replace("$popupLabelName", popupDropdownLabelName);
		System.out.println("Search Popup Dropdown Label Xpath is: "+reqdLabelXpath);
		WebElement dropdownLabel = driver.findElement(By.xpath(reqdLabelXpath));
		int columnIndex = getColumnIndex(dropdownLabel);
		System.out.println("Column Index of "+popupDropdownLabelName+" is: "+columnIndex);
		String dropdownInd = String.valueOf(columnIndex);
		System.out.println("Dropdown Index is: "+dropdownInd);
		String reqdDropdownXpath=searchPopupDropdownXpath.replace("$dropdownIndex", dropdownInd);
		WebElement dropdown = driver.findElement(By.xpath(reqdDropdownXpath));
		BrowserActionUtil.clickElement(dropdown, driver, popupDropdownLabelName+" Dropdown");
		BrowserActionUtil.handleSelectDropdown(driver, dropdown, textOptionInDropdown);
		String searchTextFieldXpath = searchPopupTextFieldXpath.replace("$dropdownIndex", dropdownInd);
		System.out.println("Search Text field Xpath is: "+searchTextFieldXpath);
		WebElement searchTextFld = driver.findElement(By.xpath(searchTextFieldXpath));
		BrowserActionUtil.clickElement(searchTextFld, driver, popupDropdownLabelName+" Search Field");
		BrowserActionUtil.clearAndType(searchTextFld, searchText, popupDropdownLabelName+" Search Field", driver);
	}
	
	/**
	 * Method to click on Search Button and double click on first record in search list
	 * @throws Exception
	 */
	public void clickOnSearchButtonInPopupAndSelectFirstRecord() throws Exception {
		BrowserActionUtil.waitForElement(popupSearchBtn, driver, "Popup Search Button", 2);
		BrowserActionUtil.clickElement(popupSearchBtn, driver, "Popup Search Button");
		GenericLib.explicitWait(driver, "//tr[contains(@id,'searchListRow')]");
		BrowserActionUtil.doubleClickOnElement(driver, firstSearchRecord, "First Record In Search List");
	}
	
	/**
	 * Method to scroll and click on Search Icon Based on Label
	 * @param sheetName
	 * @param labelRow
	 * @param labelCol
	 * @throws Exception
	 */
	public void clickOnSearchIconByLabelName(String sheetName, int labelRow, int labelCol) throws Exception {
		String searchIconLabelName = ExcelLibrary.getExcelData(GenericLib.sTestData, sheetName, labelRow, labelCol);
		System.out.println("Search Icon Label Name is: "+searchIconLabelName);
		WebElement searchIcon = driver.findElement(By.xpath("//label[contains(text(),'"+searchIconLabelName+"')]/following-sibling::div//img[contains(@id,'valsearch')]"));
		BrowserActionUtil.scrollToElement(driver, searchIcon);
		BrowserActionUtil.clickElement(searchIcon, driver, searchIconLabelName+" Search Icon");
		GenericLib.explicitWait(driver, "//div[@id='validationsearchcontroldiv']");	
	}
	
	/**
	 * Method to get column index of labels in search popup
	 * @param tdElement
	 * @return
	 */
	public int getColumnIndex(WebElement tdElement) { 
		WebElement parentRow = tdElement.findElement(By.xpath("./..")); // Get parent <tr> element. 
		List<WebElement> columns = parentRow.findElements(By.tagName("td")); // Find all <td> elements within the <tr>. 
		return columns.indexOf(tdElement)+1; //Xpath from 1 and index from 0
	}
	
	/**
	 * Method to click on Save Button
	 * @throws Exception
	 */
	public void clickOnSaveButton() throws Exception {
		GenericLib.explicitWait(driver, "//td[@id='TECHSPEC_OVERVIEW_SAVEbtnCtr']");
		BrowserActionUtil.clickElement(saveBtn, driver, "Save Button");
	}
	
	public void getLabelValue(String labelName) {
		GenericLib.explicitWait(driver, "//label[contains(text(),'"+labelName+"')]/following-sibling::input");
		String reqdLabelValueXpath = labelValueXpath.replace("$labelName", labelName);
		List<WebElement> labelValues = driver.findElements(By.xpath(reqdLabelValueXpath));
		for(WebElement value:labelValues) {
			if(value.getAttribute("value")!=null) {
				MyExtentListeners.test.pass("The "+labelName+" Value is: "+value.getAttribute("value"));
			}
		}
	}
	
	
	public void handleCalendarDateSelectionByLabelName(String sheetName, int labelRow, int labelCol, int dateToSelectRow, int dateToSelectCol ) throws Exception {
		String labelName = ExcelLibrary.getExcelData(GenericLib.sTestData, sheetName, labelRow, labelCol);
		System.out.println("Date Label Name: "+labelName);
		String dateToSelect = ExcelLibrary.getExcelData(GenericLib.sTestData, sheetName, dateToSelectRow, dateToSelectCol);
		System.out.println("Date to be selected is: "+dateToSelect);
		WebElement calendarIcon = driver.findElement(By.xpath("//label[contains(text(),'"+labelName+"')]/..//div//img[contains(@defaultvalue,'calen')]"));
	    BrowserActionUtil.scrollToElement(driver, calendarIcon);
		calendarIcon.click();
		GenericLib.explicitWait(driver, "//div[@id='caldiv']");
		driver.findElement(By.xpath("//td[@class='cpCurrentMonthDate']//a[text()='1']"));
	}
	
	public void enterColorInfoSearchTextBasedOnLabelName(String sheetName, int labelRow, int labelCol) {
		String labelName = ExcelLibrary.getExcelData(GenericLib.sTestData, sheetName, labelRow, labelCol);
		System.out.println("Color Info. Label Name: "+labelName);
		String requiredLabel = colorInformationLabelXpath.replace("$labelName", labelName);
		WebElement colorInfoLabel = driver.findElement(By.xpath(requiredLabel));
		int columnIndex = getColumnIndex(colorInfoLabel);
		System.out.println("Column Index of Color Info.-"+labelName+" is: "+columnIndex);
		String colIndex = String.valueOf(columnIndex);
		System.out.println("Index is: "+colIndex);
	}
	
	public void clickColorInfoNameSearchIconByIDAttribute(String idAttribute) throws Exception {
		WebElement colorInfoNameSearchBtn = driver.findElement(By.xpath("//img[@id='"+idAttribute+"']"));
		//Scroll to top(First Row getting hidden)
		BrowserActionUtil.scrollUpToElementUsingActionsPageUpByActionCount(driver, 3);
		BrowserActionUtil.clickElement(colorInfoNameSearchBtn, driver, "Color Info. Name Search Icon");
	}
	
	public void clickColorInfoFamilyShadeSearchIcon() throws Exception {
		BrowserActionUtil.scrollToElement(driver, colorInfoFamilyShadeSearchIcon);
		BrowserActionUtil.clickElement(colorInfoFamilyShadeSearchIcon, driver, "Color Info. Family/Shade Search Icon");
	}
	
	public void clickOnColorInfoInputFieldByIDAttributeAndEnterText(String idAttribute, String elementName, String sheetName, int textRow, int textCol) throws Exception {
		String inputValue = ExcelLibrary.getExcelData(GenericLib.sTestData, sheetName, textRow, textCol);
		WebElement inputField = driver.findElement(By.xpath("//input[@id='"+idAttribute+"']"));
		BrowserActionUtil.scrollToElement(driver, inputField);
		BrowserActionUtil.clickElement(inputField, driver, elementName+ " Input Field");
		BrowserActionUtil.clearAndType(inputField, inputValue, elementName+ " Input Field", driver);
	}
	
	public void handleSelectDropdownByIDAttribute(String IdAttribute, String dropdownName, String sheetName, int dropdownOptionRow, int dropdownOptionCol) throws Exception {
		String dropdownTextOption = ExcelLibrary.getExcelData(GenericLib.sTestData, sheetName, dropdownOptionRow, dropdownOptionCol);
		WebElement selectDropdown = driver.findElement(By.xpath("//select[@id='"+IdAttribute+"']"));
		BrowserActionUtil.scrollToElement(driver, selectDropdown);
		BrowserActionUtil.clickElement(selectDropdown, driver, dropdownName);
		BrowserActionUtil.handleSelectDropdown(driver, selectDropdown, dropdownTextOption);
	}
	
	public void getPopupMessageAndClose() {
		try {
			GenericLib.explicitWait(driver, "//div[@id='msgDiv']//div[contains(@class,'notificationHeader')]");
			WebElement msgHeader = driver.findElement(By.xpath("//div[@id='msgDiv']//div[contains(@class,'notificationHeader')]"));
			System.out.println("Message Header is: "+msgHeader.getText());
			MyExtentListeners.test.info("Message Header is: "+msgHeader.getText());
			WebElement msgContent = driver.findElement(By.xpath("//div[@id='msgDiv']//div[contains(@class,'notificationContent')]"));
			System.out.println("Message Content is: "+msgContent.getText());
			MyExtentListeners.test.info("Message Content is: "+msgContent.getText());
			if(msgHeader.getText().contains("Error Message")) {
				Assert.fail("Found Error Message: "+msgContent.getText());
			}
			else {
				WebElement closeBtn = driver.findElement(By.xpath("//div[contains(@class,'notifyClose')]//span[contains(@class,'i-close')]"));
				closeBtn.click();
				System.out.println("Clicked on Message Popup Close button");
				MyExtentListeners.test.info("Clicked on Message Popup Close button");
			}
		} catch (Exception e) {
			MyExtentListeners.test.info("No popup message displayed");
		}
	}
	
	public void clickOnSearchIconByIDAttributeAndClick(String idAttribute, String elementName) throws Exception {
		WebElement searchIcon = driver.findElement(By.xpath("//img[@id='"+idAttribute+"']"));
		BrowserActionUtil.scrollToElement(driver, searchIcon);
		BrowserActionUtil.clickElement(searchIcon, driver, elementName);
	}
	
	public void enterTextToInputFieldByIDAttribute(String IdAttribute, String elementName, String sheetName, int textToEnterRow, int textToEnterCol) throws Exception {
		String textValue = ExcelLibrary.getExcelData(GenericLib.sTestData, sheetName, textToEnterRow, textToEnterCol);
		WebElement inputField = driver.findElement(By.xpath("//input[@id='"+IdAttribute+"']"));
		BrowserActionUtil.scrollToElement(driver, inputField);
		BrowserActionUtil.clickElement(inputField, driver, elementName+" input field");
		BrowserActionUtil.clearAndType(inputField, textValue, elementName, driver);
	}
	
	
	public void clickOnAnchorTextButton(String textOption) throws Exception {
		String anchorButtonXpath = anchorTextButtonXpath.replace("$textValue", textOption);
		//GenericLib.explicitWait(driver, anchorButtonXpath);
		GenericLib.explicitWait(driver, "//div[@id='quick-process']//a[text()]");
		Thread.sleep(5000);
		WebElement anchorButton = driver.findElement(By.xpath(anchorButtonXpath));
		BrowserActionUtil.scrollToElement(driver, anchorButton);
		BrowserActionUtil.clickElement(anchorButton, driver, textOption+" button");
		Thread.sleep(5000);
		GenericLib.explicitWait(driver, "//div[@id='quick-process']//a[text()]");
	}
	
	public void scrollToHeaderLabelInFloatingTableAndEnterText(String labelHeader, String inputIdAttr, String sheetName, int textRow, int textCol) throws Exception {
		String textToEnter = ExcelLibrary.getExcelData(GenericLib.sTestData, sheetName, textRow, textCol);
		int text = Integer.parseInt(textToEnter);
		String reqdHeaderLabelXpath=floatingTableXpath.replace("$headerLabel", labelHeader);
		System.out.println("Table Header Label Xpath is: "+reqdHeaderLabelXpath);
		System.out.println("Text To Enter: "+textToEnter);
		WebElement floatingTableLabel = driver.findElement(By.xpath(reqdHeaderLabelXpath));
		int columnIndex = getColumnIndex(floatingTableLabel);
		System.out.println("Column Index of "+labelHeader+" is: "+columnIndex);
		String fieldIndex = String.valueOf(columnIndex);
		System.out.println("Field Index is: "+fieldIndex);
		String fieldXpath=floatingTableFieldXpath.replace("$fieldIndex", fieldIndex);
		WebElement field = driver.findElement(By.xpath(fieldXpath));
		BrowserActionUtil.scrollToElement(driver, field);
		WebDriverWait wait = new WebDriverWait(driver, 30); 
		wait.until(ExpectedConditions.elementToBeClickable(field));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(fieldXpath)));
		wait.until(ExpectedConditions.visibilityOf(field));
		for(;;) {
			if(field.isEnabled()) {
				System.out.println("Field enabled: "+field.isEnabled());
				System.out.println("Field displayed: "+field.isDisplayed());
				System.out.println("Field Location: "+field.getLocation());
				break;
			}
		}
		try {
			Thread.sleep(3000);
			field.click();//error-Invalid state
			Thread.sleep(3000);
			field.clear();
			Thread.sleep(3000);
			field.sendKeys(textToEnter);
			Thread.sleep(3000);
			field.sendKeys(Keys.ENTER);
			System.out.println("In try");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("In catch");
			System.out.println("Default value in Field is: "+field.getAttribute("value"));
			 JavascriptExecutor js = (JavascriptExecutor) driver;  
			 if(field.getAttribute("value")==null) {
		     Thread.sleep(6000);
			 js.executeScript("arguments[0].click();", field); //add click by JS
			 Thread.sleep(6000);
			 js.executeScript("document.getElementById('"+inputIdAttr+"').value='"+text+"';", "");
			 //Tab 
//			 js.executeScript("arguments[0].focus();", field);
//			 js.executeScript("arguments[0].blur();", field);
			 }else {
				 Thread.sleep(6000);
				 js.executeScript("arguments[0].click();", field); //add click by JS
				 //clear and enter- by JS
				 Thread.sleep(6000);
				 js.executeScript("document.getElementById('"+inputIdAttr+"').value='';", "");
				 Thread.sleep(6000);
				 js.executeScript("document.getElementById('"+inputIdAttr+"').value='"+text+"';", "");
				 //Robot and action not working
			 }
		}
		
	}
	
	public void clickOnTableTDButtonBasedOnID(String idAttribute, String elementName) throws Exception {
		Thread.sleep(3000);
		GenericLib.explicitWait(driver, "//td[@id='"+idAttribute+"']");
		WebElement button = driver.findElement(By.xpath("//td[@id='"+idAttribute+"']"));
		BrowserActionUtil.clickElement(button, driver, elementName);
		Thread.sleep(5000);
	}
	
	public void selectOnMoreActionsOption(String moreActionsOption) throws Exception {
		GenericLib.explicitWait(driver, "//label[text()='More Actions...']");
		WebElement button = driver.findElement(By.xpath("//label[text()='More Actions...']"));
		BrowserActionUtil.clickElement(button, driver, "More Actions Button");
		GenericLib.explicitWait(driver, "//div[@id='divChildActBtns_TECH_SPEC_OVERVIEW_MORE_ACTIONSvirtual']//label[text()='"+moreActionsOption+"']");
		WebElement option = driver.findElement(By.xpath("//div[@id='divChildActBtns_TECH_SPEC_OVERVIEW_MORE_ACTIONSvirtual']//label[text()='"+moreActionsOption+"']"));
		option.click();
	}
	
	public void scrollToFloatingTableHeaderLabelSearchIconAndClick(String sheetName, int headerLabelRow, int headerLabelCol) throws Exception {
		String labelHeader = ExcelLibrary.getExcelData(GenericLib.sTestData, sheetName, headerLabelRow, headerLabelCol);
		String reqdHeaderLabelXpath=floatingTableXpath.replace("$headerLabel", labelHeader);
		System.out.println("Table Header Label Xpath is: "+reqdHeaderLabelXpath);
		WebElement floatingTableLabel = driver.findElement(By.xpath(reqdHeaderLabelXpath));
		int columnIndex = getColumnIndex(floatingTableLabel);
		System.out.println("Column Index of "+labelHeader+" is: "+columnIndex);
		String fieldIndex = String.valueOf(columnIndex);
		System.out.println("Field Index is: "+fieldIndex);
		String fieldXpath=floatingTableFieldXpath.replace("$fieldIndex", fieldIndex);
		WebElement field = driver.findElement(By.xpath(fieldXpath));
		BrowserActionUtil.scrollToElement(driver, field);
		System.out.println("Default value in Field is: "+field.getAttribute("value"));
		String searchIconXpath = fieldXpath+"//img[contains(@id,'valsearch')]";
		WebElement searchIcon = driver.findElement(By.xpath(searchIconXpath));
		WebDriverWait wait = new WebDriverWait(driver, 10); 
		wait.until(ExpectedConditions.elementToBeClickable(searchIcon)).click();
		GenericLib.explicitWait(driver, "//div[@id='validationsearchcontroldiv']");
		WebElement resetBtn = driver.findElement(By.xpath("//span[contains(@id,'Reset')]"));
		BrowserActionUtil.clickElement(resetBtn, driver, "Reset Button");
		//input[@class='searchListData' and @value='CONFIRMED']
		}
	
	public void enterTextInFieldOfFloatingTable(String labelHeader, String inputIdAttr, String sheetName, int textRow, int textCol) throws Exception {
		String textToEnter = ExcelLibrary.getExcelData(GenericLib.sTestData, sheetName, textRow, textCol);
		String reqdHeaderLabelXpath=floatingTableXpath.replace("$headerLabel", labelHeader);
		System.out.println("Table Header Label Xpath is: "+reqdHeaderLabelXpath);
		System.out.println("Text To Enter: "+textToEnter);
		WebElement floatingTableLabel = driver.findElement(By.xpath(reqdHeaderLabelXpath));
		int columnIndex = getColumnIndex(floatingTableLabel);
		System.out.println("Column Index of "+labelHeader+" is: "+columnIndex);
		String fieldIndex = String.valueOf(columnIndex);
		System.out.println("Field Index is: "+fieldIndex);
		String fieldXpath=floatingTableFieldXpath.replace("$fieldIndex", fieldIndex);
		WebElement field = driver.findElement(By.xpath(fieldXpath));
		BrowserActionUtil.scrollToElement(driver, field);
		String reqdFieldXpath = "//input[@id='$inputIdAttr']/parent::td".replace("$inputIdAttr", inputIdAttr);
		WebElement reqdField = driver.findElement(By.xpath(reqdFieldXpath));
		reqdField.click();
		Actions act = new Actions(driver);
		//To clear and enter price
		act.doubleClick(reqdField).sendKeys(textToEnter).build().perform();
		//driver.findElement(By.xpath("//input[@id='0_@100_@11_@2_@0_@0_@0']")).sendKeys("29");
		//offerField.sendKeys("29");
	}
	
	public void enterTextInFieldWithSearchIconOfFloatingTable(String labelHeader, String inputIdAttr, String sheetName, int textRow, int textCol) throws Exception {
		String textToEnter = ExcelLibrary.getExcelData(GenericLib.sTestData, sheetName, textRow, textCol);
		String reqdHeaderLabelXpath=floatingTableXpath.replace("$headerLabel", labelHeader);
		System.out.println("Table Header Label Xpath is: "+reqdHeaderLabelXpath);
		System.out.println("Text To Enter: "+textToEnter);
		WebElement floatingTableLabel = driver.findElement(By.xpath(reqdHeaderLabelXpath));
		int columnIndex = getColumnIndex(floatingTableLabel);
		System.out.println("Column Index of "+labelHeader+" is: "+columnIndex);
		String fieldIndex = String.valueOf(columnIndex);
		System.out.println("Field Index is: "+fieldIndex);
		String fieldXpath=floatingTableFieldXpath.replace("$fieldIndex", fieldIndex);
		WebElement field = driver.findElement(By.xpath(fieldXpath));
		BrowserActionUtil.scrollToElement(driver, field);
		String reqdFieldXpath = "//input[@id='$inputIdAttr']/parent::div/parent::td".replace("$inputIdAttr", inputIdAttr);
		WebElement reqdField = driver.findElement(By.xpath(reqdFieldXpath));
		reqdField.click();
		Actions act = new Actions(driver);
		//To clear and enter text
		act.doubleClick(reqdField).sendKeys(textToEnter).build().perform();
	}
	
	public void checkStatusValue(String expectedStatus) throws Exception {
		BrowserActionUtil.scrollToElement(driver, statusField);
		if(statusField.getAttribute("value")!=null && statusField.getAttribute("value").equalsIgnoreCase(expectedStatus)) {
			MyExtentListeners.test.pass("User verified that Status value is as expected: "+statusField.getAttribute("value"));
		}else {
			MyExtentListeners.test.fail("User verified that Status value is not as expected and it is: "+statusField.getAttribute("value"));
		}
	}
	
	public void clickOnFirstRowCheckBox() throws Exception {
		WebElement firstRowCheckBox = driver.findElement(By.xpath("//td[@id='Ddetail_section_left_table0']//input[@id='chkRowKeys_@0_@-1_@-1_@-1_@0_@-1_@0_@detail_section']"));
		BrowserActionUtil.clickElement(firstRowCheckBox, driver, "Check Box");
	}
	
	public String getRequestNumber() throws Exception {
		BrowserActionUtil.scrollToElement(driver, requestNumberField);
		if(requestNumberField.getAttribute("value")!=null) {
			MyExtentListeners.test.pass("User verified that Request No. is READ ONLY and has value: "+requestNumberField.getAttribute("value"));
		}else {
			MyExtentListeners.test.fail("User verified that Request No. is not READ ONLY type or does not have a value associated");
		}
		return requestNumberField.getAttribute("value").trim();
	}
	
	public void clickOnConfirmQuoteButton() throws Exception {
		WebElement confirmQuoteBtn = driver.findElement(By.xpath("//a[text()='Confirm Quote']"));
		BrowserActionUtil.clickElement(confirmQuoteBtn, driver, "Confirm Quote Button");
		getPopupMessageAndClose();
	}
	
	public void clickOnBSConceptImage() throws Exception {
		 WebElement conceptImg = driver.findElement(By.xpath("//a[@title='Home']//img[contains(@title,'babyshop')]"));
		 BrowserActionUtil.clickElement(conceptImg, driver, "Concept Image");
		 GenericLib.explicitWaitForTime(driver, "//div[@class='dashTitle' and text()='Business Process']/parent::div//a[contains(text(),'Order Builder')]", 120);
	}
	
	public void enterDateByAddingNumberOfDaysToInputFieldAfterScrolling(String sheetName, int labelRow, int labelCol, int valueRow, int valueCol) throws Exception {
		String numberOfDays= ExcelLibrary.getExcelData(GenericLib.sTestData, sheetName, valueRow, valueCol);
		String requiredDate = BrowserActionUtil.getDateAfterAddingDays(Integer.parseInt(numberOfDays));
		String labelName= ExcelLibrary.getExcelData(GenericLib.sTestData, sheetName, labelRow, labelCol);
		System.out.println("Label Name is:"+labelName);
		System.out.println("Date to enter is:"+requiredDate);
		WebElement inputField = driver.findElement(By.xpath("//label[contains(text(),'"+labelName+"')]/following-sibling::div//input"));
		BrowserActionUtil.scrollToElement(driver, inputField);
		BrowserActionUtil.clickElement(inputField, driver, labelName+" field");
		BrowserActionUtil.clearAndType(inputField, requiredDate, labelName+" field", driver);
	}
	
	
	/**
	 * Method to scroll and enter text in all input fields based on label name in loop
	 * @param sheetName
	 * @param labelRow
	 * @param labelCol
	 * @param valueRow
	 * @param valueCol
	 * @throws Exception
	 */
	public void enterTextInAllInputFieldsForTechSpecAfterScrolling(String sheetNameAtrrTextAndValues, int rowNumHeaderText, String headerText, int attrTextRow, int attrValuesRow) throws Exception {
		//Excel Data-Get all Attribute text and values to be entered in field and store in Map
		HashMap<String, String> map = new HashMap<>();
		//attr start col-->based on header text of dropdown list type and attrEndCol--> attrtext is null
		int colHeaderText = ExcelLibrary.getHeaderTextColumnNumberInSpecifiedRow(GenericLib.sTestData, sheetNameAtrrTextAndValues, rowNumHeaderText, headerText);
		System.out.println("Column Index of Header Text: "+colHeaderText);
		int attrStartCol=colHeaderText+1;
		int attrEndCol =ExcelLibrary.getNullColumnIndex(GenericLib.sTestData, sheetNameAtrrTextAndValues, attrTextRow, attrStartCol);
		System.out.println("Column Index of Null Entry: "+attrEndCol);

		for(int col=attrStartCol; col<attrEndCol; col++) {//<=
			String attrText= ExcelLibrary.getExcelData(GenericLib.sTestData, sheetNameAtrrTextAndValues, attrTextRow, col);
			String attrValuesToEnter = ExcelLibrary.getExcelData(GenericLib.sTestData, sheetNameAtrrTextAndValues, attrValuesRow, col);
			//Mapping data and values
			if(attrText!=null || attrText!="") {
				map.put(attrText, attrValuesToEnter);
			}
		}

		System.out.println("Map Includes: "+map);
		
		//Perform iteration based on map entries
		for (HashMap.Entry<String, String> entry : map.entrySet()) {             
			System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
			//{Range Ref No (Non Clothing)=RANGE001, Ship Date=3, Custom Colour =BLUE, Est RSP (AED)=45, Item Description=Carry All Cosmetics, Launch Date=60}
			//date different locators
			if (entry.getKey().contains("Date")) {
				String numberOfDays= entry.getValue();
				String requiredDate = BrowserActionUtil.getDateAfterAddingDays(Integer.parseInt(numberOfDays));
				System.out.println("Date to enter is:"+requiredDate);
				WebElement inputField = driver.findElement(By.xpath("//label[contains(text(),'"+entry.getKey()+"')]/following-sibling::div//input"));
				BrowserActionUtil.scrollToElement(driver, inputField);
				BrowserActionUtil.clickElement(inputField, driver, entry.getKey()+" field");
				BrowserActionUtil.clearAndType(inputField, requiredDate, entry.getKey()+" field", driver);
			} else if(entry.getKey().contains("Colour") || entry.getKey().contains("Color")) {
				//Scroll to top(First Row getting hidden)
				BrowserActionUtil.scrollUpToElementUsingActionsPageUpByActionCount(driver, 3);
				String customColourIdAttribute="7_@13700_@11_@0_@0_@-1";
				enterTextInInputFieldByIDAttribute(customColourIdAttribute, entry.getKey(), entry.getValue());
			} else if(entry.getKey().contains("Est RSP (AED)")) {
				String estRSPIdAttribute="12_@100_@17_@2_@0_@-1";
				enterTextInInputFieldByIDAttribute(estRSPIdAttribute, entry.getKey(), entry.getValue());
			}else {
			//item desc and range ref
			WebElement inputField = driver.findElement(By.xpath("//label[contains(text(),'"+entry.getKey()+"')]/following-sibling::input"));
			BrowserActionUtil.scrollToElement(driver, inputField);
			BrowserActionUtil.clickElement(inputField, driver, entry.getKey()+" field");
			BrowserActionUtil.clearAndType(inputField, entry.getValue(), entry.getKey()+" field", driver);
			}	
		}	
	}
	
	/**
	 * Method to scroll and enter text in Style number field
	 * @param styleNumber
	 * @throws Exception
	 */
	public void enterTextInStyleNoInputFieldAfterScrolling(String styleNumber) throws Exception {
		WebElement inputField = driver.findElement(By.xpath("//label[contains(text(),'Style No')]/following-sibling::input"));
		BrowserActionUtil.scrollToElement(driver, inputField);
		BrowserActionUtil.clickElement(inputField, driver, "Style No field");
		BrowserActionUtil.clearAndType(inputField, styleNumber,"Style No field", driver);
	}
	
	public void enterTextInInputFieldByIDAttribute(String idAttributeValue, String elementName, String textToEnter) throws Exception {
		WebElement inputField = driver.findElement(By.xpath("//input[@id='"+idAttributeValue+"']"));
		BrowserActionUtil.scrollToElement(driver, inputField);
		BrowserActionUtil.clickElement(inputField, driver, elementName+" input field");
		BrowserActionUtil.clearAndType(inputField, textToEnter, elementName, driver);
	}
	
/**	public void handleAllInputFieldsWithSearchIconForTechSpecAfterScrolling(String sheetNameAtrrTextAndValues, int rowNumSearchIconHeaderText, String searchIconHeaderText, int rowNumHeaderText, String headerText,int attrTextRow, int attrValuesRow) throws Exception {
		//Array List-All Search Icon Label name
		 int searchIconLabelHeaderCol = ExcelLibrary.getHeaderTextColumnNumberInSpecifiedRow(GenericLib.sTestData, sheetNameAtrrTextAndValues, rowNumSearchIconHeaderText, searchIconHeaderText);
		 System.out.println("Column Index of Search Icon Label Header Text: "+searchIconLabelHeaderCol);
		 int searchIconLabelStartCol=searchIconLabelHeaderCol+1;
		 int searchIconLabelEndCol =ExcelLibrary.getNullColumnIndex(GenericLib.sTestData, sheetNameAtrrTextAndValues, rowNumSearchIconHeaderText, searchIconLabelStartCol);
		 System.out.println("Column Index of Null Entry: "+searchIconLabelEndCol);
		 ArrayList<String> searchIconLabelList = new ArrayList<String>();
		 for(int col=searchIconLabelStartCol; col<searchIconLabelEndCol; col++) {//<=
			String labelName = ExcelLibrary.getExcelData(GenericLib.sTestData, sheetNameAtrrTextAndValues, rowNumSearchIconHeaderText, col);	
			searchIconLabelList.add(labelName);
		}
		 System.out.println("Search Icon Labels List: "+searchIconLabelList);
		 HashSet<String> searchIconLabelSet = new HashSet<String>();
		 System.out.println("Search Icon Labels Set: "+searchIconLabelSet);
		
		 
		//Excel Data-Get all text and values to be entered in field and store in array lists(order)
		ArrayList<String> labelNameList = new ArrayList<String>();
		int colHeaderText = ExcelLibrary.getHeaderTextColumnNumberInSpecifiedRow(GenericLib.sTestData, sheetNameAtrrTextAndValues, rowNumHeaderText, headerText);
		System.out.println("Column Index of Header Text: "+colHeaderText);
		int attrStartCol=colHeaderText+1;
		int attrEndCol =ExcelLibrary.getNullColumnIndex(GenericLib.sTestData, sheetNameAtrrTextAndValues, attrTextRow, attrStartCol);
		System.out.println("Column Index of Null Entry: "+attrEndCol);

		ArrayList<String> labelAttrTextList = new ArrayList<String>();
		ArrayList<String> labelAttrValuesList = new ArrayList<String>();
		for(int col=attrStartCol; col<=attrEndCol; col++) {//<=
			String attrText= ExcelLibrary.getExcelData(GenericLib.sTestData, sheetNameAtrrTextAndValues, attrTextRow, col);
			String attrValuesToEnter = ExcelLibrary.getExcelData(GenericLib.sTestData, sheetNameAtrrTextAndValues, attrValuesRow, col);
			//Lists
			labelAttrTextList.add(attrText);			
			labelAttrValuesList.add(attrValuesToEnter);
		}

		System.out.println("Labels Text List: "+labelAttrTextList);
		System.out.println("Labels Text Values List: "+labelAttrValuesList);
		
		//Perform iteration based on list entries
		for(String searchIconLabel:searchIconLabelSet) {//set for iteration(No duplicate for search Icon)
			clickOnSearchIconByLabelName(searchIconLabel);//Season-Row 0(Only one Set)
			for(int j=0; j<=labelAttrTextList.size(); j++) {
				for(int k=0; k<=labelAttrValuesList.size(); k++) {
					handlePopupDropdownAndEnterSearchText("Create_Tech_Spec", 1, 3, 2, 3, "Dropdown_Options", 1, 0);//"Season", "Contains", "BS REGULAR"  (Row 1 and 2)
					
				}
			}
		}
		clickOnSearchButtonInPopupAndSelectFirstRecord();
	}	**/
	
	public void handleFieldsWithSearchIconAndPopupDetailsSearchForTechSpecAfterScrolling(String sheetName, int rowNumSearchIconHeaderText, String searchIconHeaderText,int popupLabelTextRow, int popupValuesRow, String dropdownOptionSheetName, int dropdownOptionRow, int dropdownOptionCol) throws Exception {
		//get search icon label name from header reference and click on it
		int searchIconLabelHeaderCol = ExcelLibrary.getHeaderTextColumnNumberInSpecifiedRow(GenericLib.sTestData, sheetName, rowNumSearchIconHeaderText, searchIconHeaderText);
		int searchIconLabelCol=searchIconLabelHeaderCol+1;
		String searchIconLabelName = ExcelLibrary.getExcelData(GenericLib.sTestData, sheetName, rowNumSearchIconHeaderText, searchIconLabelCol);
		System.out.println("Search Icon Label Name: "+searchIconLabelName);
		//Locators differ
		if(!(searchIconLabelName.contains("Color Information")) && !(searchIconLabelName.contains("Supplier"))){
		clickOnSearchIconByLabelName(searchIconLabelName);
		}
		
		 int searchIconLabelEndCol =ExcelLibrary.getNullColumnIndex(GenericLib.sTestData, sheetName, rowNumSearchIconHeaderText, searchIconLabelCol);
		 System.out.println("Column Index of Null Entry: "+searchIconLabelEndCol);

		 //Store popup search details in Map
		 HashMap<String, String> map = new HashMap<String, String>();
		 for(int col=searchIconLabelCol; col<searchIconLabelEndCol; col++) {//<=
			 String popupLabelText= ExcelLibrary.getExcelData(GenericLib.sTestData, sheetName, popupLabelTextRow, col);
			 String popupValuesToEnter = ExcelLibrary.getExcelData(GenericLib.sTestData, sheetName, popupValuesRow, col);
			 //Mapping data and values
			 if(popupLabelText!=null) {
				 map.put(popupLabelText, popupValuesToEnter);
			 }
		 }

		 System.out.println("Search Popup Map Includes: "+map);
		 
		 //Enter Search Popup Details based on Map entries
		 try {
		 for (HashMap.Entry<String, String> entry : map.entrySet()) {
			 System.out.println("Key: "+entry.getKey()+" Value: "+entry.getValue());
			 
			 if(searchIconLabelName.contains("Color Information")||searchIconLabelName.contains("Colour Information")||searchIconLabelName.contains("Supplier")) {
					if(searchIconLabelName.contains("Color Information")||searchIconLabelName.contains("Colour Information")){
						BrowserActionUtil.scrollUpToElementUsingActionsPageUpByActionCount(driver, 2);
						String[] requiredLabelName = searchIconLabelName.split("-");
						System.out.println("Required Label : "+requiredLabelName);
						String colorInfoSearchLabelName = requiredLabelName[requiredLabelName.length-1];
						System.out.println("Search Icon Label Name: "+colorInfoSearchLabelName);//Name or Family/Shade or Custom Colour
						String nameIDAttr="valsearch_7_@1_@101_@0_@0_@-1_@desc";
						String familyShadeIDAttr="valsearch_7_@13700_@12_@0_@0_@-1_@desc";
						if(colorInfoSearchLabelName.equalsIgnoreCase("Name")) {
							WebElement searchIcon = driver.findElement(By.xpath("//img[@id='"+nameIDAttr+"']"));
							BrowserActionUtil.scrollToElement(driver, searchIcon);
							BrowserActionUtil.clickElement(searchIcon, driver, "Color Info-Name");
						}else {
							WebElement searchIcon = driver.findElement(By.xpath("//img[@id='"+familyShadeIDAttr+"']"));
							BrowserActionUtil.scrollToElement(driver, searchIcon);
							BrowserActionUtil.clickElement(searchIcon, driver, "Color Info-Family/Shade");
						}
					}else {
						//Supplier
						BrowserActionUtil.scrollUpToElementUsingActionsPageUpByActionCount(driver, 1);
						String[] requiredLabelName = searchIconLabelName.split("-");
						System.out.println("Required Label : "+requiredLabelName);
						String supplierSearchLabelName = requiredLabelName[requiredLabelName.length-1];
						System.out.println("Search Icon Label Name: "+supplierSearchLabelName);//Supplier
						WebElement searchIcon = driver.findElement(By.xpath("//img[@id='valsearch_12_@100_@4_@2_@0_@-1_@desc']"));
						BrowserActionUtil.scrollToElement(driver, searchIcon);
						BrowserActionUtil.clickElement(searchIcon, driver, "Supplier");
					}
				handlePopupDropdownAndEnterSearchTextInSearchPopup(entry.getKey(), entry.getValue(), dropdownOptionSheetName, searchIconLabelCol, searchIconLabelEndCol);
				}else {
			        handlePopupDropdownAndEnterSearchTextInSearchPopup(entry.getKey(), entry.getValue(), dropdownOptionSheetName, searchIconLabelCol, searchIconLabelEndCol);
				}
		 }
		 clickOnSearchButtonInPopupAndSelectFirstRecord();
		 }catch(Exception e) {
			 e.printStackTrace();
			 MyExtentListeners.test.info("Not found key and value pair as expected");
		 }
	}
	
	
	
	public void clickOnSearchIconByLabelName(String searchIconLabelName) throws Exception {
		System.out.println("Search Icon Label Name is: "+searchIconLabelName);
		WebElement searchIcon = driver.findElement(By.xpath("//label[contains(text(),'"+searchIconLabelName+"')]/following-sibling::div//img[contains(@id,'valsearch')]"));
		BrowserActionUtil.scrollToElement(driver, searchIcon);
		BrowserActionUtil.clickElement(searchIcon, driver, searchIconLabelName+" Search Icon");
		GenericLib.explicitWait(driver, "//div[@id='validationsearchcontroldiv']");	
	}
	
	public void handlePopupDropdownAndEnterSearchTextInSearchPopup(String popupDropdownLabelName, String searchText, String dropDownOptionSheetName, int dropdownOptionRow, int dropdownOptionCol ) throws Exception{
		String textOptionInDropdown = ExcelLibrary.getExcelData(GenericLib.sTestData, dropDownOptionSheetName, dropdownOptionRow, dropdownOptionCol);
		//Popup handling-Dropdown and text field
		String reqdLabelXpath=searchPopupDropdownLabelXpath.replace("$popupLabelName", popupDropdownLabelName);
		System.out.println("Search Popup Dropdown Label Xpath is: "+reqdLabelXpath);
		WebElement dropdownLabel = driver.findElement(By.xpath(reqdLabelXpath));
		int columnIndex = getColumnIndex(dropdownLabel);
		System.out.println("Column Index of "+popupDropdownLabelName+" is: "+columnIndex);
		String dropdownInd = String.valueOf(columnIndex);
		System.out.println("Dropdown Index is: "+dropdownInd);
		String reqdDropdownXpath=searchPopupDropdownXpath.replace("$dropdownIndex", dropdownInd);
		WebElement dropdown = driver.findElement(By.xpath(reqdDropdownXpath));
		BrowserActionUtil.clickElement(dropdown, driver, popupDropdownLabelName+" Dropdown");
		BrowserActionUtil.handleSelectDropdown(driver, dropdown, textOptionInDropdown);
		String searchTextFieldXpath = searchPopupTextFieldXpath.replace("$dropdownIndex", dropdownInd);
		System.out.println("Search Text field Xpath is: "+searchTextFieldXpath);
		WebElement searchTextFld = driver.findElement(By.xpath(searchTextFieldXpath));
		BrowserActionUtil.clickElement(searchTextFld, driver, popupDropdownLabelName+" Search Field");
		BrowserActionUtil.clearAndType(searchTextFld, searchText, popupDropdownLabelName+" Search Field", driver);
		Thread.sleep(1000);
	}	
	
	public void handleAllSelectDropdownByIDAttributeForTechSpec(String sheetName, int rowNumDropdownHeaderText, String dropdownHeaderText, int dropdownLabelTextRow, int dropdownOptionTextRow) throws Exception {
		//get dropdown from header reference 
		int dropdownsLabelHeaderCol = ExcelLibrary.getHeaderTextColumnNumberInSpecifiedRow(GenericLib.sTestData, sheetName, rowNumDropdownHeaderText, dropdownHeaderText);
		int selectDropdownLabelCol=dropdownsLabelHeaderCol+1;
		String selectDropdownLabelName = ExcelLibrary.getExcelData(GenericLib.sTestData, sheetName, rowNumDropdownHeaderText, selectDropdownLabelCol);
		System.out.println("Select Dropdown Label Name: "+selectDropdownLabelName);

		//Last cell with content
		int selectDropdownLabelEndCol =ExcelLibrary.getNullColumnIndex(GenericLib.sTestData, sheetName, rowNumDropdownHeaderText, selectDropdownLabelCol);
		System.out.println("Column Index of Null Entry: "+selectDropdownLabelEndCol);
		
		HashMap<String, String> map = new HashMap<String, String>();
		for(int col=selectDropdownLabelCol; col<selectDropdownLabelEndCol; col++) {
			String dropdownLabelText= ExcelLibrary.getExcelData(GenericLib.sTestData, sheetName, dropdownLabelTextRow, col);
			 String dropdownOptionToSelect = ExcelLibrary.getExcelData(GenericLib.sTestData, sheetName, dropdownOptionTextRow, col);
			 //Mapping data and values
			 if(dropdownLabelText!=null) {
				 map.put(dropdownLabelText, dropdownOptionToSelect);
			 }
		}
		System.out.println("Select Dropdowns Map Include: "+map);
		
		for (HashMap.Entry<String, String> entry : map.entrySet()) {
			System.out.println("Key: "+entry.getKey()+ " Value: "+entry.getValue());
			if(entry.getKey().trim().equalsIgnoreCase("Size Range")) {
				WebElement selectDropdownSize = driver.findElement(By.xpath("//select[@id='8_@14200_@2_@0_@0_@-1']"));
				BrowserActionUtil.scrollToElement(driver, selectDropdownSize);
				BrowserActionUtil.clickElement(selectDropdownSize, driver, entry.getKey());
				BrowserActionUtil.handleSelectDropdown(driver, selectDropdownSize, entry.getValue());
				Thread.sleep(1000);
			}
			else if(entry.getKey().trim().equalsIgnoreCase("Garment Type")) {
				WebElement selectDropdownGarment = driver.findElement(By.xpath("//select[@id='18_@100_@81_@0_@0_@0']"));
				BrowserActionUtil.scrollToElement(driver, selectDropdownGarment);
				BrowserActionUtil.clickElement(selectDropdownGarment, driver, entry.getKey());
				BrowserActionUtil.handleSelectDropdown(driver, selectDropdownGarment, entry.getValue());
				Thread.sleep(1000);
			}
			else {
				MyExtentListeners.test.info("Add Locator Id attribute for:"+entry.getKey());
			}
		
		}
	}
	
	public void handleToggleButtonAfterScrolling(String idAttribute, String sheetName, int labelRow, int labelCol, int valueRow, int valueCol) throws Exception {
		String valueToUpdate= ExcelLibrary.getExcelData(GenericLib.sTestData, sheetName, valueRow, valueCol);//Yes or No
		String labelName= ExcelLibrary.getExcelData(GenericLib.sTestData, sheetName, labelRow, labelCol);
		System.out.println("Label Name is:"+labelName);
		System.out.println("Toggle has to update to:"+valueToUpdate);
		WebElement toggleBtn = driver.findElement(By.xpath("//label[contains(text(),'"+labelName+"')]/following::div[@id='"+idAttribute+"' and @class='toggle-btn']"));
		WebElement toggleBtnStatus = driver.findElement(By.xpath("//label[contains(text(),'"+labelName+"')]/following::input[contains(@id,'chk')and @class='toggle-chkbox']"));
		BrowserActionUtil.scrollToElement(driver, toggleBtn);
		BrowserActionUtil.clickElement(toggleBtn, driver, labelName+" Toggle");
		MyExtentListeners.test.info("Toogle Value for "+labelName+ " after click is: "+toggleBtnStatus.getAttribute("value"));
		if(toggleBtnStatus.getAttribute("value").equalsIgnoreCase(valueToUpdate)) {
			MyExtentListeners.test.pass("The default toggle value is same as value to be overriden: "+toggleBtnStatus.getAttribute("value"));
		}else {
		BrowserActionUtil.clickElement(toggleBtn, driver, labelName+" Toggle Button");
		Thread.sleep(2000);
		WebElement toggleBtnUpdated = driver.findElement(By.xpath("//label[contains(text(),'"+labelName+"')]/following::input[contains(@id,'chk')and @class='toggle-chkbox']"));
		MyExtentListeners.test.pass("The toggle value is overriden to: "+toggleBtnUpdated.getAttribute("value"));
		}	
	}

	
}
	
