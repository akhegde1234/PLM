package com.lmg_dubai.PLM.library;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.zeroturnaround.zip.ZipUtil;
import com.lmg_dubai.PLM.exceptiontype.ExceptionType;
import com.lmg_dubai.PLM.init.GlobalVariables;
import com.lmg_dubai.PLM.listeners.*;


public class BaseLib {
	public static GlobalVariables globalVar = new GlobalVariables();
	public static Properties properties;
	public static String sDirPath = System.getProperty("user.dir");
	public static final String CONFIGPATHEN = sDirPath + "\\src\\main\\resources\\config.properties";

	static {
		try {
			properties = new Properties();
			FileInputStream fis = new FileInputStream(CONFIGPATHEN);
			properties.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	public static long startTime;
	public static long endtTime;
	public static String sStartTime;
	public static String sStartTime1;
	public static String sEndTime;
	public static String causePartMsg;
	public static String path = null;
	public static Date date1 = null;
	public static Date date2 = null;
	public static Date date3 = null;
	public static Date date4 = null;
	static int a = 1;
	static int b = 0;
	static int c = 1;
	static int totalScripts = a - 1
			+ Integer.parseInt(GenericLib.getProprtyValue(GenericLib.sValidationFile, "Class_Count"));

	@BeforeSuite(alwaysRun = true)
	public void createSheet() throws IOException {
		date1 = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		String sDate = sdf.format(date1);
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		String sDate1 = sdf1.format(date1);
		sStartTime = sDate;
		sStartTime1 = sDate1;
		FileInputStream fis = new FileInputStream(new File(GenericLib.sEXcelResult));
		Workbook wb = new XSSFWorkbook(fis);
		Sheet sh = wb.getSheet("Dashboard");
		path = GenericLib.sEXcelCopy + sStartTime1 + ".xlsx";
		FileOutputStream fos = new FileOutputStream(new File(path));
		wb.setForceFormulaRecalculation(true);
		wb.write(fos);
		wb.close();
		fis.close();
		fos.close();
		ExcelLibrary.writeExcelData(path, "Dashboard", 2, 2, sStartTime);
		
	}

	@BeforeMethod(alwaysRun = true)
	public void _LaunchBrowser() throws Exception {
		String browserName = ExcelLibrary.getExcelData(System.getProperty("user.dir")+"/Controller_PLM.xlsx", "BrowserExecution", 0, 1);
		if(browserName!=null) {
			System.out.println("Browser for Execution: "+browserName);
		}else {
			System.out.println("Select a Browser for Execution");
		}
		// Launching Browser
		if(browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
			globalVar.driver = new ChromeDriver();
		}else if(browserName.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", "./drivers/msedgedriver.exe");
			globalVar.driver = new EdgeDriver();
		}else if(browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
			globalVar.driver = new FirefoxDriver();
		}else {
			System.out.println("Select only Available Browser for Execution");
		}
	
		globalVar.driver.manage().window().maximize();
		globalVar.driver.get(BaseLib.properties.getProperty("PLM_Url"));
		globalVar.driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		System.out.println("Title is: "+globalVar.driver.getTitle());

		date3 = new Date();

	}

	@AfterMethod(alwaysRun = true)
	public void result(ITestResult res) throws Exception {
		
		// Closing Browser
		//globalVar.driver.quit();
		
		String stat = null;
		String test = res.getName();
		String testCls = res.getMethod().getTestClass().getName();
//		System.out.println("Test Class name: "+testCls);//Mithun
		String[] str = testCls.split("_");
//		for(int i=0; i<str.length; i++) {//Mithun
//			System.out.println("str: "+str[i]);
//		}
		String testClsName = str[2];//2 or 3 based on test name
		String test1 = test.substring(0, 2).toUpperCase();
		String testName = test1 + "" + test.substring(2);

		int status = res.getStatus();
		if (status == 1) {
			stat = "PASS";
		} else if (status == 2) {
			stat = "FAIL";
		} else {
			stat = "SKIP";
		}
		if (stat.equals("FAIL")) {
			Throwable testResultException = res.getThrowable();
			if (testResultException instanceof InvocationTargetException) {
				testResultException = ((InvocationTargetException) testResultException).getCause();
			}
			String causeFullMsg = testResultException.toString();
			causePartMsg = causeFullMsg.substring(0, causeFullMsg.indexOf(":"));
			String expMsg = "";
			if (causePartMsg.equals(ExceptionType.ASSERTIONERROR)) {
				expMsg = ExceptionType.ASSERTIONERRORMSG;
			} else if (causePartMsg.equals(ExceptionType.INDEXOUTOFBOUNDSEXCEPTION)) {
				expMsg = ExceptionType.INDEXOUTOFBOUNDSEXCEPTIONMSG;
			} else if (causePartMsg.equals(ExceptionType.NOSUCHELEMENTEXCEPTION)) {
				expMsg = ExceptionType.NOSUCHELEMENTEXCEPTIONMSG;
			} else if (causePartMsg.equals(ExceptionType.SELENIUMNOSUCHELEMENTEXCEPTION)) {
				expMsg = ExceptionType.SELENIUMNOSUCHELEMENTEXCEPTIONMSG;
			} else if (causePartMsg.equals(ExceptionType.TIMEOUTEXCEPTION)) {
				expMsg = ExceptionType.TIMEOUTEXCEPTIONMSG;
			} else if (causePartMsg.equals(ExceptionType.WEBDRIVEREXCEPTION)) {
				expMsg = ExceptionType.WEBDRIVEREXCEPTIONMSG;
			} else {
				expMsg = causePartMsg.toString();
			}
			

		ExcelLibrary.writeExcelData(path, "Data", c, 4, expMsg);

		}
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String sDate = sdf.format(date);
		date4 = new Date();//
		long execution = date4.getTime() - date3.getTime();//
		String executionTime = GenericLib.printDifference(execution);//

		for (int i = a; i <= totalScripts; i++) {
			for (int j = 0; j < 1; j++) {
				ExcelLibrary.writeExcelData(path, "Data", i, j++, testClsName);
				ExcelLibrary.writeExcelData(path, "Data", i, j++, testName);
				ExcelLibrary.writeExcelData(path, "Data", i, j++, stat);
				ExcelLibrary.writeExcelData(path, "Data", i, j, executionTime);
			}
		}
		a++;
		b++;
		c++;			
	}
	

	@AfterSuite(alwaysRun = true)
	public void finish(ITestContext context) throws Exception {
		date2 = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		String sDate = sdf.format(date2);
		sEndTime = sDate;

		long execution = date2.getTime() - date1.getTime();
		ExcelLibrary.writeExcelData(path, "Dashboard", 3, 2, sEndTime);
		String executionTime = GenericLib.printDifference(execution);
		ExcelLibrary.writeExcelData(path, "Dashboard", 4, 2, "" + executionTime);
		ExcelLibrary.writeExcel(path, "Dashboard", 5, 2, "" + b);

		File sourceFile = new File("./ExcelResult/" + sStartTime + ".xlsx");
		File destFile = new File(MyExtentListeners.excelDir);
		FileUtils.copyFileToDirectory(sourceFile, destFile);
		System.out.println("Creating Zip Report.....");
		ZipUtil.pack(new File("./reports/" + MyExtentListeners.folName),
				new File("./reports/jenkins_mailer/ExtentReport.zip"));
		System.out.println("------Converted zip folder------");

	
	}

}
