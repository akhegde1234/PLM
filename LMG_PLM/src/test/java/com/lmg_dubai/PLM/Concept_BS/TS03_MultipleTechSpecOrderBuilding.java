package com.lmg_dubai.PLM.Concept_BS;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.lmg_dubai.PLM.init.InitializePages;
import com.lmg_dubai.PLM.library.BaseLib;
import com.lmg_dubai.PLM.library.ExcelLibrary;
import com.lmg_dubai.PLM.library.GenericLib;

public class TS03_MultipleTechSpecOrderBuilding extends BaseLib{
	@Test
	public void ts03() throws Exception {
		InitializePages init = new InitializePages(globalVar.driver);
		//Get all styles
		ArrayList<String> allStyles = ExcelLibrary.getAllDataInARowBasedOnHeader(GenericLib.sTestData, "Create_Tech_Spec_Multiple", 4, 0);
		System.out.println("All Styles : "+allStyles);
		init.plmLoginPage.loginToPLM("Login_Cred_PLM", 0, 1, 1, 1);
		//Create Multiple Tech Spec-For every style
		for(int styleCount=0; styleCount<allStyles.size(); styleCount++) {
		init.plmHomePage.clickOnNavigationHeader("DESIGN CENTER");
		init.plmHomePage.clickOnNavigationSubHeader("DESIGN CENTER DASHBOARD");
		init.plmHomePage.clickOnNavigationDashboardTextOption("Tech Spec");
		init.plmTechSpecPage.clickOnCreateTechSpecBtn();
		//Loop similar fields-add methods
		//Enter Style no.
		init.plmTechSpecOverviewPage.enterTextInStyleNoInputFieldAfterScrolling(allStyles.get(styleCount));
		//1.All Input fields
		init.plmTechSpecOverviewPage.enterTextInAllInputFieldsForTechSpecAfterScrolling("Create_Tech_Spec_Multiple", 0, "All Input Fields", 1, 2);
		//2.All Search Icon and Search Popup fields
		init.plmTechSpecOverviewPage.handleFieldsWithSearchIconAndPopupDetailsSearchForTechSpecAfterScrolling("Create_Tech_Spec_Multiple", 0, "Search Icon Label Name-Season", 1, 2, "Dropdown_Options", 1, 0);
		init.plmTechSpecOverviewPage.handleFieldsWithSearchIconAndPopupDetailsSearchForTechSpecAfterScrolling("Create_Tech_Spec_Multiple", 0, "Search Icon Label Name-SubClass", 1, 2, "Dropdown_Options", 1, 0);
		init.plmTechSpecOverviewPage.handleFieldsWithSearchIconAndPopupDetailsSearchForTechSpecAfterScrolling("Create_Tech_Spec_Multiple", 0, "Search Icon Label Name-POType", 1, 2, "Dropdown_Options", 1, 0);
		init.plmTechSpecOverviewPage.handleFieldsWithSearchIconAndPopupDetailsSearchForTechSpecAfterScrolling("Create_Tech_Spec_Multiple", 0, "Search Icon Label Name-BrandUDA", 1, 2, "Dropdown_Options", 1, 0);
		init.plmTechSpecOverviewPage.handleFieldsWithSearchIconAndPopupDetailsSearchForTechSpecAfterScrolling("Create_Tech_Spec_Multiple", 0, "Search Icon Label Name-AllBrand(Sub-Brand)", 1, 2, "Dropdown_Options", 1, 0);
		init.plmTechSpecOverviewPage.handleFieldsWithSearchIconAndPopupDetailsSearchForTechSpecAfterScrolling("Create_Tech_Spec_Multiple", 0, "Search Icon Label Name-Buyer", 1, 2, "Dropdown_Options", 1, 0);//done
		//handled locators difference(ID Attribute)
		init.plmTechSpecOverviewPage.handleFieldsWithSearchIconAndPopupDetailsSearchForTechSpecAfterScrolling("Create_Tech_Spec_Multiple", 0, "Search Icon Label Name-Supplier", 1, 2, "Dropdown_Options", 1, 0);
		init.plmTechSpecOverviewPage.handleFieldsWithSearchIconAndPopupDetailsSearchForTechSpecAfterScrolling("Create_Tech_Spec_Multiple", 0, "Search Icon Label Name-Color Info Name", 1, 2, "Dropdown_Options", 1, 0);//check
		init.plmTechSpecOverviewPage.handleFieldsWithSearchIconAndPopupDetailsSearchForTechSpecAfterScrolling("Create_Tech_Spec_Multiple", 0, "Search Icon Label Name-Color Info Family Shade", 1, 2, "Dropdown_Options", 1, 0);
		//3.All select dropdowns
		init.plmTechSpecOverviewPage.handleAllSelectDropdownByIDAttributeForTechSpec("Create_Tech_Spec_Multiple", 0, "All Dropdown Main Label Names", 1, 2);
		//save and handle warning message
		init.plmTechSpecOverviewPage.clickOnSaveButton();
		init.plmTechSpecOverviewPage.getPopupMessageAndClose();
		//save and handle warning message
		init.plmTechSpecOverviewPage.handleFieldsWithSearchIconAndPopupDetailsSearchForTechSpecAfterScrolling("Create_Tech_Spec_Multiple", 0, "Search Icon Label Name-Assortment", 1, 2, "Dropdown_Options", 1, 0);//Only after saving
		//save and handle success message
		init.plmTechSpecOverviewPage.clickOnSaveButton();
		init.plmTechSpecOverviewPage.getPopupMessageAndClose();
		init.plmTechSpecOverviewPage.getLabelValue("Request No");
		
		//Attachments
		init.plmTechSpecPage.clickOnTopTabByName("Attachments");
		init.plmTechSpecAttachmentsPage.clickOnMoreActionsAndSelectOption("Add Colorway images");
		//Ex---E:\5001776_PLM_Workspace\LMG_PLM\UploadItems\Toys_Image_048_TB24JUL23_2.jpg
		init.plmTechSpecAttachmentsPage.uploadItemImageFile("UploadItems", "Toys_Image_048_TB24JUL23_2.jpg");
		init.plmTechSpecAttachmentsPage.clickOnSaveButton();
		init.plmTechSpecAttachmentsPage.getPopupMessageAndClose();     

		//Attributes 
		init.plmTechSpecPage.clickOnTopTabByName("Attributes");
		//UDA List
		init.plmTechSpecAttributesPage.handleDropdownAndVerifyAttributeInfo("UDA LIST", "Template Name", "0_@13900_@2_@0_@0_@0");
		init.plmTechSpecAttributesPage.enterDetailsBasedOnAttributeText("Create_Tech_Spec", "Attributes Tab-UDA List", 0, 1, 2, 3, "Attribute", "Field Type", "Attribute Value (Lov)","Attribute Value (FF/NUM)");
		init.plmTechSpecAttributesPage.clickOnSaveButton();
		//UDAS FOR MEGA DC
		init.plmTechSpecAttributesPage.handleDropdownAndVerifyAttributeInfo("UDAS FOR MEGA DC", "Template Name", "0_@13900_@2_@0_@0_@0");
		init.plmTechSpecAttributesPage.enterDetailsBasedOnAttributeText("Create_Tech_Spec","Attributes Tab-UDAS for Mega DC List", 0, 1, 2, 3, "Attribute", "Field Type", "Attribute Value (Lov)","Attribute Value (FF/NUM)");
		init.plmTechSpecAttributesPage.clickOnSaveButton();
		//Baby Shop - Toys
		init.plmTechSpecAttributesPage.handleDropdownAndVerifyAttributeInfo("Baby Shop - Toys", "Template Name", "0_@13900_@2_@0_@0_@0");
		init.plmTechSpecAttributesPage.enterDetailsBasedOnAttributeText("Create_Tech_Spec", "Attributes Tab-Baby Shop - Toys", 0, 1, 2, 3, "Attribute", "Field Type", "Attribute Value (Lov)", "Attribute Value (FF/NUM)");
		init.plmTechSpecAttributesPage.clickOnSaveButton();  
		
		//Offer Price Updation
		init.plmTechSpecPage.clickOnTopTabByName("Overview");
		init.plmTechSpecOverviewPage.clickOnAnchorTextButton("View Quote");
		init.plmTechSpecPage.clickOnTopTabByName("Details");
		init.plmTechSpecOverviewPage.enterTextInFieldOfFloatingTable("Offer Price", "0_@100_@11_@2_@0_@0_@0", "Create_Tech_Spec", 2, 23);//24
		init.plmTechSpecOverviewPage.clickOnTableTDButtonBasedOnID("SAVEbtnCtr", "Save Button");
		init.plmTechSpecOverviewPage.getPopupMessageAndClose();      
		
		//Confirm the Offer
		init.plmTechSpecOverviewPage.clickOnFirstRowCheckBox();
		init.plmTechSpecOverviewPage.clickOnAnchorTextButton("Confirm Offer");
		init.plmTechSpecOverviewPage.getPopupMessageAndClose(); 
		init.plmTechSpecPage.clickOnTopTabByName("Overview");
		init.plmTechSpecOverviewPage.clickOnAnchorTextButton("View Tech Spec"); 
		init.plmTechSpecOverviewPage.selectOnMoreActionsOption("Adopt");
		init.plmTechSpecOverviewPage.getPopupMessageAndClose();  
		
		//Verify Status
		String requestNo = init.plmTechSpecOverviewPage.getRequestNumber();
		init.plmTechSpecOverviewPage.checkStatusValue("ADOPTED"); 
		   	
		//Order Creation
		init.plmHomePage.clickOnNavigationSubHeader("DESIGN CENTER DASHBOARD");
		init.plmHomePage.clickOnNavigationDashboardTextOption("Dashboard");
		init.plmDashboardPage.clickOnTerritoryQtyAllocReg();//Pre-Order Tasks and Territory Quantity Allocn REG
		
		//Territory Allocator Qty 
		init.plmTerritoryQuantityAllocationPage.getPopupMessageAndClose();
		init.plmTerritoryQuantityAllocationPage.handleSelectDropdownAndEnterSearchText("Request No", "Like", requestNo);
		init.plmTerritoryQuantityAllocationPage.clickOnSearchButtonAndWaitForRequestNo(requestNo);
		init.plmTerritoryQuantityAllocationPage.scrollToEndOfPage();
		init.plmTerritoryQuantityAllocationPage.enterAllocatorQuantityBasedOnTerritory("Create_Tech_Spec", 0, "Allocation Qty Across Territories", 1, 2, "Territory", 3);
		init.plmTerritoryQuantityAllocationPage.clickOnSaveButton();//wait added
		init.plmTerritoryQuantityAllocationPage.waitForPopupMessageAndClose(120); 
		init.plmTerritoryQuantityAllocationPage.scrollToRequestNoAndClick(requestNo);
		
		//Confirm Quote
		init.plmTechSpecOverviewPage.clickOnConfirmQuoteButton();
		
		//Navigate to dashboard-Order Builder
		init.plmTechSpecOverviewPage.clickOnBSConceptImage(); 
		init.plmDashboardPage.clickOnOrderBuilder();//"Business Process", "Order Builder"
		
		//Order Builder
		init.plmOrderBuilderPage.handleSelectDropdownAndEnterSearchText("Request No", "Like", requestNo);
		init.plmOrderBuilderPage.clickOnSearchButton();
		init.plmOrderBuilderPage.clickOnAddAllItemsButtonAndBuild();  
		
		//Get Purchase Order
		//init.plmDashboardPage.quickSearchUsingOptionAndText("Purchase Order by Order No", "TBD-041539");//remove
		init.plmOrderManagementOverviewPage.getPurchaseNumber();
		//Validate PDF PO Report for Qty and price against UI
		init.plmOrderManagementOverviewPage.getTotalQtyAndPriceAndCompareWithPDFReportInMoreActions("PO Report"); 
		}
		
	}

}
