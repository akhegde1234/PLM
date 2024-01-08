package com.lmg_dubai.PLM.pages;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Set;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.gargoylesoftware.htmlunit.javascript.host.dom.Document;
import com.lmg_dubai.PLM.library.BasePage;
import com.lmg_dubai.PLM.library.GenericLib;
import com.lmg_dubai.PLM.listeners.MyExtentListeners;
import com.lmg_dubai.PLM.util.BrowserActionUtil;

import junit.framework.Assert;

public class PLMOrderManagementOverviewPage extends BasePage{

	public PLMOrderManagementOverviewPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//label[text()='Total Qty']/following::td//input[@id='4_@300_@46_@0_@0_@0']")
	private WebElement totalQty;
	
	@FindBy(xpath="//label[text()='Net Value']/following::td//input[@id='4_@300_@41_@0_@0_@0']")
	private WebElement netPriceValue;
	
	public String getPurchaseNumber() {
		GenericLib.explicitWait(driver, "//td//input[@id='0_@300_@3_@0_@0_@0']");
		WebElement purchaseNo = driver.findElement(By.xpath("//td//input[@id='0_@300_@3_@0_@0_@0']"));
		MyExtentListeners.test.pass("Purchase Number is: "+purchaseNo.getAttribute("value"));
		return purchaseNo.getAttribute("value");
	}
	
	public void clickOnRecentlyViewedDocuments(String anchorText) throws Exception {
		WebElement recentDoc = driver.findElement(By.xpath("//a[text()='"+anchorText+"']"));
		BrowserActionUtil.scrollToElement(driver, recentDoc);
		BrowserActionUtil.clickElement(recentDoc, driver, "Recent Doc");
	}
	
	public void getTotalQtyAndPriceAndCompareWithPDFReportInMoreActions(String moreActionsOption) throws Exception {
		BrowserActionUtil.scrollToElement(driver, totalQty);
		String totalQty_PO = totalQty.getAttribute("value").replaceAll(",","");
		System.out.println("Total Qty in PLM PO Page: "+totalQty_PO);
		MyExtentListeners.test.info("Total Qty in PLM PO Page: "+totalQty_PO);
		
		BrowserActionUtil.scrollToElement(driver, totalQty);
		String netPriceValue_PO=netPriceValue.getAttribute("value").replaceAll(",","");
		System.out.println("Net Price Value in PLM PO Page: "+netPriceValue_PO);
		MyExtentListeners.test.info("Net Price Value in PLM PO Page: "+netPriceValue_PO);
		String requiredPOPrice = "";
		for(int i=0; i<netPriceValue_PO.length(); i++) {
			System.out.println("Price: "+netPriceValue_PO.charAt(i));
			if(netPriceValue_PO.charAt(i)=='.') {
				break;
			}else {
				requiredPOPrice=requiredPOPrice+netPriceValue_PO.charAt(i);
			}
		}
		System.out.println("Required PO Price: "+requiredPOPrice);

		GenericLib.explicitWait(driver, "//label[text()='More Actions...']");
		WebElement button = driver.findElement(By.xpath("//label[text()='More Actions...']"));
		
		Actions actions = new Actions(driver);
		actions.moveToElement(button).click().build().perform();
		GenericLib.explicitWait(driver, "//div[@id='divChildActBtns_MORE_ACTIONSvirtual']//td//label[text()='"+moreActionsOption+"']");
		WebElement option = driver.findElement(By.xpath("//div[@id='divChildActBtns_MORE_ACTIONSvirtual']//td//label[text()='"+moreActionsOption+"']"));
		actions.moveToElement(option).click().build().perform();
		
		//Parent Window
		String parentWindow = driver.getWindowHandle();
		
		//Switch to PDF and download
		Set<String> allWindowHandles = driver.getWindowHandles();
		System.out.println("All Window Handles: "+allWindowHandles);//[E6DBCF0653DF67286583A8F6CC08423D, A446FAE7F91F054D13B5D21BDB6644D4]
		allWindowHandles.remove(parentWindow);
		System.out.println("After removing parent Window Handle: "+allWindowHandles);
		for(String window:allWindowHandles) {
			driver.switchTo().window(window);
			System.out.println(driver.getCurrentUrl());
			System.out.println(driver.getPageSource());
			//download wait and close window
			WebElement embedElement = driver.findElement(By.tagName("embed"));         
			// Focus on the PDF viewer to ensure keyboard shortcuts work        
			embedElement.click();         
			// Simulate pressing "Ctrl + S" to trigger the browser's download functionality
			Robot robot = new Robot();  
			robot.keyPress(KeyEvent.VK_CONTROL); 
			robot.keyPress(KeyEvent.VK_S); 
			Thread.sleep(1000);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_S);
			Thread.sleep(3000);
			//Click Enter to save             
			robot.keyPress(KeyEvent.VK_ENTER);         
			robot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(8000);//wait for download
		}
		
		//Close pdf window and switch control to parent window
		driver.close();
		driver.switchTo().window(parentWindow);
		
		//get path and verify text
          try {
        	String filePath = BrowserActionUtil.getLatestDownloadedFileFromDownloads();
        	PDDocument document = PDDocument.load(new File(filePath));
            System.out.println("No of pages is: "+ document.getNumberOfPages());
            AccessPermission ap = document.getCurrentAccessPermission();
            if (!ap.canExtractContent())
            {            
            	throw new IOException("You do not have permission to extract text");
            }
            PDFTextStripper stripper = new PDFTextStripper();
            stripper.setSortByPosition(true);
           stripper.setWordSeparator("||");
           String text=null;
           for (int p = 1; p <= document.getNumberOfPages(); ++p)
           {                     
        	   text = stripper.getText(document);
        	   System.out.println("PDF Text >>" + text);
        	   MyExtentListeners.test.info("PO PDF Report Content includes: "+text);
           }   
           document.close();
           //Comparing PLM PO Page with PDF PO report for Qty and Price
           System.out.println("For comparing: "+""+totalQty_PO+""+"||"+""+requiredPOPrice.trim()+"");
           if(text.contains("Total||Total US$") && text.contains(""+totalQty_PO+""+"||"+""+requiredPOPrice.trim()+"")) {
        	   System.out.println("User validated PDF PO report against PLM PO Page");
        	   MyExtentListeners.test.pass("User is able validate PDF PO report against PLM PO Page for Qty: "+totalQty_PO+" and Price: "+netPriceValue_PO);
           }else {
        	   Assert.fail("User could not validate PDF PO report against PLM PO Page for Qty and Price");
           }
        }
            catch (Exception e) {
            e.printStackTrace();
        } 
    }


	
	
}
