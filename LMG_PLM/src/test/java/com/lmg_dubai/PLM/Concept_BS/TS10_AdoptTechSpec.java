package com.lmg_dubai.PLM.Concept_BS;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.lmg_dubai.PLM.init.InitializePages;
import com.lmg_dubai.PLM.library.BaseLib;

public class TS10_AdoptTechSpec extends BaseLib{
	@Test
	public void ts04() throws Exception {

		InitializePages init = new InitializePages(globalVar.driver);

//		init.plmLoginPage.loginToPLM("Login_Cred_PLM", 0, 1, 1, 1);
//		init.plmHomePage.clickOnNavigationDashboardTextOption("Tech Spec");
		
		init.plmLoginPage.loginToPLM("Login_Cred_PLM", 2, 1, 3, 1);
		init.plmHomePage.clickOnNavigationDashboardTextOption("Tech Spec");

		// kundan

		// By Style No
		init.plmAdvanceSearchTechSpecPage.enterTextInInputFieldAfterScrolling("AdvanceSearch_Tech_Spec", 0, 1, 2, 1);

		// init.plmTechSpecPage.clickOnStyleNo("BS");
		init.plmTechSpecPage.SelectItemFromStyleNo("BSItem");
		init.plmTechSpecPage.SelectMoreAction("More Actions");
		init.plmTechSpecPage.SelectAdoptAction("Adopt Actions");
//		init.plmTechSpecPage.SelectConfirmAction("Select confirm");
//		init.plmTechSpecPage.SelectCancelAction("Cancel");
//		String actualstatus = init.plmTechSpecPage.verifyStatusOfStyleNumber();
//		Assert.assertEquals(actualstatus, "Delete");
	}
}
