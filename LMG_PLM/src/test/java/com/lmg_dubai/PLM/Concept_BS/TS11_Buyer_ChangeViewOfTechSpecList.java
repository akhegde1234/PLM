package com.lmg_dubai.PLM.Concept_BS;

import org.testng.annotations.Test;

import com.lmg_dubai.PLM.init.InitializePages;
import com.lmg_dubai.PLM.library.BaseLib;

public class TS11_Buyer_ChangeViewOfTechSpecList extends BaseLib{
	@Test
	public void ts38() throws Exception {
		InitializePages init = new InitializePages(globalVar.driver);
		//Buyer Role 
		init.plmLoginPage.loginToPLM("Login_Cred_PLM", 2, 1, 3, 1);
		init.plmHomePage.clickOnNavigationHeader("DESIGN CENTER");
		init.plmHomePage.clickOnNavigationSubHeader("DESIGN CENTER DASHBOARD");
		init.plmHomePage.clickOnNavigationDashboardTextOption("Tech Spec");
		//Enter Style No and validate search result views
		init.plmAdvanceSearchTechSpecPage.enterTextInInputFieldAfterScrolling("AdvanceSearch_Tech_Spec", 0, 1, 2, 1);
		init.plmAdvanceSearchTechSpecPage.verifySearchDetailsInListView("AdvanceSearch_Tech_Spec", 2, 1);
		//Select multiple styles
		init.plmAdvanceSearchTechSpecPage.selectMultipleStylesInSearchList(3);
		init.plmAdvanceSearchTechSpecPage.verifySearchDetailsInMediumView();
		init.plmAdvanceSearchTechSpecPage.verifySearchDetailsInGridView();
	}
}
