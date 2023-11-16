package com.lmg_dubai.PLM.init;

import org.openqa.selenium.WebDriver;

import com.lmg_dubai.PLM.pages.PLMDashboardPage;
import com.lmg_dubai.PLM.pages.PLMHomePage;
import com.lmg_dubai.PLM.pages.PLMLoginPage;
import com.lmg_dubai.PLM.pages.PLMOrderBuilderPage;
import com.lmg_dubai.PLM.pages.PLMOrderManagementOverviewPage;
import com.lmg_dubai.PLM.pages.PLMTechSpecAttachmentsPage;
import com.lmg_dubai.PLM.pages.PLMTechSpecAttributesPage;
import com.lmg_dubai.PLM.pages.PLMTechSpecOverviewPage;
import com.lmg_dubai.PLM.pages.PLMTechSpecPage;
import com.lmg_dubai.PLM.pages.PLMTerritoryQuantityAllocationPage;

public class InitializePages {

	/** APP CLASSES **/
	public PLMLoginPage plmLoginPage;
	public PLMHomePage plmHomePage;
	public PLMTechSpecPage plmTechSpecPage;
	public PLMTechSpecOverviewPage plmTechSpecOverviewPage;
	public PLMTechSpecAttachmentsPage plmTechSpecAttachmentsPage;
	public PLMTechSpecAttributesPage plmTechSpecAttributesPage;
	public PLMDashboardPage plmDashboardPage;
	public PLMTerritoryQuantityAllocationPage plmTerritoryQuantityAllocationPage;
	public PLMOrderBuilderPage plmOrderBuilderPage;
	public PLMOrderManagementOverviewPage plmOrderManagementOverviewPage;

	/** APP CLASSES INITIALISATION **/
	public  InitializePages(WebDriver driver) {
	plmLoginPage = new PLMLoginPage(driver);
	plmHomePage = new PLMHomePage(driver);
	plmTechSpecPage= new PLMTechSpecPage(driver);
	plmTechSpecOverviewPage = new PLMTechSpecOverviewPage(driver);
	plmTechSpecAttachmentsPage = new PLMTechSpecAttachmentsPage(driver);
	plmTechSpecAttributesPage = new PLMTechSpecAttributesPage(driver);
	plmDashboardPage= new PLMDashboardPage(driver);
	plmTerritoryQuantityAllocationPage= new PLMTerritoryQuantityAllocationPage(driver);
	plmOrderBuilderPage = new PLMOrderBuilderPage(driver);
	plmOrderManagementOverviewPage = new PLMOrderManagementOverviewPage(driver);
	}
}
