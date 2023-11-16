package com.lmg_dubai.PLM.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.gargoylesoftware.htmlunit.javascript.host.Map;
import com.lmg_dubai.PLM.library.BasePage;
import com.lmg_dubai.PLM.library.ExcelLibrary;
import com.lmg_dubai.PLM.library.GenericLib;
import com.lmg_dubai.PLM.listeners.MyExtentListeners;
import com.lmg_dubai.PLM.util.BrowserActionUtil;

public class PLMTechSpecAttributesPage extends BasePage{

	public PLMTechSpecAttributesPage(WebDriver driver) {
		super(driver);
	}

	//Element to identify dropdown list
	@FindBy(xpath = "//select[@id='current_page']")
	private WebElement dropdownList;

	//Element to identify message popup header
	@FindBy(xpath = "//div[contains(@class,'notificationHeader')]")
	private WebElement msgHeader;

	//Element to identify message popup description
	@FindBy(xpath = "//div[contains(@class,'notificationContent')]")
	private WebElement msgContent;

	//Element to identify message popup close button
	@FindBy(xpath = "//span[contains(@class,'i-close')]")
	private WebElement closeBtnInPopup;

	//Element to identify Save button
	@FindBy(xpath = "//td[@id='CONSTRUCTION_SAVEbtnCtr']")
	private WebElement saveBtn;

	private static String attributeInfoValueXpath = "//label[contains(text(),'$labelName')]/../following-sibling::td//input[@id='$idAttribute']";
	private static String rowHeaderLabelXpath= "//table[@id='13900_2_one_table_one_table']//label[contains(text(),'$labelName')]/parent::td[@class='clslabelheader']";
	private static String tableRowInputXpath ="(//table[@id='13900_2_one_table_one_table']//tr[contains(@id,'listRow_$rowNum')]//td//input[not(@type='hidden')])[$colIndex]";
	private static String fieldTypeInputXpath="(//table[@id='13900_2_one_table_one_table']//tr[contains(@id,'listRow_$rowNum')]//td//input[not(@type='hidden')])[$colIndex]";

	public void handleDropdownAndVerifyAttributeInfo(String optionToSelect, String labelName, String inputIdAttribute) throws Exception {
		GenericLib.explicitWait(driver, "//select[@id='current_page']");
		BrowserActionUtil.handleSelectDropdown(driver, dropdownList, optionToSelect);
		String attributeValue = attributeInfoValueXpath.replace("$labelName", labelName).replace("$idAttribute", inputIdAttribute);
		GenericLib.explicitWait(driver, "//td[@id='CONSTRUCTION_SAVEbtnCtr']");
		Thread.sleep(5000);//10 seconds
		WebElement attributeInfo = driver.findElement(By.xpath(attributeValue));
		MyExtentListeners.test.pass("User is able to validate Attribute Info for label name: "+labelName+" as: "+attributeInfo.getAttribute("value"));
	}

	/**
	 * Method to get column index of labels in table
	 * @param tdElement
	 * @return
	 */
	public int getColumnIndex(WebElement tdElement) { 
		WebElement parentRow = tdElement.findElement(By.xpath("./..")); // Get parent <tr> element. 
		List<WebElement> columns = parentRow.findElements(By.tagName("td")); // Find all <td> elements within the <tr>. 
		return columns.indexOf(tdElement)+1; //Xpath from 1 and index from 0
	}


	public void enterDetailsBasedOnAttributeText(String sheetNameAtrrTextAndValues,String headerText, int rowNumHeaderText, int attrTextRow, int attrValuesRow,int rowOfRowCheck, String attrLabelName, String fieldTypeLabelText, String attrributeLOVLabelText, String attrributeFFNumLabelText) throws Exception {
		//Excel Data-Get all Attribute text and values to be entered in field and store in Map
		HashMap<String, String> map = new HashMap<>();
		//attr start col-->based on header text of dropdown list type and attrEndCol--> attrtext is null
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
				map.put(attrText, attrValuesToEnter);
			}
		}
		
		System.out.println("Map Includes: "+map);

		//Get Attribute Table Header Column Index
		String attributeLabelXpath = rowHeaderLabelXpath.replace("$labelName", attrLabelName);
		//		System.out.println("Attribute Label Xpath:"+attributeLabelXpath);
		WebElement attributeLabel = driver.findElement(By.xpath(attributeLabelXpath));//Attribute
		BrowserActionUtil.scrollToElement(driver, attributeLabel);
		int colIndex = getColumnIndex(attributeLabel);
		System.out.println("Column Index for Label: "+attrLabelName+" is: "+colIndex);//2

		//Get number of rows to search in UI
		int colNum = ExcelLibrary.getHeaderTextColumnNumberInSpecifiedRow(GenericLib.sTestData, sheetNameAtrrTextAndValues, rowNumHeaderText, headerText);
		int rowsForCheck = ExcelLibrary.getNumberOfRowsToCheck(GenericLib.sTestData, sheetNameAtrrTextAndValues, rowOfRowCheck, colNum);
		System.out.println("Rows To Consider for Checking : "+rowsForCheck);
		
		//Start from Row Zero in Table and refer Map for data entry
		for(int rowNumber=0; rowNumber<=rowsForCheck; rowNumber++) {
			//Getting Attribute Label value row wise
			//			System.out.println("Table Row Number: "+rowNumber);
			String requiredInputField = tableRowInputXpath.replace("$rowNum", String.valueOf(rowNumber)).replace("$colIndex", String.valueOf(colIndex));
			//			System.out.println("Table Row Input Field Xpath: "+requiredInputField);
			WebElement inputField = driver.findElement(By.xpath(requiredInputField));
			BrowserActionUtil.scrollToElement(driver, inputField);
			String rowAttributeValue = inputField.getAttribute("value").trim();
			System.out.println("Label value for "+attrLabelName+" is: "+rowAttributeValue);
			//If attribute value is null move out of loop
			if(rowAttributeValue.isBlank() || rowAttributeValue.isEmpty()) {
				System.out.println("No Attribute Values available");
				break;
			}
			//Compare for key in map 
			if(map.containsKey(rowAttributeValue)) {
				String fieldTypeXpath = rowHeaderLabelXpath.replace("$labelName", fieldTypeLabelText);
				//				System.out.println("Field Type Label Xpath:"+fieldTypeXpath);
				WebElement fieldTypeLabel = driver.findElement(By.xpath(fieldTypeXpath));//Field Type 
				BrowserActionUtil.scrollToElement(driver, fieldTypeLabel);
				int fieldTypeColIndex = getColumnIndex(fieldTypeLabel);//4
				//				System.out.println("Column Index for Label: "+fieldTypeLabelText+" is: "+fieldTypeColIndex);
				String fieldType = fieldTypeInputXpath.replace("$rowNum", String.valueOf(rowNumber)).replace("$colIndex", String.valueOf(fieldTypeColIndex));
				//				System.out.println("Field Type Xpath: "+fieldType);
				WebElement fieldTypeInput = driver.findElement(By.xpath(fieldType));
				System.out.println("Field Type Value is: "+fieldTypeInput.getAttribute("value"));
				if(fieldTypeInput.getAttribute("value").equalsIgnoreCase("LOV")) {
					System.out.println("Verified LOV");
					//check if null and fill Attribute(Lov) col
					String attrLovXpath = rowHeaderLabelXpath.replace("$labelName", attrributeLOVLabelText);
					//					System.out.println("Attribute LOV Label Xpath:"+attrLovXpath);
					WebElement attributeLOVLabel = driver.findElement(By.xpath(attrLovXpath));//Attribute Value(LOV) 
					BrowserActionUtil.scrollToElement(driver, attributeLOVLabel);
					int attrLOVColIndex = getColumnIndex(attributeLOVLabel);//5
					//					System.out.println("Column Index for Label: "+attrributeLOVLabelText+" is: "+attrLOVColIndex);
					String attributeValueLovXpath = fieldTypeInputXpath.replace("$rowNum", String.valueOf(rowNumber)).replace("$colIndex", String.valueOf(attrLOVColIndex));
					//					System.out.println("Attribute Value(LOV) Value Xpath: "+attributeValueLovXpath);
					WebElement attributeLOVInput = driver.findElement(By.xpath(attributeValueLovXpath));
					System.out.println("Attribute LOV Field Value is: "+attributeLOVInput.getAttribute("value"));
					String lovDefaultValue = attributeLOVInput.getAttribute("value");
					System.out.println("Attribute LOV Default value not available: "+lovDefaultValue.isBlank());
					System.out.println("Attribute LOV Default value not available: "+lovDefaultValue.isEmpty());
					if(lovDefaultValue.isBlank() || lovDefaultValue.isEmpty()) {
						String valueToEnter =map.get(rowAttributeValue);
						System.out.println("Value To Enter in Attribute LOV Field is: "+valueToEnter);
						BrowserActionUtil.clickElement(attributeLOVInput, driver, "Attribute LOV Field");
						attributeLOVInput.sendKeys(valueToEnter);
						attributeLOVInput.sendKeys(Keys.ENTER);
					}else {
						System.out.println("Attribute LOV Default value available is: "+lovDefaultValue);
					}
				}
				else if(fieldTypeInput.getAttribute("value").equalsIgnoreCase("FF") || fieldTypeInput.getAttribute("value").equalsIgnoreCase("NUM")) {
					System.out.println("Verified FF/NUM");
					//check if null and fill FF/num col
					String attrFFXpath = rowHeaderLabelXpath.replace("$labelName", attrributeFFNumLabelText);
					//					System.out.println("Attribute FF/NUM Label Xpath:"+attrFFXpath);
					WebElement attributeFFLabel = driver.findElement(By.xpath(attrFFXpath));//Attribute Value(FF/NUM) 
					BrowserActionUtil.scrollToElement(driver, attributeFFLabel);
					int attrFFColIndex = getColumnIndex(attributeFFLabel);//6
					//					System.out.println("Column Index for Label: "+attrributeFFNumLabelText+" is: "+attrFFColIndex);
					String attributeValueFFXpath = fieldTypeInputXpath.replace("$rowNum", String.valueOf(rowNumber)).replace("$colIndex", String.valueOf(attrFFColIndex));
					//					System.out.println("Attribute Value(FF/NUM) Value Xpath: "+attributeValueFFXpath);
					WebElement attributeFForNumInput = driver.findElement(By.xpath(attributeValueFFXpath));
					System.out.println("Attribute (FF/NUM) Field Value is: "+attributeFForNumInput.getAttribute("value"));
					String ffOrNumDefaultValue = attributeFForNumInput.getAttribute("value");
					System.out.println("Attribute (FF/NUM) Default value not available: "+ffOrNumDefaultValue.isBlank());
					System.out.println("Attribute (FF/NUM) Default value not available: "+ffOrNumDefaultValue.isEmpty());
					if(ffOrNumDefaultValue.isBlank() || ffOrNumDefaultValue.isEmpty()) {
						String valueToEnter =map.get(rowAttributeValue);
						System.out.println("Value To Enter in Attribute FF/NUM Field is: "+valueToEnter);
						BrowserActionUtil.clickElement(attributeFForNumInput, driver, "Attribute FF/NUM Field");
						attributeFForNumInput.sendKeys(valueToEnter);
						attributeFForNumInput.sendKeys(Keys.ENTER);
					}else {
						System.out.println("Attribute FF/NUM Default value available is: "+ffOrNumDefaultValue);
					}
				}else {
					System.out.println("Field Type Value is empty or null or not as expected to enter details: "+fieldTypeInput.getAttribute("value"));
				}
			}
			else {
				System.out.println("Not Able to get attribute text, update the testdata excel file");
			}
		}
		//handleMsgPopup();  
	}


	public void handleMsgPopup() {
		try {
			if(msgHeader.isDisplayed()) {
				MyExtentListeners.test.info("User is seeing Popup Msg Header: "+msgHeader.getText());
				MyExtentListeners.test.info("User is seeing Popup Msg Content: "+msgContent.getText());
				BrowserActionUtil.clickElement(closeBtnInPopup, driver, "Popup Message Close Button");
			}
		} catch (Exception e) {
			MyExtentListeners.test.info("User is not seeing any popup message.");
		}
	}

	public void clickOnSaveButton() throws Exception {
		GenericLib.explicitWait(driver, "//td[@id='CONSTRUCTION_SAVEbtnCtr']");
		//BrowserActionUtil.clickElement(saveBtn, driver, "Save Button");
		WebElement saveButton = driver.findElement(By.xpath("//td[@id='CONSTRUCTION_SAVEbtnCtr']"));
		saveButton.click();
		Thread.sleep(5000);//msg
		handleMsgPopup();
	}




}
