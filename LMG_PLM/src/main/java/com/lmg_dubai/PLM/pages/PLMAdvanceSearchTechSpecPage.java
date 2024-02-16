package com.lmg_dubai.PLM.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.lmg_dubai.PLM.library.BasePage;
import com.lmg_dubai.PLM.library.ExcelLibrary;
import com.lmg_dubai.PLM.library.GenericLib;
import com.lmg_dubai.PLM.listeners.MyExtentListeners;
import com.lmg_dubai.PLM.util.BrowserActionUtil;

public class PLMAdvanceSearchTechSpecPage extends BasePage{
	public PLMAdvanceSearchTechSpecPage(WebDriver driver) {
		super(driver);
	}
	
	//Element to identify search button in popup
	@FindBy(xpath="//td//span[@id='Search_Btn']")
	private WebElement popupSearchBtn;

	//Element to identify first record in search list
	@FindBy(xpath="//tr[contains(@id,'searchListRow')]")
	private WebElement firstSearchRecord;


	//Element to identify search button in popup
	@FindBy(xpath="//td[@id='_searchBtnbtnCtr']")
	private WebElement searchBtn;

	//Element to identify clear fields button
	@FindBy(xpath="//td[@id='_clearBtnbtnCtr']")
	private WebElement clearFieldsBtn;

	//Element to identify Advanced Search: Tech Spec
	@FindBy(xpath="//img[@id='_SearchSection_collapImg']")
	private WebElement techSpecSearchBtn;

	//Element to identify Tech Spec List
	@FindBy(xpath="//td//b[text()='Tech Spec List']")
	private WebElement techSpecListBtn;
	
	
	
	private static String searchPopupDropdownLabelXpath="//div[@id='searchBarDiv']//td[@class='searchLabels' and text()='$popupLabelName']";
	private static String searchPopupDropdownXpath="(//td//select[@class='searchOpDropDown'])[$dropdownIndex]";
	private static String searchPopupTextFieldXpath="(//td//input[@class='searchInput'])[$dropdownIndex]";
	
	/**
	 * Method to scroll and enter text in input field based on label name and search 
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
		inputField.sendKeys(Keys.ENTER);
		Thread.sleep(10000);
	}
	
	
	public void enterTextInInputFieldAfterScrollingNew(String sheetName, int labelRow, int labelCol, int valueRow, int valueCol) throws Exception {
		String valueToEnter= ExcelLibrary.getExcelData(GenericLib.sTestData, sheetName, valueRow, valueCol);
		String labelName= ExcelLibrary.getExcelData(GenericLib.sTestData, sheetName, labelRow, labelCol);
		System.out.println("Value to enter is:"+valueToEnter);
		System.out.println("Label Name is:"+labelName);
		WebElement inputField = driver.findElement(By.xpath("//label[contains(text(),'"+labelName+"')]/following-sibling::input"));
		BrowserActionUtil.scrollToElement(driver, inputField);
		BrowserActionUtil.clickElement(inputField, driver, labelName+" field");
		BrowserActionUtil.clearAndType(inputField, valueToEnter, labelName+" field", driver);
		//inputField.sendKeys(Keys.ENTER);
		Thread.sleep(10000);
	}
	
	
	
	
	public void verifySearchDetails() {
		try {
			Thread.sleep(3000);
			WebElement msgHeader = driver.findElement(By.xpath("//div[@id='msgDiv']//div[contains(@class,'notificationHeader')]"));
			System.out.println("Message Header is: "+msgHeader.getText());
			MyExtentListeners.test.info("Message Header is: "+msgHeader.getText());
			WebElement msgContent = driver.findElement(By.xpath("//div[@id='msgDiv']//div[contains(@class,'notificationContent')]"));
			System.out.println("Message Content is: "+msgContent.getText());
			MyExtentListeners.test.info("Message Content is: "+msgContent.getText());
			if(msgHeader.isDisplayed()) {
				WebElement closeBtn = driver.findElement(By.xpath("//div[contains(@class,'notifyClose')]//span[contains(@class,'i-close')]"));
				closeBtn.click();
				System.out.println("Clicked on Message Popup Close button");
				MyExtentListeners.test.info("Clicked on Message Popup Close button");
			}
		} catch (Exception e) {
			MyExtentListeners.test.info("No popup message displayed");
		}
		try {
			GenericLib.explicitWait(driver, "//table[@id='MainSection_left_table']//tr//td//a[text()]");
			List<WebElement> mainSectionDetails = driver.findElements(By.xpath("//table[@id='MainSection_left_table']//tr[@class='row1']//td//*[text()]"));
			for(WebElement detail:mainSectionDetails) {
			MyExtentListeners.test.pass("Validated the Details in Advanced Search Results: "+detail.getText());
			}
		} catch (Exception e) {
			MyExtentListeners.test.info("Could not validate the Details in Advanced Search Results");
		}
	}
	
	public void expandAdvancedSearchTechSpecAndClearSearchFields() throws Exception {
		GenericLib.explicitWait(driver, "//img[@id='_SearchSection_collapImg']");
		System.out.println("Tech Spec Search Button: "+techSpecSearchBtn.getLocation());
		if(techSpecSearchBtn.getLocation()!=null) {
		BrowserActionUtil.clickElement(techSpecSearchBtn, driver, "Advance Search-Tech Spec");
		}
		BrowserActionUtil.scrollToElement(driver, clearFieldsBtn);
		BrowserActionUtil.clickElement(clearFieldsBtn, driver, "Clear Fields Button");
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
		Thread.sleep(1000);
	}
	
	public void scrollAndClickSearchButton() throws Exception {
		BrowserActionUtil.scrollToElement(driver, searchBtn);
		BrowserActionUtil.clickElement(searchBtn, driver, "Search Button");
		Thread.sleep(10000);
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
	
	//Mithun
	
		@FindBy(xpath="//td//b[text()='Tech Spec List']/following::td//img[contains(@src,'listview')]")
		private WebElement listViewBtn;
		
		
		public void verifySearchDetailsInListView(String sheetName, int styleNoRow, int styleNoCol) throws Exception {
			GenericLib.explicitWaitForTime(driver, "//td//b[text()='Tech Spec List']/following::td//img[contains(@src,'listview')]", 2);
			BrowserActionUtil.clickElement(listViewBtn, driver, "List View Button");
			GenericLib.explicitWaitForTime(driver, "//td//b[text()='Tech Spec List']/following::td//img[contains(@src,'listviewactive')]", 5);
			MyExtentListeners.test.info("Search Details is in List View");
			try {
				WebElement styleHeaderLabel = driver.findElement(By.xpath("//table[@id='MainSection_left_table']//td[@class='clslabelheader']//label[contains(text(),'Style No')]"));
				WebElement itemImg = driver.findElement(By.xpath("//table[@id='MainSection_left_table']//img[@id='0_@10000_@13_@0_@0_@0']"));
				WebElement styleNo = driver.findElement(By.xpath("//table[@id='MainSection_left_table']//a[@id='0_@100_@4_@0_@0_@0lnk']"));
				MyExtentListeners.test.pass("Buyer or User is able to validate the list view for Style entered: "+styleNo.getText());
			} catch (Exception e) {
				e.printStackTrace();
				Assert.fail("User could not validate List View In Advance Search for Tech Spec.");
			}	
		}
		
		@FindBy(xpath="//td//b[text()='Tech Spec List']/following::td//img[contains(@src,'mediumview')]")
		private WebElement mediumViewBtn;
		
		public void verifySearchDetailsInMediumView() throws Exception {
			GenericLib.explicitWaitForTime(driver, "//td//b[text()='Tech Spec List']/following::td//img[contains(@src,'mediumview')]", 2);
			BrowserActionUtil.clickElement(mediumViewBtn, driver, "Medium View Button");
			GenericLib.explicitWaitForTime(driver, "//td//b[text()='Tech Spec List']/following::td//img[contains(@src,'mediumviewactive')]", 5);
			MyExtentListeners.test.info("Search Details is in Medium View");
			try {
				WebElement itemImg = driver.findElement(By.xpath("//div[@id='gridDiv_0' and @class='colorWallCellSpace']//img[@id='img_icon_0_@10000_@12_@0_@0_@-1']"));
				WebElement checkBoxAndStyleNo = driver.findElement(By.xpath("//table[@id='_tableMainMainSection']//table[@id='MainSection_data_table']//tr//td//input[contains(@id,'chkRowKeys')]/following::a[@id='0_@100_@4_@0_@0_@0_@_mainDivlnk']"));
				MyExtentListeners.test.pass("Buyer or User is able to validate the medium view for Style entered: "+checkBoxAndStyleNo.getText());
			} catch (Exception e) {
				e.printStackTrace();
				Assert.fail("User could not validate Medium View In Advance Search for Tech Spec.");
			}	
		}
	 
		@FindBy(xpath="//td//b[text()='Tech Spec List']/following::td//img[contains(@src,'gridview')]")
		private WebElement gridViewBtn;
		
		public void verifySearchDetailsInGridView() throws Exception {
			GenericLib.explicitWaitForTime(driver, "//td//b[text()='Tech Spec List']/following::td//img[contains(@src,'gridview')]", 2);
			BrowserActionUtil.clickElement(gridViewBtn, driver, "Grid View Button");
			GenericLib.explicitWaitForTime(driver, "//td//b[text()='Tech Spec List']/following::td//img[contains(@src,'gridviewactive')]", 5);
			MyExtentListeners.test.info("Search Details is in Grid View");
			try {
				WebElement itemImg = driver.findElement(By.xpath("//div[@id='gridDiv_0' and @class='colorWallCell']//img[@id='0_@10000_@14_@0_@0_@0']"));
				WebElement checkBoxAndStyleNo = driver.findElement(By.xpath("//table[@id='_tableMainMainSection']//table[@id='MainSection_data_table']//tr//td//input[contains(@id,'chkRowKeys')]/following::a[@id='0_@100_@4_@0_@0_@0_@_mainDivlnk']"));
				MyExtentListeners.test.pass("Buyer or User is able to validate the Grid view for Style entered: "+checkBoxAndStyleNo.getText());
			} catch (Exception e) {
				e.printStackTrace();
				Assert.fail("User could not validate Grid View In Advance Search for Tech Spec.");
			}	
		}
		
		@FindBy(xpath="//td[@id='DMainSection_left_table0']//input[@type='checkbox']")
		private List<WebElement> allStyleCheckboxes;
		
		public void selectMultipleStylesInSearchList(int numberOfCheckBoxesToSelect) throws Exception {
			try {
				for(int num=1; num<=numberOfCheckBoxesToSelect; num++) {
					WebElement styleCheckbox = driver.findElement(By.xpath("(//td[@id='DMainSection_left_table0']//input[@type='checkbox'])["+num+"]"));
					BrowserActionUtil.clickElement(styleCheckbox, driver, "Style Checkbox: "+num);
					MyExtentListeners.test.pass("User selected Style Checbox: "+num);
				}
			} catch (Exception e) {
				e.printStackTrace();
				Assert.fail("Could not select multiple styles checkboxes as expected.");
			}
		}
		
		@FindBy(xpath="//a[@id='0_@100_@4_@0_@0_@0lnk']")
		private WebElement firstStyleLink;
		
		public void clickOnFirstStyleLinkInTheSearchResultsList() throws Exception {
			BrowserActionUtil.scrollToElement(driver, firstStyleLink);
			GenericLib.explicitWaitForTime(driver, "//a[@id='0_@100_@4_@0_@0_@0lnk']", 5);
			BrowserActionUtil.clickElement(firstStyleLink, driver, "First Style Link: "+firstStyleLink.getText());
			GenericLib.explicitWaitForTime(driver, "//td[@id='TECHSPEC_OVERVIEW_SAVEbtnCtr']", 10);
		}
		
	

}
