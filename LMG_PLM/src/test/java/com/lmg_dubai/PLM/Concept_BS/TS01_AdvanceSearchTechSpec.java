package com.lmg_dubai.PLM.Concept_BS;

import org.testng.annotations.Test;

import com.lmg_dubai.PLM.init.InitializePages;
import com.lmg_dubai.PLM.library.BaseLib;

public class TS01_AdvanceSearchTechSpec extends BaseLib{
	@Test
	public void ts01() throws Exception {

		InitializePages init = new InitializePages(globalVar.driver);

		init.plmLoginPage.loginToPLM("Login_Cred_PLM", 2, 1, 3, 1);
		init.plmHomePage.clickOnNavigationHeader("DESIGN CENTER");
		init.plmHomePage.clickOnNavigationSubHeader("DESIGN CENTER DASHBOARD");
		init.plmHomePage.clickOnNavigationDashboardTextOption("Tech Spec");
		//By Style No
		init.plmAdvanceSearchTechSpecPage.enterTextInInputFieldAfterScrolling("AdvanceSearch_Tech_Spec", 0, 1, 2, 1);
		init.plmAdvanceSearchTechSpecPage.verifySearchDetails();
		init.plmAdvanceSearchTechSpecPage.expandAdvancedSearchTechSpecAndClearSearchFields();
		//By description
		init.plmAdvanceSearchTechSpecPage.enterTextInInputFieldAfterScrolling("AdvanceSearch_Tech_Spec", 0, 2, 2, 2);
		init.plmAdvanceSearchTechSpecPage.verifySearchDetails();
		init.plmAdvanceSearchTechSpecPage.expandAdvancedSearchTechSpecAndClearSearchFields();
		//By Item Name
		init.plmAdvanceSearchTechSpecPage.enterTextInInputFieldAfterScrolling("AdvanceSearch_Tech_Spec", 0, 3, 2, 3);
		init.plmAdvanceSearchTechSpecPage.verifySearchDetails();
		init.plmAdvanceSearchTechSpecPage.expandAdvancedSearchTechSpecAndClearSearchFields();
		//By Range Ref No (Non Clothing)
		init.plmAdvanceSearchTechSpecPage.enterTextInInputFieldAfterScrolling("AdvanceSearch_Tech_Spec", 0, 4, 2, 4);
		init.plmAdvanceSearchTechSpecPage.verifySearchDetails();
		init.plmAdvanceSearchTechSpecPage.expandAdvancedSearchTechSpecAndClearSearchFields();
		//By Product Category-NA
		//By Type of Fit
		init.plmAdvanceSearchTechSpecPage.enterTextInInputFieldAfterScrolling("AdvanceSearch_Tech_Spec", 0, 6, 2, 6);
		init.plmAdvanceSearchTechSpecPage.verifySearchDetails();
		init.plmAdvanceSearchTechSpecPage.expandAdvancedSearchTechSpecAndClearSearchFields();
		//By Storyline-popup
		init.plmAdvanceSearchTechSpecPage.clickOnSearchIconByLabelName("AdvanceSearch_Tech_Spec", 0, 7);
		init.plmAdvanceSearchTechSpecPage.handlePopupDropdownAndEnterSearchText("AdvanceSearch_Tech_Spec", 1, 7, 2, 7, "Dropdown_Options", 1, 0);
		init.plmAdvanceSearchTechSpecPage.clickOnSearchButtonInPopupAndSelectFirstRecord();
		init.plmAdvanceSearchTechSpecPage.scrollAndClickSearchButton();
		init.plmAdvanceSearchTechSpecPage.verifySearchDetails();
		init.plmAdvanceSearchTechSpecPage.expandAdvancedSearchTechSpecAndClearSearchFields();
		//By Hit
		init.plmAdvanceSearchTechSpecPage.enterTextInInputFieldAfterScrolling("AdvanceSearch_Tech_Spec", 0, 8, 2, 8);
		init.plmAdvanceSearchTechSpecPage.verifySearchDetails();
		init.plmAdvanceSearchTechSpecPage.expandAdvancedSearchTechSpecAndClearSearchFields();
		//By Buy Program popup-NA
		//By Supplier-popup
//		init.plmAdvanceSearchTechSpecPage.clickOnSearchIconByLabelName("AdvanceSearch_Tech_Spec", 0, 10);
//		init.plmAdvanceSearchTechSpecPage.handlePopupDropdownAndEnterSearchText("AdvanceSearch_Tech_Spec", 1, 10, 2, 10, "Dropdown_Options", 1, 0);
//		init.plmAdvanceSearchTechSpecPage.clickOnSearchButtonInPopupAndSelectFirstRecord();
//		init.plmAdvanceSearchTechSpecPage.scrollAndClickSearchButton();
//		init.plmAdvanceSearchTechSpecPage.verifySearchDetails();
//		init.plmAdvanceSearchTechSpecPage.expandAdvancedSearchTechSpecAndClearSearchFields();
		//By Dept
		init.plmAdvanceSearchTechSpecPage.enterTextInInputFieldAfterScrolling("AdvanceSearch_Tech_Spec", 0, 11, 2, 11);
		init.plmAdvanceSearchTechSpecPage.verifySearchDetails();
		init.plmAdvanceSearchTechSpecPage.expandAdvancedSearchTechSpecAndClearSearchFields();
		//By Dept desc-NA 
		//By Status
		init.plmAdvanceSearchTechSpecPage.enterTextInInputFieldAfterScrolling("AdvanceSearch_Tech_Spec", 0, 13, 2, 13);
		init.plmAdvanceSearchTechSpecPage.verifySearchDetails();
		init.plmAdvanceSearchTechSpecPage.expandAdvancedSearchTechSpecAndClearSearchFields();
		//By Group popup
		init.plmAdvanceSearchTechSpecPage.clickOnSearchIconByLabelName("AdvanceSearch_Tech_Spec", 0, 14);
		init.plmAdvanceSearchTechSpecPage.handlePopupDropdownAndEnterSearchText("AdvanceSearch_Tech_Spec", 1, 14, 2, 14, "Dropdown_Options", 1, 0);
		init.plmAdvanceSearchTechSpecPage.clickOnSearchButtonInPopupAndSelectFirstRecord();
		init.plmAdvanceSearchTechSpecPage.scrollAndClickSearchButton();
		init.plmAdvanceSearchTechSpecPage.verifySearchDetails();
		init.plmAdvanceSearchTechSpecPage.expandAdvancedSearchTechSpecAndClearSearchFields();
		//By Season popup
		init.plmAdvanceSearchTechSpecPage.clickOnSearchIconByLabelName("AdvanceSearch_Tech_Spec", 0, 15);
		init.plmAdvanceSearchTechSpecPage.handlePopupDropdownAndEnterSearchText("AdvanceSearch_Tech_Spec", 1, 15, 2, 15, "Dropdown_Options", 1, 0);
		init.plmAdvanceSearchTechSpecPage.clickOnSearchButtonInPopupAndSelectFirstRecord();
		init.plmAdvanceSearchTechSpecPage.scrollAndClickSearchButton();
		init.plmAdvanceSearchTechSpecPage.verifySearchDetails();
		init.plmAdvanceSearchTechSpecPage.expandAdvancedSearchTechSpecAndClearSearchFields();
		//By Buyer
		init.plmAdvanceSearchTechSpecPage.enterTextInInputFieldAfterScrolling("AdvanceSearch_Tech_Spec", 0, 16, 2, 16);
		init.plmAdvanceSearchTechSpecPage.verifySearchDetails();
		init.plmAdvanceSearchTechSpecPage.expandAdvancedSearchTechSpecAndClearSearchFields();
		//By Designer
		init.plmAdvanceSearchTechSpecPage.enterTextInInputFieldAfterScrolling("AdvanceSearch_Tech_Spec", 0, 17, 2, 17);
		init.plmAdvanceSearchTechSpecPage.verifySearchDetails();
		init.plmAdvanceSearchTechSpecPage.expandAdvancedSearchTechSpecAndClearSearchFields();
		//By Graphic Designer-NA



	}

}
