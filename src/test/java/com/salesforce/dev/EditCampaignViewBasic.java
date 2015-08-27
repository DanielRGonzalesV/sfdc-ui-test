package com.salesforce.dev;

import com.google.common.collect.Iterables;
import com.salesforce.dev.framework.DataDrivenManager;
import com.salesforce.dev.framework.LoggerManager;
import com.salesforce.dev.framework.Objects.ViewSalesForce;
import com.salesforce.dev.pages.Base.NavigationBar;
import com.salesforce.dev.pages.Base.SearchLookupBase;
import com.salesforce.dev.pages.Campaigns.*;
import com.salesforce.dev.pages.Home.HomePage;
import com.salesforce.dev.pages.Login.Transporter;
import com.salesforce.dev.pages.MainPage;
import com.salesforce.dev.pages.Objects.CampaignGenie;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Veronica Prado on 8/22/2015.
 */
public class EditCampaignViewBasic {
    private CampaignsHome campaignsHome;
    private CampaignDetail campaignDetail;

    private CampaignForm campaignForm;
    private HomePage homePage;
    private MainPage mainPage;
    private NavigationBar navigationBar;
    private DataDrivenManager dataDriveManager = new DataDrivenManager();
    private CampaignView campaignView;
    private Iterator<ViewSalesForce[]> dataViewCampaign;
    private String nameView;

    @DataProvider(name = "dataDriven")
    public Iterator<ViewSalesForce[]> getValues() {
        DataDrivenManager dataDrivenManager = new DataDrivenManager();
        return dataDrivenManager.getDataView("EditCampaignViewBasic.json");
    }
    @BeforeMethod(groups = {"Acceptance"})
    public void setUp() {
        System.out.println("start datt pre-requirements");
        ViewSalesForce viewSalesForce = CampaignGenie.getCampaignView();
        nameView = viewSalesForce.getViewName();
        System.out.println("Data where added");
    }


    @Test(groups = {"Acceptance"}, dataProvider = "dataDriven")
    public void testEditCampaign(ViewSalesForce viewSalesForceUpdate) {
        mainPage = Transporter.driverMainPage();
        navigationBar = mainPage.gotoNavBar();
        campaignsHome = navigationBar.goToCampaignsHome();
        String fieldToUpdate ="View Name";
        String newValue = "Update view Campaing";
        campaignView = campaignsHome.clickEditViewLnk(nameView)
                .setViewName(newValue);
    }

    @AfterMethod(groups = {"Acceptance"})
    public void tearDown() {
        campaignDetail.clickDeleteBtn(true);
        LoggerManager.getInstance().addInfoLog(this.getClass().getName(),
                "Campaign was deleted");
        mainPage = campaignDetail.gotoMainPage();
        navigationBar = mainPage.gotoNavBar();
        campaignsHome = navigationBar.goToCampaignsHome();
       // campaignDetail = campaignsHome.selectRecentItem(parentCampaign);
        campaignDetail.clickDeleteBtn(true);
        LoggerManager.getInstance().addInfoLog(this.getClass().getName(),
                "Campaign Parent was deleted");
    }
}
