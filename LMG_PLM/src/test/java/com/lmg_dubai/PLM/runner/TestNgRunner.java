
package com.lmg_dubai.PLM.runner;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlSuite.FailurePolicy;
import org.testng.xml.XmlTest;

import com.lmg_dubai.PLM.library.GenericLib;



public class TestNgRunner {
	 
	
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		  XmlSuite xmlSuite = new XmlSuite();
		  xmlSuite.setName("PLM Automation");
		  xmlSuite.setConfigFailurePolicy(FailurePolicy.CONTINUE); 
//		  xmlSuite.setParallel("false");
//		  xmlSuite.setVerbose(1);
		  xmlSuite.addListener("com.lmg_dubai.PLM.listeners.MyExtentListeners");
		  
		  XmlTest xmlTest = new XmlTest(xmlSuite); 
		  xmlTest.setName("RegressionTest");
//		  xmlTest.setPreserveOrder("true");
		
		  String classNames[]=getToRunTestClasses(System.getProperty("user.dir")+"/Controller_PLM.xlsx");
		  List<XmlClass> list =  new ArrayList<XmlClass>();
		  for(int i=0;i<classNames.length;i++)
		  {
		    list.add(new XmlClass(Class.forName(classNames[i])));
		  }
		  int classCount = list.size();
		  GenericLib.setPropertyValue(GenericLib.sValidationFile, "Class_Count", Integer.toString(classCount));
		  System.out.println("No of script are running :"+ classCount);
		  xmlTest.setXmlClasses(list);
		  
		  TestNG testng = new TestNG();
		  
		  List<XmlSuite> suites = new ArrayList<XmlSuite>();
		  suites.add(xmlSuite);
		  
		  testng.setXmlSuites(suites);
//		  testng.run();
		  Files.write(Paths.get(System.getProperty("user.dir")+"/TestSuite.xml"),xmlSuite.toXml().getBytes());
		 

//		System.out.println(Arrays.toString(getToRunTestClasses(System.getProperty("user.dir")+"/Controller.xlsx")));
	}

	static String[] getToRunTestClasses(String strXlFilePath) throws IOException
	{
		FileInputStream fis = new FileInputStream(new File(strXlFilePath));
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheet("Controller");
		ArrayList<String> lstClassNames = new ArrayList<String>();
		String [] strClassNames=null;
		
		if (sh != null)
		{
			int rowcnt = sh.getPhysicalNumberOfRows();

			for (int i = 1; i <= rowcnt; i++)
			{
				XSSFRow r = null;
				r = sh.getRow(i);

				if (r != null)
				{
                   	if(r.getCell(3).getStringCellValue().equalsIgnoreCase("yes"))
                   	{
                   		lstClassNames.add(r.getCell(1).getStringCellValue()+"."+r.getCell(2).getStringCellValue());
                   	}
				}
			
			}
		}
		wb.close();
        strClassNames=new String[lstClassNames.size()];
        
		for(int i=0;i<lstClassNames.size();i++)
		{
			strClassNames[i]=lstClassNames.get(i);
		}
	
     	return strClassNames;
    }

 
}