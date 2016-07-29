package com.salesforce.dev;

import java.util.Iterator;

import com.salesforce.dev.framework.DataDrivenManager;
import com.salesforce.dev.framework.Objects.Campaign;
import com.salesforce.dev.pages.Base.NavigationBar;
import com.salesforce.dev.pages.Base.SearchLookupBase;
import com.salesforce.dev.pages.Campaigns.CampaignDetail;
import com.salesforce.dev.pages.Campaigns.CampaignForm;
import com.salesforce.dev.pages.Campaigns.CampaignsHome;
import com.salesforce.dev.pages.Home.HomePage;
import com.salesforce.dev.pages.Home.LoginPage;
import com.salesforce.dev.pages.MainPage;
import com.salesforce.dev.pages.Objects.CampaignGenie;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created by Marcelo.Vargas on 6/21/2015.
 */
public class EditCampaign {
    private static final Logger LOGGER = Logger.getLogger(EditCampaign.class.getName());
    private String campaignNameToUpdated;
    private String campaignNameUpdated;
    private String campaignParentName;
 /*   private String campaignName = "Campaign Name";
    private String campaignType = "Webinar";
    private String campaignStatus = "Completed";
    private String startDate = "6/15/2016";
    private String endDate = "6/17/2016";
    private String expectedRevenue = "2500";
    private String budgetedCost = "2000";
    private String actualCost = "3500";
    private String expectedResponse = "78";
    private String numSent = "10";
    private String parentCampaign = "CampaignParent";*/

    private SearchLookupBase searchLookup;

    private CampaignsHome campaignsHome;
    private CampaignDetail campaignDetail;
    private CampaignForm campaignForm;
    private HomePage homePage;
    private MainPage mainPage;
    private NavigationBar navigationBar;

    @BeforeMethod(groups = {"Acceptance"})
    public void setUp() {
        
        ///create campaign
        Campaign campaign = CampaignGenie.getCampaign();
        //create parent Campaign
        CampaignGenie.createParentCampaign(campaign.getParentCampaign());
        campaignNameToUpdated = campaign.getCampaignName();
        campaignParentName = campaign.getParentCampaign();
        homePage = new HomePage();
        mainPage = LoginPage.loginAsPrimaryUser();
        navigationBar = mainPage.gotoNavBar();
        campaignsHome = navigationBar.goToCampaignsHome();
        campaignForm = campaignsHome.clickNewBtn();
        campaignForm.setCampaignName(campaignNameToUpdated);
        campaignForm.checkActiveCheckbox();
        campaignDetail = campaignForm.clickSaveBtn();
        mainPage = campaignDetail.gotoMainPage();
    }

    @DataProvider(name = "dataDriven")
    public Iterator<Campaign[]> getValues() {
        DataDrivenManager dataDrivenManager = new DataDrivenManager();
        return dataDrivenManager.getCampaign("EditCampaign.json");
    }

    @Test(groups = {"Acceptance"}, dataProvider = "dataDriven")
    public void testEditCampaign(Campaign campaign) {
       // mainPage = Transporter.driverMainPage();
        navigationBar = mainPage.gotoNavBar();
        campaignsHome = navigationBar.goToCampaignsHome();

        campaignDetail = campaignsHome.selectRecentItem(campaignNameToUpdated);
        campaignForm = campaignDetail.clickEditBtn();
        campaignForm.setCampaignName(campaign.getCampaignName());

        campaignForm.setTypeSelect(campaign.getCampaignType());
        campaignForm.setStatusSelect(campaign.getCampaignStatus());
        campaignForm.setStartDate(campaign.getStartDate());
        campaignForm.setEndDate(campaign.getEndDate());
        campaignForm.clickPanel();
        campaignForm.setExpectedRevenue(campaign.getExpectedRevenue());
        campaignForm.setBudgetedCost(campaign.getBudgetedCost());
        campaignForm.setActualCost(campaign.getActualCost());
        campaignForm.setExpectedResponse(campaign.getExpectedResponse());
        campaignForm.setNumSent(campaign.getNumSent());
        searchLookup = campaignForm.clickLookupParentCampaign();
        searchLookup.searchText(campaign.getParentCampaign());
        campaignForm = searchLookup.goToCampaignForm();
        campaignDetail = campaignForm.clickSaveBtn();
        campaignNameUpdated = campaign.getCampaignName();
        campaignParentName = campaign.getParentCampaign();
        Assert.assertTrue(campaignDetail.validateCampaignNameFld(campaign.getCampaignName()));
        Assert.assertTrue(campaignDetail.validateCampaignType(campaign.getCampaignType()));
        Assert.assertTrue(campaignDetail.validateCampaignStatus(campaign.getCampaignStatus()));
        Assert.assertTrue(campaignDetail.validateCampaignStartDate(campaign.getStartDate()));
        Assert.assertTrue(campaignDetail.validateCampaignEndDate(campaign.getEndDate()));
        Assert.assertTrue(campaignDetail.validateCampaignParent(campaign.getParentCampaign()));
        LOGGER.info("Campaign has been updated");
    }

    @AfterMethod(groups = {"Acceptance"})
    public void tearDown() {
        campaignDetail.clickDeleteBtn(true);
        LOGGER.info("Campaign was deleted");
        mainPage = campaignDetail.gotoMainPage();
        navigationBar = mainPage.gotoNavBar();
        campaignsHome = navigationBar.goToCampaignsHome();
        campaignDetail = campaignsHome.selectRecentItem(campaignParentName);
        campaignDetail.clickDeleteBtn(true);
        LOGGER.info("Campaign Parent was deleted");
    }
}
