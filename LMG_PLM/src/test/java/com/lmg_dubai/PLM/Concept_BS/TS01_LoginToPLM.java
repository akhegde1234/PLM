package com.lmg_dubai.PLM.Concept_BS;

import org.testng.annotations.Test;

import com.lmg_dubai.PLM.init.InitializePages;
import com.lmg_dubai.PLM.library.BaseLib;

public class TS01_LoginToPLM extends BaseLib{
	@Test
	public void ts01() {
		
		InitializePages init = new InitializePages(globalVar.driver);
		
		init.plmLoginPage.loginToPLM("Login_Cred_PLM", 0, 1, 1, 1);
		
	}

}
