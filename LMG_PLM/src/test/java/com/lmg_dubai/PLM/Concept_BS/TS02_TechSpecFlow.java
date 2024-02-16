package com.lmg_dubai.PLM.Concept_BS;

import org.testng.annotations.Test;
import com.lmg_dubai.PLM.init.InitializePages;
import com.lmg_dubai.PLM.library.BaseLib;

public class TS02_TechSpecFlow extends BaseLib{
	@Test
	public void ts02() throws Exception {
		
//		InitializePages init = new InitializePages(globalVar.driver);
//		init.plmLoginPage.loginToPLM("Login_Cred_PLM", 0, 1, 1, 1);
		
		InitializePages init = new InitializePages(globalVar.driver);
		init.plmLoginPage.loginToPLM("Login_Cred_PLM", 2, 1, 3, 1);
		
		//Create Tech Spec-BS
		init.plmHomePage.clickOnNavigationHeader("DESIGN CENTER");
		init.plmHomePage.clickOnNavigationSubHeader("DESIGN CENTER DASHBOARD");
		init.plmHomePage.clickOnNavigationDashboardTextOption("Tech Spec");
		init.plmTechSpecPage.clickOnCreateTechSpecBtn();
		init.plmTechSpecOverviewPage.enterTextInInputFieldAfterScrolling("Create_Tech_Spec", 0, 1, 2, 1);
		init.plmTechSpecOverviewPage.enterTextInInputFieldAfterScrolling("Create_Tech_Spec", 0, 2, 2, 2);
		init.plmTechSpecOverviewPage.clickOnSearchIconByLabelName("Create_Tech_Spec", 0, 3);//Season
		//"Season", "Contains", "BS REGULAR"
		init.plmTechSpecOverviewPage.handlePopupDropdownAndEnterSearchText("Create_Tech_Spec", 1, 3, 2, 3, "Dropdown_Options", 1, 0);
		init.plmTechSpecOverviewPage.clickOnSearchButtonInPopupAndSelectFirstRecord();
		init.plmTechSpecOverviewPage.clickOnSearchIconByLabelName("Create_Tech_Spec", 0, 4);//Sub-Class
		//"Subclass Desc.", "Contains", "BALLS"
		init.plmTechSpecOverviewPage.handlePopupDropdownAndEnterSearchText("Create_Tech_Spec", 1, 4, 2, 4, "Dropdown_Options", 1, 0);
		//"Brand /Group", "Contains", "TOYS BOYS"
		init.plmTechSpecOverviewPage.handlePopupDropdownAndEnterSearchText("Create_Tech_Spec", 1, 5, 2, 5, "Dropdown_Options", 1, 0);
		//"Department Desc.", "Contains", "BOYS TOYS"
		init.plmTechSpecOverviewPage.handlePopupDropdownAndEnterSearchText("Create_Tech_Spec", 1, 6, 2, 6, "Dropdown_Options", 1, 0);
		init.plmTechSpecOverviewPage.clickOnSearchButtonInPopupAndSelectFirstRecord();
		init.plmTechSpecOverviewPage.clickOnSearchIconByLabelName("Create_Tech_Spec", 0, 7);//PO Type
		//"Description", "Contains", "IMPORT"
		init.plmTechSpecOverviewPage.handlePopupDropdownAndEnterSearchText("Create_Tech_Spec", 1, 7, 2, 7, "Dropdown_Options", 1, 0);
		init.plmTechSpecOverviewPage.clickOnSearchButtonInPopupAndSelectFirstRecord();
		init.plmTechSpecOverviewPage.clickOnSaveButton();
		init.plmTechSpecOverviewPage.getPopupMessageAndClose();
		init.plmTechSpecOverviewPage.getLabelValue("Request No");  
		init.plmTechSpecOverviewPage.enterTextInInputFieldAfterScrolling("Create_Tech_Spec", 0, 8, 2, 8);//Range Ref No (Non Clothing)-RANGE001
		init.plmTechSpecOverviewPage.enterDateByAddingNumberOfDaysToInputFieldAfterScrolling("Create_Tech_Spec", 0, 9, 2, 9);//Ship Date-dd/mm/yyyy
		init.plmTechSpecOverviewPage.enterDateByAddingNumberOfDaysToInputFieldAfterScrolling("Create_Tech_Spec", 0, 10, 2, 10);//Launch Date-dd/mm/yyyy
		init.plmTechSpecOverviewPage.clickOnSearchIconByLabelName("Create_Tech_Spec", 0, 11);//Assortment
		//"Description", "Contains", "PLAY BALLS"
		init.plmTechSpecOverviewPage.handlePopupDropdownAndEnterSearchText("Create_Tech_Spec", 1, 11, 2, 11, "Dropdown_Options", 1, 0);
		init.plmTechSpecOverviewPage.clickOnSearchButtonInPopupAndSelectFirstRecord();
		init.plmTechSpecOverviewPage.clickOnSearchIconByLabelName("Create_Tech_Spec", 0, 12);//Brand UDA
		//"Description", "Contains", "BS-JUNIORS"
		init.plmTechSpecOverviewPage.handlePopupDropdownAndEnterSearchText("Create_Tech_Spec", 1, 12, 2, 12, "Dropdown_Options", 1, 0);
		init.plmTechSpecOverviewPage.clickOnSearchButtonInPopupAndSelectFirstRecord();
		init.plmTechSpecOverviewPage.clickOnSearchIconByLabelName("Create_Tech_Spec", 0, 13);//All - Brand (Sub-brand)
		//"Description", "Contains", "BS-JUNIORS"
		init.plmTechSpecOverviewPage.handlePopupDropdownAndEnterSearchText("Create_Tech_Spec", 1, 13, 2, 13, "Dropdown_Options", 1, 0);
		init.plmTechSpecOverviewPage.clickOnSearchButtonInPopupAndSelectFirstRecord();
		init.plmTechSpecOverviewPage.clickOnSearchIconByLabelName("Create_Tech_Spec", 0, 14);//Buyer
		//"Buyer Name", "Contains", "AKSHAY VOHRA"
		init.plmTechSpecOverviewPage.handlePopupDropdownAndEnterSearchText("Create_Tech_Spec", 1, 14, 2, 14, "Dropdown_Options", 1, 0);
		init.plmTechSpecOverviewPage.clickOnSearchButtonInPopupAndSelectFirstRecord(); 
		init.plmTechSpecOverviewPage.clickColorInfoNameSearchIconByIDAttribute("valsearch_7_@1_@101_@0_@0_@-1_@desc");//ID-Name
		init.plmTechSpecOverviewPage.handlePopupDropdownAndEnterSearchText("Create_Tech_Spec", 1, 15, 2, 15, "Dropdown_Options", 1, 0);
		init.plmTechSpecOverviewPage.clickOnSearchButtonInPopupAndSelectFirstRecord();
		init.plmTechSpecOverviewPage.clickOnColorInfoInputFieldByIDAttributeAndEnterText("7_@13700_@11_@0_@0_@-1", "Custom Color", "Create_Tech_Spec", 2, 16);
		init.plmTechSpecOverviewPage.clickColorInfoFamilyShadeSearchIcon();//ID-Family/Shade
		init.plmTechSpecOverviewPage.handlePopupDropdownAndEnterSearchText("Create_Tech_Spec", 1, 17, 2, 17, "Dropdown_Options", 1, 0);
		init.plmTechSpecOverviewPage.clickOnSearchButtonInPopupAndSelectFirstRecord();
		init.plmTechSpecOverviewPage.handleSelectDropdownByIDAttribute("8_@14200_@2_@0_@0_@-1", "Select Size Range", "Create_Tech_Spec", 2, 18);//select size range-BSSIZE
		init.plmTechSpecOverviewPage.clickOnSearchIconByIDAttributeAndClick("valsearch_12_@100_@4_@2_@0_@-1_@desc", "Supplier Search Icon");//Supplier Search
		init.plmTechSpecOverviewPage.handlePopupDropdownAndEnterSearchText("Create_Tech_Spec", 1, 19, 2, 19, "Dropdown_Options", 1, 0);
		init.plmTechSpecOverviewPage.clickOnSearchButtonInPopupAndSelectFirstRecord();
		init.plmTechSpecOverviewPage.enterTextToInputFieldByIDAttribute("12_@100_@17_@2_@0_@-1", "Estd. Retail Selling Price(AED)", "Create_Tech_Spec", 2, 20);
		init.plmTechSpecOverviewPage.handleSelectDropdownByIDAttribute("18_@100_@81_@0_@0_@0", "Garment Type", "Create_Tech_Spec", 2, 21);//Garment Type-NA
		init.plmTechSpecOverviewPage.clickOnSaveButton();
		init.plmTechSpecOverviewPage.getPopupMessageAndClose();//Success Msg 
		
		//Attachments
		init.plmTechSpecPage.clickOnTopTabByName("Attachments");
		init.plmTechSpecAttachmentsPage.clickOnMoreActionsAndSelectOption("Add Colorway images");
		//Ex---E:\5001776_PLM_Workspace\LMG_PLM\UploadItems\Toys_Image_048_TB24JUL23_2.jpg
		init.plmTechSpecAttachmentsPage.uploadItemImageFile("UploadItems", "Toys_Image_048_TB24JUL23_2.jpg");
		init.plmTechSpecAttachmentsPage.clickOnSaveButton();
		init.plmTechSpecAttachmentsPage.getPopupMessageAndClose();     
		
		//To get created Tech Spec  
		//init.plmTechSpecPage.clickRecentDocument("1");
		
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
		init.plmTechSpecOverviewPage.checkStatusValue("ADOPTED");//ADOPTED or CFM  
		   
		//String requestNo="HQ030358";//HQ073358, HQ143540
		
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
		//init.plmOrderManagementOverviewPage.getTotalQtyAndPriceAndCompareWithPDFReportInMoreActions("PO Report");
		
	}

}
