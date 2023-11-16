package com.lmg_dubai.PLM.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import com.lmg_dubai.PLM.library.BasePage;
import com.lmg_dubai.PLM.library.ExcelLibrary;
import com.lmg_dubai.PLM.library.GenericLib;
import com.lmg_dubai.PLM.listeners.MyExtentListeners;
import com.lmg_dubai.PLM.util.BrowserActionUtil;

public class PLMTerritoryQuantityAllocationPage extends BasePage{

	public PLMTerritoryQuantityAllocationPage(WebDriver driver) {
		super(driver);
	}
	
	//Element to identify search button 
	@FindBy(xpath="//button[text()='Search']")
	private WebElement searchBtn;
	
	private static String searchLabelDropdown="//form[@id='search-form']//span[text()='$labelName']/following-sibling::select";
	private static String searchLabelInputField="//form[@id='search-form']//span[text()='$labelName']/parent::div//input";
	private static String rowHeaderLabelXpath ="//form//table[@role='grid']//a[text()='$labelName']/parent::th";
	private static String inputFieldXpath="//div[@class='k-grid-content k-auto-scrollable']//table//tr[$rowNum]//td[$colNum]//input";
	
	public void getPopupMessageAndClose() {
		try {
			GenericLib.explicitWait(driver, "//div[@id='msgDiv']//div[contains(@class,'notificationHeader')]");
			//Thread.sleep(3000);
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
	}
	
	/**
	 * Method to handle select dropdown
	 * @param labelName
	 * @param dropdownOption
	 * @param searchText
	 * @throws Exception
	 */
	public void handleSelectDropdownAndEnterSearchText(String labelName, String dropdownOption, String searchText) throws Exception{
		String searchLabelDropdownXpath = searchLabelDropdown.replace("$labelName", labelName);
		WebElement selectDropdown = driver.findElement(By.xpath(searchLabelDropdownXpath));
		BrowserActionUtil.clickElement(selectDropdown, driver, labelName+" Dropdown");
		BrowserActionUtil.handleSelectDropdown(driver, selectDropdown, dropdownOption);
		String searchTextFieldXpath = searchLabelInputField.replace("$labelName", labelName);
		WebElement searchTextFld = driver.findElement(By.xpath(searchTextFieldXpath));
		BrowserActionUtil.clickElement(searchTextFld, driver, labelName+" Search Field");
		BrowserActionUtil.clearAndType(searchTextFld, searchText, labelName+" Search Field", driver);
	}
	
	public void clickOnSearchButtonAndWaitForRequestNo(String reqNo) throws Exception {
		GenericLib.explicitWait(driver, "//button[text()='Search']");
		BrowserActionUtil.clickElement(searchBtn, driver, "Search Button");
		GenericLib.explicitWait(driver, "//table//a[text()='"+reqNo+"']");
	}
	
	/**
	 * Method to enter Allocator Quantity based on Territory Name
	 * @param sheetNameAtrrTextAndValues
	 * @param rowNumHeaderText
	 * @param headerText
	 * @param attrTextRow
	 * @param attrValuesRow
	 * @param attrLabelName
	 * @param rowOfRowCheck
	 * @throws InvalidFormatException
	 * @throws Exception
	 */
	public void enterAllocatorQuantityBasedOnTerritory(String sheetNameAtrrTextAndValues, int rowNumHeaderText, String headerText, int attrTextRow, int attrValuesRow, String attrLabelName, int rowOfRowCheck) throws InvalidFormatException, Exception {
		//Excel Data-Get all Alloc Qty text against Territory and store in Map
		HashMap<String, String> map_AllocQty = new HashMap<>();
		//attr start col-->based on header text and attrEndCol--> attrtext is null
		int colHeaderText = ExcelLibrary.getHeaderTextColumnNumberInSpecifiedRow(GenericLib.sTestData, sheetNameAtrrTextAndValues, rowNumHeaderText, headerText);
		System.out.println("Column Index of Header Text: "+colHeaderText);
		int attrStartCol=colHeaderText+1;
		int attrEndCol =ExcelLibrary.getNullColumnIndex(GenericLib.sTestData, sheetNameAtrrTextAndValues, attrTextRow, attrStartCol);
		System.out.println("Column Index of Null Entry: "+attrEndCol);

		for(int col=attrStartCol; col<=attrEndCol; col++) {
			String attrText= ExcelLibrary.getExcelData(GenericLib.sTestData, sheetNameAtrrTextAndValues, attrTextRow, col);
			String attrValuesToEnter = ExcelLibrary.getExcelData(GenericLib.sTestData, sheetNameAtrrTextAndValues, attrValuesRow, col);
			//Mapping data and values
			if(attrText!=null) {
				map_AllocQty.put(attrText, attrValuesToEnter);
			}
		}

		System.out.println("Allocator Qty Map includes: "+map_AllocQty);
		
		//Get number of rows to search from excel
		int colNum = ExcelLibrary.getHeaderTextColumnNumberInSpecifiedRow(GenericLib.sTestData, sheetNameAtrrTextAndValues, rowNumHeaderText, headerText);
		int rowsForCheck = ExcelLibrary.getNumberOfRowsToCheck(GenericLib.sTestData, sheetNameAtrrTextAndValues, rowOfRowCheck, colNum);
		System.out.println("Rows To Consider for Checking : "+rowsForCheck);
		
		//Get number of rows to search from UI
		
		//Get Table Header Column Index-Territory
		String attributeLabelXpath = rowHeaderLabelXpath.replace("$labelName", attrLabelName);
		System.out.println("Attribute Label Xpath:"+attributeLabelXpath);
		WebElement attributeLabel = driver.findElement(By.xpath(attributeLabelXpath));//Header Label-Territory
		
		//To get control of table
		driver.findElement(By.xpath("//th//a[text()='Style No']")).click();
		Thread.sleep(5000);
		
		int colIndex = getColumnHeaderIndex(attributeLabel);
		System.out.println("Column Index for Header Label: "+attrLabelName+" is: "+colIndex);//Territory-11
		
		//To Territory Column
		for(int i=1; i<colIndex; i++) {
			Actions act = new Actions(driver);	
			act.sendKeys(Keys.ARROW_RIGHT).build().perform(); 
			System.out.println("Scroll Right perfomed");
			Thread.sleep(1000);
		}
		
		//Get all Terr Names-List
		ArrayList<String> allTerrNames = new ArrayList<String>();
		for(int rowNumber=1; rowNumber<=rowsForCheck; rowNumber++) {
			try {
			String territoryNameXpath = "(//td//span[contains(text(),'BS')])[$rowIndex]".replace("$rowIndex", String.valueOf(rowNumber));
			WebElement territoryName = driver.findElement(By.xpath(territoryNameXpath));
			allTerrNames.add(territoryName.getText().trim());
			}catch(Exception e) {
				MyExtentListeners.test.info("Could not find Territory Name at Row Number: "+rowNumber);
			}
		}
		System.out.println("Territory Array: "+allTerrNames);
		System.out.println("Territory List Size: "+allTerrNames.size());
		
		//Get Table Header Column Index-Allocator Quantity
		String attributeLabelXpath_AllocQty = rowHeaderLabelXpath.replace("$labelName", "Allocator Quantity");
		System.out.println("Attribute Label Xpath:"+attributeLabelXpath_AllocQty);
		WebElement attributeLabel_AllocQty = driver.findElement(By.xpath(attributeLabelXpath_AllocQty));//Header Label-Allocator Quantity
		
		int colIndex_AllocQty = getColumnHeaderIndex(attributeLabel_AllocQty);
		System.out.println("Column Index for Header Label: Allocator Quantity is: "+colIndex_AllocQty);//Allocator Quantity-16

		//To Allocator Quantity Column
		for(int i=colIndex; i<=colIndex_AllocQty; i++) {//<=
			Actions act = new Actions(driver);	
			act.sendKeys(Keys.ARROW_RIGHT).build().perform(); 
			System.out.println("Scroll Right perfomed");
			Thread.sleep(1000);
		}
		
		//Column Index is constant row changes inside loop
		String requiredColNum = String.valueOf(colIndex_AllocQty);
		String requiredAllocQtyColXpath = inputFieldXpath.replace("$colNum", requiredColNum);
		System.out.println("Required Alloc Qty Col Xpath: "+requiredAllocQtyColXpath);
		
		//Check and enter qty Map-Excel, allTerrNames-UI
		for(int rowNumber=1; rowNumber<=rowsForCheck; rowNumber++) {
				if(map_AllocQty.containsKey(allTerrNames.get(rowNumber-1))) {
					System.out.println("Entering Alloc Qty for Terr: "+allTerrNames.get(rowNumber-1)+ " as: "+map_AllocQty.get(allTerrNames.get(rowNumber-1)));
					String inputFieldRequiredXpath = requiredAllocQtyColXpath.replace("$rowNum", String.valueOf(rowNumber));
					System.out.println("Required Alloc Qty Input Xpath: "+inputFieldRequiredXpath);
					GenericLib.explicitWaitForTime(driver, inputFieldRequiredXpath, 10);
					System.out.println("Alloc Qty Xpath: "+inputFieldRequiredXpath);
					WebElement allocQtyFld = driver.findElement(By.xpath(inputFieldRequiredXpath));
					BrowserActionUtil.scrollToElement(driver, allocQtyFld);
					BrowserActionUtil.clickElement(allocQtyFld, driver, "Allocator Qty Field");
					allocQtyFld.sendKeys(map_AllocQty.get(allTerrNames.get(rowNumber-1)));
					MyExtentListeners.test.pass("Territory: "+allTerrNames.get(rowNumber-1)+" found at Row: "+rowNumber+" and Allocator Qty is: "+map_AllocQty.get(allTerrNames.get(rowNumber-1)));
				}
			}
		}




	public int getColumnHeaderIndex(WebElement thElement) {
		WebElement parentRow = thElement.findElement(By.xpath("./..")); // Get parent <tr> element. 
		List<WebElement> columns = parentRow.findElements(By.tagName("th")); // Find all <th> elements within the <tr>. 
		return columns.indexOf(thElement)+1; //Xpath from 1 and index from 0
	}
	
	public void scrollToEndOfPage() throws Exception {
		BrowserActionUtil.scrollToEndUsingActions(driver);
	}
	
	public void waitForPopupMessageAndClose(int timeSeconds) {
		try {
			//GenericLib.explicitWait(driver, "//div[@id='msgDiv']//div[contains(@class,'notificationHeader')]");
			GenericLib.explicitWaitForTime(driver, "//div[@id='msgDiv']//div[contains(@class,'notificationHeader')]", timeSeconds);
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
	}
	
	public void scrollToRequestNoAndClick(String reqNo) throws Exception {
		//To get control of table
		driver.findElement(By.xpath("//th//a[text()='Allocator Quantity']")).click();
		Thread.sleep(5000);
		//Get Table Header Column Index-Allocator Quantity.
		String attributeLabelXpath_AllocQty = rowHeaderLabelXpath.replace("$labelName", "Allocator Quantity");
		System.out.println("Alloc Qty Label Xpath:"+attributeLabelXpath_AllocQty);
		WebElement attributeLabel_AllocQty = driver.findElement(By.xpath(attributeLabelXpath_AllocQty));//Header Label
		int colIndex_AllocQty = getColumnHeaderIndex(attributeLabel_AllocQty);
		System.out.println("Column Index for Alloc Qty Label Label is: "+colIndex_AllocQty);//Allocator Quantity
		
		//Get Table Header Column Index-Request No.
		String attributeLabelXpath_ReqNo = rowHeaderLabelXpath.replace("$labelName", "Request No");
		System.out.println("Request No Label Xpath:"+attributeLabelXpath_ReqNo);
		WebElement attributeLabel_ReqNo = driver.findElement(By.xpath(attributeLabelXpath_ReqNo));//Header Label
		int colIndex_ReqNo = getColumnHeaderIndex(attributeLabel_ReqNo);
		System.out.println("Column Index for Request No is: "+colIndex_ReqNo);//Request No.

		//To Request No. Column
		for(int i=colIndex_AllocQty; i>=colIndex_ReqNo; i--) {
			Actions act = new Actions(driver);	
			act.sendKeys(Keys.ARROW_LEFT).build().perform(); 
			System.out.println("Scroll Left perfomed");
			Thread.sleep(1000);
		}
		
		WebElement requestNumber = driver.findElement(By.xpath("(//td//a[text()='"+reqNo+"'])[1]"));
		BrowserActionUtil.clickElement(requestNumber, driver, "Request Number Link: "+reqNo);
		GenericLib.explicitWaitForTime(driver, "//a[text()='Confirm Quote']",120);//Tech Spec Overview page
	}
	
	public void clickOnSaveButton() throws Exception {
		WebElement saveBtn = driver.findElement(By.xpath("//td[@id='qvSavebtnCtr']"));
		BrowserActionUtil.scrollToElement(driver, saveBtn);
		BrowserActionUtil.clickElement(saveBtn, driver, "Save Button");
		//Thread.sleep(90000);
	}
}