package com.lmg_dubai.PLM.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.lmg_dubai.PLM.library.ExcelLibrary;
import com.lmg_dubai.PLM.library.GenericLib;
import com.lmg_dubai.PLM.listeners.MyExtentListeners;

public class BrowserActionUtil {

	public final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	public static void waitForElementToLoad(int milliSeconds) throws InterruptedException {
		Thread.sleep(milliSeconds);
	}

	/**
	 * Description : This method has fluent wait implementation for element to load which is polling every 250 miliseconds
	 * @param element
	 * @param driver
	 * @param eleName
	 * @throws IOException
	 */
	public static void waitForElement(WebElement element, WebDriver driver, String elementName, int seconds)
			throws IOException {
		try {
			logger.info("---------Waiting for visibility of element---------" + elementName);
			BrowserActionUtil.isEleDisplayed(element, driver, 2, 1, elementName);
		} catch (Exception e) {
			MyExtentListeners.test.addScreenCaptureFromPath(capture(driver, elementName));
			logger.info("---------Element is not visible---------" + elementName);
		} catch (AssertionError e) {
			MyExtentListeners.test.addScreenCaptureFromPath(capture(driver, elementName));
			logger.info("---------Element is not visible---------" + elementName);
			throw e;
		}

	}


	/**
	 * Description: This method helps to verify whether given web Element is present page or not.
	 * @param element
	 * @param driver
	 * @param elementName
	 * @throws IOException
	 */
	public static void isEleDisplayed(WebElement element, WebDriver driver, String elementName) throws IOException {
		try {
			logger.info("---------Verifying element is displayed or not ---------");

			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(1, TimeUnit.MINUTES)
					.pollingEvery(250, TimeUnit.MICROSECONDS).ignoring(NoSuchElementException.class);
			if (element.isDisplayed()) {
				System.out.println(elementName + "------ is displayed");
				MyExtentListeners.test.pass("Verify " + "\'" + elementName + "\'" + " is displayed || " + "\'"
						+ elementName + "\'" + " is displayed ");
			}
		} catch (RuntimeException e) {

			MyExtentListeners.test.fail(MarkupHelper.createLabel("Verify " + "\'" + elementName + "\'"
					+ " is displayed || " + "\'" + elementName + "\'" + " is not displayed ", ExtentColor.RED));

			MyExtentListeners.test.addScreenCaptureFromPath(capture(driver, elementName));
			System.out.println(elementName + "------ is not displayed");
			throw e;
		}
	}

	/**
	 * Description: This method helps to verify whether given web Element is present page or not. 
	 * @param element
	 * @param driver
	 * @param elementName
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static boolean isEleDisplayed(WebElement element, WebDriver driver, int seconds, int loop,
			String elementName) throws IOException, InterruptedException {

		boolean flag = false;


		try 
		{
			logger.info("---------Verifying element is displayed or not ---------");
			flag=element.isDisplayed(); 

		}

		/*int count = loop;   
        while (loop > 0) {
            try {
                logger.info("---------Verifying element is displayed or not ---------");
                count--;
                loop--;
                element.isDisplayed();
                flag = true;
                break;

            }*/
		catch (RuntimeException e) {
			Thread.sleep(seconds * 1000);
			flag = false;
		}
		MyExtentListeners.test.pass("Verify " + "\'" + elementName + "\'" + " is displayed  || " + "\'" + elementName
				+ "\'" + " is displayed ");

		return flag;
	}

	/**
	 * Method to verify visibility of element and log the message accordingly 
	 * @param element
	 * @param driver
	 * @param elementName
	 * @throws IOException
	 */

	public static void verifyElementIsDisplayed(WebElement element, WebDriver driver, String elementName)
			throws IOException {
		try {
			logger.info("---------Verifying element is displayed or not ---------");
			WebDriverWait wait = new WebDriverWait(driver, 15);

			if (wait.until(ExpectedConditions.visibilityOf(element)) != null) {
				MyExtentListeners.test.pass("Verify " + "\'" + elementName + "\'" + " is displayed  || " + "\'"
						+ elementName + "\'" + " is displayed ");
				System.out.println(elementName + "------ is displayed");
			}

		} catch (Exception e) {
			MyExtentListeners.test.info(MarkupHelper.createLabel("Verify " + "\'" + elementName + "\'"
					+ " is displayed  || " + "\'" + elementName + "\'" + " is not displayed ", ExtentColor.GREEN));

			MyExtentListeners.test.addScreenCaptureFromPath(capture(driver, elementName));
			System.out.println(elementName + "------ is not displayed");
		}
	}

	/**
	 * Description: This method will click on element which is provided.
	 * @param element
	 * @param driver
	 * @param elementName
	 * @throws Exception
	 * @throws Exception
	 */
	public static void clickElement(WebElement element, WebDriver driver, String elementName) throws Exception {

		try {			
			logger.info("---------Verifying element is displayed or not ---------");
			//waitForElement(element, driver, elementName, 3);
			element.click();
			logger.info("After Click on: " + elementName);
			MyExtentListeners.test.pass("Verifying if user is able to click on " + "\'" + elementName + "\'");
		} catch (AssertionError error) {
			MyExtentListeners.test.fail(MarkupHelper.createLabel("Verify user is able to click on " + "\'" + elementName
					+ "\'" + "  || User is not able to click on " + "\'" + elementName + "\'", ExtentColor.RED));

			MyExtentListeners.test.addScreenCaptureFromPath(capture(driver, elementName));
			Assert.fail("unable to Click on " + "\'" + elementName + "\'");
			throw error;
		} catch (Exception error) {
			MyExtentListeners.test.fail(MarkupHelper.createLabel("Verify user is able to click on " + "\'" + elementName
					+ "\'" + " || User is not able to click on " + "\'" + elementName + "\'", ExtentColor.RED));
			MyExtentListeners.test.addScreenCaptureFromPath(capture(driver, elementName));
			Assert.fail("unable to Click on " + "\'" + elementName + "\'");
			throw error;
		}

	}

	/**
	 * Description: This method clears the text from textbox/editbox and type the value which is provided
	 * @param element
	 * @param value
	 * @param elementName
	 * @param driver
	 * @throws Exception
	 */
	public static void clearAndType(WebElement element, String value, String elementName, WebDriver driver)
			throws Exception {
		try {
			logger.info("---------Method clear and type  ---------");
			element.clear();
			logger.info(elementName + " is cleared");
			element.sendKeys(value);
			logger.info(value + " is entered in " + elementName);

			MyExtentListeners.test.pass("Verify user is able to type " + "\'" + value + "\'" + "in " + "\'" + elementName
					+ "\'" + " || User is able to type " + "\'" + value + "\'" + "in " + "\'" + elementName + "\'");
		} catch (AssertionError error) {

			MyExtentListeners.test.fail(MarkupHelper.createLabel(
					"Verify user is able to type " + "\'" + value + "\'" + "in " + "\'" + elementName + "\'"
							+ " || User is not able to type " + "\'" + value + "\'" + "in " + "\'" + elementName + "\'",
							ExtentColor.RED));

			MyExtentListeners.test.addScreenCaptureFromPath(capture(driver, elementName));
			Assert.fail("Unable to type on " + "\'" + elementName + "\'");
		} catch (Exception e) {
			MyExtentListeners.test.fail(MarkupHelper.createLabel(
					"Verify user is able to type " + "\'" + value + "\'" + "in " + "\'" + elementName + "\'"
							+ " || User is not able to type " + "\'" + value + "\'" + "in " + "\'" + elementName + "\'",
							ExtentColor.RED));

			MyExtentListeners.test.addScreenCaptureFromPath(capture(driver, elementName));
			Assert.fail("Unable to type in " + "\'" + elementName + "\'");
		}

	}


	/**
	 * Description: Method to Explicitly wait and check whether element is click able.
	 * @param element
	 * @param driver
	 * @param eleName
	 * @throws IOException
	 */
	public static void isEleClickable(WebElement element, WebDriver driver, String eleName) throws IOException {
		try {
			logger.info("---------Method is Element clickable  ---------");
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(1, TimeUnit.MINUTES)
					.pollingEvery(250, TimeUnit.MICROSECONDS).ignoring(NoSuchElementException.class);
			Assert.assertTrue(wait.until(ExpectedConditions.elementToBeClickable(element)) != null);
			System.out.println(" element is clickable ");
		} catch (AssertionError e) {
			MyExtentListeners.test.fail(MarkupHelper.createLabel("Verify " + "\'" + eleName + "\'" + " is clickable || "
					+ "\'" + eleName + "\'" + " is not clickable", ExtentColor.RED));

			MyExtentListeners.test.addScreenCaptureFromPath(capture(driver, eleName));
			System.out.println(" element is not clickable ");
			throw e;
		} catch (Exception e) {
			MyExtentListeners.test.fail(MarkupHelper.createLabel("Verify " + "\'" + eleName + "\'" + " is clickable || "
					+ "\'" + eleName + "\'" + " is not clickable", ExtentColor.RED));

			MyExtentListeners.test.addScreenCaptureFromPath(capture(driver, eleName));
			System.out.println(" element is not clickable ");

		}
	}



	/**
	 * Description: This method verifies if expected text is contained in actual text
	 * @param actResult
	 * @param expResult
	 * @throws IOException
	 */

	public static void verifyContainsText(String actResult, String expResult, WebDriver driver) throws IOException {
		if (actResult.contains(expResult)) {
			MyExtentListeners.test.pass("Verify  Expected : " + "\'" + expResult + "\''" + " contains  Actual :  "
					+ actResult + "  || Expected : " + "\'" + expResult + "\''" + "contains  Actual :  " + actResult);

		} else {
			MyExtentListeners.test.fail(MarkupHelper.createLabel("Verify  Expected : " + "\'" + expResult + "\''"
					+ " contains  Actual :  " + actResult + " ||  Expected : " + "\'" + expResult + "\''"
					+ " does not contains  Actual :  " + actResult, ExtentColor.RED));
			MyExtentListeners.test.addScreenCaptureFromPath(capture(driver, expResult));

		}
	}

	/**
	 * Description: This method verifies if expected result text is present in actual result text
	 * @param actResult
	 * @param expResult
	 * @param desc
	 * @throws Exception
	 * @author:Srinivas Hippargi
	 */
	public static void verifyContainsText(String actResult, String expResult, String desc) throws Exception {
		if (actResult.contains(expResult)) {
			MyExtentListeners.test.pass("Verify  Expected : " + "\'" + expResult + "\''" + " contains  Actual :  "
					+ actResult + "  || Expected : " + "\'" + expResult + "\''" + "contains  Actual :  " + actResult);

		} else {

			MyExtentListeners.test.fail(MarkupHelper.createLabel("Verify  Expected : " + "\'" + expResult + "\''"
					+ " contains  Actual :  " + actResult + " ||  Expected : " + "\'" + expResult + "\''"
					+ " does not contains  Actual :  " + actResult, ExtentColor.RED));

			throw new Exception();

		}
	}

	/**
	 * Description: This method verifies if expected text result is equal to actual text result ignoring the case
	 * @param desc
	 * @param actResult
	 * @param expResult
	 * @author:Srinivas Hippargi
	 */
	public static void verifyEqualsText(String desc, String actResult, String expResult) throws Exception {
		if (expResult.equalsIgnoreCase(actResult)) {
			MyExtentListeners.test.pass("Verify " + desc + " ||  Expected : " + "\'" + expResult + "\''"
					+ " eqauls  to Actual :  " + actResult);
		} else {
			MyExtentListeners.test.fail(MarkupHelper.createLabel("Verify " + desc + "  || Expected : " + "\'" + expResult
					+ "\''" + " not eqauls to  Actual :  " + "\'" + actResult + "\'", ExtentColor.RED));
			throw new Exception();
		}
	}

	/**
	 * Description: This method verifies if expected text result is not equal to actual text result 
	 * @param desc
	 * @param actResult
	 * @param expResult
	 */

	public static void verifyNotEqualsText(String desc, String actResult, String expResult) {
		if (!(expResult.equalsIgnoreCase(actResult))) {
			MyExtentListeners.test.pass("Verify " + desc + " is printed on receipt or not" + " ||  Expected : " + "\'"
					+ expResult + "\''" + " not  to Actual :  " + actResult);
		} else {
			MyExtentListeners.test
			.fail(MarkupHelper
					.createLabel(
							"Verify " + desc + " is printed on receipt or not" + "  || Expected : " + "\'"
									+ expResult + "\''" + "  eqauls to  Actual :  " + "\'" + actResult + "\'",
									ExtentColor.RED));
		}
	}

	/**
	 * Description: This method verifies if actual result is null or not.
	 * @param actResult
	 * @param desc
	 */
	public static void verifyIsNull(String actResult, String desc) {
		if (actResult == null) {
			MyExtentListeners.test.fail(MarkupHelper.createLabel("Value is null", ExtentColor.RED));
			throw new RuntimeException();
		} else {
			MyExtentListeners.test.pass("Verify" + desc + " is Printed on Receipt or not" + " || " + desc + " : "
					+ actResult + " is printed on receipt");
		}
	}



	/**
	 * Description: This method is used to capture the screenshot
	 * @param driver
	 * @param screenShotName
	 * @return
	 * @throws IOException
	 */
	public static String capture(WebDriver driver, String screenShotName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File screenshotFile = ts.getScreenshotAs(OutputType.FILE);
		String dest = MyExtentListeners.screenShotPath +screenShotName + ".png";
		System.out.println(dest);
		File destination = new File(dest);
		FileUtils.copyFile(screenshotFile, destination);
		return dest;
	}

	/**
	 * This method is used to capture the screenshot with date
	 * @param driver
	 * @return
	 * @throws IOException
	 */
	public static String capture(WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
		String sDate = sdf.format(date);
		String dest = MyExtentListeners.screenShotPath + "/ " + sDate + ".png";
		System.out.println(dest);
		File destination = new File(dest);
		FileUtils.copyFile(source, destination);
		return dest;
	}

	/**
	 * This method is used to capture the element screenshot
	 * @param driver
	 * @param element
	 * @return
	 * @throws IOException
	 */
	public static String captureElementScreenshot(WebDriver driver, WebElement element) throws IOException {
		// Get entire page screenshot
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		BufferedImage fullImg = ImageIO.read(src);
		// Get the location of element on the page
		Point point = element.getLocation();
		// Get width and height of the element
		int elementWidth = element.getSize().getWidth();
		int elementHeight = element.getSize().getHeight();
		// Crop the entire page screenshot to get only element screenshot
		BufferedImage elementScreenshot = fullImg.getSubimage(point.getX(), point.getY(), elementWidth, elementHeight);
		ImageIO.write(elementScreenshot, "png", src);
		// Copy the element screenshot to disk
		String dest = MyExtentListeners.screenShotPath + element + ".png";
		File destination = new File(dest);
		FileUtils.copyFile(src, destination);
		return dest;
	}


	/**
	 * Description : Method to verify if actual text is equal to expected text
	 * @param desc
	 * @param actResult
	 * @param expResult
	 */
	public static void verifyEqualsText_Funct(String desc, String actResult, String expResult) {
		// if (expResult.equalsIgnoreCase(actResult)) {
		if (expResult.equals(actResult)) {
			MyExtentListeners.test.pass("Verify " + desc + " is displayed or not " + " ||  Expected : " + "\'"
					+ expResult + "\''" + " eqauls  to Actual :  " + actResult);
		} else {
			MyExtentListeners.test.fail(MarkupHelper
					.createLabel("Verify " + desc + " is diaplayed or not" + "  || Expected : " + "\'" + expResult
							+ "\''" + " not eqauls to  Actual :  " + "\'" + actResult + "\'", ExtentColor.RED));
		}
	}

	/**
	 * Description : Method to verify if expected text is present in actual text
	 * @param actResult
	 * @param expResult
	 * @param desc
	 */
	public static void verifyContainsText_Funct(String actResult, String expResult, String desc) {
		if (actResult.contains(expResult)) {
			MyExtentListeners.test.pass("Verify Text" + desc + " is displayed or not " + " ||  Expected : " + "\'"
					+ expResult + "\''" + " eqauls  to Actual :  " + actResult);

		} else {
			MyExtentListeners.test.fail(MarkupHelper
					.createLabel("Verify Text" + desc + " is diaplayed or not" + "  || Expected : " + "\'" + expResult
							+ "\''" + " not eqauls to  Actual :  " + "\'" + actResult + "\'", ExtentColor.RED));
		}
	}

	/**
	 * Description : Method to verify if the element is enabled 
	 * @param element
	 * @param driver
	 * @param elementName
	 * @throws IOException
	 */
	public static void isEleIsEnabled(WebElement element, WebDriver driver, String elementName) throws IOException {
		try {
			logger.info("---------Verifying element is Enabled or not ---------");

			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(1, TimeUnit.MINUTES)
					.pollingEvery(250, TimeUnit.MICROSECONDS).ignoring(NoSuchElementException.class);
			if (element.isEnabled()) {
				System.out.println(elementName + "------ is displayed");
				MyExtentListeners.test.pass("Verify " + "\'" + elementName + "\'" + " is enabled || " + "\'"
						+ elementName + "\'" + " is enabled ");
			}
		} catch (RuntimeException e) {

			MyExtentListeners.test.fail(MarkupHelper.createLabel("Verify " + "\'" + elementName + "\'"
					+ " is enabled || " + "\'" + elementName + "\'" + " is not enabled ", ExtentColor.RED));

			MyExtentListeners.test.addScreenCaptureFromPath(capture(driver, elementName));
			System.out.println(elementName + "------ is not Enabled");
			throw e;
		}
	}

	/**
	 * Description : Method to verify if the element is selected
	 * @param element
	 * @param driver
	 * @param elementName
	 * @throws IOException
	 */
	public static void isEleIsSelected_funct(WebElement element, WebDriver driver, String elementName)
			throws IOException {
		try {
			logger.info("---------Verifying element is Selected or not ---------");

			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(1, TimeUnit.MINUTES)
					.pollingEvery(250, TimeUnit.MICROSECONDS).ignoring(NoSuchElementException.class);
			if (element.isSelected() == false) {
				System.out.println(elementName + "------ is  Not Selected");
				MyExtentListeners.test.pass("Verify " + "\'" + elementName + "\'" + " is Not Selected || " + "\'"
						+ elementName + "\'" + " is Not Selected ");
			}
		} catch (RuntimeException e) {

			MyExtentListeners.test.fail(MarkupHelper.createLabel("Verify " + "\'" + elementName + "\'"
					+ " is Selected || " + "\'" + elementName + "\'" + " is  Selected ", ExtentColor.RED));

			MyExtentListeners.test.addScreenCaptureFromPath(capture(driver, elementName));
			System.out.println(elementName + "------ is Selected");
			throw e;
		}
	}

	/** 
	 * Description: Method to click on target element
	 * @param element
	 * @param driver
	 * @param elementName
	 * @throws IOException
	 * @author:Srinivas Hippargi
	 */
	public static void actionClick(WebElement element, WebDriver driver, String elementName) throws IOException {
		try {
			Actions action = new Actions(driver);
			action.moveToElement(element).click().build().perform();
			MyExtentListeners.test.pass("Verify user is able to click on " + "\'" + elementName + "\'"
					+ " ||  User is able to click on " + "\'" + elementName + "\'");
		} catch (AssertionError error) {
			MyExtentListeners.test.fail(MarkupHelper.createLabel("Verify user is able to click on " + "\'" + elementName
					+ "\'" + "  || User is not able to click on " + "\'" + elementName + "\'", ExtentColor.RED));

			MyExtentListeners.test.addScreenCaptureFromPath(capture(driver, elementName));
			Assert.fail("unable to Click on " + "\'" + elementName + "\'");

			MyExtentListeners.test.addScreenCaptureFromPath(capture(driver, elementName));
			throw error;
		} catch (Exception e) {
			MyExtentListeners.test.fail(MarkupHelper.createLabel("Verify user is able to click on " + "\'" + elementName
					+ "\'" + " || User is not able to click on " + "\'" + elementName + "\'", ExtentColor.RED));

			MyExtentListeners.test.addScreenCaptureFromPath(capture(driver, elementName));
		}
	}


	/**
	 * Description: Method to fetch the system date and time in format- yyyy-mm-dd T hh-mm-ss
	 * @author:Srinivas Hippargi
	 */
	public static String getSystemDate() {
		SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		TimeZone istTimeZone = TimeZone.getTimeZone("Asia/Kolkata");
		Date currentDate = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);
		Date finalNewDate = calendar.getTime();
		dateTimeInGMT.setTimeZone(istTimeZone);
		String finalNewDateString = dateTimeInGMT.format(finalNewDate);
		System.out.println(finalNewDateString);
		return finalNewDateString;
	}



	/**
	 * Description: Method to wait till page loads/until progress bar is invisible
	 * @param eleName
	 * @param driver
	 * @param pageName
	 * @throws IOException
	 */
	public static void waitTillProgressBarLoad(String eleName, WebDriver driver, String pageName, int seconds)
			throws IOException {
		try {
			logger.info("---------Method waiting for invisibility of progress bar  ---------");
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(seconds, TimeUnit.SECONDS)
					.pollingEvery(250, TimeUnit.MICROSECONDS).ignoring(NoSuchElementException.class);
			Assert.assertTrue(
					(wait.until(ExpectedConditions
							.invisibilityOfElementLocated(By.id("com.stellapps.usb:id/progressBar")))),
					"On clicking" + eleName + " Page is on load, Unable to proceed");
			MyExtentListeners.test.pass(" Verify On clicking " + "\'" + eleName + "\''" + " user is redirected to "
					+ "\'" + pageName + "\''" + "  ||  On clicking " + "\'" + eleName + "\''"
					+ " user is redirected to " + "\'" + pageName + "\''");
		} catch (AssertionError e) {
			MyExtentListeners.test.fail(MarkupHelper.createLabel(" Verify On clicking " + "\'" + eleName + "\''"
					+ " user is redirected to " + "\'" + pageName + "\''" + "  ||  On clicking " + "\'" + eleName
					+ "\''" + " user is not redirected to " + "\'" + pageName + "\''", ExtentColor.RED));

			MyExtentListeners.test.addScreenCaptureFromPath(capture(driver, eleName));
			Assert.fail("On clicking " + "\'" + eleName + "\''" + ", Page is on load, Unable to proceed");
			throw e;
		} catch (Exception e) {
			MyExtentListeners.test.fail(MarkupHelper.createLabel(" Verify On clicking " + "\'" + eleName + "\''"
					+ " user is redirected to " + "\'" + pageName + "\''" + "  ||  On clicking " + "\'" + eleName
					+ "\''" + " user is not redirected to " + "\'" + pageName + "\''", ExtentColor.RED));

			MyExtentListeners.test.addScreenCaptureFromPath(capture(driver, eleName));
			Assert.fail("On clicking " + "\'" + eleName + "\''" + ", Page is on load, Unable to proceed");

		}
	}

	/**
	 * Method for Rounding Decimal Number
	 * @param data
	 * @param format
	 * @return
	 */
	public static String decimalRoundingOff(String data, String format) {

		String str = data;

		switch (format) {

		case ".0":
			if (!(str.contains("."))) {
				str = str + ".0";
			}
			break;

		case ".00":
			if (!(str.contains("."))) {
				str = str + ".00";
			} else if ((str.substring(str.indexOf('.'))).length() == 2) {
				str = str + "0";
			}
			break;
		}

		return str;
	}


	/**
	 * Method to verify actual element text is same as expected text from validation file
	 * @param element
	 * @param driver
	 * @param eleName
	 * @param sKey
	 * @throws IOException
	 */
	public static void verifyText(WebElement element, WebDriver driver, String eleName, String sKey)
			throws IOException {
		try {
			logger.info("---------verifying the text---------");
			String actualResult = element.getText();
			Assert.assertEquals(actualResult, GenericLib.getProprtyValue(GenericLib.sValidationFile, sKey));
			MyExtentListeners.test
			.pass(MarkupHelper.createLabel("Verify " + "\'" + eleName + "\'" + " text is same as expected || "
					+ "\'" + eleName + "\'" + " text is same as expected", ExtentColor.GREEN));
		} catch (AssertionError e) {
			MyExtentListeners.test
			.fail(MarkupHelper.createLabel("Verify " + "\'" + eleName + "\'" + " text is same as expected  || "
					+ "\'" + eleName + "\'" + " text is not same as expected", ExtentColor.RED));

			MyExtentListeners.test.addScreenCaptureFromPath(capture(driver, eleName));
			throw e;
		} catch (Exception e) {
			MyExtentListeners.test
			.fail(MarkupHelper.createLabel("Verify " + "\'" + eleName + "\'" + " text is same as expected  || "
					+ "\'" + eleName + "\'" + " text is not same as expected", ExtentColor.RED));

			MyExtentListeners.test.addScreenCaptureFromPath(capture(driver, eleName));

		}
	}


	/**
	 * Method to verify visibility of element before click
	 * @param element
	 * @param driver
	 * @param elementName
	 * @param seconds
	 * @throws IOException
	 */
	public static void verifyElementPresent_Click(WebElement element, WebDriver driver, String elementName, int seconds)
			throws IOException 
	{
		try {
			logger.info("---------Waiting for visibility of element---------" + element);
			WebDriverWait wait = new WebDriverWait(driver, seconds);
			wait.until(ExpectedConditions.visibilityOf(element));
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ element present");
			//			Wait<AppiumDriver> wait1 = new FluentWait<AppiumDriver>(driver).withTimeout(seconds, TimeUnit.SECONDS)
			//					.pollingEvery(250, TimeUnit.MICROSECONDS).ignoring(NoSuchElementException.class);
			//			 Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(element))
			// !=
			// null);
			logger.info("---------Element is visible---------" + element);
		} catch (Exception e) {
			//			MyExtentListners.test.fail(MarkupHelper.createLabel("Verify " + "\'" + elementName + "\'"
			//					+ " is displayed || " + "\'" + elementName + "\'" + " is not displayed ", ExtentColor.RED));
			MyExtentListeners.test.addScreenCaptureFromPath(capture(driver, elementName));
			logger.info("---------Element is not visible---------" + element);
		} catch (AssertionError e) {
			//			MyExtentListners.test.fail(MarkupHelper.createLabel("Verify " + "\'" + elementName + "\'"
			//					+ " is displayed || " + "\'" + elementName + "\'" + " is not displayed ", ExtentColor.RED));
			MyExtentListeners.test.addScreenCaptureFromPath(capture(driver, elementName));
			logger.info("---------Element is not visible---------" + element);
			throw e;
		}
	}

	/**
	 * Description: This method will click on first element in the list of elements which is provided.
	 * @author Raghaw
	 * @param element
	 * @param driver
	 * @param elementName
	 * @throws Exception
	 */
	public static void click_Element(List<WebElement> element, WebDriver driver, String elementName) throws Exception {

		try {
			logger.info("---------Verifying element is displayed or not ---------");
			//waitForElement(element, driver, elementName, 10);
			element.get(0).click();
			logger.info("After Click on: "+elementName);
			MyExtentListeners.test.pass("Verify user is able to click on " + "\'" + elementName + "\'"
					+ " ||  User is able to click on " + "\'" + elementName + "\'");
		} catch (AssertionError error) {
			MyExtentListeners.test.fail(MarkupHelper.createLabel("Verify user is able to click on " + "\'" + elementName
					+ "\'" + "  || User is not able to click on " + "\'" + elementName + "\'", ExtentColor.RED));

			MyExtentListeners.test.addScreenCaptureFromPath(capture(driver, elementName));
			Assert.fail("unable to Click on " + "\'" + elementName + "\'");

			MyExtentListeners.test.addScreenCaptureFromPath(capture(driver, elementName));
			throw error;
		} catch (Exception error) {
			MyExtentListeners.test.fail(MarkupHelper.createLabel("Verify user is able to click on " + "\'" + elementName
					+ "\'" + " || User is not able to click on " + "\'" + elementName + "\'", ExtentColor.RED));
			Assert.fail("unable to Click on " + "\'" + elementName + "\'");
			MyExtentListeners.test.addScreenCaptureFromPath(capture(driver, elementName));
			throw error;
		}

	}	

	/**
	 * Description: Method to fetch element text from list of elements and click on it
	 * @author Raghaw
	 * @param element
	 * @param elet
	 * @param driver
	 * @param elementName
	 * @throws Exception
	 */
	public static void verify_Element(List<WebElement> element,WebElement elet, WebDriver driver, String elementName) throws Exception {

		try {
			logger.info("---------Verifying element is displayed or not ---------");
			//waitForElement(element, driver, elementName, 10);
			for(int i=0;i<element.size();i++) {
				String Expectedtext=elet.getText();
				String AcutalText=element.get(i).getText();
				System.out.println("expected test----> " +Expectedtext);
				System.out.println("text values present---> "+AcutalText);

				if(AcutalText.contentEquals(Expectedtext)) {
					element.get(i).click();
					break;
				}
			}
			logger.info("After Click on: "+elementName);
			MyExtentListeners.test.pass("Verify user is able to click on " + "\'" + elementName + "\'"
					+ " ||  User is able to click on " + "\'" + elementName + "\'");
		} catch (AssertionError error) {
			MyExtentListeners.test.fail(MarkupHelper.createLabel("Verify user is able to click on " + "\'" + elementName
					+ "\'" + "  || User is not able to click on " + "\'" + elementName + "\'", ExtentColor.RED));

			MyExtentListeners.test.addScreenCaptureFromPath(capture(driver, elementName));
			Assert.fail("unable to Click on " + "\'" + elementName + "\'");

			MyExtentListeners.test.addScreenCaptureFromPath(capture(driver, elementName));
			throw error;
		} catch (Exception error) {
			MyExtentListeners.test.fail(MarkupHelper.createLabel("Verify user is able to click on " + "\'" + elementName
					+ "\'" + " || User is not able to click on " + "\'" + elementName + "\'", ExtentColor.RED));
			Assert.fail("unable to Click on " + "\'" + elementName + "\'");
			MyExtentListeners.test.addScreenCaptureFromPath(capture(driver, elementName));
			throw error;
		}
	}

	/**
	 * This Method explicitly waits for particular element to be located for 2 seconds and takes screenshot if element is not displayed
	 * @param driver
	 * @param locator
	 * @throws IOException
	 * @author Mithun M
	 */
	public static void explicitWaitAndTakeScreenshot(WebDriver driver, String locator) throws IOException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 2);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
		}
		catch(Exception e) {
			String timeStamp = LocalDateTime.now().toString().replace(':', '-');
			File tempFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File destFile = new File("./sessionStartScreenshots/S_"+timeStamp+".png");//Local path
			//			File destFile = new File("/Users/runner/work/1/a/LMG_SahlaStore/reports/ScreenCapture/cap_"+timeStamp+".png");//Devops path to save screenshot
			FileUtils.copyFile(tempFile, destFile);
		}
	}


	/**
	 * Method to check availability of the element
	 * @param ele
	 * @param elementName
	 * @throws Exception
	 * @author Mithun M
	 */
	public static void checkForElementAvailablility(WebElement ele, String elementName) throws Exception {
		try {
			BrowserActionUtil.waitForElementToLoad(2000);
			Point i= ele.getLocation();
			MyExtentListeners.test.pass(elementName+" Element is displayed at co-ordinates: "+i);
		}catch(Exception e) {
			MyExtentListeners.test.fail(elementName+" Element is not displayed");
			Assert.fail(elementName+" Element is not displayed");
		} 
	}

	/**
	 * Method to check the pattern text contains actual text value from element
	 * @param actualTextFromElement
	 * @param patternText
	 * @author Mithun M
	 */
	public static void matchThePatternWithoutCaseCheck(String actualTextFromElement, String patternText) {
		Pattern pattern = Pattern.compile(actualTextFromElement, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(patternText);
		boolean matchFound = matcher.find();
		if(matchFound) {
			System.out.println("Match found for: "+actualTextFromElement+" in the pattern "+patternText);
			MyExtentListeners.test.pass("Match found for: "+actualTextFromElement+" in the pattern "+patternText);
		} else {
			System.out.println("Match not found");
			Assert.fail("User is not able to validate the pattern match");
		}
	}


	/**
	 * Method to explicitly wait for an element
	 * @param driver
	 * @param locator
	 * @param timeInSeconds
	 * @throws IOException
	 */
	public static void explicitWait(WebDriver driver, String locator, int timeInSeconds) throws IOException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to scroll to particular WebElement by JS anywhere in page
	 * @param driver
	 * @param elementToScrollTo
	 * @param elementName
	 */
	public static void scrollToElementByJavascript(WebDriver driver, WebElement elementToScrollTo, String elementName) {
		try {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("arguments[0].scrollIntoView(true);", elementToScrollTo);
			MyExtentListeners.test.pass("User scrolled to Element: "+elementName);
			Thread.sleep(2000);
		}
		catch (Exception e) {
			MyExtentListeners.test.fail("Could not scroll by JS to Element: "+elementName);
			e.printStackTrace();
		}
	}

	/**
	 * Method to get absolute file path for upload
	 * @param fileName
	 * @return
	 * @author Mithun
	 */
	public static String getFilePathForUpload(String folderPath, String fileName) {
		Path currentWorkingDir = Paths.get("").toAbsolutePath();
		String reqdLocalPath = currentWorkingDir.normalize().toString() + File.separator + folderPath + File.separator + fileName;
		System.out.println("The File Path for Upload is: "+reqdLocalPath);
		return reqdLocalPath;
	}

	/**
	 * Method to scroll to WebElement by JS and click on it.
	 * @param driver
	 * @param targetElement
	 * @param elementName
	 */
	public static void scrollToTargetElementByJavascriptAndClick(WebDriver driver, WebElement targetElement, String elementName) {
		try {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("arguments[0].scrollIntoView();", targetElement);
			targetElement.click();
			MyExtentListeners.test.pass("User successfully scrolled and clicked on: "+elementName);
		} catch (Exception e) {
			MyExtentListeners.test.fail("User could not scroll and click by JS on: "+elementName);
			e.printStackTrace();
		}
	}

	/**
	 * Method to scroll to end of page and click on Target Element by Actions-End Key
	 * @param driver
	 * @param targetElement
	 * @param elementName
	 * @throws Exception
	 */
	public static void scrollToEndOfPageByActionsAndClickOnTargetElement(WebDriver driver, WebElement elementToScrollTo,String scrollToElementName , WebElement targetElement, String targetElementName) throws Exception {
		try {
			Actions act = new Actions(driver);
			act.sendKeys(Keys.END).perform();
			System.out.println("Scroll down perfomed");
			Thread.sleep(4000);
			if(elementToScrollTo.isDisplayed()) {
				MyExtentListeners.test.pass("Scrolled to Element: "+scrollToElementName);
				targetElement.click();
				MyExtentListeners.test.pass("Clicked on Target Element: "+targetElementName);
			}
		} catch (Exception e) {
			MyExtentListeners.test.fail("Could not scroll to Target Element and click-By Actions-End");
			e.printStackTrace();
		}
	}

	/**
	 * Method to scroll to Target element By Actions-Page Down
	 * @param driver
	 * @param targetElement
	 * @param elementName
	 * @throws Exception
	 */
	public static void scrollToTargetElementByActions(WebDriver driver, WebElement targetElement, String elementName) throws Exception {
		try {
			for(int i=0; i<=30; i++) {
				Actions act = new Actions(driver);	
				act.sendKeys(Keys.PAGE_DOWN).build().perform(); 
				System.out.println("Scroll down perfomed");
				Thread.sleep(3000);
				if(targetElement.isDisplayed()) {
					MyExtentListeners.test.pass("Scrolled to Target Element: "+elementName);
					break;
				}
			}
		} catch (Exception e) {
			MyExtentListeners.test.fail("Could not scroll to Target Element using Actions-Page Down");
			e.printStackTrace();
		}
	}

	/**
	 * Method to scroll to end of the page using Actions-End 
	 * @param driver
	 * @param elementToScrollTo
	 * @param scrollToElementName
	 * @throws Exception
	 */
	public static void scrollToEndUsingActions(WebDriver driver) throws Exception {
		try {
			Actions act = new Actions(driver);
			act.sendKeys(Keys.END).perform();
			System.out.println("Scroll down perfomed");
			Thread.sleep(3000);
			MyExtentListeners.test.pass("Scrolled to end of the page"); 	
		} catch (Exception e) {
			MyExtentListeners.test.fail("Could not Scroll to end of the page-By Actions-End"); 
			e.printStackTrace();
		}
	}

	/**
	 * Method to get all List attribute values-text, value, etc.
	 * @param driver
	 * @param allListOptions
	 * @throws Exception
	 */
	public static void getListAttributeValues(WebDriver driver, List<WebElement> allListOptions, String attributeName) throws Exception {
		for(WebElement option:allListOptions) {
			if(attributeName.equalsIgnoreCase("text")) {
				MyExtentListeners.test.pass("List includes text: "+option.getText());
			}else if(attributeName.equalsIgnoreCase("value")) {
				MyExtentListeners.test.pass("List includes value: "+option.getAttribute("value"));
			}else {
				MyExtentListeners.test.fail("Check Attribute name");
			}
		}
	}

	/**
	 * Method to scroll down to Target Element and click using Actions-ArrowDown
	 * @param driver
	 * @param targetElement
	 * @param elementName
	 */
	public static void scrollDownToElementAndClickOnTargetByActionsArrowDown(WebDriver driver, WebElement targetElement, String elementName) {
		try {
			for(int i=0; i<=40; i++) {
				Actions act = new Actions(driver);	
				act.sendKeys(Keys.ARROW_DOWN).build().perform(); 
				System.out.println("Scroll down perfomed");
				Thread.sleep(3000);
				if(targetElement.isDisplayed()) {
					clickElement(targetElement, driver, elementName);
					MyExtentListeners.test.pass("Scrolled to Target Element and clicked: "+elementName);
					break;
				}
			}
		} catch (Exception e) {
			MyExtentListeners.test.fail("Could not scroll to Target Element and click by Actions-Arrow Down");
			e.printStackTrace();
		}
	}

	/**
	 * Method to handle select dropdown and select option by text
	 * @param driver
	 * @param selectDropdown
	 * @param dropdownTextOption
	 */
	public static void handleSelectDropdown(WebDriver driver, WebElement selectDropdown, String dropdownTextOption) {
		try {
			Select s = new Select(selectDropdown);
			List<WebElement> allOptions = s.getOptions();
			for(WebElement option:allOptions) {
				System.out.println("Option Text includes: "+option.getText());
				if(option.getText().equalsIgnoreCase(dropdownTextOption)) {
					option.click();
					MyExtentListeners.test.pass("User is able to select Dropdown Option as: "+dropdownTextOption);
					break;
				}
			}
		}catch (Exception e) {
			MyExtentListeners.test.fail("User is not able to select Dropdown Option as: "+dropdownTextOption);
			e.printStackTrace();
		}
	}



	public static void scrollToElement(WebDriver driver, WebElement element) throws Exception
	{	
		try
		{
			while(!element.isDisplayed())
			{  		
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
				Thread.sleep(1000);	
			}
		}
		catch(StaleElementReferenceException e)
		{
			//do nothing
		}

		System.out.println("scrolled to element");			
	}

	public static int getColumnIndex(WebDriver driver, String tableXpath,String colName) throws Exception
	{
		List<WebElement> table= driver.findElements(By.xpath(tableXpath+"//td"));
		int index = -1;
		Exception e;
		for(int i=1;i<table.size();i++)
		{
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			String tempXpath = tableXpath+"//td["+i+"]"+"//div[text()='"+colName+"']";
			try
			{
				//	System.out.println(i);

				WebElement ele = driver.findElement(By.xpath(tempXpath));
				index = i;
				break;

			}
			catch(Exception e1)
			{
				//	System.out.println("Not found at "+i);
			}
		}

		if(index==-1)
		{
			MyExtentListeners.test.fail(MarkupHelper.createLabel("Column: "+colName+" is not present", ExtentColor.RED));
			throw new Exception("Column: "+colName+" is not present");
		}
		return index;		
	}

	public static void LaunchLinkInNewTab(WebDriver driver, String link) throws InterruptedException
	{
		JavascriptExecutor je = (JavascriptExecutor)driver;
		je.executeScript("window.open()");	
		Thread.sleep(5000);		
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());			
		int lastIndex = tabs.size()-1;
		driver.switchTo().window(tabs.get(lastIndex));
		driver.navigate().to(link);		
	}

	public static void dragElement(WebDriver driver, WebElement element,String elementName,int xOffset, int yOffset, int timeOut) throws Exception
	{	
		waitForElement(element, driver,elementName,timeOut);
		Actions action = new Actions(driver);
		action.clickAndHold(element).moveByOffset(xOffset, yOffset).perform();
		action.release();
		System.out.println("Element draged to :"+xOffset+","+yOffset);
	}




	public static void scrollDownAndClickInComboBox(WebDriver driver, WebElement option) throws Exception
	{
		for(int i =0;i<30;i++)
		{
			try
			{
				option.click();
				System.out.println("clicked option");
				break;
			}
			catch(Exception error)
			{
				Actions action = new Actions(driver);
				action.sendKeys(Keys.DOWN);
				System.out.println("pressed down");
			}
		}
	}

	/**
	 * Method to double click on Element using Actions
	 * @param driver
	 * @param elementToDoubleClick
	 * @param elementName
	 */
	public static void doubleClickOnElement(WebDriver driver, WebElement elementToDoubleClick, String elementName) {
		Actions actions = new Actions(driver);
		actions.doubleClick(elementToDoubleClick).perform();
		MyExtentListeners.test.pass("Double Clicked on : "+elementName);
	}
	
	/**
	 * Method to click on Particular Text option in List
	 * @param allElements
	 * @param textOption
	 * @param driver
	 * @throws Exception
	 */
	public static void clickOnParticularTextOptionInElementsList(List<WebElement> allElements, String textOption,WebDriver driver) throws Exception {
		for(WebElement element:allElements) {
			if(element.getText().trim().equalsIgnoreCase(textOption)) {
				BrowserActionUtil.clickElement(element, driver, textOption);
				break;
			}
		}
	}
	
	/**
	 * Method to verify if element is readonly
	 * @param ele
	 * @param elementName
	 * @param driver
	 */
	public static void verifyFieldIsReadOnly(WebElement ele, String elementName ,WebDriver driver) {
		if(ele.getAttribute("readonly").equalsIgnoreCase("READONLY")) {
			MyExtentListeners.test.pass("User verified that "+elementName+" is READ ONLY type");
		}else {
			MyExtentListeners.test.fail("User verified that "+elementName+" is not READ ONLY type");
			Assert.fail("User verified that "+elementName+" is not READ ONLY type");
		}
	}
	
	/**
	 * Method to get Readonly field value
	 * @param ele
	 * @param elementName
	 * @param driver
	 */
	public static void getReadOnlyFieldValue(WebElement ele, String elementName ,WebDriver driver, String expectedValue) {
		if(ele.getAttribute("readonly").equalsIgnoreCase("READONLY") && ele.getAttribute("value")!=null) {
			MyExtentListeners.test.pass("User verified that "+elementName+" is READ ONLY and has value: "+ele.getAttribute("value"));
			if(expectedValue!=null && ele.getAttribute("value").trim().equalsIgnoreCase(expectedValue)) {
				MyExtentListeners.test.pass("User verified the Readonly field value as expected and it is: "+ele.getAttribute("value"));
			}else {
				Assert.fail("User could not verify the Readonly field value as expected and it is: "+ele.getAttribute("value"));
			}
		}else {
			MyExtentListeners.test.fail("User verified that "+elementName+" is not READ ONLY type or does not have a value associated");
			Assert.fail("User verified that "+elementName+" is not READ ONLY type or does not have a value associated");
		}
	}
	
	/**
	 * Method to scroll to element using vertical and horizontal reference
	 * @param verticalReferenceElement
	 * @param horizontalReferenceElement
	 * @param driver
	 */
	public static void scrollInVerticalAndHorizontalDirectionUsingActions(WebElement verticalReferenceElement, WebElement horizontalReferenceElement, WebDriver driver) {
		try {
			for(int i=0; i<=30; i++) {
				Actions act = new Actions(driver);	
				act.sendKeys(Keys.ARROW_DOWN).build().perform(); 
				System.out.println("Scroll Down perfomed");
				Thread.sleep(3000);
				if(verticalReferenceElement.isDisplayed()) {
					MyExtentListeners.test.pass("Scrolled to Vertical Target Element");
					for(int j=0; j<=20; j++) {
						act.sendKeys(Keys.ARROW_RIGHT).build().perform(); 
						System.out.println("Scroll Right perfomed");
						Thread.sleep(3000);
						if(horizontalReferenceElement.isDisplayed()) {
							MyExtentListeners.test.pass("Scrolled to Horizontal Target Element");
							break;
						}
				}
			}
		  }
		} catch (Exception e) {
			MyExtentListeners.test.fail("Could not scroll to Target Element using Actions Down and Right");
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to locate label input field and scroll to Element by JS 
	 * @param driver
	 * @param labelName
	 */
	public static void scrollToInputElementByLabelNameUsingJS(WebDriver driver, String labelName) {
		try {
			WebElement elementToScrollTo = driver.findElement(By.xpath("//label[contains(text(),'"+labelName+"')]/following-sibling::input"));
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("arguments[0].scrollIntoView(true);", elementToScrollTo);
			Thread.sleep(2000);
			if(elementToScrollTo.isDisplayed()) {
				MyExtentListeners.test.pass("User scrolled to Element: "+labelName);
			}else {
				MyExtentListeners.test.fail("Could not scroll by JS to Element: "+labelName);
				Assert.fail("Could not scroll by JS to Element: "+labelName);
			}
		}
		catch (Exception e) {
			MyExtentListeners.test.fail("Could not scroll by JS to Element: "+labelName);
			e.printStackTrace();
		}
	}
	
	public static void scrollUpToElementUsingActionsPageUp(WebDriver driver, WebElement targetElement, String elementName) {
		try {
			while(!targetElement.isDisplayed()) {
				Actions act = new Actions(driver);	
				act.sendKeys(Keys.PAGE_UP).build().perform(); 
				System.out.println("Scroll Up perfomed");
				Thread.sleep(3000);
				if(targetElement.isDisplayed()) {
					MyExtentListeners.test.pass("Scrolled to Target Element: "+elementName);
					break;
				}
			}
		} catch (Exception e) {
			MyExtentListeners.test.fail("Could not scroll to Target Element and click by Actions-Page Up");
			e.printStackTrace();
		}
	}
	
	public static void scrollUpToElementUsingActionsPageUpByActionCount(WebDriver driver, int actionCount) {
		try {
			for(int i=1; i<=actionCount; i++) {
				Actions act = new Actions(driver);	
				act.sendKeys(Keys.PAGE_UP).build().perform(); 
				System.out.println("Scroll Up perfomed");
				Thread.sleep(2000);
			}
		} catch (Exception e) {
			MyExtentListeners.test.fail("Could not scroll to by Actions-Page Up");
			e.printStackTrace();
		}
	}
	
	public static void scrollToRightHorizontallyToTargetElementByActions(WebDriver driver, WebElement targetElement, String elementName) throws Exception {
		try {
			while(!targetElement.isDisplayed()) {
				Actions act = new Actions(driver);	
				act.sendKeys(Keys.ARROW_RIGHT).build().perform(); 
				System.out.println("Scroll Right perfomed");
				Thread.sleep(3000);
			}
		} catch (Exception e) {
			MyExtentListeners.test.fail("Could not scroll to Target Element using Actions");
			e.printStackTrace();
		}
		MyExtentListeners.test.pass("Scrolled to Target Element: "+elementName);
	}
	
	public static String getDateAfterAddingDays(int daysToAdd) {        
		// Get the current date        
		LocalDate currentDate = LocalDate.now();         
		// Add the specified number of days        
		LocalDate newDate = currentDate.plusDays(daysToAdd);         
		// Format the date       
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");         
		String formattedDate = newDate.format(formatter);
		System.out.println("Date is: "+formattedDate);
		return formattedDate;    
	}
	
	public static String getLatestDownloadedFileFromDownloads() {
		String downloadsPath = System.getProperty("user.home") + "/Downloads";
		File downloadsDir = new File(downloadsPath);
		File[] files = downloadsDir.listFiles();
		if (files != null) {
			Arrays.sort(files, Comparator.comparingLong(File::lastModified).reversed());
			// Get the last downloaded file
			if (files.length > 0) {
				File lastDownloadedFile = files[0];
				System.out.println("Last Downloaded File: " + lastDownloadedFile.getAbsolutePath());
				return lastDownloadedFile.getAbsolutePath();
			} else {
				System.out.println("No files found in the Downloads directory.");
			}
		} else {
			System.out.println("Downloads directory not found.");
		}
		return null;
	}
	
	
	
	}
	



