package com.lmg_dubai.PLM.Concept_BS;

import org.testng.annotations.Test;

import com.lmg_dubai.PLM.init.InitializePages;
import com.lmg_dubai.PLM.library.BaseLib;

public class TS08_CancelAdvanceSearch extends BaseLib{
	@Test
	public void ts01() throws Exception {

		InitializePages init = new InitializePages(globalVar.driver);

		init.plmLoginPage.loginToPLM("Login_Cred_PLM", 0, 1, 1, 1);
		init.plmHomePage.clickOnNavigationDashboardTextOption("Tech Spec");

		// By Style No
		init.plmAdvanceSearchTechSpecPage.enterTextInInputFieldAfterScrollingNew("AdvanceSearch_Tech_Spec", 0, 1, 2, 1);
		//init.plmTechSpecPage.clickOnAdvSearchTechSpec();
		init.plmTechSpecPage.clickOnCancelBtn();
}
}