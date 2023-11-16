package com.lmg_dubai.PLM.pages;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.lmg_dubai.PLM.library.BasePage;
import com.lmg_dubai.PLM.library.GenericLib;
import com.lmg_dubai.PLM.listeners.MyExtentListeners;
import com.lmg_dubai.PLM.util.BrowserActionUtil;

public class PLMTechSpecAttachmentsPage extends BasePage{

	public PLMTechSpecAttachmentsPage(WebDriver driver) {
		super(driver);
	}
	
	//Element to identify More Actions button
	@FindBy(xpath = "//td[contains(@id,'MORE_ACTIONSbtnCtr')]//label[contains(text(),'More Actions...')]")
	private WebElement moreActionsBtn;

	//Element to identify add file '+' button
	@FindBy(xpath = "//span[contains(@class,'circle-plus')]")
	private WebElement addFileBtn;

	//Element to identify add attachment button
	@FindBy(xpath = "(//img[@title='Click to Add Attachment'])[1]")
	private WebElement addAttachmentBtn;

	//Element to identify save button
	@FindBy(xpath = "//td[@id='TECH_SPEC_ATTACHMENT_SAVEbtnCtr']")
	private WebElement saveBtn;
	
	private static String moreActionOptionsXpath="//div[@id='divChildActBtns_TECH_SPEC_ATTACHMENT_MORE_ACTIONSvirtual']//td//label[contains(text(),'$labelName')]";
	private static String uploadedImageXpath="//img[contains(@src,'$imageName')]";
	
	public void clickOnMoreActionsAndSelectOption(String labelName) throws Exception {
		GenericLib.explicitWait(driver, "//td[contains(@id,'TECH_SPEC_ATTACHMENT_MORE_ACTIONSbtnCtr')]//label[contains(text(),'More Actions...')]");
		BrowserActionUtil.clickElement(moreActionsBtn, driver, "More Actions Button");
		GenericLib.explicitWait(driver, "//div[@id='divChildActBtns_TECH_SPEC_ATTACHMENT_MORE_ACTIONSvirtual']");
		String requiredOptionXpath = moreActionOptionsXpath.replace("$labelName", labelName);
		WebElement requiredOption = driver.findElement(By.xpath(requiredOptionXpath));
		BrowserActionUtil.clickElement(requiredOption, driver, labelName+" in More Actions");
		Thread.sleep(6000);
	}
	
	public void uploadItemImageFile(String folderName, String fileName) throws Exception {
		GenericLib.explicitWait(driver, "(//img[@title='Click to Add Attachment'])[1]");
		BrowserActionUtil.clickElement(addAttachmentBtn, driver, "Add Attachment Button");
		GenericLib.explicitWait(driver, "//span[contains(@class,'circle-plus')]");
		BrowserActionUtil.clickElement(addFileBtn, driver, "Add File Button");
		String filePath = BrowserActionUtil.getFilePathForUpload(folderName, fileName);

		Thread.sleep(2000);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(filePath), null);
		//Handle File Upload
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		Thread.sleep(5000);//For Upload Time
		 
		try {
			String[] imageName = fileName.split(".");
			String uploadedImage = uploadedImageXpath.replace("$imageName", imageName[0]);
			System.out.println("Image Xpath: "+uploadedImage);
			GenericLib.explicitWait(driver, uploadedImage);
			WebElement image = driver.findElement(By.xpath(uploadedImage));
			System.out.println("Image Name: "+imageName[0]);
			System.out.println("Image Type/Extension: "+imageName[1]);
			System.out.println("Image Uploaded Successfully");
			MyExtentListeners.test.pass("Image Uploaded Successfully and ID atrribute is: "+image.getAttribute("id"));
			MyExtentListeners.test.pass("Image Uploaded Successfully and src atrribute is: "+image.getAttribute("src"));
		} catch (Exception e) {
			System.out.println("Image Upload could not be verified");
			MyExtentListeners.test.info("Image Upload could not be verified");
		}
	}
	
	public void clickOnSaveButton() throws Exception {
		GenericLib.explicitWait(driver, "//td[@id='TECH_SPEC_ATTACHMENT_SAVEbtnCtr']");
		BrowserActionUtil.clickElement(saveBtn, driver, "Save Button");
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
			if(msgHeader.isDisplayed()) {
				WebElement closeBtn = driver.findElement(By.xpath("//div[contains(@class,'notifyClose')]//span[contains(@class,'i-close')]"));
				closeBtn.click();
				System.out.println("Clicked on Popup Close button");
			}
		} catch (Exception e) {
			MyExtentListeners.test.info("No popup message displayed");
		}
	}
	
	
	
}
