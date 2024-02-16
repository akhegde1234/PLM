package com.lmg_dubai.PLM.Concept_BS;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.lmg_dubai.PLM.init.InitializePages;
import com.lmg_dubai.PLM.library.BaseLib;

public class TS05_AdvanceSearch extends BaseLib {
	@Test
	public void ts05() throws Exception {

		InitializePages init = new InitializePages(globalVar.driver);

		init.plmLoginPage.loginToPLM("Login_Cred_PLM", 0, 1, 1, 1);
		init.plmHomePage.clickOnNavigationHeader("DESIGN CENTER");
		init.plmHomePage.clickOnNavigationSubHeader("DESIGN CENTER DASHBOARD");
		init.plmHomePage.clickOnNavigationDashboardTextOption("Tech Spec");

		String Style_value = init.plmTechSpecPage.verifyFieldPresentOnTechSpecPage("Style No");
		Assert.assertEquals(Style_value, "Style No");

		String Description_value = init.plmTechSpecPage.verifyFieldPresentOnTechSpecPage("Description");
		Assert.assertEquals(Description_value, "Description");

		String Item_Name_value = init.plmTechSpecPage.verifyFieldPresentOnTechSpecPage("Item Name");
		Assert.assertEquals(Item_Name_value, "Item Name");

		String Range_value = init.plmTechSpecPage.verifyFieldPresentOnTechSpecPage("Range Ref No (Non Clothing)");
		Assert.assertEquals(Range_value, "Range Ref No (Non Clothing)");

		String Product_Category_value = init.plmTechSpecPage.verifyFieldPresentOnTechSpecPage("Product Category");
		Assert.assertEquals(Product_Category_value, "Product Category");

		String Type_of_Fit_value = init.plmTechSpecPage.verifyFieldPresentOnTechSpecPage("Type of Fit");
		Assert.assertEquals(Type_of_Fit_value, "Type of Fit");

		String Storyline_value = init.plmTechSpecPage.verifyFieldPresentOnTechSpecPage("Storyline");
		Assert.assertEquals(Storyline_value, "Storyline");

		String Hit_value = init.plmTechSpecPage.verifyFieldPresentOnTechSpecPage("Hit");
		Assert.assertEquals(Hit_value, "Hit");

		String Buy_Program_value = init.plmTechSpecPage.verifyFieldPresentOnTechSpecPage("Buy Program");
		Assert.assertEquals(Buy_Program_value, "Buy Program");

		String Supplier_value = init.plmTechSpecPage.verifyFieldPresentOnTechSpecPage("Supplier");
		Assert.assertEquals(Supplier_value, "Supplier");

		String Dept_value = init.plmTechSpecPage.verifyFieldPresentOnTechSpecPage("Dept");
		Assert.assertEquals(Dept_value, "Dept");

		String Dept_Desc_value = init.plmTechSpecPage.verifyFieldPresentOnTechSpecPage("Dept Desc");
		Assert.assertEquals(Dept_Desc_value, "Dept Desc");

		String Status_value = init.plmTechSpecPage.verifyFieldPresentOnTechSpecPage("Status");
		Assert.assertEquals(Status_value, "Status");

		String Field_value = init.plmTechSpecPage.verifyFieldPresentOnTechSpecPageOperational("Field");
		Assert.assertEquals(Field_value, "Field");

		String Operation_value = init.plmTechSpecPage.verifyFieldPresentOnTechSpecPageOperational("Operation");
		Assert.assertEquals(Operation_value, "Operation");

		String Value_value = init.plmTechSpecPage.verifyFieldPresentOnTechSpecPageOperational("Value");
		Assert.assertEquals(Value_value, "Value");

		String Group_value = init.plmTechSpecPage.verifyFieldPresentOnTechSpecPageOperational("Group");
		Assert.assertEquals(Group_value, "Group");

		String Season_value = init.plmTechSpecPage.verifyFieldPresentOnTechSpecPageOperational("Season");
		Assert.assertEquals(Season_value, "Season");

		String Buyer_value = init.plmTechSpecPage.verifyFieldPresentOnTechSpecPageOperational("Buyer");
		Assert.assertEquals(Buyer_value, "Buyer");

		String Designer_value = init.plmTechSpecPage.verifyFieldPresentOnTechSpecPageOperational("Designer");
		Assert.assertEquals(Designer_value, "Designer");

		String Graphic_Designer_value = init.plmTechSpecPage
				.verifyFieldPresentOnTechSpecPageOperational("Graphic Designer");
		Assert.assertEquals(Graphic_Designer_value, "Graphic Designer");

		String Changes_Since_value = init.plmTechSpecPage.verifyFieldPresentOnTechSpecPageOperational("Changes Since");
		Assert.assertEquals(Changes_Since_value, "Changes Since");

	}
}