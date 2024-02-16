package com.lmg_dubai.PLM.Concept_BS;

import org.testng.annotations.Test;

import com.lmg_dubai.PLM.init.InitializePages;
import com.lmg_dubai.PLM.library.BaseLib;

public class TS17_Buyer_ChangeParametersOfTechSpec extends BaseLib{
	@Test
	public void ts39() throws Exception {
		InitializePages init = new InitializePages(globalVar.driver);
		//Buyer Role 
		init.plmLoginPage.loginToPLM("Login_Cred_PLM", 2, 1, 3, 1);
		init.plmHomePage.clickOnNavigationHeader("DESIGN CENTER");
		init.plmHomePage.clickOnNavigationSubHeader("DESIGN CENTER DASHBOARD");
		init.plmHomePage.clickOnNavigationDashboardTextOption("Tech Spec");
		//Enter Pre-existing Style No
		init.plmAdvanceSearchTechSpecPage.enterTextInInputFieldAfterScrolling("TechSpec_Params", 0, 1, 2, 1);
		//Open existing Tech Spec
		init.plmAdvanceSearchTechSpecPage.clickOnFirstStyleLinkInTheSearchResultsList();
		//Update Storyline
		init.plmTechSpecOverviewPage.clickOnSearchIconByLabelName("TechSpec_Params", 0, 2);//Storyline
		init.plmTechSpecOverviewPage.handlePopupDropdownAndEnterSearchText("TechSpec_Params", 1, 2, 2, 2, "Dropdown_Options", 1, 0);
		init.plmTechSpecOverviewPage.clickOnSearchButtonInPopupAndSelectFirstRecord();
		//Update Style No
		init.plmTechSpecOverviewPage.enterTextInInputFieldAfterScrolling("TechSpec_Params", 0, 3, 2, 3);
		//Item Description
		init.plmTechSpecOverviewPage.enterTextInInputFieldAfterScrolling("TechSpec_Params", 0, 4, 2, 4);
		//QA Approval-toggle
		init.plmTechSpecOverviewPage.handleToggleButtonAfterScrolling("3_@100_@103_@0_@0_@0","TechSpec_Params", 0, 5, 2, 5);
		//Lead Time-Select dropdown
		init.plmTechSpecOverviewPage.handleSelectDropdownByIDAttribute("6_@100_@107_@0_@0_@0", "Lead Time", "TechSpec_Params", 2, 6);
		init.plmTechSpecOverviewPage.clickOnSaveButton();
		init.plmTechSpecOverviewPage.getPopupMessageAndClose();//Success Msg 
		//Design Intent-Select Dropdown and upload attachment
		init.plmTechSpecPage.clickOnTopTabByName("Design Intent");	
		//Select Dropdown
		init.plmTechSpecOverviewPage.handleSelectDropdownByIDAttribute("2_@14600_@3_@2_@0_@0", "Image Type", "TechSpec_Params", 2, 7);
		//Upload attachment
		init.plmTechSpecAttachmentsPage.uploadItemImageFile("UploadItems", "Toys_Image_048_TB24JUL23_2.jpg");
		init.plmTechSpecAttachmentsPage.clickOnSaveButtonByIdAttribute("DESIGN_INTENT_SAVEbtnCtr");
		init.plmTechSpecAttachmentsPage.getPopupMessageAndClose();     
	}
	
	
}
